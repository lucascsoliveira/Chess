/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp.pieces.strategies;

import br.ifes.poo2.chess.cdp.Board;
import br.ifes.poo2.chess.cdp.Position;

/**
 *
 * @author lucas_000
 */
class StrategyKnight implements Strategy {

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
        int line, column;

        line = original.getLine();
        column = original.getColumn();

        return new Position(column - 1, line - 2).equals(target)
                || new Position(column + 1, line - 2).equals(target)
                || new Position(column - 2, line - 1).equals(target)
                || new Position(column + 2, line - 1).equals(target)
                || new Position(column - 2, line + 1).equals(target)
                || new Position(column + 2, line + 1).equals(target)
                || new Position(column - 1, line + 2).equals(target)
                || new Position(column + 1, line + 2).equals(target);
    }

}
