package com.example.demo.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Choice {
    private Long id;
    private Option option;
    private Question question;
}
