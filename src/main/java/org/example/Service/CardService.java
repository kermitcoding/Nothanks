package org.example.Service;

import org.example.Model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 *  @Author kermit
 *  @Date 2024/12/12 16:06
 *
 */
public class CardService {
    // 初始化牌库
    public List<Card> buildCardList(int cardsCount) {
        // 初始化一个列表存储Card
        List<Card> cardList = new ArrayList<>();

        // 生成牌，并将其添加至牌库中
        for (int i = 0; i < cardsCount; i++) {
            Card card = new Card(i, i + 1, 0);
            cardList.add(card);
        }

        // 返回牌库
        return cardList;
    }

    // 随机抽牌，构建本局牌库
    public List<Card> buildPresentCardList(List<Card> cardList, int outCardsCount) {
        // 初始化一个列表存储抽取的Card
        List<Card> presentCardList = new ArrayList<>();

        // 确保抽取数量不超过总牌库数量
        if (outCardsCount >= cardList.size()) {
            throw new IllegalArgumentException("抽取数量大于等于牌库总数！");
        }

        // 打乱cardList中的牌
        Collections.shuffle(cardList);

        // 将打乱后剩余的牌添加至列表
        presentCardList.addAll(cardList.subList(0, cardList.size() - outCardsCount));

        // 再次打乱
        Collections.shuffle(presentCardList);

        // 返回抽取后的本局牌库
        return presentCardList;
    }
}
