/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp;

import br.ifes.poo2.chess.cln.cdp.pieces.Color;
import java.util.List;
import br.ifes.poo2.chess.cln.cdp.pieces.Piece;
import br.ifes.poo2.chess.cln.cdp.pieces.PieceName;
import br.ifes.poo2.chess.cln.cdp.pieces.factories.PieceFactory;
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

    public void attack(Color turn, Position original, Position target) throws InvalidMoveException {
        Piece pieceOriginal, pieceTarget;

        pieceOriginal = getPieceAtPosition(original);
        pieceTarget = getPieceAtPosition(target);

        //Se não houver peça na posição original
        if (pieceOriginal == null
                //Ou não houver peça na posição target
                || pieceTarget == null
                //Ou a cor da peça na posição original não pertencer ao jogador da vez
                || !pieceOriginal.getColor().equals(turn)
                //Ou ambas as peças possuem a mesma cor (logo, não pode atacar a si mesma,)
                || pieceOriginal.getColor().equals(pieceTarget.getColor())) {
            //Dispara exceção
            throw new InvalidMoveException();
        } else {
            //Se a peça puder realizar o ataque
            if (pieceOriginal.canAttack(this, target)) {
                changePieces(pieceOriginal, pieceTarget);
            } else {
                //Dispara exceção
                throw new InvalidMoveException();
            }
        }
    }

    //FIXME: Corrigir changePieces()
    private void changePieces(Piece piece, Piece pieceOut) {
        //Remove a peça capturada da lista de peças in-game
        inGame.remove(pieceOut);
        //Adiciona a peça capturada na lista de peças capturadas
        captured.add(pieceOut);

        //Defini a nova posição da "captora"
        piece.setPosition(pieceOut.getPosition());

        //Coloca a "captora" no local da capturada
        pieceOut = piece;
        //"Limpa" a posição inicial
        piece = null;
    }

    public void move(Color turn, Position original, Position target) throws InvalidMoveException {
        Piece pieceOriginal, pieceTarget;

        pieceOriginal = getPieceAtPosition(original);
        pieceTarget = getPieceAtPosition(target);

        //Se não houver peça na posição original
        if (pieceOriginal == null
                //Ou não houver peça na posição target
                || pieceTarget != null
                //Ou a cor da peça na posição original não pertencer ao jogador da vez
                || !pieceOriginal.getColor().equals(turn)) {
            //Dispara exceção
            throw new InvalidMoveException();
        } else {
            //Se a peça puder realizar o ataque
            if (pieceOriginal.canMove(this, target)) {
                movePiece(pieceOriginal, target);
            } else {
                //Dispara exceção
                throw new InvalidMoveException();
            }
        }
    }

    //FIXME: Corrigir movePieces()
    private void movePiece(Piece piece, Position target) {

        //Defini a nova posição da peça
        piece.setPosition(target);

        //Coloca a peça na nova posição
        Piece auxPiece = getPieceAtPosition(target);
        auxPiece = piece;

        //"Limpa" a posição inicial
        piece = null;
    }

    public void bigCastling(Color turn) throws InvalidMoveException {
        //TODO: Implementar método "Roque Maior"
    }

    public void smallCastling(Color turn) throws InvalidMoveException {
        //TODO: Implementar método "Roque Menor"
    }

    public boolean isCheck(Color turn) {
        Position position = kingsPosition.get(turn);

        for (int line = 0; line < MAX_SIZE; line++) {
            for (int column = 0; column < MAX_SIZE; column++) {
                Piece piece = board[line][column];
                if (piece != null && !piece.getColor().equals(turn)) {
                    return piece.canAttack(this, position);
                }
            }
        }

        return false;
    }

    private boolean isCheck(Color turn, Position position) {
        for (int line = 0; line < MAX_SIZE; line++) {
            for (int column = 0; column < MAX_SIZE; column++) {
                Piece piece = board[line][column];
                if (piece != null && !piece.getColor().equals(turn)) {
                    return piece.canAttack(this, position);
                }
            }
        }
        return false;
    }

    private List<Position> getPositionsAround(Position position) {
        int line = position.getLine();
        int column = position.getColumn();
        List<Position> positions = new ArrayList<Position>();

        for (int lineAux = line - 1; lineAux <= line + 1; lineAux++) {
            for (int columnAux = column - 1; columnAux <= column + 1; columnAux++) {
                if (lineAux >= MIN_SIZE && columnAux >= MIN_SIZE && lineAux <= MAX_SIZE && columnAux <= MAX_SIZE) {
                    positions.add(new Position(columnAux, lineAux));
                }
            }
        }
        return positions;
    }

    public boolean isCheckmate(Color turn) {
        //Se o rei está em check
        if(isCheck(turn)){
            Iterator iterator = getPositionsAround(kingsPosition.get(turn)).iterator();
            //Verifica para todas posições válidas em volta, se elas também estão sob ataque
            while(iterator.hasNext()){
                //Se alguma posição não estiver em check retorna false
                if(! isCheck(turn, (Position)iterator.next()))
                    return false;
            }
        }
        return false;
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
