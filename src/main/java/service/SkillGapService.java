package com.gmlaasya.resumeanalyzer.service;

import com.gmlaasya.resumeanalyzer.dto.SkillGapDTO;
import com.gmlaasya.resumeanalyzer.model.JobDescription;
import com.gmlaasya.resumeanalyzer.model.Resume;
import com.gmlaasya.resumeanalyzer.repository.JobRepository;
import com.gmlaasya.resumeanalyzer.repository.ResumeRepository;
import com.gmlaasya.resumeanalyzer.util.SkillExtractor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SkillGapService {

    private final ResumeRepository resumeRepository;
    private final JobRepository jobRepository;
    private final SkillExtractor skillExtractor;

    public SkillGapService(
            ResumeRepository resumeRepository,
            JobRepository jobRepository,
            SkillExtractor skillExtractor) {

        this.resumeRepository = resumeRepository;
        this.jobRepository = jobRepository;
        this.skillExtractor = skillExtractor;
    }

    public SkillGapDTO analyzeSkillGap(Long resumeId, Long jobId) {

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        JobDescription job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        Set<String> resumeSkills =
                skillExtractor.extractSkills(resume.getExtractedText());

        Set<String> jobSkills =
                skillExtractor.extractSkills(job.getDescription());

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String skill : jobSkills) {

            if (resumeSkills.contains(skill)) {
                matchedSkills.add(skill);
            } else {
                missingSkills.add(skill);
            }
        }

        return new SkillGapDTO(
                resumeId,
                jobId,
                matchedSkills,
                missingSkills
        );
    }
}
