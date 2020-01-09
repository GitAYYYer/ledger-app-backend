package com.ledger.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledger.controller.model.PoolModel;
import com.ledger.controller.model.TransactionModel;
import com.ledger.controller.model.UserModel;
import com.ledger.model.PoolEntity;
import com.ledger.model.PoolUserXref;
import com.ledger.model.TransactionEntity;
import com.ledger.model.UserEntity;
import com.ledger.model.key.PoolUserXrefEntityKey;
import com.ledger.repository.PoolRepository;
import com.ledger.repository.TransactionRepository;
import com.ledger.repository.UserRepository;
import com.ledger.repository.XrefRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/v1/pool")
public class PoolController {

    @Autowired
    PoolRepository poolRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    XrefRepository xrefRepository;

    @Autowired
    TransactionRepository transRepository;

    @Autowired
    ObjectMapper mapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping(produces = "application/json")
    public ResponseEntity getAllPools() throws JsonProcessingException {
        logger.trace("Call to getAllPools()");

        List<PoolEntity> pools = StreamSupport
                .stream(poolRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        logger.trace("Return call to getAllPools() with {}", pools);
        return ResponseEntity.ok().body(mapper.writeValueAsString(pools));
    }

    @GetMapping(value = "/{pool_id}", produces = "application/json")
    public ResponseEntity getPoolDetails(@PathVariable("pool_id") Integer poolId) throws JsonProcessingException {
        logger.trace("Call to getPoolDetails");

        PoolEntity poolEntity = poolRepository.findById(poolId).get();
        PoolModel poolModel =  new PoolModel(poolEntity);

        List<PoolUserXref> usersXref = xrefRepository.getByEntityKey_PoolEntity(poolEntity);

        for(PoolUserXref m : usersXref) {
            poolModel.addUser(new UserModel(m.getEntityKey().getUserEntity().getUserId()));
        }

        logger.trace("Return call to getPoolDetails() with {}", poolEntity);
        return ResponseEntity.ok().body(mapper.writeValueAsString(poolModel));
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity createPool(@RequestBody PoolModel model) throws JsonProcessingException {
        logger.trace("Call to createPool()");

        PoolEntity newPool = new PoolEntity(model.getName(), model.getDesc());
        poolRepository.save(newPool);
        logger.info("Added new pool - {}", newPool);

        logger.trace("Return call to createPool() with {}", newPool);
        return ResponseEntity.ok().body(mapper.writeValueAsString(newPool));
    }

    @PostMapping(value = "/{pool_id}", produces = "application/json")
    public ResponseEntity addMemberToPool(@PathVariable("pool_id") Integer poolId, @RequestBody UserModel userModel) {
        logger.trace("Call to addMemberToPool()");

        //Check if the pool exists
        if(!poolRepository.existsById(poolId))
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Pool does not exist");

        PoolEntity poolEntity = poolRepository.findById(poolId).get();

        //Check if the user exists
        if(!userRepository.existsById(userModel.getUserid()))
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "User does not exist");

        UserEntity userEntity = userRepository.findById(userModel.getUserid()).get();

        //Check if the user is already in the pool
        if(xrefRepository.existsById(new PoolUserXrefEntityKey(poolEntity, userEntity)))
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "User already in pool");

        PoolUserXref newPoolUser = new PoolUserXref(new PoolUserXrefEntityKey(poolEntity, userEntity));

        xrefRepository.save(newPoolUser);
        logger.info("Added user to pool - {}", newPoolUser);

        logger.trace("Return call to addMemberToPool() with null");
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/{pool_id}/trans", produces = "applcation/json")
    public ResponseEntity addTransaction(@PathVariable(name = "pool_id") Integer poolId, @RequestBody TransactionModel model) {

        PoolEntity pool = poolRepository.findById(poolId).get();

        UserEntity payee = userRepository.findById(model.getPayeeId()).get();

        UserEntity owee = userRepository.findById(model.getOweeId()).get();

        Double ammount = model.getAmmount();

        TransactionEntity newTrans = new TransactionEntity(pool,payee,owee,ammount);

        transRepository.save(newTrans);

        //TODO i dont this i tested this

        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value = "/{pool_name}/calc", produces = "application/json")
    public ResponseEntity calcPoolDebt(@PathVariable("pool_name") String poolName) {



        return ResponseEntity.ok().body(null);
    }


}
