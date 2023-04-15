package com.example.candida.candidature.Services;


import com.example.candida.candidature.Entity.Entretien;
import com.example.candida.candidature.Repository.CandidatureRepository;
import com.example.candida.candidature.Entity.Candidature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CandidatureService implements ICandidatureService
{
    @Autowired
    CandidatureRepository candidatureRepository;
    @Autowired
    EntretienService entretienService ;

    public CandidatureService(CandidatureRepository candidatureRepository){this.candidatureRepository = candidatureRepository;}





    @Override
    public List<Candidature> findAll() {
        return (List<Candidature>) candidatureRepository.findAll();
    }

    @Override
    public Candidature save(Candidature candidature) {
       return candidatureRepository.save(candidature);
    }
    @Override
    public void  supprimerCandidate(long id) {
        candidatureRepository.deleteById(id);}
    @Override
    public Candidature updateCandidate(Long id, Candidature c)
    {
        if (candidatureRepository.findById(id).isPresent()) {
            Candidature toUpdateCandidature =candidatureRepository.findById(id).get();
            toUpdateCandidature.setFirstName(c.getFirstName());
            toUpdateCandidature.setLastname(c.getLastname());
            toUpdateCandidature.setEnumEtat(c.getEnumEtat());
            toUpdateCandidature.setEnumPost(c.getEnumPost());
            toUpdateCandidature.setEmail(c.getEmail());
            toUpdateCandidature.setMobile((c.getMobile()));
            toUpdateCandidature.setCv(c.getCv());
            return candidatureRepository.save(toUpdateCandidature);


        }
        return null;
    }
    @Override
    public Candidature findById(long id)
    {
        return  candidatureRepository.findById(id).orElse(null);
    }

    @Override
    public Candidature assignCandidToEntretien(long idCondidate, long idEntretien){

        Entretien e=entretienService.findById(idEntretien);
        Candidature c=findById(idCondidate);
        c.setEntretien(e);
        entretienService.save(e);
        candidatureRepository.save(c);
        return c;
    }



}