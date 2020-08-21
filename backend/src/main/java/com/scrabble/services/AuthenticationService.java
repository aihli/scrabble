package com.scrabble.services;

import com.scrabble.constants.GlobalVariables;
import com.scrabble.models.GameBoardRepresentation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthenticationService {

    public boolean verifyLettersAlignment(GameBoardRepresentation gameBoardRepresentation) {
        List<List<Character>> oldBoard = gameBoardRepresentation.getOldBoard();
        List<List<Character>> newBoard = gameBoardRepresentation.getNewBoard();
        List<Integer> newCharactersX = new ArrayList<>();
        List<Integer> newCharactersY = new ArrayList<>();
        for (int i = 0; i < GlobalVariables.BOARD_SIZE; ++i) {
            for (int j = 0; j < GlobalVariables.BOARD_SIZE; ++j) {
                if (oldBoard.get(i).get(j) != newBoard.get(i).get(j)) {
                    newCharactersX.add(i);
                    newCharactersY.add(j);
                }
            }
        }

        if (newCharactersX.size() == 0 || newCharactersX.size() == 1) {
            return true;
        }
        int differenceX = newCharactersX.get(1) - newCharactersX.get(0), differenceY = newCharactersY.get(1) - newCharactersY.get(0);
        if (differenceX != 0 && differenceY != 0) {
            return false;
        }
        for (int i = 2; i < newCharactersX.size(); ++i) {
            if ((newCharactersX.get(i) - newCharactersX.get(i - 1) != differenceX) &&
            newCharactersY.get(i) - newCharactersY.get(i - 1) != differenceY) {
                return false;
            }
        }
        return true;
    }

    public Set<String> getAllWords(GameBoardRepresentation gameBoardRepresentation) {
        Set<String> allWords = new HashSet<>();
        List<List<Character>> oldBoard = gameBoardRepresentation.getOldBoard();
        List<List<Character>> newBoard = gameBoardRepresentation.getNewBoard();
        List<Integer> newCharactersX = new ArrayList<>();
        List<Integer> newCharactersY = new ArrayList<>();
        for (int i = 0; i < GlobalVariables.BOARD_SIZE; ++i) {
            for (int j = 0; j < GlobalVariables.BOARD_SIZE; ++j) {
                if (oldBoard.get(i).get(j) != newBoard.get(i).get(j)) {
                    newCharactersX.add(i);
                    newCharactersY.add(j);
                }
            }
        }
        for (int i = 0; i < newCharactersX.size(); ++i) {
            String newWordRow = expandWordRow(gameBoardRepresentation, newCharactersX.get(i), newCharactersY.get(i));
            String newWordColumn = expandWordColumn(gameBoardRepresentation, newCharactersX.get(i), newCharactersY.get(i));
            if (newWordRow != null) {
                allWords.add(newWordRow);
            }
            if (newWordColumn != null) {
                allWords.add(newWordColumn);
            }
        }
        return allWords;
    }

    private String expandWordRow(GameBoardRepresentation gameBoardRepresentation, int i, int j) {
        int tempHi = i, tempLo = i;
        while (tempHi < GlobalVariables.BOARD_SIZE && gameBoardRepresentation.getNewBoard().get(tempHi).get(j) != null && gameBoardRepresentation.getNewBoard().get(tempHi).get(j) != '\u0000') {
            ++tempHi;
        }
        --tempHi;
        while (tempLo >= 0 && gameBoardRepresentation.getNewBoard().get(tempLo).get(j) != null && gameBoardRepresentation.getNewBoard().get(tempLo).get(j) != '\u0000') {
            --tempLo;
        }
        ++tempLo;
        if (tempHi - tempLo != 0) {
            StringBuilder builder = new StringBuilder();
            while (tempLo <= tempHi) {
                builder.append(gameBoardRepresentation.getNewBoard().get(tempLo).get(j));
                ++tempLo;
            }
            return builder.toString();
        }
        return null;
    }

    private String expandWordColumn(GameBoardRepresentation gameBoardRepresentation, int i, int j) {
        int tempHi = j, tempLo = j;
        while (tempHi < GlobalVariables.BOARD_SIZE && gameBoardRepresentation.getNewBoard().get(i).get(tempHi) != null && gameBoardRepresentation.getNewBoard().get(i).get(tempHi) != '\u0000') {
            ++tempHi;
        }
        --tempHi;
        while (tempLo >= 0 && gameBoardRepresentation.getNewBoard().get(i).get(tempLo) != null && gameBoardRepresentation.getNewBoard().get(i).get(tempLo) != '\u0000') {
            --tempLo;
        }
        ++tempLo;
        if (tempHi - tempLo != 0) {
            StringBuilder builder = new StringBuilder();
            while (tempLo <= tempHi) {
                builder.append(gameBoardRepresentation.getNewBoard().get(i).get(tempLo));
                ++tempLo;
            }
            return builder.toString();
        }
        return null;
    }
}
