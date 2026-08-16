package com.gmlaasya.resumeanalyzer.controller;

import com.gmlaasya.resumeanalyzer.dto.SkillGapDTO;
import com.gmlaasya.resumeanalyzer.service.SkillGapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skill-gap")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    @GetMapping
    public ResponseEntity<SkillGapDTO> analyzeSkillGap(
            @RequestParam Long resumeId,
            @RequestParam Long jobId) {

        SkillGapDTO result =
                skillGapService.analyzeSkillGap(resumeId, jobId);

        return ResponseEntity.ok(result);
    }
}
