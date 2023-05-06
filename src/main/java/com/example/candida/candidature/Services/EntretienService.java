package com.example.candida.candidature.Services;


import com.example.candida.candidature.Entity.Entretien;
import com.example.candida.candidature.Entity.Candidature;

import com.example.candida.candidature.Repository.CandidatureRepository;
import com.example.candida.candidature.Repository.EntretienRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class EntretienService implements IEntretienService
{
    @Autowired
    EntretienRepository entretienRepository;


    @Autowired
    CandidatureRepository candidatureRepository;

    @Override
    public List<Entretien> findAll() {
        return (List<Entretien>) entretienRepository.findAll();
    }

    @Override
    public Entretien save(Entretien e) {
        return entretienRepository.save(e);
    }

    @Override
    public Entretien AssignEntToCandidate(Entretien e, Long IdCandidate) {
        Candidature can=candidatureRepository.findById(IdCandidate).get();
        e.setCandidate(can);
        return entretienRepository.save(e);
    }

    @Override
    public void  supprimerE(long id) {
        entretienRepository.deleteById(id);}
    @Override
    public Entretien updateE(Entretien e)
    {
        return entretienRepository.save(e);
    }
    @Override
    public Entretien findById(long id)
    {
        return  entretienRepository.findById(id).orElse(null);
    }

    @Override
    public List<Long> findAllCandidatesEntre() {
        List<Entretien> list=entretienRepository.findAll();
        List<Long> candidateIdFK = new ArrayList<>();

        for (Entretien entretien : list) {
            candidateIdFK.add(entretien.getCandidate().getIdCondidate());
        }
        return candidateIdFK;
    }

    @Override
    public List<Candidature> findAllCandidatesNotAssign() {
        List<Candidature> CandidatesNotAssign = new ArrayList<Candidature>();
        List<Long> CandidatesAssign=findAllCandidatesEntre();
        List<Candidature> Candidates= candidatureRepository.findAll();
        for (Candidature candidate : Candidates) {
            if (!CandidatesAssign.contains(candidate.getIdCondidate())) {
                CandidatesNotAssign.add(candidate);
            }
        }


        return CandidatesNotAssign;
    }


}