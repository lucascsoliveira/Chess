/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp.pieces.factories;

import br.ifes.poo2.chess.cln.cdp.pieces.Color;

/**
 *
 * @author lucas_000
 */
class WhitePieceFactory extends AbstractPieceFactoryImpl {

    private static WhitePieceFactory instance;

    private WhitePieceFactory() {
        super(Color.WHITE);
    }

    public static WhitePieceFactory getInstance() {
        if (instance == null) {
            instance = new WhitePieceFactory();
        }

        return instance;
    }

}
