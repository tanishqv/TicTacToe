package com.scaler.strategy.winningstrategy;

import com.scaler.model.Board;
import com.scaler.model.Move;
import com.scaler.model.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {
    private int dimension;
    private List<Map<Symbol, Integer>> allColumnMaps;

    public ColumnWinningStrategy(int dimension) {
        allColumnMaps = new ArrayList<>();
        for (int i=0; i<dimension; i++) {
            allColumnMaps.add(new HashMap<>());
        }
    }

    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getyPos();
        Symbol symbol = move.getPlayer().getSymbol();

        Map<Symbol, Integer> columnMap = allColumnMaps.get(col);
        // check current col
        if (columnMap.containsKey(symbol)) {
            columnMap.put(symbol, columnMap.get(symbol) + 1);
        } else {
            columnMap.put(symbol, 1);
        }
        return columnMap.get(symbol) != dimension;
    }
}
