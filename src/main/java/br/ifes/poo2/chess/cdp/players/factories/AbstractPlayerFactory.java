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
interface AbstractPlayerFactory {

    public Player createPlayer(String name);

}