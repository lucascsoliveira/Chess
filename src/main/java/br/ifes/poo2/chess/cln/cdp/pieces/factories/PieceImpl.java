/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp.pieces.factories;

import br.ifes.poo2.chess.cln.cdp.Board;
import br.ifes.poo2.chess.cln.cdp.Position;
import br.ifes.poo2.chess.cln.cdp.pieces.Color;
import br.ifes.poo2.chess.cln.cdp.pieces.Piece;
import br.ifes.poo2.chess.cln.cdp.pieces.PieceName;
import br.ifes.poo2.chess.cln.cdp.pieces.strategies.Strategy;
import br.ifes.poo2.chess.cln.cdp.pieces.strategies.StrategyManager;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas_000
 */
class PieceImpl extends Observable implements Piece {

    private final Color color;
    private PieceName pieceName;
    private final int points;
    private Position position;
    private Strategy strategy;
    private Position lastPosition;

    public PieceImpl(Color color, PieceName pieceName) {
        this.color = color;
        this.pieceName = pieceName;
        this.points = pieceName.getPoints();
        //A posição ainda não é conhecida
        this.strategy = StrategyManager.getInstance().getStrategy(pieceName);
    }

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
        this.lastPosition = this.position;

        this.position = position;
        this.setChanged();
        this.notifyObservers();
    }

    public boolean canAttack(Board board, Position target) {
        return strategy.canAttack(board, position, target);
    }

    public boolean canMove(Board board, Position target) {
        return strategy.canMove(board, position, target);
    }

    public Position getLastMove() {
        return lastPosition;
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

    @Override
    public String toString() {
        String string;

        string = "["
                + this.color.toString().charAt(0)
                + "]"
                + pieceName.toString();

        return string;
    }

    public void setStrategy(PieceName pieceName) {
        this.pieceName = pieceName;
        strategy = StrategyManager.getInstance().getStrategy(pieceName);
    }

    public List<Position> getPath(Board board, Position original) {
        return this.strategy.getPath(board, original);
    }

}
