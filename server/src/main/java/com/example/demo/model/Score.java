package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String score;
    @OneToOne
    private User user;
}
