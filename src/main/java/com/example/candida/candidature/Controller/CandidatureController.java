package com.example.candida.candidature.Controller;

import com.example.candida.candidature.Entity.Candidature;
import com.example.candida.candidature.Repository.CandidatureRepository;
import com.example.candida.candidature.Services.CandidatureService;
import com.example.candida.candidature.Services.ICandidatureService;
import com.example.candida.candidature.Utils.FileUploadUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.candida.candidature.Services.ExportExperienceService;

import org.apache.catalina.Context;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;








@RestController
@CrossOrigin(origins = "*")
public class CandidatureController  implements ServletContextAware {
    ICandidatureService ICandidatureService;

    public CandidatureController(ICandidatureService ICandidatureService) {
        this.ICandidatureService = ICandidatureService;

    }

    @Autowired
    private ExportExperienceService exportExperienceService;
    @Autowired
    ServletContext context;


    @GetMapping("/findAllcandidates")
    @ResponseBody
    public List<Candidature> findAll() {
        return ICandidatureService.retrieveAll();
    }

    @PostMapping("/newcandidates")
    public long newCandidate(@RequestBody Candidature c) {

        return ICandidatureService.save(c);
    }


    @DeleteMapping("/supp/{id}")
    public void supprimerCandidate(@PathVariable("id") long id) {
        ICandidatureService.supprimerCandidate(id);

    }

    @PutMapping("/updatecandidate/{id}")
    public Candidature updateCandidate(@PathVariable("id") long id, @RequestBody Candidature c) throws Exception {
        return ICandidatureService.updateCandidate(id, c);

    }

    @GetMapping("/getByIdcandidate/{id}")
    public Candidature findById(@PathVariable("id") long id) {
        return ICandidatureService.findById(id);
    }




    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;


    }

    @PostMapping("/addCV")
    public long add(@RequestParam("candidature") String candidature,
                    @RequestParam("file") MultipartFile image) throws JsonParseException, JsonMappingException, Exception {

        Candidature arti = new ObjectMapper().readValue(candidature, Candidature.class);
        boolean isExit = new File(context.getRealPath("/Imagess/")).exists();
        String fileN = StringUtils.cleanPath(image.getOriginalFilename());
        arti.setCv(fileN);
        System.out.println("====================");

        System.out.println(arti.getIdCondidate());
        String uploadDir = "Imagess/" + arti.getIdCondidate();
        System.out.println(uploadDir);
        FileUploadUtil.saveFile(uploadDir, fileN, image);
        if (!isExit) {
            new File(context.getRealPath("/Imagess/")).mkdir();
            System.out.println("mk dir Imagess.............");
        }
        System.out.println("Save Article  22222.............");

        String fileName = image.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(fileName);
        File serverFile = new File(context.getRealPath("/Imagess/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, image.getBytes());


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Save Article 333333.............");
        arti.setCv(newFileName);


        return ICandidatureService.save(arti);
    }

    @GetMapping(path = "/cvarticle/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
        Candidature Article = ICandidatureService.findById(id);
        return Files.readAllBytes(Paths.get("Imagess/null/" + Article.getCv()));
    }


    @GetMapping("/export/pdf")
    public ResponseEntity<InputStreamResource> exportTermsPdf() {
        List<Candidature> candidature = (List<Candidature>) ICandidatureService.retrieveAll();
        ByteArrayInputStream bais = exportExperienceService.experiencesPDFRepot(candidature);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=experiences.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bais));
    }

    @GetMapping("/export/excel")
    public ResponseEntity<InputStreamResource> exportExperiencesExcel() throws IOException {
        List<Candidature> candidature = (List<Candidature>) ICandidatureService.retrieveAll();
        ByteArrayInputStream bais = exportExperienceService.candidatureExcelReport(candidature);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=experiences.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
    }


}