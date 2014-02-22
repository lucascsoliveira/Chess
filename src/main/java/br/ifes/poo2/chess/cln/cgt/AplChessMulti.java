/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cgt;

import br.ifes.poo2.chess.cln.cdp.Game;
import br.ifes.poo2.chess.cln.cdp.players.Player;
import br.ifes.poo2.chess.cln.cdp.players.PlayerType;
import br.ifes.poo2.chess.cln.cdp.players.Computer;
import br.ifes.poo2.chess.cln.cdp.players.factories.PlayerFactory;

/**
 *
 * @author lucas_000
 */
public class AplChessMulti extends AplChess {

    public AplChessMulti(){
        super();
    }
    
    @Override
    public void newGame(String player1, String player2) {
        Player white_player, black_player;

        white_player = PlayerFactory.build(player1, PlayerType.HUMAN);
        black_player = PlayerFactory.build(player2, PlayerType.HUMAN);

        game = new Game(white_player, black_player);
    }

    @Override
    public Computer getCPU() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
