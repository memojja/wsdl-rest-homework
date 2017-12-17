package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String value;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private Question question;
    @Enumerated(EnumType.STRING)
    Choices choices;
    Boolean isTrue;
}
