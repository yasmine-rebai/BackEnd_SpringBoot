package com.example.candida.candidature.Services;

import com.example.candida.candidature.Entity.Candidature;

import java.util.List;

public interface ICandidatureService {
   // Candidature findById(Long id);
    List<Candidature> findAll();
    Candidature save(Candidature candidature);
   void supprimerCandidate(long id);


    Candidature updateCandidate(Long id, Candidature c);

    Candidature findById(long id);

    Candidature assignCandidToEntretien(long idCondidate, long idEntretien);
}
