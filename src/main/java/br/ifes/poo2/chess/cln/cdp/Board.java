/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp;

import br.ifes.poo2.chess.cln.cdp.pieces.Piece;

/**
 *
 * @author 20101BSI0534
 */
public interface Board {

    public Piece getPieceAtPosition(Position position);

    public boolean isPositionEmpty(Position position);

}
