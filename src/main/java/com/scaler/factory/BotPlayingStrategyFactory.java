package com.scaler.factory;

import com.scaler.model.BotDifficulty;
import com.scaler.strategy.botplayingstrategy.BotPlayingStrategy;
import com.scaler.strategy.botplayingstrategy.EasyBotPlayingStrategy;
import com.scaler.strategy.botplayingstrategy.HardBotPlayingStrategy;
import com.scaler.strategy.botplayingstrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficulty botDifficulty) {
        if (botDifficulty.equals(BotDifficulty.EASY)) {
            return new EasyBotPlayingStrategy();
        } else if (botDifficulty.equals(BotDifficulty.HARD)) {
            return new HardBotPlayingStrategy();
        } else if (botDifficulty.equals(BotDifficulty.MEDIUM)) {
            return new MediumBotPlayingStrategy();
        }
        return null;
    }
}
