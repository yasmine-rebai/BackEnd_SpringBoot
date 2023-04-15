package com.example.candida.candidature.Repository;
import com.example.candida.candidature.Entity.Candidature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
}
