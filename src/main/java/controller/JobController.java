package com.gmlaasya.resumeanalyzer.controller;

import com.gmlaasya.resumeanalyzer.dto.JobDTO;
import com.gmlaasya.resumeanalyzer.model.JobDescription;
import com.gmlaasya.resumeanalyzer.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobDescription> createJob(
            @RequestBody JobDTO request) {

        JobDescription job = jobService.createJob(request);

        return ResponseEntity.ok(job);
    }

    @GetMapping
    public ResponseEntity<List<JobDescription>> getAllJobs() {

        return ResponseEntity.ok(jobService.getAllJobs());
    }
}