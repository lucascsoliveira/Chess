/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp.pieces.strategies;

import br.ifes.poo2.chess.cln.cdp.pieces.PieceName;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20101BSI0534
 */
public class StrategyManager {

    private static StrategyManager instance;
    private final Map<PieceName, Strategy> strategies;

    private StrategyManager() {
        strategies = new HashMap<PieceName, Strategy>();
    }

    public static StrategyManager getInstance() {
        if (instance == null) {
            instance = new StrategyManager();
        }

        return instance;
    }

    public Strategy getStrategy(PieceName pieceName) {
        Strategy strategy = null;

        if (strategies.containsKey(pieceName)) {
            strategy = strategies.get(pieceName);
        } else {
            String nameClass = Strategy.class.getName();
            nameClass += pieceName.toString();

            try {
                strategy = (Strategy) Class.forName(nameClass).newInstance();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StrategyManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(StrategyManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(StrategyManager.class.getName()).log(Level.SEVERE, null, ex);
            }

            strategies.put(pieceName, strategy);
        }

        return strategy;
    }

}
