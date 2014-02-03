/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp;

import br.ifes.poo2.chess.cdp.pieces.Color;
import java.util.List;
import br.ifes.poo2.chess.cdp.pieces.Piece;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Adelson
 */
public class ChessBoard {

    private static final int SIZE = 8; //ChessBoard é uma matriz SIZExSIZE;

    private final List<Piece> inGame;
    private final List<Piece> captured;
    private final Map<Color, Position> kingsPosition;
    private final Piece[][] board;

    public ChessBoard() {
        inGame = new ArrayList<Piece>();
        captured = new ArrayList<Piece>();
        kingsPosition = new HashMap<Color, Position>();
        board = new Piece[SIZE][SIZE];

    }

    public Piece[][] getChessBoard() {
        return board;
    }

    public void setup() {
        clear();
        putBlackPieces();
        putWhitePieces();
    }

    public void clear() {
        for (int line = 0; line < SIZE; line++) {
            for (int column = 0; column < SIZE; column++) {
                board[line][column] = null;
            }
        }

        inGame.clear();
        captured.clear();
        kingsPosition.clear();

    }

    public Iterator<Piece> getInGamePieces() {
        return inGame.iterator();
    }

    public Iterator<Piece> getCapturedPieces() {
        return captured.iterator();
    }

    private void putBlackPieces() {
        //TODO: Trazer (e adaptar) o código antigo;
    }

    private void putWhitePieces() {
        //TODO: Trazer (e adaptar) o código antigo;
    }

}
