package com.gmlaasya.resumeanalyzer.service;

import com.gmlaasya.resumeanalyzer.dto.MatchResultDTO;
import com.gmlaasya.resumeanalyzer.model.JobDescription;
import com.gmlaasya.resumeanalyzer.model.Resume;
import com.gmlaasya.resumeanalyzer.repository.JobRepository;
import com.gmlaasya.resumeanalyzer.repository.ResumeRepository;
import com.gmlaasya.resumeanalyzer.util.TextPreprocessor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MatchingService {

    private final ResumeRepository resumeRepository;
    private final JobRepository jobRepository;
    private final TextPreprocessor textPreprocessor;

    public MatchingService(
            ResumeRepository resumeRepository,
            JobRepository jobRepository,
            TextPreprocessor textPreprocessor) {

        this.resumeRepository = resumeRepository;
        this.jobRepository = jobRepository;
        this.textPreprocessor = textPreprocessor;
    }

    public MatchResultDTO calculateMatch(Long resumeId, Long jobId) {

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        JobDescription job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        Set<String> resumeWords =
                textPreprocessor.preprocess(resume.getExtractedText());

        Set<String> jobWords =
                textPreprocessor.preprocess(job.getDescription());

        Set<String> matchingWords = new HashSet<>(resumeWords);
        matchingWords.retainAll(jobWords);

        double score = 0;

        if (!jobWords.isEmpty()) {
            score = ((double) matchingWords.size()
                    / jobWords.size()) * 100;
        }

        score = Math.round(score * 100.0) / 100.0;

        return new MatchResultDTO(
                resumeId,
                jobId,
                score
        );
    }
}