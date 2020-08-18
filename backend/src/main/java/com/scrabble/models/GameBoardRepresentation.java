package com.scrabble.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameBoardRepresentation {
    private List<List<Character>> oldBoard;
    private List<List<Character>> newBoard;
}
