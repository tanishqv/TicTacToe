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

    public void handleNewMove(Board board, Move move) {
        int row = move.getCell().getxPos();
        Symbol symbol = move.getPlayer().getSymbol();

        Map<Symbol, Integer> rowMap = allRowMaps.get(row);
        // check current row
        if (rowMap.containsKey(symbol)) {
            rowMap.put(symbol, rowMap.get(symbol) + 1);
        } else {
            rowMap.put(symbol, 1);
        }
    }

    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getxPos();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol, Integer> rowMap = allRowMaps.get(row);

        if (rowMap.get(symbol) == dimension) {
            System.out.println("Winner declared through row " + row);
            return true;
        }
        return false;
    }

    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getxPos();
        Symbol symbol = move.getPlayer().getSymbol();

        Map<Symbol, Integer> rowMap = allRowMaps.get(row);
        rowMap.put(symbol, rowMap.get(symbol) - 1);
    }
}
