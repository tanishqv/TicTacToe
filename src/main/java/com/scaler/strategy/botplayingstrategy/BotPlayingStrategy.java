package com.scaler.strategy.botplayingstrategy;

import com.scaler.model.Board;
import com.scaler.model.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
