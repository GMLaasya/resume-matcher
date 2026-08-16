package com.gmlaasya.resumeanalyzer.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SkillExtractor {

    private final Set<String> knownSkills = new HashSet<>(Arrays.asList(
            "java",
            "python",
            "c",
            "c++",
            "c#",
            "spring",
            "spring boot",
            "hibernate",
            "mysql",
            "sql",
            "html",
            "css",
            "javascript",
            "react",
            "angular",
            "node.js",
            "git",
            "github",
            "docker",
            "aws",
            "azure",
            "machine learning",
            "deep learning",
            "artificial intelligence",
            "tensorflow",
            "pytorch",
            "nlp",
            "data structures",
            "algorithms",
            "mongodb",
            "rest api"
    ));

    public Set<String> extractSkills(String text) {

        Set<String> extractedSkills = new HashSet<>();

        if (text == null || text.isBlank()) {
            return extractedSkills;
        }

        String lowerText = text.toLowerCase();

        for (String skill : knownSkills) {

            if (lowerText.contains(skill.toLowerCase())) {
                extractedSkills.add(skill);
            }
        }

        return extractedSkills;
    }
}