/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp.pieces.factories;

import br.ifes.poo2.chess.cdp.pieces.Color;
import br.ifes.poo2.chess.cdp.pieces.Piece;
import br.ifes.poo2.chess.cdp.pieces.PieceName;

/**
 *
 * @author lucas_000
 */
public class PieceFactory {

    //TODO: [Dúvida] Posso usar o método fábrica assim?
    public static Piece build(PieceName pieceName, Color color) {
        if (color.equals(Color.BLACK)) {
            return BlackPieceFactory.getInstance().createPiece(pieceName);
        } else {
            return WhitePieceFactory.getInstance().createPiece(pieceName);
        }
    }

}
