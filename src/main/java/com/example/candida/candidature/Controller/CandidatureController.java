package com.example.candida.candidature.Controller;

import com.example.candida.candidature.Entity.Candidature;
import com.example.candida.candidature.Repository.CandidatureRepository;
import com.example.candida.candidature.Services.CandidatureService;
import com.example.candida.candidature.Services.ICandidatureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CandidatureController {
    ICandidatureService ICandidatureService;
    public CandidatureController(ICandidatureService ICandidatureService) {
        this.ICandidatureService = ICandidatureService;
}


    @GetMapping("/findAllcandidates")
    @ResponseBody
    public List<Candidature> findAll() {
        return ICandidatureService.findAll();
    }

    @PostMapping("/newcandidates")
    public Candidature newCandidate(@RequestBody Candidature c) {

        return ICandidatureService.save(c);
    }


    @DeleteMapping("/supp/{id}")
    public void supprimerCandidate(@PathVariable("id") long id) {
        ICandidatureService.supprimerCandidate(id);

}
    @PutMapping("/updatecandidate/{id}")
    public Candidature updateCandidate(@PathVariable ("id") long id,@RequestBody Candidature c) throws Exception{
        return ICandidatureService.updateCandidate(id,c);

    }
    @GetMapping("/getByIdcandidate/{id}")
    public Candidature findById(@PathVariable("id") long id)
    {
        return ICandidatureService.findById(id);
    }

    @PutMapping("/assign/{id}/{idEntretien}")
        public Candidature assignCandidToEntretien(@PathVariable("id") long id, @PathVariable("idEntretien") long idEntretien){
        return ICandidatureService.assignCandidToEntretien(id,idEntretien);
    }

}

