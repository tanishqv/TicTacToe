package com.scaler.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> grid;
    private int dimension;

    public Board(int dimension) {
        this.dimension = dimension;
        grid = new ArrayList<>();
        for (int i=0; i<dimension; i++) {
            this.grid.add(new ArrayList<>());
            for (int j=0; j<dimension; j++) {
                grid.get(i).add(new Cell(i, j, CellStatus.EMPTY));
            }
        }
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
}
