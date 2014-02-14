/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp.pieces;

/**
 *
 * @author lucas_000
 */
public enum PieceName {

    BISHOP(3, "Bishop"),
    KING(0, "King"),
    KNIGHT(3, "Knight"),
    PAWN(1, "Pawn"),
    QUEEN(9, "Queen"),
    ROOK(5, "Rook");

    private final int points;
    private final String name;

    private PieceName(int points, String name) {
        this.points = points;
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
