package com.scrabble;

import com.scrabble.models.LetterDistributionRepresentation;
import com.scrabble.services.LetterDistributorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LetterDistributorServiceTest {
    private LetterDistributorService letterDistributorService;
    private LetterDistributionRepresentation letterDistributionRepresentation;

    @BeforeEach
    public void setUp() {
        this.letterDistributorService = new LetterDistributorService();
        this.letterDistributionRepresentation = new LetterDistributionRepresentation();
        List<Character> letterBank = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            letterBank.add('A');
            letterBank.add('B');
            letterBank.add('C');
            letterBank.add('D');
            letterBank.add('E');
        }
        List<Character> tradedLetters = new ArrayList<>();
        List<Character> playerLetters = new ArrayList<>();
        for (int i = 0; i < 7; ++i) {
            playerLetters.add('F');
        }
        this.letterDistributionRepresentation.setLetterBank(letterBank);
        this.letterDistributionRepresentation.setTradedLetters(tradedLetters);
        this.letterDistributionRepresentation.setPlayerLetters(playerLetters);
    }

    @Test
    public void testDistributeLettersNothing() {
        this.letterDistributionRepresentation = this.letterDistributorService.distributeLetters(this.letterDistributionRepresentation);
        assertEquals(15, this.letterDistributionRepresentation.getLetterBank().size());
        assertEquals(0, this.letterDistributionRepresentation.getTradedLetters().size());
        assertEquals(7, this.letterDistributionRepresentation.getPlayerLetters().size());
    }

    @Test
    public void testDistributeThreeLetters(){
        for (int i = 0; i < 3; ++i) {
            this.letterDistributionRepresentation.getTradedLetters().add(this.letterDistributionRepresentation.getPlayerLetters().remove(0));
        }

        this.letterDistributionRepresentation = this.letterDistributorService.distributeLetters(this.letterDistributionRepresentation);
        assertEquals(15, this.letterDistributionRepresentation.getLetterBank().size());
        assertEquals(0, this.letterDistributionRepresentation.getTradedLetters().size());
        assertEquals(7, this.letterDistributionRepresentation.getPlayerLetters().size());

    }


}
