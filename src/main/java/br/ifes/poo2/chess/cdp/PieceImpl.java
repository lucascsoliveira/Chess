/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp;

import br.ifes.poo2.chess.cdp.strategies.*;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas_000
 */
public class PieceImpl extends Observable implements Piece {

    private Color color;
    private PieceName pieceName;
    private int points;
    private Position position;
    private Strategy strategy;

    //TODO: Criar construtor (quais parâmetros? recebe observadores também?)
    
    public Color getColor() {
        return color;
    }

    public PieceName getName() {
        return pieceName;
    }

    public int getPoints() {
        return points;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean canAttack(Piece[][] board, Position target) {
        return strategy.canAttack(board, position, target);
    }

    public boolean canMove(Piece[][] board, Position target) {
        return strategy.canMove(board, position, target);
    }

    @Override
    public Object clone() {
        Object obj = null;

        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(PieceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

}
