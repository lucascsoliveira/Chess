/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp.players.factories;

import br.ifes.poo2.chess.cln.cdp.players.Player;

/**
 *
 * @author Adelson
 */
class PlayerComputerFactory implements AbstractPlayerFactory {

    private static PlayerComputerFactory instance;

    private PlayerComputerFactory() {
    }

    public static PlayerComputerFactory getInstance() {
        if (instance == null) {
            instance = new PlayerComputerFactory();
        }

        return instance;
    }

    public Player createPlayer(String name) {
        return new PlayerComputer(name);
    }

}
