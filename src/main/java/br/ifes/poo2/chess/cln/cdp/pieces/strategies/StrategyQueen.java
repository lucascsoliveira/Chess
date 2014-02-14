/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp.pieces.strategies;

import br.ifes.poo2.chess.cln.cdp.Board;
import br.ifes.poo2.chess.cln.cdp.Position;
import br.ifes.poo2.chess.cln.cdp.pieces.PieceName;

/**
 *
 * @author lucas_000
 */
class StrategyQueen implements Strategy {

    private final Strategy strategyBishop = StrategyManager.getInstance().getStrategy(PieceName.BISHOP);
    private final Strategy strategyRook = StrategyManager.getInstance().getStrategy(PieceName.ROOK);

    public boolean canAttack(Board board, Position original, Position target) {
        return strategyBishop.canAttack(board, original, target) || strategyRook.canAttack(board, original, target);
    }

    public boolean canMove(Board board, Position original, Position target) {
        return strategyBishop.canMove(board, original, target) || strategyRook.canMove(board, original, target);
    }

}
