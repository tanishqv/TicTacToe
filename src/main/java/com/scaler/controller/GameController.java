package com.scaler.controller;

import com.scaler.exception.InvalidBotCountException;
import com.scaler.exception.InvalidPlayerCountException;
import com.scaler.exception.SymbolNotAssignedException;
import com.scaler.model.Game;
import com.scaler.model.Player;
import com.scaler.strategy.winningstrategy.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players, int nextPlayerMoveId, List<WinningStrategy> winningStrategies) throws InvalidBotCountException, InvalidPlayerCountException, SymbolNotAssignedException {
        return Game.Builder.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setNextPlayerMoveId(nextPlayerMoveId)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void displayGame(Game game) {
        game.display();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void undo(Game game) {
        game.undo();
    }
}
