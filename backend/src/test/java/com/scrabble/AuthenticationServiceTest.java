package com.scrabble;

import com.scrabble.constants.GlobalVariables;
import com.scrabble.models.GameBoardRepresentation;
import com.scrabble.services.AuthenticationService;
import com.scrabble.services.DictionaryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationServiceTest {
    private AuthenticationService authenticationService;
    private DictionaryService dictionaryService;
    private GameBoardRepresentation gameBoardRepresentation;

    public List<List<Character>> populateBoard() {
        List<List<Character>> board = new ArrayList<>();
        for (int i = 0; i < GlobalVariables.BOARD_SIZE; ++i) {
            List<Character> newRow = new ArrayList<>();
            for (int j = 0; j < GlobalVariables.BOARD_SIZE; ++j) {
                newRow.add('\u0000');
            }
            board.add(newRow);
        }
        return board;
    }

    @BeforeEach
    public void setUpBoard() {
        this.authenticationService = new AuthenticationService();
        this.dictionaryService = new DictionaryService();
        this.gameBoardRepresentation = new GameBoardRepresentation();
        this.gameBoardRepresentation.setOldBoard(populateBoard());
        this.gameBoardRepresentation.setNewBoard(populateBoard());
    }

    @Test
    public void testVerifyLettersAlignmentSameBoard() {
        assertTrue(authenticationService.verifyLettersAlignment(this.gameBoardRepresentation));
    }

    @Test
    public void testVerifyLettersAlignmentOneCharacter() {
        this.gameBoardRepresentation.getNewBoard().get(0).set(0, 'A');
        assertTrue(authenticationService.verifyLettersAlignment(this.gameBoardRepresentation));
    }

    @Test
    public void testVerifyLettersAlignmentThreeCharacters() {
        this.gameBoardRepresentation.getNewBoard().get(0).set(0, 'A');
        this.gameBoardRepresentation.getNewBoard().get(0).set(1, 'A');
        this.gameBoardRepresentation.getNewBoard().get(0).set(2, 'A');
        assertTrue(authenticationService.verifyLettersAlignment(this.gameBoardRepresentation));
    }

    @Test
    public void testVerifyLettersAlignmentIncorrect() {
        this.gameBoardRepresentation.getNewBoard().get(0).set(0, 'A');
        this.gameBoardRepresentation.getNewBoard().get(1).set(1, 'A');
        this.gameBoardRepresentation.getNewBoard().get(2).set(2, 'A');
        assertFalse(authenticationService.verifyLettersAlignment(this.gameBoardRepresentation));
    }

    @Test
    public void testGetAllWordsRepeated() {
        this.gameBoardRepresentation.getNewBoard().get(0).set(0, 'A');
        this.gameBoardRepresentation.getNewBoard().get(0).set(1, 'A');
        this.gameBoardRepresentation.getNewBoard().get(0).set(2, 'A');
        Set<String> allWords = authenticationService.getAllWords(gameBoardRepresentation);
        assertTrue(allWords.contains("AAA"));
        assertEquals(1, allWords.size());
    }

    @Test
    public void testGetAllWordsRowAndColumn() {
        this.gameBoardRepresentation.getNewBoard().get(0).set(0, 'A');
        this.gameBoardRepresentation.getNewBoard().get(0).set(1, 'A');
        this.gameBoardRepresentation.getNewBoard().get(0).set(2, 'A');
        this.gameBoardRepresentation.getNewBoard().get(1).set(0, 'A');
        this.gameBoardRepresentation.getNewBoard().get(1).set(1, 'A');
        this.gameBoardRepresentation.getNewBoard().get(1).set(2, 'A');
        this.gameBoardRepresentation.getNewBoard().get(1).set(3, 'A');
        Set<String> allWords = authenticationService.getAllWords(gameBoardRepresentation);
        assertTrue(allWords.contains("AA"));
        assertTrue(allWords.contains("AAA"));
        assertTrue(allWords.contains("AAAA"));
        assertEquals(3, allWords.size());
    }

    @Test
    public void testVerifyOneWord() {
        this.gameBoardRepresentation.getNewBoard().get(0).set(0, 'A');
        this.gameBoardRepresentation.getNewBoard().get(0).set(1, 'B');
        this.gameBoardRepresentation.getNewBoard().get(0).set(2, 'L');
        this.gameBoardRepresentation.getNewBoard().get(0).set(3, 'E');
        Set<String> allWords = authenticationService.getAllWords(gameBoardRepresentation);
        assertTrue(dictionaryService.verifyAllWords(allWords));
    }
}
