package com.scaler.strategy.winningstrategy;

import com.scaler.model.Board;
import com.scaler.model.Move;
import com.scaler.model.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    private int dimension;
    private List<Map<Symbol, Integer>> allRowMaps;

    public RowWinningStrategy(int dimension) {
        this.dimension = dimension;

        allRowMaps = new ArrayList<>();
        for (int i=0; i<dimension; i++) {
            allRowMaps.add(new HashMap<>());
        }
    }

    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getxPos();
        Symbol symbol = move.getPlayer().getSymbol();

        Map<Symbol, Integer> rowMap = allRowMaps.get(row);
        // check current col
        if (rowMap.containsKey(symbol)) {
            rowMap.put(symbol, rowMap.get(symbol) + 1);
        } else {
            rowMap.put(symbol, 1);
        }
        return rowMap.get(symbol) != dimension;
    }
}
