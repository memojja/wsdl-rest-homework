package com.example.demo.repository;

import com.example.demo.model.Choice;
import com.example.demo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice,Long>{
    List<Choice> findChoiceByQuestion(Question question);
}
