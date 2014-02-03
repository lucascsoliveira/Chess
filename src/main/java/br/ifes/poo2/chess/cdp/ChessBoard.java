/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp;

import br.ifes.poo2.chess.cdp.pieces.Color;
import java.util.List;
import br.ifes.poo2.chess.cdp.pieces.Piece;
import br.ifes.poo2.chess.cdp.pieces.PieceName;
import br.ifes.poo2.chess.cdp.pieces.factories.PieceFactory;
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
        synchronizePieces();
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
        //Criando torres
        board[7][0] = PieceFactory.build(PieceName.ROOK, Color.BLACK);
        board[7][7] = PieceFactory.build(PieceName.ROOK, Color.BLACK);

        //Criando cavalos
        board[7][1] = PieceFactory.build(PieceName.KNIGHT, Color.BLACK);
        board[7][6] = PieceFactory.build(PieceName.KNIGHT, Color.BLACK);

        //Criando bispos
        board[7][2] = PieceFactory.build(PieceName.BISHOP, Color.BLACK);
        board[7][5] = PieceFactory.build(PieceName.BISHOP, Color.BLACK);

        //Criando rainha
        board[7][3] = PieceFactory.build(PieceName.QUEEN, Color.BLACK);

        //Criando rei
        board[7][4] = PieceFactory.build(PieceName.KING, Color.BLACK);

        //Criando peões
        for (int column = 0; column < SIZE; column++) {
            board[6][column] = PieceFactory.build(PieceName.PAWN, Color.BLACK);
        }
    }

    private void putWhitePieces() {
        //Criando torres
        board[0][0] = PieceFactory.build(PieceName.ROOK, Color.WHITE);
        board[0][7] = PieceFactory.build(PieceName.ROOK, Color.WHITE);

        //Criando cavalos
        board[0][1] = PieceFactory.build(PieceName.KNIGHT, Color.WHITE);
        board[0][6] = PieceFactory.build(PieceName.KNIGHT, Color.WHITE);

        //Criando bispos
        board[0][2] = PieceFactory.build(PieceName.BISHOP, Color.WHITE);
        board[0][5] = PieceFactory.build(PieceName.BISHOP, Color.WHITE);

        //Criando rainha
        board[0][3] = PieceFactory.build(PieceName.QUEEN, Color.WHITE);

        //Criando rei
        board[0][4] = PieceFactory.build(PieceName.KING, Color.WHITE);

        //Criando peões
        for (int column = 0; column < SIZE; column++) {
            board[1][column] = PieceFactory.build(PieceName.PAWN, Color.WHITE);
        }
    }

    private void synchronizePieces() {
        for (int line = 0; line < SIZE; line++) {
            for (int column = 0; column < SIZE; column++) {
                Piece piece = board[line][column];
                if (piece != null) {
                    inGame.add(piece);
                    piece.setPosition(new Position(column, line));
                }
            }
        }
    }

    //TODO: Implements Observer(para manter a posição do rei atualizada);
}
