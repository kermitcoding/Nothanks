package org.example.Model;

import java.util.List;

/*
 *  @Author kermit
 *  @Date 2024/12/12 15:27
 *
 */
public class Game {
    // 玩家列表
    private List<Player> playerList;

    // 本局游戏牌库（抽牌后）
    private List<Card> presentCardList;


    // 下一操作玩家
    private int next;

    // 总座位数
    private int seatsCount;

    // 当前轮转牌
    private Card nowCard;

    public Game(List<Player> playerList, List<Card> presentCardList, int next, int seatsCount, Card nowCard) {
        this.playerList = playerList;
        this.presentCardList = presentCardList;
        this.next = next;
        this.seatsCount = seatsCount;
        this.nowCard = nowCard;
    }

    public Game(List<Player> playerList, List<Card> presentCardList, int next, int seatsCount) {
        this.playerList = playerList;
        this.presentCardList = presentCardList;
        this.next = next;
        this.seatsCount = seatsCount;
        // 初始轮转牌为牌库最后一张牌
        this.nowCard = presentCardList.get(presentCardList.size() - 1);
        presentCardList.remove(presentCardList.size() - 1);
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Card> getPresentCardList() {
        return presentCardList;
    }

    public void setPresentCardList(List<Card> presentCardList) {
        this.presentCardList = presentCardList;
    }


    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }

    public Card getNowCard() {
        return nowCard;
    }

    public void setNowCard(Card nowCard) {
        this.nowCard = nowCard;
    }
}
