package com.scrabble.resources;

import com.scrabble.models.GameBoardRepresentation;
import com.scrabble.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationResource {

    @Autowired
    private AuthenticationService authenticationService;
    
    @PostMapping()
    public boolean authenticateMove(GameBoardRepresentation gameBoardRepresentation) {
        return false;
    }
}
