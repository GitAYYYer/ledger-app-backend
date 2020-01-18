package com.ledger.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledger.controller.model.UserModel;
import com.ledger.model.UserEntity;
import com.ledger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper mapper;

    @GetMapping(produces = "application/json")
    public ResponseEntity getUserDetails(@PathVariable String username) throws JsonProcessingException {
        if(!userRepository.existsById(username))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");

        UserEntity userEntity = userRepository.getUserByUsername(username);

        return ResponseEntity.ok().body(mapper.writeValueAsString(new UserModel(userEntity)));
    }
}
