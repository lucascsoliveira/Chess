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
public enum PieceName {

    BISHOP, KING, KNIGHT, PAWN, QUEEN, ROOK;

    @Override
    public String toString() {
        String name = super.toString();
        //Transforma a string toda para minúsculo
        name = name.toLowerCase();
        //Capitaliza a string
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        return name;
    }

}
