/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp.players.factories;

import br.ifes.poo2.chess.cln.cdp.players.Computer;
import br.ifes.poo2.chess.cln.cdp.Game;
import br.ifes.poo2.chess.cln.cdp.Position;
import br.ifes.poo2.chess.cln.cdp.pieces.Color;
import br.ifes.poo2.chess.cln.cdp.pieces.Piece;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.IteratorUtils;

/**
 *
 * @author Adelson
 */
class PlayerComputer extends PlayerImpl implements Computer {

    public PlayerComputer(String name) {
        super(name);
    }

    public String play(Color color, Game game) {
        Iterator<Piece> iterator = game.getPiecesOfPlayer(color);
        List<Piece> listOfPieces = IteratorUtils.toList(iterator);
        List<Position> positions;
        int rand;
        Piece piece;

        Position positionAux;
        int count = 0;
        while (count < 50) {
            if (listOfPieces.size() > 0) {
                rand = (int) (Math.random() * (listOfPieces.size() - 1));
                piece = listOfPieces.get(rand);

                positions = piece.getPath(game.getChessBoard(), piece.getPosition());

                if (positions.size() > 0) {
                    rand = (int) (Math.random() * (positions.size() - 1));
                    positionAux = positions.get(rand);

                    return ""
                            + piece.getPosition().getColumn()
                            + piece.getPosition().getLine()
                            + positionAux.getColumn()
                            + positionAux.getLine();
                }

            }
            count++;
        }
        return "desistir";
    }
}
