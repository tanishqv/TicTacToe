package com.scaler;

import com.scaler.controller.GameController;
import com.scaler.exception.InvalidBotCountException;
import com.scaler.exception.InvalidDimensionException;
import com.scaler.model.*;
import com.scaler.strategy.winningstrategy.ColumnWinningStrategy;
import com.scaler.strategy.winningstrategy.DiagonalWinningStrategy;
import com.scaler.strategy.winningstrategy.RowWinningStrategy;
import com.scaler.strategy.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidBotCountException, InvalidDimensionException {

        // creates the players, dimension, winningStrategies, nextPlayerMoveIndex
        // creates a gamecontroller to control the game
        int dimension = 3;
        List<Player> players = new ArrayList<>();
        players.add(
                new Player(1, "P1", PlayerType.BOT, new Symbol('X', null))
        );
        players.add(
                new Player(2, "P2", PlayerType.HUMAN, new Symbol('O', null))
        );
        int nextPlayerMoveIndex = 2;

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy(dimension));
        winningStrategies.add(new ColumnWinningStrategy(dimension));
        winningStrategies.add(DiagonalWinningStrategy.Builder.getBuilder()
                                .setDimension(dimension)
                                .build());

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
        }
        gameController.displayGame(game);
        System.out.println(game.getWinner());
    }
}
