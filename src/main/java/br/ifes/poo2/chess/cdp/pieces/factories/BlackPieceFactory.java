/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp.pieces.factories;

import br.ifes.poo2.chess.cdp.pieces.Color;

/**
 *
 * @author lucas_000
 */
class BlackPieceFactory extends AbstractPieceFactoryImpl {

    private static BlackPieceFactory instance;

    private BlackPieceFactory() {
        super(Color.BLACK);
    }

    public static BlackPieceFactory getInstance() {
        if (instance == null) {
            instance = new BlackPieceFactory();
        }

        return instance;
    }

}
