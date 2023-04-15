package com.example.candida.candidature.Services;

import com.example.candida.candidature.Entity.Candidature;
import com.example.candida.candidature.Entity.Entretien;

import java.util.List;

public interface IEntretienService {
    List<Entretien> findAll();
    Entretien save(Entretien e);
    void supprimerE(long id);

    Entretien updateE(Entretien e);

    Entretien findById(long id);

}
