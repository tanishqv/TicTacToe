package com.scaler.model;

public class Cell {
    private int xPos;
    private int yPos;
    private CellStatus cellStatus;
    private Player player;

    public Cell(int xPos, int yPos, CellStatus cellStatus) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.cellStatus = cellStatus;
    }

    public void printCell() {
        if (cellStatus.equals(CellStatus.EMPTY)) {
            System.out.print("| |");
        } else {
            System.out.print("|" + player.getSymbol().getSign() + "|");
        }
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "yPos=" + yPos +
                ", xPos=" + xPos +
                '}';
    }
}
