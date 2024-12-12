package org.example.Service;

import org.example.Model.Card;
import org.example.Model.Player;

import java.util.ArrayList;
import java.util.List;

/*
 *  @Author kermit
 *  @Date 2024/12/12 15:35
 *
 */
public class PlayerService {
    // 初始化玩家
    public List<Player> buildPlayerList(int playersCount, int coins) {
        // 初始化一个列表存储Player
        List<Player> playerList = new ArrayList<>();

        // 生成玩家，并将其添加至玩家列表
        for (int i = 0; i < playersCount; i++) {
            // 初始化玩家名称
            String name = String.format("Player %d", i + 1);
            Player player = new Player(i, name, coins, i);
            playerList.add(player);
        }

        // 返回玩家列表
        return playerList;
    }

    // 计算玩家分数
    public void calculateScore(Player player) {
        // 获取玩家当前所有手牌（默认手牌已按照value从大大小排）
        List<Card> handCards = player.getHandCards();

        // 若玩家手牌数为0，其得分为其所剩硬币数
        if (handCards.size() == 0) {
            player.setScore(player.getCoins());
        } else {
            // 若玩家手牌数不为0，先计算其手牌和
            // 设置一个值记录手牌和
            int sum = 0;
            // 先处理第一张手牌，将其值标记为flag，并计入总和
            int flag = handCards.get(0).getValue();
            sum += flag;
            // 从第二张手牌开始遍历所有手牌
            for (int i = 1; i < handCards.size(); i++) {
                // 判定当前手牌是否与flag连续
                if (handCards.get(i).getValue() - flag == 1) {
                    // 当前手牌与flag连续，则当前手牌不计入总和，flag值加1
                    flag++;
                } else {
                    // 当前手牌与flag不连续，则当前手牌计入总和，flag置为当前值
                    flag = handCards.get(i).getValue();
                    sum += flag;
                }
            }

            // 将玩家手牌和减去玩家硬币数，得到玩家得分
            player.setScore(sum - player.getCoins());
        }
    }

    // 玩家操作当前轮转牌
    public int checkCard(Card card, Player player, int choice) {
        // 判断玩家是否仍有硬币
        if (player.getCoins() > 0) {
            // 玩家仍有硬币
            if (choice == 1) {
                // 玩家选择过牌
                return passCard(player, card);
            } else if (choice == 0) {
                // 玩家选择拿走该牌
                return getCard(player, card);
            } else {
                throw new IllegalArgumentException("玩家选择异常！");
            }
        } else if (player.getCoins() == 0) {
            // 玩家已无硬币，只能拿走该牌
            System.out.println("You have zero coins, you have to take it.");
            return getCard(player, card);
        } else {
            throw new IllegalArgumentException("玩家硬币余额异常！");
        }
    }

    // 玩家选择过牌
    public int passCard(Player player, Card card) {
        // 玩家支付一枚硬币
        player.setCoins(player.getCoins() - 1);
        // 该牌上增加一枚硬币
        card.setCoins(card.getCoins() + 1);
        return 1;
    }

    // 玩家选择拿牌
    public int getCard(Player player, Card card) {
        // 玩家取走该牌上的硬币
        player.setCoins(player.getCoins() + card.getCoins());
        card.setCoins(0);
        // 将该牌加入手牌
        player.getHandCards().add(card);
        return 0;
    }
}
