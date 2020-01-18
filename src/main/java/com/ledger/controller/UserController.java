package com.ledger.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledger.controller.model.UserModel;
import com.ledger.model.PoolEntity;
import com.ledger.model.PoolUserXref;
import com.ledger.model.TransactionEntity;
import com.ledger.model.UserEntity;
import com.ledger.model.key.PoolUserXrefEntityKey;
import com.ledger.repository.TransactionRepository;
import com.ledger.repository.UserRepository;
import com.ledger.repository.XrefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    XrefRepository xrefRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ObjectMapper mapper;

    @GetMapping(produces = "application/json")
    public ResponseEntity getAllUser() throws JsonProcessingException {

        List<UserEntity> users = StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(mapper.writeValueAsString(users));
    }

    @GetMapping(value = "/{userid}", produces = "application/json")
    public ResponseEntity getUserDetails(@PathVariable String userid) throws JsonProcessingException {

        if(!userRepository.existsById(userid))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");

        UserEntity userEntity = userRepository.findById(userid).get();

        return ResponseEntity.ok().body(mapper.writeValueAsString(new UserModel(userEntity)));
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity createUser(@RequestBody UserModel userModel) {

        if(userRepository.existsById(userModel.getUserid()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken");

        UserEntity newUser = new UserEntity(userModel.getUserid(), userModel.getPassword(),
                userModel.getFirstName(), userModel.getLastName(), userModel.getRoles());

        userRepository.save(newUser);

        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value = "/{userId}/trans", produces = "application/json")
    public ResponseEntity getTransactions(@PathVariable String userId) throws JsonProcessingException{

        UserEntity userEntity = userRepository.findById(userId).get();

        List<TransactionEntity> transactionEntities = transactionRepository.getByBorrowerOrLender(userEntity, userEntity);

        return ResponseEntity.ok().body(mapper.writeValueAsString(transactionEntities));
    }

    @GetMapping(value = "/{userId}/pools", produces = "application/json")
    public ResponseEntity getPools(@PathVariable String userId) throws JsonProcessingException{

        UserEntity userEntity = userRepository.findById(userId).get();

        List<PoolUserXref> maps = xrefRepository.getByEntityKey_UserEntity(userEntity);

        List<PoolEntity> pools = new ArrayList<>();
        for(PoolUserXref m : maps) {
            pools.add(m.getEntityKey().getPoolEntity());
        }

        return ResponseEntity.ok().body(mapper.writeValueAsString(pools));
    }
}
