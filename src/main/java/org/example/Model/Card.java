package org.example.Model;

/*
 *  @Author kermit
 *  @Date 2024/12/12 15:22
 *
 */
public class Card {
    // 牌ID
    private int id;

    // 牌的数值
    private int value;
    
    // 当前牌上的硬币数量
    private int coins;

    public Card(int id, int value, int coins) {
        this.id = id;
        this.value = value;
        this.coins = coins;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }


}
