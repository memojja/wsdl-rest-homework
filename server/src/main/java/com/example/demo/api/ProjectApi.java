package com.example.demo.api;

import com.example.demo.exception.ChoiceException;
import com.example.demo.exception.ScoreException;
import com.example.demo.model.Choice;
import com.example.demo.model.Question;
import com.example.demo.model.Score;
import com.example.demo.model.User;
import com.example.demo.repository.ChoiceRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.ScoreRepository;
import com.example.demo.repository.UserRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/project")
@CrossOrigin(origins = "*")
public class ProjectApi {

    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;
    private final UserRepository userRepository;
    private final ScoreRepository scoreRepository;

    @Autowired
    public ProjectApi(QuestionRepository questionRepository, ChoiceRepository choiceRepository, UserRepository userRepository, ScoreRepository scoreRepository) {
        this.questionRepository = questionRepository;
        this.choiceRepository = choiceRepository;
        this.userRepository = userRepository;
        this.scoreRepository = scoreRepository;
    }

    @RequestMapping(value = "/questions",method = RequestMethod.POST)
    public Question createQuestion(@Valid @RequestBody Question question){
        questionRepository.save(question);
        return question;
    }

    @RequestMapping(value = "/questions",method = RequestMethod.GET)
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    @RequestMapping(value = "/questions/{id}",method = RequestMethod.GET)
    public Question getQuestionById(@PathVariable("id")Long id){
        return questionRepository.findOne(id);
    }

    @Transactional
    @RequestMapping(value = "/questions/{id}",method = RequestMethod.DELETE)
    public void deleteQuestionById(@PathVariable("id")Long id){
        choiceRepository.deleteAllByQuestion(questionRepository.findOne(id));
    }

    @RequestMapping(value = "/questions/{id}/choices",method = RequestMethod.GET)
    public Optional<List<Choice>> getChoiceByQuestionId(@PathVariable("id")Long id){
        this.validateChoice(questionRepository.findOne(id));
        return choiceRepository.findChoiceByQuestion(questionRepository.findOne(id));
    }

    @RequestMapping(value = "/questions/{id}/choices",method = RequestMethod.POST)
    public Choice createChoiceForQuestionId(@PathVariable("id")Long id,@Valid @RequestBody Choice choice){
        Question question = questionRepository.findOne(id);
        choice.setQuestion(question);
        choiceRepository.save(choice);
        return choice;
    }
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.PUT)
    public User createUser(@PathVariable("id")Long id,@Valid @RequestBody User user){
        user.setId(id);
        userRepository.save(user);
        return user;
    }

    @Transactional
    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable("id")Long id){
        scoreRepository.deleteAllByUser(userRepository.findOne(id));
    }

    @RequestMapping(value = "/users/{id}/score",method = RequestMethod.GET)
    public Optional<Score> getScoreByUserId(@PathVariable("id") Long id){
        this.validateScore(userRepository.findOne(id));
        return scoreRepository.findScoreByUser(userRepository.findOne(id));
    }

    @RequestMapping(value = "/users/{id}/score",method = RequestMethod.POST)
    public Score createScoreByUserId(@PathVariable("id") Long id,@Valid @RequestBody Score score){
        score.setUser(userRepository.findOne(id));
        return scoreRepository.save(score);
    }

    @RequestMapping(value = "competition")
    public HashMap<Page<Question>, Page<Choice>> getQuestionList(Pageable pageable){
        HashMap<Page<Question>, Page<Choice>> listMap = new HashMap<>();
        listMap.put(questionRepository.findAll(pageable),choiceRepository.findAll(pageable));
        return listMap;
    }

    private void validateScore(User user){
        this.scoreRepository.findScoreByUser(user).orElseThrow(
                () -> new ScoreException(user.getId())
        );
    }

    private void validateChoice(Question question){
        this.choiceRepository.findChoiceByQuestion(question).orElseThrow(
                () -> new ChoiceException(question.getId())
        );
    }

}
