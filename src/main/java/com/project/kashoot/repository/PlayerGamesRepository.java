package com.project.kashoot.repository;

import com.project.kashoot.model.PlayerGames;
import com.project.kashoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerGamesRepository extends JpaRepository<PlayerGames, Long> {
    List<PlayerGames> findPlayerGamesByUserId(User userId);
}
