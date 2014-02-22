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
import br.ifes.poo2.chess.util.InvalidPromotionException;
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
    }

    public void clear() {
        for (int line = MIN_SIZE; line <= MAX_SIZE; line++) {
            for (int column = MIN_SIZE; column <= MAX_SIZE; column++) {
                clearPosition(new Position(column, line));
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
        putPieceAtPosition(PieceFactory.build(PieceName.ROOK, Color.BLACK), new Position(1, 8));
        putPieceAtPosition(PieceFactory.build(PieceName.ROOK, Color.BLACK), new Position(8, 8));

        //Criando cavalos
        putPieceAtPosition(PieceFactory.build(PieceName.KNIGHT, Color.BLACK), new Position(2, 8));
        putPieceAtPosition(PieceFactory.build(PieceName.KNIGHT, Color.BLACK), new Position(7, 8));

        //Criando bispos
        putPieceAtPosition(PieceFactory.build(PieceName.BISHOP, Color.BLACK), new Position(3, 8));
        putPieceAtPosition(PieceFactory.build(PieceName.BISHOP, Color.BLACK), new Position(6, 8));

        //Criando rainha
        putPieceAtPosition(PieceFactory.build(PieceName.QUEEN, Color.BLACK), new Position(4, 8));

        //Criando rei
        putPieceAtPosition(PieceFactory.build(PieceName.KING, Color.BLACK), new Position(5, 8));
        kingsPosition.put(Color.BLACK, new Position(5, 8));
        getPieceAtPosition(new Position(5, 8)).addObserver(this); //Adicionando ChessBoard como observador do "Black King"

        //Criando peões
        for (int column = MIN_SIZE; column <= MAX_SIZE; column++) {
            putPieceAtPosition(PieceFactory.build(PieceName.PAWN, Color.BLACK), new Position(column, 7));
        }
    }

    private void putWhitePieces() {
        //Criando torres
        putPieceAtPosition(PieceFactory.build(PieceName.ROOK, Color.WHITE), new Position(1, 1));
        putPieceAtPosition(PieceFactory.build(PieceName.ROOK, Color.WHITE), new Position(8, 1));

        //Criando cavalos
        putPieceAtPosition(PieceFactory.build(PieceName.KNIGHT, Color.WHITE), new Position(2, 1));
        putPieceAtPosition(PieceFactory.build(PieceName.KNIGHT, Color.WHITE), new Position(7, 1));

        //Criando bispos
        putPieceAtPosition(PieceFactory.build(PieceName.BISHOP, Color.WHITE), new Position(3, 1));
        putPieceAtPosition(PieceFactory.build(PieceName.BISHOP, Color.WHITE), new Position(6, 1));

        //Criando rainha
        putPieceAtPosition(PieceFactory.build(PieceName.QUEEN, Color.WHITE), new Position(4, 1));

        //Criando rei
        putPieceAtPosition(PieceFactory.build(PieceName.KING, Color.WHITE), new Position(5, 1));
        kingsPosition.put(Color.WHITE, new Position(5, 1));
        getPieceAtPosition(new Position(5, 1)).addObserver(this); //Adicionando ChessBoard como observador do "White King"

        //Criando peões
        for (int column = MIN_SIZE; column <= MAX_SIZE; column++) {
            putPieceAtPosition(PieceFactory.build(PieceName.PAWN, Color.WHITE), new Position(column, 2));
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
                capturePiece(pieceOriginal, pieceTarget);
            } else {
                //Dispara exceção
                throw new InvalidMoveException();
            }
        }
    }

    public void capturePiece(Piece piece, Piece capturedPiece) {
        Position originalPosition = piece.getPosition();

        //Remove a peça capturada da lista de peças in-game
        inGame.remove(capturedPiece);
        //Adiciona a peça capturada na lista de peças capturadas
        captured.add(capturedPiece);

        //Defini a nova posição da "captora"
        piece.setPosition(capturedPiece.getPosition());

        //Coloca a "captora" no local da capturada
        putPieceAtPosition(piece, piece.getPosition());

        //"Limpa" a posição inicial
        clearPosition(originalPosition);
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

    public void movePiece(Piece piece, Position target) {
        Position originalPosition = piece.getPosition();

        //Define a nova posição na peça
        piece.setPosition(target);

        //Coloca a peça na posição target
        putPieceAtPosition(piece, target);

        //"Limpa" a posição inicial
        clearPosition(originalPosition);
    }

    public void bigCastling(Color turn) throws InvalidMoveException {
        Position kingPosition, rookPosition;

        //BLACK BIGCASTLING
        if (turn.equals(Color.BLACK)) {
            kingPosition = new Position(5, 8);
            rookPosition = new Position(1, 8);
        } //WHITE BIGCASTLING
        else {
            kingPosition = new Position(5, 1);
            rookPosition = new Position(1, 1);
        }

        bigCastling(turn, kingPosition, rookPosition);
    }

    private void bigCastling(Color turn, Position kingPosition, Position rookPosition) throws InvalidMoveException {
        int line = rookPosition.getLine();

        Position[] positions = new Position[3];
        positions[0] = new Position(2, line);
        positions[1] = new Position(3, line);
        positions[2] = new Position(4, line);

        //O rei e a torre devem estar nas posições iniciais
        if (!isPositionEmpty(rookPosition) && !isPositionEmpty(kingPosition)
                //Verifica se as peças que estão nas posições iniciais são o rei e a torre
                && getPieceAtPosition(rookPosition).getName().equals(PieceName.ROOK)
                && getPieceAtPosition(kingPosition).getName().equals(PieceName.KING)
                //Nem o rei nem a torre podem ter se movido
                && getPieceAtPosition(rookPosition).getLastMove() == null
                && getPieceAtPosition(kingPosition).getLastMove() == null
                //As posições entre o rei e a torre devem estar vazias
                && isPositionEmpty(positions[0])
                && isPositionEmpty(positions[1])
                && isPositionEmpty(positions[2])
                //Nenhuma das posições em que o rei passará pode estar em check
                && !isPositionInCheck(turn, kingPosition)
                && !isPositionInCheck(turn, positions[0])
                && !isPositionInCheck(turn, positions[1])
                && !isPositionInCheck(turn, positions[2])) {

            //Pega o rei e a torre
            Piece king = getPieceAtPosition(kingPosition);
            Piece rook = getPieceAtPosition(rookPosition);

            movePiece(king, positions[1]);
            movePiece(rook, positions[2]);

        } else {
            throw new InvalidMoveException();
        }
    }

    public void smallCastling(Color turn) throws InvalidMoveException {
        Position kingPosition, rookPosition;

        //BLACK SMALLCASTLING
        if (turn.equals(Color.BLACK)) {
            kingPosition = new Position(5, 8);
            rookPosition = new Position(8, 8);
        } //WHITE SMALLCASTLING
        else {
            kingPosition = new Position(5, 1);
            rookPosition = new Position(8, 1);
        }

        smallCastling(turn, kingPosition, rookPosition);
    }

    private void smallCastling(Color turn, Position kingPosition, Position rookPosition) throws InvalidMoveException {
        int line = rookPosition.getLine();

        Position[] positions = new Position[2];
        positions[0] = new Position(6, line);
        positions[1] = new Position(7, line);
        //O rei e a torre devem estar nas posições iniciais
        if (!isPositionEmpty(rookPosition) && !isPositionEmpty(kingPosition)
                //Verifica se as peças que estão nas posições iniciais são o rei e a torre
                && getPieceAtPosition(rookPosition).getName().equals(PieceName.ROOK)
                && getPieceAtPosition(kingPosition).getName().equals(PieceName.KING)
                //Nem o rei nem a torre podem ter se movido
                && getPieceAtPosition(rookPosition).getLastMove() == null
                && getPieceAtPosition(kingPosition).getLastMove() == null
                //As posições entre o rei e a torre devem estar vazias
                && isPositionEmpty(positions[0])
                && isPositionEmpty(positions[1])
                //Nenhuma das posições em que o rei passará pode estar em check
                && !isPositionInCheck(turn, kingPosition)
                && !isPositionInCheck(turn, positions[0])
                && !isPositionInCheck(turn, positions[1])) {

            //Pega o rei e a torre
            Piece king = getPieceAtPosition(kingPosition);
            Piece rook = getPieceAtPosition(rookPosition);

            movePiece(king, positions[1]);
            movePiece(rook, positions[0]);

        } else {
            throw new InvalidMoveException();
        }
    }

    public void promotion(Position position, PieceName pieceName) throws InvalidPromotionException {
        Piece piece = getPieceAtPosition(position);

        if (piece.getName().equals(PieceName.PAWN) && (position.getLine() == 1 || position.getLine() == 8)) {
            getPieceAtPosition(position).setStrategy(pieceName);
        } else {
            throw new InvalidPromotionException();
        }
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

    private boolean isPositionInCheck(Color turn, Position position) {
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
        if (isCheck(turn)) {
            Iterator iterator = getPositionsAround(kingsPosition.get(turn)).iterator();
            //Verifica para todas posições válidas em volta, se elas também estão sob ataque
            while (iterator.hasNext()) {
                //Se alguma posição não estiver em check retorna false
                if (!isPositionInCheck(turn, (Position) iterator.next())) {
                    return false;
                }
            }
        }
        return false;
    }

    public Piece getPieceAtPosition(Position position) {
        return board[position.getLine() - FIXPOSITION][position.getColumn() - FIXPOSITION];
    }

    public void clearPosition(Position position) {
        int line = position.getLine() - FIXPOSITION;
        int column = position.getColumn() - FIXPOSITION;

        board[line][column] = null;
    }

    public void putPieceAtPosition(Piece piece, Position position) {
        int line = position.getLine() - FIXPOSITION;
        int column = position.getColumn() - FIXPOSITION;

        piece.setPosition(position);

        if (!inGame.contains(piece)) {
            inGame.add(piece);
        }

        board[line][column] = piece;
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

    @Override
    public String toString() {
        String string = "";

        for (int line = MAX_SIZE - 1; line >= 0; line--) {
            for (int column = 0; column < MAX_SIZE; column++) {
                if (board[line][column] != null) {
                    string += board[line][column];
                } else {
                    string += "[ ]------";
                }
                string += "(" + (column + 1) + "," + (line + 1) + ")"
                        + "\t";
            }
            string += "\n";
        }
        return string;
    }
}
