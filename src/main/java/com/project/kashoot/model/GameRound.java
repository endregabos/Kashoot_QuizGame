package com.project.kashoot.model;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "game_round")
public class GameRound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long round_id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category_id;

    @Column(name = "date", nullable = false)
    private Date date;

    public GameRound() {
        super();
    }

    public GameRound(Category category_id, Date date) {
        this.category_id = category_id;
        this.date = date;
    }

    public long getRound_id() {
        return round_id;
    }

    public void setRound_id(long round_id) {
        this.round_id = round_id;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "GameRound [round_id=" + round_id + ", category_id=" + category_id.getCategory_id() + ", date=" + date + " ]";
    }
}
