package com.gmlaasya.resumeanalyzer.dto;

public class ResumeUploadRequest {

    private String candidateName;

    private String email;

    private String phone;

    public ResumeUploadRequest() {
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
