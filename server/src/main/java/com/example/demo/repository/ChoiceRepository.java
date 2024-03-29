package com.example.demo.repository;

import com.example.demo.model.Choice;
import com.example.demo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice,Long>,PagingAndSortingRepository<Choice,Long> {
    Optional<List<Choice>> findChoiceByQuestion(Question question);
    void deleteAllByQuestion(Question question);
}