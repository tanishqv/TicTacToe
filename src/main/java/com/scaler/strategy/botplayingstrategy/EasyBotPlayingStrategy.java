package com.scaler.strategy.botplayingstrategy;

import com.scaler.model.Board;
import com.scaler.model.Cell;
import com.scaler.model.CellStatus;
import com.scaler.model.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    public Move makeMove(Board board) {
        for (List<Cell> row: board.getGrid()) {
            for (Cell cell: row) {
                if (cell.getCellStatus().equals(CellStatus.EMPTY)) {
                    return new Move(cell, null);
                }
            }
        }
        return null;
    }
}
