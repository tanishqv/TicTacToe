package com.scaler.strategy.winningstrategy;

import com.scaler.model.Board;
import com.scaler.model.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
    void handleUndo(Board board, Move move);
    void handleNewMove(Board board, Move move);
}
