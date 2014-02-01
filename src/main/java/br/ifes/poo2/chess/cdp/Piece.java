/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp;

/**
 *
 * @author lucas_000
 */
public interface Piece extends Cloneable {

    public Color getColor();

//    public void setColor(Color clor);

    public PieceName getName();

    public int getPoints();

//    public Position getLastMove();

    public Position getPosition();

    public void setPosition(Position position);

    public boolean canAttack(Piece[][] board, Position target);

    public boolean canMove(Piece[][] board, Position target);

    public Object clone(); //TODO: Tratar ou ignorar a exceção?

}
