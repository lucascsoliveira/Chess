/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cgt;

import br.ifes.poo2.chess.cln.cdp.ChessBoard;
import br.ifes.poo2.chess.cln.cdp.Game;
import br.ifes.poo2.chess.cln.cdp.pieces.Color;
import br.ifes.poo2.chess.cln.cdp.players.Player;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 20101BSI0534
 */
public abstract class AplChess {

    protected Game game;

    public AplChess(Player white, Player black) {
        game = new Game(white, black);
    }

    public abstract void play(String play);

    public Player getWinnerPlayer() {
        return game.getWinner();
    }

    public Color getTurn() {
        return game.getTurn();
    }
    
}
