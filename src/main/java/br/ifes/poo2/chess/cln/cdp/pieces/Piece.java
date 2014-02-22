/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp.pieces;

import br.ifes.poo2.chess.cln.cdp.Board;
import br.ifes.poo2.chess.cln.cdp.Position;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author lucas_000
 */
public interface Piece extends Cloneable {

    public Color getColor();

    public PieceName getName();

    public int getPoints();

    public Position getLastMove();

    public Position getPosition();

    public void setPosition(Position position);

    public boolean canAttack(Board board, Position target);

    public boolean canMove(Board board, Position target);

    public void setStrategy(PieceName pieceName);

    public Object clone();

    public void addObserver(Observer o);
    
    public List<Position> getPath(Board board, Position original);

}
