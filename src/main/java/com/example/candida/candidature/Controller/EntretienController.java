package com.example.candida.candidature.Controller;

import com.example.candida.candidature.Entity.Candidature;
import com.example.candida.candidature.Entity.Entretien;
import com.example.candida.candidature.Services.IEntretienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntretienController {
    @Autowired
    IEntretienService IEntretienService;



    @GetMapping("/FindAllE")
    @ResponseBody
    public List<Entretien> findAll() {
        return IEntretienService.findAll();
    }

    @PostMapping("/newE")
    public Entretien newEntretien(@RequestBody Entretien e) {

        return IEntretienService.save(e);
    }


    @DeleteMapping("/suppE/{id}")
    public void supprimer(@PathVariable("id") long id) {
        IEntretienService.supprimerE(id);

    }
    @PutMapping("/updateE")
    public Entretien update(@RequestBody Entretien e) {
        return IEntretienService.updateE(e);

    }
    @GetMapping("/getByIdE/{id}")
    public Entretien findById(@PathVariable("id") long id)
    {
        return IEntretienService.findById(id);
    }

}

