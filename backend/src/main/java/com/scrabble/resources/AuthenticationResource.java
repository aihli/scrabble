package com.scrabble.resources;

import com.scrabble.models.GameBoardRepresentation;
import com.scrabble.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationResource {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public boolean authenticateMove(@RequestBody GameBoardRepresentation gameBoardRepresentation) {
        return false;
    }
}
