package com.gmlaasya.resumeanalyzer.dto;

public class ATSScoreDTO {

    private Long resumeId;
    private Long jobId;
    private double atsScore;

    public ATSScoreDTO() {
    }

    public ATSScoreDTO(Long resumeId, Long jobId, double atsScore) {
        this.resumeId = resumeId;
        this.jobId = jobId;
        this.atsScore = atsScore;
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

    public double getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(double atsScore) {
        this.atsScore = atsScore;
    }
}
