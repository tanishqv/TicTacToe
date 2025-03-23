package com.scaler;

import com.scaler.controller.GameController;
import com.scaler.exception.InvalidBotCountException;
import com.scaler.model.*;
import com.scaler.strategy.winningstrategy.ColumnWinningStrategy;
import com.scaler.strategy.winningstrategy.DiagonalWinningStrategy;
import com.scaler.strategy.winningstrategy.RowWinningStrategy;
import com.scaler.strategy.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidBotCountException {

        // creates the players, dimension, winningStrategies, nextPlayerMoveIndex
        // creates a gamecontroller to control the game
        int dimension = 3;
        List<Player> players = new ArrayList<>();
        players.add(
                new Player(1, "P1", PlayerType.BOT, new Symbol('X', null))
        );
        players.add(
                new Player(2, "P2", PlayerType.HUMAN, new Symbol('O', null))
        );
        int nextPlayerMoveIndex = 2;

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        GameController gameController = new GameController();
        Game game = gameController.startGame(
                dimension,
                players,
                nextPlayerMoveIndex,
                winningStrategies
        );

        while (game.getGameState().equals(GameState.IN_PROGRESS)) {
            // displayGame
            // makeMove
            // playerChange
            // undo
        }
    }
}
/*
    Client

    GameController
     -dimension
     -players
     -winningStrategies
     -nextPlayerMoveId
     +buildGame(dimension, players, winningStrategies, nextPlayerMoveId)
     +printGame()
     +undo()
     +makeMove(Player, Cell)


    Game
     -Grid
     -Moves
     -size
     -Players
     -GameStatus
     -winner
     -nextMoveId
     -winningStrategies

     +makeMove(Player, Cell)

    Grid
     -List<List<Cell>> grid
     -dimension

    Cell
     -i
     -j
     -Player
     -CellStatus

    CellStatus
     EMPTY,
     OCCUPIED

    Player
     -id
     -name
     -Symbol
     -PType

    Bot
     -difficulty

    Difficulty
     EASY,
     MED,
     HARD

    GameState
     INPROGRESS,
     ENDED,
     DRAW

    Symbol
     -char

    PType
     HUMAN,
     BOT

    Move
     -Cell
     -Player

    <<WinningStrategy>>

    RowWinningStrategy
    ColWinningStrategy
    DiagWinningStrategy

 */

/*
    Controller: startGame(), makeMove(), reset(), nextPlayer, winningStrategies, dimension, printGame(), undo()

    Game
    -board
    -dimension
    -players
    -winner
    -nextPlayer
    -winningStrategies
    -gameStatus
    -moves
    +makeMove()

    Board
    -cell[][]
    -dimension

    Player
    -name
    -type
    -symbol

    Bot <- Player
    -botDifficulty

    Type: HUMAN, BOT

    BotDifficulty: EASY, MED, HARD

    Symbol
    -char

    <<WinningStrategy>>: RowWinningStrategy, ColWinningStrategy, DiagWinningStrategy

    GameStatus: INPROGRESS,DRAW,ENDED

    Cell
    -x
    -y
    -player
    -cellStatus

    CellStatus: OCCUPIED, FREE
 */