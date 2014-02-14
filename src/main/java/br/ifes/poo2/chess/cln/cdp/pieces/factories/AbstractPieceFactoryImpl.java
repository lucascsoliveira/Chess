/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp.pieces.factories;

import br.ifes.poo2.chess.cln.cdp.pieces.Color;
import br.ifes.poo2.chess.cln.cdp.pieces.Piece;
import br.ifes.poo2.chess.cln.cdp.pieces.PieceName;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lucas_000
 */
abstract class AbstractPieceFactoryImpl implements AbstractPieceFactory {

    protected Color color;
    protected final Map<PieceName, PieceImpl> pieces;

    public AbstractPieceFactoryImpl(Color color) {
        this.color = color;
        this.pieces = new HashMap<PieceName, PieceImpl>();
    }

    public Piece createPiece(PieceName pieceName) {
        PieceImpl pieceImpl;

        if (pieces.containsKey(pieceName)) {
            pieceImpl = (PieceImpl) pieces.get(pieceName);
        } else {
            pieceImpl = new PieceImpl(color, pieceName);

            pieces.put(pieceName, pieceImpl);
        }

        return (Piece) pieceImpl.clone();
    }

}
