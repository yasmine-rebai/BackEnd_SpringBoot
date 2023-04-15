package com.example.candida.candidature.Services;


import com.example.candida.candidature.Entity.Entretien;
import com.example.candida.candidature.Repository.EntretienRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class EntretienService implements IEntretienService
{
    @Autowired
    EntretienRepository entretienRepository;




    @Override
    public List<Entretien> findAll() {
        return (List<Entretien>) entretienRepository.findAll();
    }

    @Override
    public Entretien save(Entretien e) {
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



}