package org.example.Model;

import java.util.ArrayList;
import java.util.List;

/*
 *  @Author kermit
 *  @Date 2024/12/12 15:28
 *
 */
public class Player {
    // 玩家ID
    private int id;

    // 玩家昵称
    private String name;

    // 玩家当前硬币数
    private int coins;

    // 玩家当前手牌
    private List<Card> handCards;

    // 玩家分数
    private int score;

    // 玩家位置
    private int seat;

    public Player(int id, String name, int coins, List<Card> handCards, int score, int seat) {
        this.id = id;
        this.name = name;
        this.coins = coins;
        this.handCards = handCards;
        this.score = score;
        this.seat = seat;
    }


    public Player(int id, String name, int coins, int seat) {
        this.id = id;
        this.name = name;
        this.coins = coins;
        this.handCards = new ArrayList<>();
        this.score = 0;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
