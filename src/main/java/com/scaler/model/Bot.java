package com.scaler.model;

import com.scaler.exception.InvalidMoveException;
import com.scaler.factory.BotPlayingStrategyFactory;
import com.scaler.strategy.botplayingstrategy.BotPlayingStrategy;

public class Bot extends Player {
    private BotDifficulty botDifficulty;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(int id, String name, PlayerType playerType, Symbol symbol, BotDifficulty botDifficulty) {
        super(id, name, playerType, symbol);
        this.botDifficulty = botDifficulty;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficulty);
    }

    @Override
    public Move makeMove(Board board) throws InvalidMoveException {
        Move move = botPlayingStrategy.makeMove(board);
        move.setPlayer(this);
        return move;
    }

    public BotDifficulty getBotDifficulty() {
        return botDifficulty;
    }

    public void setBotDifficulty(BotDifficulty botDifficulty) {
        this.botDifficulty = botDifficulty;
    }
}
