package com.gmlaasya.resumeanalyzer.controller;

import com.gmlaasya.resumeanalyzer.dto.MatchResultDTO;
import com.gmlaasya.resumeanalyzer.service.MatchingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matching")
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping
    public ResponseEntity<MatchResultDTO> calculateMatch(
            @RequestParam Long resumeId,
            @RequestParam Long jobId) {

        MatchResultDTO result =
                matchingService.calculateMatch(resumeId, jobId);

        return ResponseEntity.ok(result);
    }
}