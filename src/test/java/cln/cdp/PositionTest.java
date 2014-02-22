/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cln.cdp;

import br.ifes.poo2.chess.cln.cdp.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lucas_000
 */
public class PositionTest {

    private Position position;
    private final int COLUMN = 1;
    private final int LINE = 1;

    @Before
    public void before() {
        position = new Position(COLUMN, LINE);
    }

    @Test
    public void testEquals() {
        //Cria posição com COLUMN e LINE iguais a position
        Position testPosition = new Position(1, 1);

        //Verifica se elas são consideradas iguais
        Assert.assertEquals(position, testPosition);
    }

    @Test
    public void testNotEquals() {
        //Cria posição com COLUMN ou LINE diferente
        Position testPosition = new Position(2, 1);

        //Verifica se elas são consideradas diferentes
        Assert.assertNotSame(position, testPosition);
    }

}
