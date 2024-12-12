package org.example.Service;

import org.example.Model.Card;
import org.example.Model.Game;
import org.example.Model.Player;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
 *  @Author kermit
 *  @Date 2024/12/12 15:32
 *
 */
public class GameService {

    // 初始化游戏
    public Game buildGame(int playersCount, int cardsCount, int outCardsCount, int coins) {
        // 确保初始参数合法
        if (playersCount <= 0) {
            throw new IllegalArgumentException("非法玩家数量！");
        }
        if (cardsCount <= 0) {
            throw new IllegalArgumentException("非法牌库数量！");
        }
        if (coins <= 0) {
            throw new IllegalArgumentException("非法硬币数量！");
        }

        // 初始化玩家列表，发放金币
        PlayerService playerService = new PlayerService();
        List<Player> playerList = playerService.buildPlayerList(playersCount, coins);

        // 初始化本局牌库
        CardService cardService = new CardService();
        // 构建总牌库
        List<Card> cardList = cardService.buildCardList(cardsCount);
        // 抽牌形成本局牌库
        List<Card> presentCardList = cardService.buildPresentCardList(cardList, outCardsCount);

        // 初始化游戏
        Game game = new Game(playerList, presentCardList, 0, playersCount);

        // 返回初始化后的游戏对象
        return game;
    }

    // 结束游戏并结算
    public List<Player> endGame(Game game) {
        // 计算所有玩家分数
        PlayerService playerService = new PlayerService();
        List<Player> playerList = game.getPlayerList();
        // 遍历所有玩家
        for (int i = 0; i < playerList.size(); i++) {
            // 计算当前玩家分数
            playerService.calculateScore(playerList.get(i));
        }

        // 根据分数对所有玩家进行排序，得分低者在前，得分高者在后
        Collections.sort(playerList, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p1.getScore(), p2.getScore());
            }
        });

        // 返回最终排序过的玩家列表
        return playerList;
    }

    // 运行游戏
    public void runGame(Game game) {
        // 判断当前是否有轮转牌
        if (game.getNowCard() == null) {
            // 当前无轮转牌，结束游戏
            // 在命令行中展示游戏结果
            displayResults(endGame(game));
            return;
        }

        // 获取next玩家
        Player player = game.getPlayerList().get(game.getNext());
        // 让next玩家执行操作
        PlayerService playerService = new PlayerService();
        // 提示当前轮转牌
        System.out.println("Now Card is: " + game.getNowCard().getValue());
        // 提示next玩家
        System.out.println("Player " + player.getName() + "(" + player.getCoins() + "coins)" + " make choice now:");
        // next玩家执行操作
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        // 判断next玩家操作
        int operation = playerService.checkCard(game.getNowCard(), player, choice);
        if (operation == 1) {
            // next玩家过牌
            // 改变next值
            findNext(game);
            // 该牌继续轮转，不改变轮转牌
            // 递归执行
            runGame(game);
        } else if (operation == 0) {
            // next玩家拿牌
            // 改变next值
            findNext(game);
            // 该牌停止轮转，改变轮转牌
            changeNowCard(game);
            // 递归执行
            runGame(game);
        } else {
            throw new IllegalArgumentException("玩家操作异常！");
        }

    }

    // 设置next值
    public void findNext(Game game) {
        // 获取当前next值
        int next = game.getNext();
        // 获取总座位数
        int seats = game.getSeatsCount();
        game.setNext((next + 1) % seats);
    }

    // 改变轮转牌
    public void changeNowCard(Game game) {
        // 获取当前牌库
        List<Card> cardList = game.getPresentCardList();

        // 检查当前牌库是否为空
        if (!cardList.isEmpty()) {
            // 当前牌库非空，将最后一张牌设置为轮转牌
            game.setNowCard(cardList.get(cardList.size() - 1));
            // 将该牌从牌库中删除
            cardList.remove(cardList.size() - 1);
        } else {
            // 当前牌库为空，置为空值
            game.setNowCard(null);
        }
    }

    // 游戏结果展示
    public void displayResults(List<Player> playerList) {
        // 遍历该列表，从第一位到最后一位依次输出
        for (int i = 0; i < playerList.size(); i++) {
            System.out.println("Rank #" + i + " - Player:" + playerList.get(i).getName() + " Score:" + playerList.get(i).getScore());
        }
    }
}
