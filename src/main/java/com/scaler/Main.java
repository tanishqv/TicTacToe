package com.scaler;

import com.scaler.controller.GameController;
import com.scaler.exception.InvalidBotCountException;
import com.scaler.model.*;
import com.scaler.strategy.winningstrategy.ColumnWinningStrategy;
import com.scaler.strategy.winningstrategy.DiagonalWinningStrategy;
import com.scaler.strategy.winningstrategy.RowWinningStrategy;
import com.scaler.strategy.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidBotCountException {

        Scanner scn = new Scanner(System.in);
        // creates the players, dimension, winningStrategies, nextPlayerMoveIndex
        // creates a gamecontroller to control the game
        int dimension = 3;
        List<Player> players = new ArrayList<>();
        players.add(
                new Bot(1, "P1", PlayerType.BOT, new Symbol('X', null), BotDifficulty.EASY)
        );
        players.add(
                new Player(2, "P2", PlayerType.HUMAN, new Symbol('O', null))
        );
        int nextPlayerMoveIndex = 1;

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy(dimension));
        winningStrategies.add(new ColumnWinningStrategy(dimension));
        winningStrategies.add(new DiagonalWinningStrategy());

        GameController gameController = new GameController();
        Game game = gameController.startGame(
                dimension,
                players,
                nextPlayerMoveIndex,
                winningStrategies
        );

        while (game.getGameState().equals(GameState.IN_PROGRESS)) {
            // displayGame
            gameController.displayGame(game);
            // makeMove
            gameController.makeMove(game);
            // undo
            System.out.print("Do you want to undo?(Y/N): ");
            String undoConfirmation = scn.next();
            if (undoConfirmation.equalsIgnoreCase("y") || undoConfirmation.equals("1")) {
                gameController.undo(game);
            }
        }
        gameController.displayGame(game);
        System.out.println("Winner: " + game.getWinner());
    }
}
