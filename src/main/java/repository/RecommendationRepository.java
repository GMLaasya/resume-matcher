package com.gmlaasya.resumeanalyzer.repository;

import com.gmlaasya.resumeanalyzer.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

}