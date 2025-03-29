package com.scaler.strategy.winningstrategy;

import com.scaler.exception.InvalidDimensionException;
import com.scaler.model.Board;
import com.scaler.model.Move;
import com.scaler.model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy {
    private Map<Symbol, Integer> leftDiagonal;
    private Map<Symbol, Integer> rightDiagonal;

    private DiagonalWinningStrategy() {
        leftDiagonal = new HashMap<>();
        rightDiagonal = new HashMap<>();
    }
    public static class Builder {
        private int dimension;

        private boolean validateDimension() throws InvalidDimensionException{
            if (this.dimension % 2 != 0) {
                return true;
            }
            throw new InvalidDimensionException("Cannot introduce diagonal winning strategy for even sized boards");
        }

        public static Builder getBuilder() {
            return new Builder();
        }

        public Builder setDimension(int dimension) throws InvalidDimensionException {
            this.dimension = dimension;
            validateDimension();
            return this;
        }

        public DiagonalWinningStrategy build() {
            return new DiagonalWinningStrategy();
        }
    }

    public boolean checkWinner(Board board, Move move) {
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

            if (leftDiagonal.get(symbol) == board.getDimension()) {
                return true;
            }
        }
        // right diagonal
        if (row + col == board.getDimension() - 1) {
            if (rightDiagonal.containsKey(symbol)) {
                rightDiagonal.put(symbol, rightDiagonal.get(symbol) + 1);
            } else {
                rightDiagonal.put(symbol, 1);
            }

            if (rightDiagonal.get(symbol) == board.getDimension()) {
                return true;
            }
        }
        return false;
    }
}
