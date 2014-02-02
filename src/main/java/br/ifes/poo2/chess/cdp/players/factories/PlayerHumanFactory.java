/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp.players.factories;

import br.ifes.poo2.chess.cdp.players.Player;

/**
 *
 * @author Adelson
 */
class PlayerHumanFactory implements AbstractPlayerFactory {

    private static PlayerHumanFactory instance;

    private PlayerHumanFactory() {
    }

    public static PlayerHumanFactory getInstance() {
        if (instance == null) {
            instance = new PlayerHumanFactory();
        }

        return instance;
    }

    public Player createPlayer(String name) {
        return new PlayerHuman(name);
    }

}
