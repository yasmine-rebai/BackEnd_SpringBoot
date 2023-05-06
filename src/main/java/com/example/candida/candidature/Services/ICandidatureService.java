package com.example.candida.candidature.Services;

import com.example.candida.candidature.Entity.Candidature;

import java.io.File;
import java.util.List;

public interface ICandidatureService {
   // Candidature findById(Long id);


    List<Candidature> retrieveAll();

    long save(Candidature candidature);
   void supprimerCandidate(long id);


    Candidature updateCandidate(Long id, Candidature c);

    Candidature findById(long id);
    
    public void generatePdf(String toString);


}
