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
abstract class PlayerImpl implements Player {

    private String name;
    private int defeats;
    private int draws;
    private int victories;

    public PlayerImpl(String name) {
        this.name = name;
        defeats = 0;
        draws = 0;
        victories = 0;
    }

    public void addDefeat() {
        defeats++;
    }

    public int getDefeats() {
        return defeats;
    }

    public void addDraw() {
        draws++;
    }

    public int getDraws() {
        return draws;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addVictory() {
        victories++;
    }

    public int getVictories() {
        return victories;
    }

}
