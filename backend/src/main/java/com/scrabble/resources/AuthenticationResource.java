package com.scrabble.resources;

import com.scrabble.models.GameBoardRepresentation;
import com.scrabble.services.AuthenticationService;
import com.scrabble.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/authenticate")
public class AuthenticationResource {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Map<String, Boolean> authenticateMove(@RequestBody GameBoardRepresentation gameBoardRepresentation) {
        gameBoardRepresentation.transform();
        if (!authenticationService.verifyLettersAlignment(gameBoardRepresentation)) {
            return Collections.singletonMap("success", false);
        }
        boolean authenticated = dictionaryService.verifyAllWords(authenticationService.getAllWords(gameBoardRepresentation));
        return Collections.singletonMap("success", authenticated);
    }
}
