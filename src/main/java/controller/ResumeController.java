package com.gmlaasya.resumeanalyzer.controller;

import com.gmlaasya.resumeanalyzer.dto.ResumeUploadRequest;
import com.gmlaasya.resumeanalyzer.model.Resume;
import com.gmlaasya.resumeanalyzer.service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Resume> uploadResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("candidateName") String candidateName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone) {

        try {

            ResumeUploadRequest request = new ResumeUploadRequest();

            request.setCandidateName(candidateName);
            request.setEmail(email);
            request.setPhone(phone);

            Resume resume =
                    resumeService.uploadResume(file, request);

            return ResponseEntity.ok(resume);

        } catch (Exception e) {

            return ResponseEntity.badRequest().build();
        }
    }
}
