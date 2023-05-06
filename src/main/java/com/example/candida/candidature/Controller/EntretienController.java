package com.example.candida.candidature.Controller;

import com.example.candida.candidature.Entity.Candidature;
import com.example.candida.candidature.Entity.Entretien;
import com.example.candida.candidature.Services.IEntretienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class EntretienController {
    @Autowired
    IEntretienService IEntretienService;



    @GetMapping("/FindAllE")
    @ResponseBody
    public List<Entretien> findAll() {
        return IEntretienService.findAll();
    }


    @GetMapping("/FindAllCandidatesOfEntre")
    @ResponseBody
    public List<Long> findAllCandidatesEntre() {

        List<Long> Cand=IEntretienService.findAllCandidatesEntre();
        return Cand;
    }
    @GetMapping("/FindAllCandidatesNotAssign")
    @ResponseBody
    public List<Candidature> findAllCandidatesNotAssign() {

        List<Candidature> Cand=IEntretienService.findAllCandidatesNotAssign();
        System.out.println(Cand);
        return Cand;
    }




    @PostMapping("/newE")
    public Entretien newEntretien(@RequestBody Entretien e) {

        return IEntretienService.save(e);
    }
    @PostMapping("/assignEntToCand/{id}")
    public Entretien assignEntToCand(@RequestBody Entretien e,@PathVariable("id") long id) {

        return IEntretienService.AssignEntToCandidate(e,id);
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

