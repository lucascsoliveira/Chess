/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp;

import br.ifes.poo2.chess.cln.cdp.pieces.Color;
import br.ifes.poo2.chess.cln.cdp.pieces.Piece;
import br.ifes.poo2.chess.cln.cdp.players.Player;
import br.ifes.poo2.chess.util.reuse.Model;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Adelson
 */
public class Game {

    private final ChessBoard chessBoard;
    private Calendar startTime;
    private Calendar endTime;
    private final Map<Color, Player> players;
    private Player winner;

    private boolean gameOver;

    private Color turn;

    public Game(Player player1, Player player2) {
        chessBoard = new ChessBoard();
        chessBoard.setup();
        startTime = Calendar.getInstance();
        //endTime
        players = new HashMap<Color, Player>();
        players.put(Color.WHITE, player1);
        players.put(Color.BLACK, player2);
        //winner
        turn = Color.WHITE;
    }

    public int getPoints(Color color) {
        int points = 0;
        Iterator iterator = chessBoard.getCapturedPieces();

        while (iterator.hasNext()) {
            Piece piece = (Piece) iterator.next();

            if (!piece.getColor().equals(color)) {
                points += piece.getPoints();
            }
        }

        return points;
    }

    public Iterator getPiecesOfPlayer(Color color) {
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        Iterator iterator = chessBoard.getInGamePieces();

        while (iterator.hasNext()) {
            Piece piece = (Piece) iterator.next();
            if (piece.getColor().equals(color)) {
                pieces.add(piece);
            }
        }

        return pieces.iterator();
    }

    public Iterator getCapturedPiecesByPlayer(Color color) {
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        Iterator iterator = chessBoard.getCapturedPieces();

        while (iterator.hasNext()) {
            Piece piece = (Piece) iterator.next();
            if (!piece.getColor().equals(color)) {
                pieces.add(piece);
            }
        }

        return pieces.iterator();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public Player getWinner() {
        return winner;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getPlayer(Color color) {
        return players.get(color);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void nextTurn() {
        if (turn.equals(Color.BLACK)) {
            turn = Color.WHITE;
        } else {
            turn = Color.BLACK;
        }
    }

    public Color getTurn() {
        return turn;
    }

    public void draw() {
        endTime = Calendar.getInstance();

        players.get(Color.BLACK).addDraw();
        players.get(Color.WHITE).addDraw();

        gameOver = true;

    }

    public void giveUp(Color turn) {
        endTime = Calendar.getInstance();

        if (turn.equals(Color.BLACK)) {
            players.get(Color.BLACK).addDefeat();
            players.get(Color.WHITE).addVictory();
        } else {
            players.get(Color.BLACK).addVictory();
            players.get(Color.WHITE).addDefeat();
        }

        gameOver = true;
    }

    public void stop() {
        gameOver = true;
    }

    public void restart() {
        gameOver = false;
    }
}
