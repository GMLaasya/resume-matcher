package com.gmlaasya.resumeanalyzer.service;

import com.gmlaasya.resumeanalyzer.dto.ResumeUploadRequest;
import com.gmlaasya.resumeanalyzer.extractor.PdfExtractor;
import com.gmlaasya.resumeanalyzer.model.Resume;
import com.gmlaasya.resumeanalyzer.repository.ResumeRepository;
import com.gmlaasya.resumeanalyzer.util.SkillExtractor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final PdfExtractor pdfExtractor;
    private final SkillExtractor skillExtractor;

    private final String uploadDirectory = "uploads";

    public ResumeService(
            ResumeRepository resumeRepository,
            PdfExtractor pdfExtractor,
            SkillExtractor skillExtractor) {

        this.resumeRepository = resumeRepository;
        this.pdfExtractor = pdfExtractor;
        this.skillExtractor = skillExtractor;
    }

    public Resume uploadResume(
            MultipartFile file,
            ResumeUploadRequest request) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Resume file is empty.");
        }

        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null ||
                !originalFileName.toLowerCase().endsWith(".pdf")) {

            throw new IllegalArgumentException(
                    "Only PDF resumes are supported.");
        }

        Path uploadPath = Paths.get(uploadDirectory);

        Files.createDirectories(uploadPath);

        Path filePath = uploadPath.resolve(originalFileName);

        Files.write(filePath, file.getBytes());

        File savedFile = filePath.toFile();

        String extractedText = pdfExtractor.extractText(savedFile);

        Set<String> extractedSkills =
                skillExtractor.extractSkills(extractedText);

        Resume resume = new Resume();

        resume.setCandidateName(request.getCandidateName());
        resume.setEmail(request.getEmail());
        resume.setPhone(request.getPhone());
        resume.setResumeFileName(originalFileName);
        resume.setExtractedText(extractedText);

        resume.setSkills(String.join(", ", extractedSkills));

        return resumeRepository.save(resume);
    }
}