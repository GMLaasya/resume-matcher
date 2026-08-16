package com.gmlaasya.resumeanalyzer.dto;

public class MatchResultDTO {

    private Long resumeId;
    private Long jobId;
    private double matchScore;

    public MatchResultDTO() {
    }

    public MatchResultDTO(Long resumeId, Long jobId, double matchScore) {
        this.resumeId = resumeId;
        this.jobId = jobId;
        this.matchScore = matchScore;
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

    public double getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(double matchScore) {
        this.matchScore = matchScore;
    }
}