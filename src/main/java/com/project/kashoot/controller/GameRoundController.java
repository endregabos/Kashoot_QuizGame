package com.project.kashoot.controller;

import com.project.kashoot.exception.ResourceNotFoundException;
import com.project.kashoot.model.GameRound;
import com.project.kashoot.repository.GameRoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class GameRoundController {

    @Autowired
    private GameRoundRepository gameRoundRepository;

    //get game rounds
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("gameround")
    public List<GameRound> getAllGameRounds(){
        return this.gameRoundRepository.findAll();
    }

    // get game round by id
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("gameround/{id}")
    public ResponseEntity<GameRound> getGameRoundById(@PathVariable(value = "id") Long round_id) throws ResourceNotFoundException {
        GameRound gameRound = gameRoundRepository.findById(round_id).orElseThrow(() -> new ResourceNotFoundException("Game Round not found for this id :: " + round_id));

        return ResponseEntity.ok().body(gameRound);
    }

    // save game round
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("gameround")
    public GameRound createGameRound(@RequestBody GameRound gameRound){
        return this.gameRoundRepository.save(gameRound);
    }

    // update game round
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("gameround/{id}")
    public ResponseEntity<GameRound> updateGameRound(@PathVariable(value = "id") Long round_id, @Validated @RequestBody GameRound gameRoundDetails) throws ResourceNotFoundException {
        GameRound gameRound = gameRoundRepository.findById(round_id).orElseThrow(() -> new ResourceNotFoundException("Game Round not found for this id :: " + round_id));

        gameRound.setCategory_id(gameRoundDetails.getCategory_id());
        gameRound.setDate(gameRoundDetails.getDate());

        return ResponseEntity.ok(this.gameRoundRepository.save(gameRound));
    }

    // delete game round
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("gameround/{id}")
    public Map<String, Boolean> deleteGameRound(@PathVariable(value = "id") Long round_id) throws ResourceNotFoundException {
        GameRound gameRound = gameRoundRepository.findById(round_id).orElseThrow(() -> new ResourceNotFoundException("Game Round not found for this id :: " + round_id));

        this.gameRoundRepository.delete(gameRound);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

}
