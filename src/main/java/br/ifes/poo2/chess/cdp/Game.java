/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp;

import br.ifes.poo2.chess.cdp.pieces.Color;
import br.ifes.poo2.chess.cdp.pieces.Piece;
import br.ifes.poo2.chess.cdp.players.Player;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Adelson
 */
public class Game {

    private ChessBoard chessBoard;
    private Calendar startTime;
    private Calendar endTime;
    private Map<Color, Player> players;
    private Player winner;

    private Color turn;
    //TODO: [DICA] Implementar o EnPassant usando uma classe chamada LastMove que armazena o nome da peça a posição atual e a posição anterior; Assim quando uma jogada atender todos os requisitos do movimento temos como saber se o peão moveu ou não 2 casas;
    //TODO: Implementar construtor;

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

}
