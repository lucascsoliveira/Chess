/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp.players;

import br.ifes.poo2.chess.cln.cdp.Game;

/**
 *
 * @author Adelson
 */
public interface Player {

    public void addDefeat();

    public int getDefeats();

    public void addDraw();

    public int getDraws();

    public String getName();

    public void setName(String name);

    public void addVictory();

    public int getVictories();

    public String play(Game game);

}
