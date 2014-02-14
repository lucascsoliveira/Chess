/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp.pieces.factories;

import br.ifes.poo2.chess.cln.cdp.pieces.Piece;
import br.ifes.poo2.chess.cln.cdp.pieces.PieceName;

/**
 *
 * @author lucas_000
 */
interface AbstractPieceFactory {

    public Piece createPiece(PieceName pieceName);

}
