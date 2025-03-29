package com.scaler.model;

import com.scaler.exception.InvalidMoveException;

import java.util.Scanner;

public class Player {
    private int id;
    private String name;
    private PlayerType playerType;
    private Symbol symbol;
    private final Scanner scn = new Scanner(System.in);

    public Player(int id, String name, PlayerType playerType, Symbol symbol) {
        this.id = id;
        this.name = name;
        this.playerType = playerType;
        this.symbol = symbol;
    }

    public Move makeMove(Board board) throws InvalidMoveException {
        System.out.println("Enter row number (0-indexed): ");
        int row = scn.nextInt();
        System.out.println("Enter col number (0-indexed): ");
        int col = scn.nextInt();

        if (board.validateMove(row, col)) {
            return new Move(new Cell(row, col, CellStatus.EMPTY), this);
        }
        throw new InvalidMoveException("Move is invalid");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}
