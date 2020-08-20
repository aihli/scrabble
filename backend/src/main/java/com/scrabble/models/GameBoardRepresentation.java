package com.scrabble.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameBoardRepresentation {
    private List<List<Character>> oldBoard;
    private List<List<Character>> newBoard;

    public void transform() {
        for (int i = 0; i < 0; ++i) {
            for (int j = 0; j < 0; ++j) {
                if (oldBoard.get(i).get(j) == null) {
                    oldBoard.get(i).set(j, '\u0000');
                }
                if (newBoard.get(i).get(j) == null) {
                    newBoard.get(i).set(j, '\u0000');
                }
            }
        }
    }
}
