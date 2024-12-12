package org.example;


import org.example.Model.Game;
import org.example.Service.GameService;

/*
 *  @Author kermit
 *  @Date 2024/12/12 15:20
 *
 */
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        GameService gameService = new GameService();
        Game game = gameService.buildGame(3, 10, 3, 4);
        System.out.println("Game Start! 0 for get, 1 for pass.");
        // 输出当前卡池
        for (int i = 0; i < game.getPresentCardList().size(); i++) {
            System.out.println(game.getPresentCardList().get(i).getValue());
        }
        gameService.runGame(game);
    }
}