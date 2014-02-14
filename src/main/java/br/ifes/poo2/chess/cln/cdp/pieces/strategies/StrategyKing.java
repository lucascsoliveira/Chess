/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp.pieces.strategies;

import br.ifes.poo2.chess.cln.cdp.Board;
import br.ifes.poo2.chess.cln.cdp.Position;

/**
 *
 * @author lucas_000
 */
class StrategyKing implements Strategy {

    public boolean canAttack(Board board, Position original, Position target) {
        if ((board.getPieceAtPosition(target) == null) || (original.equals(target))) {
            return false;
        } else {
            return movement(original, target);
        }
    }

    public boolean canMove(Board board, Position original, Position target) {
        if ((board.getPieceAtPosition(target) != null) || (original.equals(target))) {
            return false;
        } else {
            return movement(original, target);
        }
    }

    private boolean movement(Position original, Position target) {
        int column, line;

        line = original.getLine();
        column = original.getColumn();

        return target.equals(new Position(column - 1, line - 1))
                || target.equals(new Position(column - 1, line))
                || target.equals(new Position(column - 1, line + 1))
                || target.equals(new Position(column, line - 1))
                || target.equals(new Position(column, line + 1))
                || target.equals(new Position(column + 1, line - 1))
                || target.equals(new Position(column + 1, line))
                || target.equals(new Position(column + 1, line + 1));
    }
}
