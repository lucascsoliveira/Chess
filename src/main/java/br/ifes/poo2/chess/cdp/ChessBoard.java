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
import br.ifes.poo2.chess.util.InvalidMoveException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Adelson
 */
public class ChessBoard implements Board, Observer {

    public static final int FIXPOSITION = 1;

    public static final int MIN_SIZE = 1;
    public static final int MAX_SIZE = 8; //ChessBoard é uma matriz MAX_SIZExMAX_SIZE;

    private final List<Piece> inGame;
    private final List<Piece> captured;
    private final Map<Color, Position> kingsPosition;
    private final Piece[][] board;

    public ChessBoard() {
        inGame = new ArrayList<Piece>();
        captured = new ArrayList<Piece>();
        kingsPosition = new HashMap<Color, Position>();
        board = new Piece[MAX_SIZE][MAX_SIZE];

    }

    public void setup() {
        clear();
        putBlackPieces();
        putWhitePieces();
        synchronizePieces();
    }

    public void clear() {
        for (int line = 0; line < MAX_SIZE; line++) {
            for (int column = 0; column < MAX_SIZE; column++) {
                board[line][column] = null;
            }
        }

        inGame.clear();
        captured.clear();
        kingsPosition.clear();
    }

    public Iterator getInGamePieces() {
        return inGame.iterator();
    }

    public Iterator getCapturedPieces() {
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
        board[7][4].addObserver(this); //Adicionando ChessBoard como observador do "Black King"

        //Criando peões
        for (int column = 0; column < MAX_SIZE; column++) {
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
        board[0][4].addObserver(this); //Adicionando ChessBoard como observador do "White King"

        //Criando peões
        for (int column = 0; column < MAX_SIZE; column++) {
            board[1][column] = PieceFactory.build(PieceName.PAWN, Color.WHITE);
        }
    }

    private void synchronizePieces() {
        for (int line = 0; line < MAX_SIZE; line++) {
            for (int column = 0; column < MAX_SIZE; column++) {
                Piece piece = board[line][column];
                if (piece != null) {
                    inGame.add(piece);
                    piece.setPosition(new Position(column + FIXPOSITION, line + FIXPOSITION));
                }
            }
        }
    }

    public void attack() throws InvalidMoveException {
        //TODO: Implementar método atacar
    }

    public void move() throws InvalidMoveException {
        //TODO: Implementar método mover
    }

    public void bigCastling() throws InvalidMoveException {
        //TODO: Implementar método "Roque Maior"
    }

    public void smallCastling() throws InvalidMoveException {
        //TODO: Implementar método "Roque Menor"
    }

    public Piece getPieceAtPosition(Position position) {
        return board[position.getLine() - FIXPOSITION][position.getColumn() - FIXPOSITION];
    }

    public boolean isPositionEmpty(Position position) {
        return getPieceAtPosition(position) == null;
    }

    public void update(Observable o, Object arg) {
        Color color = ((Piece) o).getColor();
        PieceName pieceName = ((Piece) o).getName();
        Position position = ((Piece) o).getPosition();

        if (pieceName.equals(PieceName.KING)) {
            kingsPosition.put(color, position);
        }
    }

}
