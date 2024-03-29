package com.example.demo.repository;

import com.example.demo.model.Question;
import com.example.demo.model.Score;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Long> {
    Optional<Score> findScoreByUser(User user);
    void deleteAllByUser(User user);
}
