/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cln.cdp;

import br.ifes.poo2.chess.cln.cdp.pieces.Color;
import br.ifes.poo2.chess.cln.cdp.pieces.PieceName;
import br.ifes.poo2.chess.cln.cdp.pieces.factories.PieceFactory;
import br.ifes.poo2.chess.cln.cdp.pieces.strategies.StrategyManager;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author lucas_000
 */
public class PieceTest {

    @Test
    public void createStrategies() {
        Assert.assertNotNull(StrategyManager.getInstance().getStrategy(PieceName.BISHOP));
        Assert.assertNotNull(StrategyManager.getInstance().getStrategy(PieceName.KING));
        Assert.assertNotNull(StrategyManager.getInstance().getStrategy(PieceName.KNIGHT));
        Assert.assertNotNull(StrategyManager.getInstance().getStrategy(PieceName.PAWN));
        Assert.assertNotNull(StrategyManager.getInstance().getStrategy(PieceName.QUEEN));
        Assert.assertNotNull(StrategyManager.getInstance().getStrategy(PieceName.ROOK));
    }

    @Test
    public void createPieces() {
        Assert.assertNotNull(PieceFactory.build(PieceName.BISHOP, Color.BLACK));
        Assert.assertNotNull(PieceFactory.build(PieceName.KING, Color.BLACK));
        Assert.assertNotNull(PieceFactory.build(PieceName.KNIGHT, Color.BLACK));
        Assert.assertNotNull(PieceFactory.build(PieceName.PAWN, Color.BLACK));
        Assert.assertNotNull(PieceFactory.build(PieceName.QUEEN, Color.BLACK));
        Assert.assertNotNull(PieceFactory.build(PieceName.ROOK, Color.BLACK));

        Assert.assertNotNull(PieceFactory.build(PieceName.BISHOP, Color.WHITE));
        Assert.assertNotNull(PieceFactory.build(PieceName.KING, Color.WHITE));
        Assert.assertNotNull(PieceFactory.build(PieceName.KNIGHT, Color.WHITE));
        Assert.assertNotNull(PieceFactory.build(PieceName.PAWN, Color.WHITE));
        Assert.assertNotNull(PieceFactory.build(PieceName.QUEEN, Color.WHITE));
        Assert.assertNotNull(PieceFactory.build(PieceName.ROOK, Color.WHITE));
    }

}
