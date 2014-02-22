/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cln.cdp;

import br.ifes.poo2.chess.cln.cdp.ChessBoard;
import br.ifes.poo2.chess.cln.cdp.Position;
import br.ifes.poo2.chess.cln.cdp.pieces.Color;
import br.ifes.poo2.chess.cln.cdp.pieces.Piece;
import br.ifes.poo2.chess.cln.cdp.pieces.PieceName;
import br.ifes.poo2.chess.cln.cdp.pieces.factories.PieceFactory;
import br.ifes.poo2.chess.util.InvalidMoveException;
import br.ifes.poo2.chess.util.InvalidPromotionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lucas_000
 */
public class ChessBoardTest {

    private ChessBoard chessBoard;

    @Before
    public void before() {
        chessBoard = new ChessBoard();
    }

    @Test
    public void setupBlackPieces() {
        chessBoard.setup();

        for (int line = ChessBoard.MAX_SIZE; line >= ChessBoard.MAX_SIZE - 1; line--) {
            for (int column = ChessBoard.MIN_SIZE; column <= ChessBoard.MAX_SIZE; column++) {
                Color color = chessBoard.getPieceAtPosition(new Position(column, line)).getColor();
                Assert.assertEquals(Color.BLACK, color);
            }
        }

    }

    @Test
    public void setupWhitePieces() {
        chessBoard.setup();

        for (int line = ChessBoard.MIN_SIZE; line <= ChessBoard.MIN_SIZE + 1; line++) {
            for (int column = ChessBoard.MIN_SIZE; column <= ChessBoard.MAX_SIZE; column++) {
                Color color = chessBoard.getPieceAtPosition(new Position(column, line)).getColor();
                Assert.assertEquals(Color.WHITE, color);
            }
        }

    }

    @Test
    public void setupBlackPiecesLocationt() {
        chessBoard.setup();
        PieceName pieceName;

        //Torres
        pieceName = chessBoard.getPieceAtPosition(new Position(1, 8)).getName();
        Assert.assertSame(PieceName.ROOK, pieceName);

        pieceName = chessBoard.getPieceAtPosition(new Position(8, 8)).getName();
        Assert.assertSame(PieceName.ROOK, pieceName);

        //Cavalos
        pieceName = chessBoard.getPieceAtPosition(new Position(2, 8)).getName();
        Assert.assertSame(PieceName.KNIGHT, pieceName);

        pieceName = chessBoard.getPieceAtPosition(new Position(7, 8)).getName();
        Assert.assertSame(PieceName.KNIGHT, pieceName);

        //Bispos
        pieceName = chessBoard.getPieceAtPosition(new Position(3, 8)).getName();
        Assert.assertSame(PieceName.BISHOP, pieceName);

        pieceName = chessBoard.getPieceAtPosition(new Position(6, 8)).getName();
        Assert.assertSame(PieceName.BISHOP, pieceName);

        //Rainha
        pieceName = chessBoard.getPieceAtPosition(new Position(4, 8)).getName();
        Assert.assertSame(PieceName.QUEEN, pieceName);

        //Rei
        pieceName = chessBoard.getPieceAtPosition(new Position(5, 8)).getName();
        Assert.assertSame(PieceName.KING, pieceName);

        //Criando peões
        for (int column = ChessBoard.MIN_SIZE; column <= ChessBoard.MAX_SIZE; column++) {
            pieceName = chessBoard.getPieceAtPosition(new Position(column, 7)).getName();
            Assert.assertSame(PieceName.PAWN, pieceName);
        }

    }

    @Test
    public void setupWhitePiecesLocation() {
        chessBoard.setup();
        PieceName pieceName;

        //Torres
        pieceName = chessBoard.getPieceAtPosition(new Position(1, 1)).getName();
        Assert.assertSame(PieceName.ROOK, pieceName);

        pieceName = chessBoard.getPieceAtPosition(new Position(8, 1)).getName();
        Assert.assertSame(PieceName.ROOK, pieceName);

        //Cavalos
        pieceName = chessBoard.getPieceAtPosition(new Position(2, 1)).getName();
        Assert.assertSame(PieceName.KNIGHT, pieceName);

        pieceName = chessBoard.getPieceAtPosition(new Position(7, 1)).getName();
        Assert.assertSame(PieceName.KNIGHT, pieceName);

        //Bispos
        pieceName = chessBoard.getPieceAtPosition(new Position(3, 1)).getName();
        Assert.assertSame(PieceName.BISHOP, pieceName);

        pieceName = chessBoard.getPieceAtPosition(new Position(6, 1)).getName();
        Assert.assertSame(PieceName.BISHOP, pieceName);

        //Rainha
        pieceName = chessBoard.getPieceAtPosition(new Position(4, 1)).getName();
        Assert.assertSame(PieceName.QUEEN, pieceName);

        //Rei
        pieceName = chessBoard.getPieceAtPosition(new Position(5, 1)).getName();
        Assert.assertSame(PieceName.KING, pieceName);

        //Criando peões
        for (int column = ChessBoard.MIN_SIZE; column <= ChessBoard.MAX_SIZE; column++) {
            pieceName = chessBoard.getPieceAtPosition(new Position(column, 2)).getName();
            Assert.assertSame(PieceName.PAWN, pieceName);
        }

    }

    @Test
    public void setupEmptyLocations() {
        chessBoard.setup();
        for (int line = ChessBoard.MIN_SIZE + 2; line <= ChessBoard.MAX_SIZE - 2; line++) {
            for (int column = ChessBoard.MIN_SIZE; column <= ChessBoard.MAX_SIZE; column++) {
                Assert.assertSame(true, chessBoard.isPositionEmpty(new Position(column, line)));
            }
        }

    }

    @Test
    public void clear() {
        chessBoard.clear();

        for (int line = ChessBoard.MIN_SIZE; line <= ChessBoard.MAX_SIZE; line++) {
            for (int column = ChessBoard.MIN_SIZE; column <= ChessBoard.MAX_SIZE; column++) {
                Assert.assertSame(true, chessBoard.isPositionEmpty(new Position(column, line)));
            }
        }
    }

    @Test
    public void putPiece() {
        int line = 1, column = 1;
        Piece piece = PieceFactory.build(PieceName.PAWN, Color.BLACK);
        Piece pieceAtPosition;
        Position position = new Position(column, line);

        chessBoard.putPieceAtPosition(piece, position);

        pieceAtPosition = chessBoard.getPieceAtPosition(position);

        Assert.assertEquals(piece, pieceAtPosition);
    }

    @Test
    public void movePiece() {
        Piece piece = PieceFactory.build(PieceName.PAWN, Color.BLACK);
        Position origin = new Position(1, 1),
                target = new Position(3, 3);

        chessBoard.putPieceAtPosition(piece, origin);

        chessBoard.movePiece(piece, target);

        Assert.assertEquals(true, chessBoard.isPositionEmpty(origin));
        Assert.assertEquals(piece, chessBoard.getPieceAtPosition(target));
        Assert.assertEquals(target, this.chessBoard.getPieceAtPosition(target).getPosition());
    }

    @Test
    public void capturePiece() {
        Piece piece = PieceFactory.build(PieceName.PAWN, Color.BLACK);
        Piece capturedPiece = PieceFactory.build(PieceName.ROOK, Color.BLACK);
        Position origin = new Position(1, 1),
                target = new Position(3, 3);

        chessBoard.putPieceAtPosition(piece, origin);
        chessBoard.putPieceAtPosition(capturedPiece, target);

        chessBoard.capturePiece(piece, capturedPiece);

        Assert.assertEquals(true, chessBoard.isPositionEmpty(origin));
        Assert.assertEquals(piece, chessBoard.getPieceAtPosition(target));
        Assert.assertEquals(target, this.chessBoard.getPieceAtPosition(target).getPosition());
    }

//    @Test
//    public void move() throws InvalidMoveException{
//        chessBoard.setup();
//        
//        Position origin = new Position(1, 2);
//        Position target = new Position(1, 3);
//        
//        Assert.assertEquals(true, chessBoard.isPositionEmpty(target));
//        
//        chessBoard.move(Color.WHITE, origin, target);
//        
//        Assert.assertNull(chessBoard.isPositionEmpty(origin));
//        
//    }
    
    @Test
    public void isCheck() {
        //TODO: Fazer teste
    }

    @Test
    public void isCheckMate() {
        //TODO: Fazer teste
    }

    @Test
    public void blackSmallCastling() throws InvalidMoveException {
        Piece king = PieceFactory.build(PieceName.KING, Color.BLACK),
                rook = PieceFactory.build(PieceName.ROOK, Color.BLACK);

        Position kingPosition = new Position(5, 8),
                rookPosition = new Position(8, 8);
        
        chessBoard.putPieceAtPosition(king, kingPosition);
        chessBoard.putPieceAtPosition(rook, rookPosition);
        
        chessBoard.smallCastling(Color.BLACK);
        
        Assert.assertNull(chessBoard.getPieceAtPosition(kingPosition));
        Assert.assertNull(chessBoard.getPieceAtPosition(rookPosition));
        Assert.assertEquals(king, chessBoard.getPieceAtPosition(new Position(7, 8)));
        Assert.assertEquals(rook, chessBoard.getPieceAtPosition(new Position(6, 8)));
    }
    
    @Test
    public void whiteSmallCastling() throws InvalidMoveException {
        Piece king = PieceFactory.build(PieceName.KING, Color.WHITE),
                rook = PieceFactory.build(PieceName.ROOK, Color.WHITE);

        Position kingPosition = new Position(5, 1),
                rookPosition = new Position(8, 1);
        
        chessBoard.putPieceAtPosition(king, kingPosition);
        chessBoard.putPieceAtPosition(rook, rookPosition);
        
        chessBoard.smallCastling(Color.WHITE);
        
        Assert.assertNull(chessBoard.getPieceAtPosition(kingPosition));
        Assert.assertNull(chessBoard.getPieceAtPosition(rookPosition));
        Assert.assertEquals(king, chessBoard.getPieceAtPosition(new Position(7, 1)));
        Assert.assertEquals(rook, chessBoard.getPieceAtPosition(new Position(6, 1)));
    }

    @Test
    public void blackBigCastling() throws InvalidMoveException {
        Piece king = PieceFactory.build(PieceName.KING, Color.BLACK),
                rook = PieceFactory.build(PieceName.ROOK, Color.BLACK);

        Position kingPosition = new Position(5, 8),
                rookPosition = new Position(1, 8);
        
        chessBoard.putPieceAtPosition(king, kingPosition);
        chessBoard.putPieceAtPosition(rook, rookPosition);
        
        chessBoard.bigCastling(Color.BLACK);
        
        Assert.assertNull(chessBoard.getPieceAtPosition(kingPosition));
        Assert.assertNull(chessBoard.getPieceAtPosition(rookPosition));
        Assert.assertEquals(king, chessBoard.getPieceAtPosition(new Position(3, 8)));
        Assert.assertEquals(rook, chessBoard.getPieceAtPosition(new Position(4, 8)));
    }
    
    @Test
    public void whiteBigCastling() throws InvalidMoveException {
        Piece king = PieceFactory.build(PieceName.KING, Color.WHITE),
                rook = PieceFactory.build(PieceName.ROOK, Color.WHITE);

        Position kingPosition = new Position(5, 1),
                rookPosition = new Position(1, 1);
        
        chessBoard.putPieceAtPosition(king, kingPosition);
        chessBoard.putPieceAtPosition(rook, rookPosition);
        
        chessBoard.bigCastling(Color.WHITE);
        
        Assert.assertNull(chessBoard.getPieceAtPosition(kingPosition));
        Assert.assertNull(chessBoard.getPieceAtPosition(rookPosition));
        Assert.assertEquals(king, chessBoard.getPieceAtPosition(new Position(3, 1)));
        Assert.assertEquals(rook, chessBoard.getPieceAtPosition(new Position(4, 1)));
    }
    
    @Test
    public void promotion() throws InvalidPromotionException {
        Piece pawn = PieceFactory.build(PieceName.PAWN, Color.BLACK);
        Position pawnPosition = new Position(ChessBoard.MIN_SIZE, ChessBoard.MIN_SIZE);

        chessBoard.putPieceAtPosition(pawn, pawnPosition);

        chessBoard.promotion(pawnPosition, PieceName.BISHOP);

        Assert.assertNotSame(PieceName.PAWN, chessBoard.getPieceAtPosition(pawnPosition).getName());
    }

    @Test(expected = InvalidPromotionException.class)
    public void promotionError() throws InvalidPromotionException {
        Piece pawn = PieceFactory.build(PieceName.PAWN, Color.BLACK);
        Position pawnPosition = new Position(ChessBoard.MIN_SIZE, 5);

        chessBoard.putPieceAtPosition(pawn, pawnPosition);

        chessBoard.promotion(pawnPosition, PieceName.BISHOP);

        Assert.assertNotSame(PieceName.PAWN, chessBoard.getPieceAtPosition(pawnPosition).getName());
    }

}
