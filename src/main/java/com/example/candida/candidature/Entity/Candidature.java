package com.example.candida.candidature.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Candidature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCondidate;

    @Enumerated(EnumType.STRING)
    private EnumEtat enumEtat;
    @Enumerated(EnumType.STRING)
    private  EnumPost enumPost;

    private String firstName;
    private String lastname;
    private String email;
    private String mobile;
    private String cv;

    @OneToOne
    Entretien entretien ;
}

