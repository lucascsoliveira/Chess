/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cdp.pieces.strategies;

import br.ifes.poo2.chess.cln.cdp.Board;
import br.ifes.poo2.chess.cln.cdp.ChessBoard;
import br.ifes.poo2.chess.cln.cdp.Position;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20101BSI0534
 */
abstract class StrategyImpl implements Strategy {
    
    public List<Position> getPath(Board board, Position original){
        ArrayList<Position> listOfPositions = new ArrayList<Position>();
        
        for(int line=ChessBoard.MIN_SIZE; line <=ChessBoard.MAX_SIZE; line++){
            for(int column=ChessBoard.MIN_SIZE; column <=ChessBoard.MAX_SIZE; column++){
                Position position = new Position(column, line);
                
                if(canAttack(board, original, position) || canMove(board, original, position)){
                    listOfPositions.add(position);
                }
            }
        }
        
        return listOfPositions;
    }
    
}
