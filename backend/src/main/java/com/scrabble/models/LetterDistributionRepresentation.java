package com.scrabble.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LetterDistributionRepresentation {
    private List<Character> letterBank;
    private List<Character> tradedLetters;
    private List<Character> playerLetters;
}
