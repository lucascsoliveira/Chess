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
public interface Strategy {

    public boolean canAttack(Board board, Position original, Position target);

    public boolean canMove(Board board, Position original, Position target);

}
