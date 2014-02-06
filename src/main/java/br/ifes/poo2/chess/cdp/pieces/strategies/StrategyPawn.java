/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp.pieces.strategies;

import br.ifes.poo2.chess.cdp.Board;
import br.ifes.poo2.chess.cdp.Position;
import br.ifes.poo2.chess.cdp.pieces.Color;
import br.ifes.poo2.chess.cdp.pieces.Piece;

/**
 *
 * @author lucas_000
 */
class StrategyPawn implements Strategy {

    //TODO: State? (Peão movido, ou ainda não movido)
    
    public boolean canAttack(Board board, Position original, Position target) {
        if ((board.getPieceAtPosition(target) == null) || (original.equals(target))) {
            return false;
        } else {
            Piece piece;
            piece = board.getPieceAtPosition(new Position(original.getColumn(), original.getLine()));

            if (piece.getColor().equals(Color.BLACK)) {
                return canBlackPawnAttack(board, original, target);
            } else {
                return canWhitePawnAttack(board, original, target);
            }
        }
    }

    public boolean canMove(Board board, Position original, Position target) {
        if ((board.getPieceAtPosition(target) != null) || (original.equals(target))) {
            return false;
        } else {
            Piece piece;
            piece = board.getPieceAtPosition(new Position(original.getColumn(), original.getLine()));

            if (piece.getColor().equals(Color.BLACK)) {
                return canBlackPawnMove(board, original, target);
            } else {
                return canWhitePawnMove(board, original, target);
            }
        }
    }

    private boolean canBlackPawnAttack(Board board, Position original, Position target) {
        int line, column;
        Piece piece;

        line = original.getLine();
        column = original.getColumn();

        piece = board.getPieceAtPosition(new Position(column, line));

        //Se a posição final estiver vazia
        if (piece != null) {
            //Verifica se a posição final está diagonalmente à esquerda
            if (target.equals(new Position(column - 1, line - 1))) {
                return true;
            }
            //Ou se está diagonalmente à direta
            if (target.equals(new Position(column + 1, line - 1))) {
                return true;
            }
        }
        //Retorna false, se não houver caminho válido para atacar;
        return false;
    }

    private boolean canWhitePawnAttack(Board board, Position original, Position target) {
        int line, column;
        Piece piece;

        line = original.getLine();
        column = original.getColumn();

        piece = board.getPieceAtPosition(new Position(column, line));

        //Se a posição final estiver vazia
        if (piece != null) {
            //Verifica se a posição final está diagonalmente à esquerda
            if (target.equals(new Position(column - 1, line + 1))) {
                return true;
            }
            //Ou se está diagonalmente à direta
            if (target.equals(new Position(column + 1, line + 1))) {
                return true;
            }
        }
        //Retorna false, se não houver caminho válido para atacar;
        return false;
    }

    private boolean canBlackPawnMove(Board board, Position original, Position target) {
        //TODO: Implementar método canBlackPawnMove
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean canWhitePawnMove(Board board, Position posIni, Position posFin) {
        //TODO: Implementar método canWhitePawnMove
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
