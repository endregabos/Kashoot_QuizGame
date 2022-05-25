package com.project.kashoot.model;

import javax.persistence.*;

@Entity
@Table(name = "player_games")
public class PlayerGames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long game_id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "round_id", referencedColumnName = "round_id")
    private GameRound round_id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @Column(name = "gained_points", nullable = false)
    private long gained_points;

    public PlayerGames() {
        super();
    }

    public PlayerGames(GameRound round_id, User user_id, long gained_points) {
        this.round_id = round_id;
        this.userId = user_id;
        this.gained_points = gained_points;
    }

    public long getGame_id() {
        return game_id;
    }

    public void setGame_id(long game_id) {
        this.game_id = game_id;
    }

    public GameRound getRound_id() {
        return round_id;
    }

    public void setRound_id(GameRound round_id) {
        this.round_id = round_id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User user_id) {
        this.userId = user_id;
    }

    public long getGained_points() {
        return gained_points;
    }

    public void setGained_points(long gained_points) {
        this.gained_points = gained_points;
    }

    @Override
    public String toString() {
        return "Player Games [game_id=" + game_id + ", round_id=" + round_id + ", user_id=" + userId.getUser_id() + ", gained_points=" + gained_points + "]";
    }
}
