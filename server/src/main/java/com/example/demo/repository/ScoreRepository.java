package com.example.demo.repository;

import com.example.demo.model.Score;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Long> {
    Score findScoreByUser(User user);
}
