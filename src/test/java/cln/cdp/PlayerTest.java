/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cln.cdp;

import br.ifes.poo2.chess.cln.cdp.players.Player;
import br.ifes.poo2.chess.cln.cdp.players.PlayerType;
import br.ifes.poo2.chess.cln.cdp.players.factories.PlayerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author lucas_000
 */
public class PlayerTest {

    @Test
    public void createPlayers() {
        String nameCPU = "Computer";
        String nameHuman = "Human";

        Player playCPU = PlayerFactory.build(nameCPU, PlayerType.COMPUTER);
        Player playHuman = PlayerFactory.build(nameHuman, PlayerType.HUMAN);

        Assert.assertEquals(nameCPU, playCPU.getName());
        Assert.assertEquals(nameHuman, playHuman.getName());
    }
    
}
