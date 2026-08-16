package com.gmlaasya.resumeanalyzer.service;

import com.gmlaasya.resumeanalyzer.dto.JobDTO;
import com.gmlaasya.resumeanalyzer.model.JobDescription;
import com.gmlaasya.resumeanalyzer.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JobDescription createJob(JobDTO request) {

        JobDescription job = new JobDescription();

        job.setCompanyName(request.getCompanyName());
        job.setJobTitle(request.getJobTitle());
        job.setDescription(request.getDescription());

        return jobRepository.save(job);
    }

    public List<JobDescription> getAllJobs() {
        return jobRepository.findAll();
    }
}