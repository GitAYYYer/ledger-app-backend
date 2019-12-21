package com.ledger.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledger.model.PoolEntity;
import com.ledger.repository.PoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ObjectMapper mapper;

    @GetMapping(produces = "application/json")
    public ResponseEntity getAllPools() throws JsonProcessingException {

        List<PoolEntity> pools = StreamSupport
                .stream(poolRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(mapper.writeValueAsString(pools));
    }

    @GetMapping(value = "/{pool_name}", produces = "application/json")
    public ResponseEntity getPoolDetails(@PathVariable("pool_name") String poolName) throws JsonProcessingException {
        return ResponseEntity.ok().body(mapper.writeValueAsString(poolRepository.findById(poolName)));
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity createPool(@RequestBody String model) {

        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/{pool_name}", produces = "application/json")
    public ResponseEntity addMemberToPool(@PathVariable("pool_name") String poolName, @RequestBody String userModel) {

        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value = "/{pool_name}/calc", produces = "application/json")
    public ResponseEntity calcPoolDebt(@PathVariable("pool_name") String poolName) {

        return ResponseEntity.ok().body(null);
    }


}
