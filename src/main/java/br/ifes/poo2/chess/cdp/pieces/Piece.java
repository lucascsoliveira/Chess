/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp.pieces;

import br.ifes.poo2.chess.cdp.Position;
import java.util.Observer;

/**
 *
 * @author lucas_000
 */
public interface Piece extends Cloneable {

    public Color getColor();

    public PieceName getName();

    public int getPoints();

//    public Position getLastMove();
    public Position getPosition();

    public void setPosition(Position position);

    public boolean canAttack(Piece[][] board, Position target);

    public boolean canMove(Piece[][] board, Position target);

    //TODO: [Dúvida] Qual método de alterar strategy devo usar? Posso fazer isso em Piece?
//    public void changeStrategy(Strategy strategy);
//    public void changeStrategy(PieceName pieceName);
    public Object clone();

    public void addObserver(Observer o);

}
