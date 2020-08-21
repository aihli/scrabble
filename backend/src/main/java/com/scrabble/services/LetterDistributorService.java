package com.scrabble.services;

import com.scrabble.models.LetterDistributionRepresentation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LetterDistributorService {

    public LetterDistributionRepresentation distributeLetters(LetterDistributionRepresentation letterDistributionRepresentation) {
        Random random = new Random();
        List<Character> playerLetters = letterDistributionRepresentation.getPlayerLetters();
        List<Character> letterBank = letterDistributionRepresentation.getLetterBank();
        List<Character> tradedLetters = letterDistributionRepresentation.getTradedLetters();
        while (playerLetters.size() < 7) {
            playerLetters.add(letterBank.remove(random.nextInt(letterBank.size())));
        }
        letterBank.addAll(tradedLetters);
        letterDistributionRepresentation.setTradedLetters(new ArrayList<>());
        return letterDistributionRepresentation;
    }

}
