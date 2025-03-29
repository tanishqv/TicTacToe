package com.scaler.model;

public class Bot extends Player {
    private BotDifficulty botDifficulty;

    public Bot(int id, String name, PlayerType playerType, Symbol symbol, BotDifficulty botDifficulty) {
        super(id, name, playerType, symbol);
        this.botDifficulty = botDifficulty;
    }

    public BotDifficulty getBotDifficulty() {
        return botDifficulty;
    }

    public void setBotDifficulty(BotDifficulty botDifficulty) {
        this.botDifficulty = botDifficulty;
    }
}
