/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp.players.factories;

import br.ifes.poo2.chess.cdp.players.Player;
import br.ifes.poo2.chess.cdp.players.PlayerType;

/**
 *
 * @author Adelson
 */
public class PlayerFactory {

    public static Player build(String name, PlayerType type) {
        if (type.equals(PlayerType.COMPUTER)) {
            return PlayerComputerFactory.getInstance().createPlayer(name);
        } else {
            return PlayerHumanFactory.getInstance().createPlayer(name);
        }
    }

}
