package com.gmlaasya.resumeanalyzer.controller;

import com.gmlaasya.resumeanalyzer.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(
            RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getRecommendations(
            @RequestParam Long resumeId,
            @RequestParam Long jobId) {

        List<String> recommendations =
                recommendationService.generateRecommendations(
                        resumeId, jobId);

        return ResponseEntity.ok(recommendations);
    }
}
