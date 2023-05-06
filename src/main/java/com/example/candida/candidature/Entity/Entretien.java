package com.example.candida.candidature.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Entretien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntretien;

    @Temporal(TemporalType.DATE)
    private Date date;
    private String lieu;
    private String description;
    @OneToOne
    @JsonIgnoreProperties("entretien")
    private Candidature candidate;

}
