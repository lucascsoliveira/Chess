/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp;

import br.ifes.poo2.chess.cdp.pieces.Color;
import br.ifes.poo2.chess.cdp.players.Player;
import java.util.Calendar;
import java.util.Map;

/**
 *
 * @author Adelson
 */
public class Game {

    private ChessBoard chessBoard;
    private Calendar startTime;
    private Calendar endTime;
    private Map<Color, Player> players;
    private Player winner;

    private Color turn;

    public void nextTurn() {
        if (turn.equals(Color.BLACK)) {
            turn = Color.WHITE;
        } else {
            turn = Color.BLACK;
        }
    }

}
