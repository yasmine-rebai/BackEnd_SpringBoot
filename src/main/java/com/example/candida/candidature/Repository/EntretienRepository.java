package com.example.candida.candidature.Repository;

import com.example.candida.candidature.Entity.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EntretienRepository extends JpaRepository<Entretien, Long> {

}
