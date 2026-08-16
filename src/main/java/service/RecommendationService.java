package com.gmlaasya.resumeanalyzer.service;

import com.gmlaasya.resumeanalyzer.dto.SkillGapDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    private final SkillGapService skillGapService;

    public RecommendationService(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    public List<String> generateRecommendations(Long resumeId, Long jobId) {

        SkillGapDTO skillGap =
                skillGapService.analyzeSkillGap(resumeId, jobId);

        List<String> recommendations = new ArrayList<>();

        for (String skill : skillGap.getMissingSkills()) {

            String recommendation = getRecommendation(skill);

            recommendations.add(recommendation);
        }

        return recommendations;
    }

    private String getRecommendation(String skill) {

        return switch (skill.toLowerCase()) {

            case "java" ->
                    "Improve Java programming through OOP, collections and problem solving.";

            case "python" ->
                    "Practice Python programming and Python-based problem solving.";

            case "sql" ->
                    "Practice SQL queries, joins, subqueries and database design.";

            case "mysql" ->
                    "Learn MySQL database management, queries and optimization.";

            case "spring" ->
                    "Learn the Spring Framework and dependency injection.";

            case "spring boot" ->
                    "Build REST APIs using Spring Boot.";

            case "rest api" ->
                    "Learn REST API design, HTTP methods, status codes and API integration.";

            case "algorithms" ->
                    "Practice algorithms such as sorting, searching, graphs and dynamic programming.";

            case "data structures" ->
                    "Practice arrays, linked lists, stacks, queues, trees and graphs.";

            case "html" ->
                    "Learn HTML5 and semantic web page structure.";

            case "css" ->
                    "Practice CSS layouts, Flexbox, Grid and responsive design.";

            case "javascript" ->
                    "Practice JavaScript fundamentals, DOM manipulation and ES6.";

            case "react" ->
                    "Learn React components, state management and API integration.";

            case "docker" ->
                    "Learn Docker containers, images and basic deployment.";

            case "aws" ->
                    "Learn AWS fundamentals including EC2, S3 and IAM.";

            case "machine learning" ->
                    "Study supervised learning, unsupervised learning and model evaluation.";

            case "artificial intelligence" ->
                    "Learn AI fundamentals and practical AI application development.";

            default ->
                    "Explore courses, documentation and hands-on projects related to " + skill + ".";
        };
    }
}
