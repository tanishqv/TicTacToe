package com.scaler.strategy.winningstrategy;

import com.scaler.model.Board;
import com.scaler.model.Move;
import com.scaler.model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy {
    private Map<Symbol, Integer> leftDiagonal;
    private Map<Symbol, Integer> rightDiagonal;

    public DiagonalWinningStrategy() {
        leftDiagonal = new HashMap<>();
        rightDiagonal = new HashMap<>();
    }

    public void handleNewMove(Board board, Move move) {
        int row = move.getCell().getxPos();
        int col = move.getCell().getyPos();
        Symbol symbol = move.getPlayer().getSymbol();

        // left diagonal
        if (row == col) {
            if (leftDiagonal.containsKey(symbol)) {
                leftDiagonal.put(symbol, leftDiagonal.get(symbol) + 1);
            } else {
                leftDiagonal.put(symbol, 1);
            }
        }
        // right diagonal
        if (row + col == board.getDimension() - 1) {
            if (rightDiagonal.containsKey(symbol)) {
                rightDiagonal.put(symbol, rightDiagonal.get(symbol) + 1);
            } else {
                rightDiagonal.put(symbol, 1);
            }
        }
    }

    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getxPos();
        int col = move.getCell().getyPos();
        Symbol symbol = move.getPlayer().getSymbol();

        // left diagonal
        if (row == col) {
            if (leftDiagonal.get(symbol) == board.getDimension()) {
                System.out.println("Winner declared through left diagonal");
                return true;
            }
        }
        // right diagonal
        if (row + col == board.getDimension() - 1) {
            if (rightDiagonal.get(symbol) == board.getDimension()) {
                System.out.println("Winner declared through right diagonal");
                return true;
            }
        }
        return false;
    }

    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getxPos();
        int col = move.getCell().getyPos();
        Symbol symbol = move.getPlayer().getSymbol();

        // left diagonal
        if (row == col) {
            leftDiagonal.put(symbol, leftDiagonal.get(symbol) - 1);
        }
        // right diagonal
        if (row + col == board.getDimension() - 1) {
            rightDiagonal.put(symbol, rightDiagonal.get(symbol) - 1);
        }
    }
}
