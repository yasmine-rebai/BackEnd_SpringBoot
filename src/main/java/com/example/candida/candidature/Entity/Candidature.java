package com.example.candida.candidature.Entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.io.File;
import java.io.Serializable;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
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
    @Column(nullable = true, length = 64)

    private String cv;
    @OneToOne(mappedBy = "candidate", cascade = CascadeType.REMOVE)
    private Entretien entretien;




}

