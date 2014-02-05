/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp.pieces.strategies;

import br.ifes.poo2.chess.cdp.Board;
import br.ifes.poo2.chess.cdp.ChessBoard;
import br.ifes.poo2.chess.cdp.pieces.Piece;
import br.ifes.poo2.chess.cdp.Position;

/**
 *
 * @author lucas_000
 */
class StrategyRook implements Strategy {

    //TODO: Trazer (e adaptar) o código antigo.
    public boolean canAttack(Board board, Position original, Position target) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean canMove(Board board, Position original, Position target) {

        if ((board.getPieceAtPosition(target) != null) || (original.equals(target))) {
            return false;
        } else if (original.getLine() == target.getLine()) {
            return canMoveOnHorizontal(board, original, target);
        } else if (original.getColumn() == target.getColumn()) {
            return canMoveOnVertical(board, original, target);
        } else {
            return false;
        }
    }

    private boolean canMoveOnHorizontal(Board board, Position original, Position target) {
        int column = original.getColumn();

        if (target.getColumn() < column) {
            return atLeftOfHorizontal(board, original, target);
        } else {
            return atRightOfHorizontal(board, original, target);
        }
    }

    private boolean atLeftOfHorizontal(Board board, Position original, Position target) {

    }

    private boolean atRightOfHorizontal(Board board, Position original, Position target) {

    }

    private boolean canMoveOnVertical(Board board, Position original, Position target) {
        int line = original.getLine();

        if (original.getLine() < line) {
            return inTopOfVertical(board, original, target);
        } else {
            return inBottomOfVertical(board, original, target);
        }
    }

    private boolean inTopOfVertical(Board board, Position original, Position target) {

    }

    private boolean inBottomOfVertical(Board board, Position original, Position target) {

    }
}
