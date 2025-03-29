package com.scaler.model;

import com.scaler.exception.*;
import com.scaler.strategy.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private int dimension;
    private Board board;
    private List<Move> moves;
    private List<Player> players;
    private GameState gameState;
    private Player winner;
    private int nextPlayerMoveId;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<Player> players, int nextPlayerMoveId, List<WinningStrategy> winningStrategies) {
        this.dimension = dimension;
        this.board = new Board(dimension);
        this.moves = new ArrayList<>();
        this.players = players;
        this.nextPlayerMoveId = nextPlayerMoveId;
        this.winningStrategies = winningStrategies;
        this.gameState = GameState.IN_PROGRESS;
    }

    public void makeMove() {
        Player currentPlayer = players.get(nextPlayerMoveId);
        System.out.println("Current Player: " + currentPlayer.getName() + "(" + currentPlayer.getPlayerType() + ")");
        Move move = currentPlayer.makeMove(board);
        System.out.println("Cell chosen: " + move.getCell().toString());

        int row = move.getCell().getxPos();
        int col = move.getCell().getyPos();

        Cell cellToUpdate = board.getGrid().get(row).get(col);
        cellToUpdate.setPlayer(currentPlayer);
        cellToUpdate.setCellStatus(CellStatus.OCCUPIED);

        Move finalMove = new Move(cellToUpdate, currentPlayer);
        moves.add(finalMove);

        nextPlayerMoveId = (nextPlayerMoveId + 1) % players.size();

        updateStrategyData(finalMove);
        if (checkWinner(finalMove)) {
            winner = currentPlayer;
            gameState = GameState.ENDED;
        } else if (moves.size() == dimension * dimension) {
            gameState = GameState.DRAW;
        }
    }

    public void display() {
        board.printGrid();
    }

    public void updateStrategyData(Move move) {
        System.out.println("Updating data..");
        for (WinningStrategy winningStrategy: winningStrategies) {
            winningStrategy.handleNewMove(board, move);
        }
    }

    public boolean checkWinner(Move move) {
        System.out.println("Checking winner..");
        for (WinningStrategy winningStrategy: winningStrategies) {
            if (winningStrategy.checkWinner(board, move)) {
                return true;
            }
        }
        return false;
    }

    public void undo() throws InvalidUndoOperation {
        if (moves.isEmpty()) {
            throw new InvalidUndoOperation("Cannot perform undo on an empty board");
        }

        // Workaround: mark winner as null, revert game state to IN_PROGRESS
        winner = null;
        gameState = GameState.IN_PROGRESS;

        // find last move
        // update the cell as Empty
        Move lastMove = moves.removeLast();
        Cell cell = lastMove.getCell();
        cell.setCellStatus(CellStatus.EMPTY);
        cell.setPlayer(null);

        // remove the symbol from the winning strategies
        for (WinningStrategy winningStrategy: winningStrategies) {
            winningStrategy.handleUndo(board, lastMove);
        }

        // move the chance back to same player
        nextPlayerMoveId = (nextPlayerMoveId - 1 + players.size()) % players.size();
    }

    public static class Builder {
        private int dimension;
        private int nextPlayerMoveId;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public static Builder getBuilder() {
            return new Builder();
        }
        public Game build() throws InvalidBotCountException, InvalidPlayerCountException, SymbolNotAssignedException {
            validate();
            return new Game(dimension, players, nextPlayerMoveId, winningStrategies);
        }

        private void validate() throws InvalidBotCountException, InvalidPlayerCountException, SymbolNotAssignedException {
            checkBotCount();
            checkPlayerCount();
            checkUniqueSymbols();
        }

        private void checkBotCount() throws InvalidBotCountException {
            int botCount = 0;
            for (Player p: players) {
                if (p.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }
            if (botCount > 1) {
                throw new InvalidBotCountException("Maximum count of allowed bots is 1");
            }
        }

        private void checkPlayerCount() throws InvalidPlayerCountException {
            if (players.size() >= dimension) {
                throw new InvalidPlayerCountException("Maximum count of allowed players is "
                        + (dimension-1)
                        + " for board of size " + dimension
                );
            }
        }

        private void checkUniqueSymbols() throws DuplicateSymbolException, SymbolNotAssignedException {
            Set<Symbol> symbolSet = new HashSet<>();
            for (Player p: players) {
                if (symbolSet.contains(p.getSymbol())) {
                    throw new DuplicateSymbolException("Duplicate symbol found " + "\"" + p.getSymbol() + "\"");
                } else {
                    symbolSet.add(p.getSymbol());
                }
            }
            if (symbolSet.size() != players.size()) {
                throw new SymbolNotAssignedException("All players must be assigned a symbol");
            }
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setNextPlayerMoveId(int nextPlayerMoveId) {
            this.nextPlayerMoveId = nextPlayerMoveId;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
    }

    public int getDimension() {
        return dimension;
    }

    public Board getBoard() {
        return board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public int getNextPlayerMoveId() {
        return nextPlayerMoveId;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }
}
