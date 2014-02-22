/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.ciu.cci;

import br.ifes.poo2.chess.ciu.cih.Screen;
import br.ifes.poo2.chess.cln.cdp.pieces.Color;
import br.ifes.poo2.chess.cln.cgt.AplChess;
import br.ifes.poo2.chess.cln.cgt.AplChessMulti;
import br.ifes.poo2.chess.cln.cgt.AplChessSingle;
import br.ifes.poo2.chess.util.InvalidCommandException;
import br.ifes.poo2.chess.util.InvalidMoveException;
import br.ifes.poo2.chess.util.InvalidPromotionException;

/**
 *
 * @author lucas_000
 */
public class ControllerChess {

    private AplChess aplChess;

    public void newSinglePlayerGame(String name) {
        aplChess = new AplChessSingle();

        aplChess.newGame(name);
    }

    public void newMultiPlayerGame(String namePlayer1, String namePlayer2) {
        aplChess = new AplChessMulti();

        aplChess.newGame(namePlayer1, namePlayer2);
    }

    public void dataOfPreviousMatches() {
        //TODO: Pegar informações do BD e usar o método show() da classe Screen
    }

    public void showBoard() {
        Screen.show(aplChess.getChessBoard());
    }

    public void showTurn() {
        Color color = aplChess.getCurrentTurn();
        String name = aplChess.getPlayerOfTurn().getName();

        Screen.show("Turno: " + name + " - " + color.toString());
    }

    public boolean isGameOver() {
        return aplChess.isGameOver();
    }

    public void play(String nextLine) throws InvalidMoveException, InvalidCommandException, InvalidPromotionException {
        aplChess.play(nextLine);
    }
}
