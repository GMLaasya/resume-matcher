package com.gmlaasya.resumeanalyzer.controller;

import com.gmlaasya.resumeanalyzer.dto.ATSScoreDTO;
import com.gmlaasya.resumeanalyzer.service.ATSScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ats")
public class ATSScoreController {

    private final ATSScoreService atsScoreService;

    public ATSScoreController(ATSScoreService atsScoreService) {
        this.atsScoreService = atsScoreService;
    }

    @GetMapping
    public ResponseEntity<ATSScoreDTO> calculateATSScore(
            @RequestParam Long resumeId,
            @RequestParam Long jobId) {

        ATSScoreDTO result =
                atsScoreService.calculateATSScore(resumeId, jobId);

        return ResponseEntity.ok(result);
    }
}
