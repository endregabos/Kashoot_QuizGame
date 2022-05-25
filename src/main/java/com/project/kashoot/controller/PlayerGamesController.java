package com.project.kashoot.controller;


import com.project.kashoot.exception.ResourceNotFoundException;
import com.project.kashoot.model.PlayerGames;
import com.project.kashoot.model.User;
import com.project.kashoot.repository.PlayerGamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class PlayerGamesController {
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private PlayerGamesRepository playerGamesRepository;

    public PlayerGamesController(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    //get all players game
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("playergames")
    public List<PlayerGames> getAllPlayerGames(){
        return this.playerGamesRepository.findAll();
    }

    // get player game by id
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("playergames/{id}")
    public ResponseEntity<PlayerGames> getPlayerGamesById(@PathVariable(value = "id") Long game_id) throws ResourceNotFoundException {
        PlayerGames playerGames = playerGamesRepository.findById(game_id).orElseThrow(() -> new ResourceNotFoundException("Player Game not found for this id :: " + game_id));

        return ResponseEntity.ok().body(playerGames);
    }

    // save player game
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("playergames")
    public PlayerGames createPlayerGames(@RequestBody PlayerGames playerGames){
        simpMessagingTemplate.convertAndSend("/websockets", "Cineva a terminat un joc.");

        return this.playerGamesRepository.save(playerGames);
    }

    // update player game
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("playergames/{id}")
    public ResponseEntity<PlayerGames> updatePlayerGames(@PathVariable(value = "id") Long game_id, @Validated @RequestBody PlayerGames playerGamesDetails) throws ResourceNotFoundException {
        PlayerGames playerGames = playerGamesRepository.findById(game_id).orElseThrow(() -> new ResourceNotFoundException("Player Game not found for this id :: " + game_id));

        playerGames.setRound_id(playerGamesDetails.getRound_id());
        playerGames.setUserId(playerGamesDetails.getUserId());
        playerGames.setGained_points(playerGamesDetails.getGained_points());

        return ResponseEntity.ok(this.playerGamesRepository.save(playerGames));
    }

    // delete player game
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("playergames/{id}")
    public Map<String, Boolean> deletePlayerGames(@PathVariable(value = "id") Long game_id) throws ResourceNotFoundException {
        PlayerGames playerGames = playerGamesRepository.findById(game_id).orElseThrow(() -> new ResourceNotFoundException("Player Game not found for this id :: " + game_id));

        this.playerGamesRepository.delete(playerGames);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    // get games by user id
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("playergames/user/{userId}")
    public List<PlayerGames> getPlayerGamesByUser(@PathVariable(value = "userId") User userId){
        return this.playerGamesRepository.findPlayerGamesByUserId(userId);
    }

    @MessageMapping("/notification2")
    @SendTo("/websockets")
    public String sendNotification(String message) throws Exception {
        return message;
    }
}
