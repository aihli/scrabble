package com.scrabble.services;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DictionaryService {

    public boolean verifyAllWords(Set<String> allWords) {
        for (String word : allWords) {
            if (!verifyWord(word)) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyWord(String str) {
        return true;
    }

}
