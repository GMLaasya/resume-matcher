package com.gmlaasya.resumeanalyzer.service;

import com.gmlaasya.resumeanalyzer.dto.ATSScoreDTO;
import com.gmlaasya.resumeanalyzer.dto.MatchResultDTO;
import org.springframework.stereotype.Service;

@Service
public class ATSScoreService {

    private final MatchingService matchingService;

    public ATSScoreService(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    public ATSScoreDTO calculateATSScore(Long resumeId, Long jobId) {

        MatchResultDTO matchResult =
                matchingService.calculateMatch(resumeId, jobId);

        double atsScore = matchResult.getMatchScore();

        return new ATSScoreDTO(
                resumeId,
                jobId,
                atsScore
        );
    }
}
