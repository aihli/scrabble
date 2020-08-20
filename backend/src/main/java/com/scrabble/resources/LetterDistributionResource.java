package com.scrabble.resources;

import com.scrabble.models.LetterDistributionRepresentation;
import com.scrabble.services.LetterDistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/letters")
public class LetterDistributionResource {
    @Autowired
    private LetterDistributorService letterDistributorService;


    @PostMapping(consumes = "application/json", produces = "application/json")
    public LetterDistributionRepresentation getLetters(@RequestBody LetterDistributionRepresentation letterDistributionRepresentation) {
        return letterDistributorService.distributeLetters(letterDistributionRepresentation);
    }
}
