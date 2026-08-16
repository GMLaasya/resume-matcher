package com.gmlaasya.resumeanalyzer.repository;

import com.gmlaasya.resumeanalyzer.model.JobDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobDescription, Long> {
}