/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cln.cdp;

import br.ifes.poo2.chess.cln.cdp.Game;
import br.ifes.poo2.chess.cln.cdp.pieces.Color;
import br.ifes.poo2.chess.cln.cdp.players.PlayerType;
import br.ifes.poo2.chess.cln.cdp.players.factories.PlayerFactory;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lucas_000
 */
public class GameTest {
    private Game game;
    
    @Before
    public void before(){
        game = new Game(PlayerFactory.build("1", PlayerType.HUMAN), PlayerFactory.build("2", PlayerType.HUMAN));
    }
    
    @Test
    public void nextTurn(){
        Assert.assertEquals(Color.WHITE, game.getTurn());
        game.nextTurn();
        Assert.assertEquals(Color.BLACK, game.getTurn());
    }
}
