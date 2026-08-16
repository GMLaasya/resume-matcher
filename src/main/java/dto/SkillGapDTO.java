package com.gmlaasya.resumeanalyzer.dto;

import java.util.List;

public class SkillGapDTO {

    private Long resumeId;
    private Long jobId;
    private List<String> matchedSkills;
    private List<String> missingSkills;

    public SkillGapDTO() {
    }

    public SkillGapDTO(
            Long resumeId,
            Long jobId,
            List<String> matchedSkills,
            List<String> missingSkills) {

        this.resumeId = resumeId;
        this.jobId = jobId;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}
