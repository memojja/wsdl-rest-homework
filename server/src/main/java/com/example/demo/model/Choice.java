package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String value;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Question question;
    @Enumerated(EnumType.STRING)
    Choices choices;
    Boolean isTrue;
}
