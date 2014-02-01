/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp.pieces.factories;

import br.ifes.poo2.chess.cdp.Position;
import br.ifes.poo2.chess.cdp.pieces.Color;
import br.ifes.poo2.chess.cdp.pieces.Piece;
import br.ifes.poo2.chess.cdp.pieces.PieceName;
import br.ifes.poo2.chess.cdp.pieces.strategies.Strategy;
import br.ifes.poo2.chess.cdp.pieces.strategies.StrategyManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas_000
 */
class PieceImpl implements Piece {

    private final Color color;
    private final PieceName pieceName;
    private final int points;
    private Position position;
    private Strategy strategy; //TODO: [Dúvida] Como fazer a alteração da strategy in-game?

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
