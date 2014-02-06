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
        int line = original.getLine();
        int column = original.getColumn();
        Piece onOriginal = board.getPieceAtPosition(new Position(original.getColumn(), original.getLine()));
        Piece onTarget = board.getPieceAtPosition(new Position(target.getColumn(), target.getLine()));

        //Se a posição final estiver vazia
        if (onTarget == null) {
            //Se for se mover uma casa
            if (target.equals(new Position(column, line - 1))) {
                return true;
            }

            //Se o peão ainda não se moveu
            if (onOriginal.getLastMove() == null) {
                //Se a posição target é duas casas a frente
                if (target.equals(new Position(column, line - 2))
                        //E a posição uma casa a frente está vazia
                        && board.isPositionEmpty(new Position(column, line - 1))) {
                    return true;
                }
            }
        }

        //Retorna false, se não houver caminho válido para mover;
        return false;
    }

    private boolean canWhitePawnMove(Board board, Position original, Position target) {
        int line = original.getLine();
        int column = original.getColumn();
        Piece onOriginal = board.getPieceAtPosition(new Position(original.getColumn(), original.getLine()));
        Piece onTarget = board.getPieceAtPosition(new Position(target.getColumn(), target.getLine()));

        //Se a posição final estiver vazia
        if (onTarget == null) {
            //Se for se mover uma casa
            if (target.equals(new Position(column, line + 1))) {
                return true;
            }

            //Se o peão ainda não se moveu
            if (onOriginal.getLastMove() == null) {
                //Se a posição target é duas casas a frente
                if (target.equals(new Position(column, line + 2))
                        //E a posição uma casa a frente está vazia
                        && board.isPositionEmpty(new Position(column, line + 1))) {
                    return true;
                }
            }
        }

        //Retorna false, se não houver caminho válido para mover;
        return false;
    }

}
