package com.scaler.model;

public class Cell {
    private int xPos;
    private int yPos;
    private CellStatus cellStatus;
    private Player player;

    public Cell(CellStatus cellStatus, int yPos, int xPos) {
        this.cellStatus = cellStatus;
        this.yPos = yPos;
        this.xPos = xPos;
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
}
