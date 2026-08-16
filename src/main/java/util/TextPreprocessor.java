package com.gmlaasya.resumeanalyzer.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TextPreprocessor {

    public Set<String> preprocess(String text) {

        if (text == null || text.isBlank()) {
            return new HashSet<>();
        }

        return Arrays.stream(
                        text.toLowerCase()
                                .replaceAll("[^a-zA-Z0-9\\s]", " ")
                                .split("\\s+")
                )
                .filter(word -> word.length() > 2)
                .collect(Collectors.toSet());
    }
}