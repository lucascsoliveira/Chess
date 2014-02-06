/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cdp.pieces.strategies;

import br.ifes.poo2.chess.cdp.Board;
import br.ifes.poo2.chess.cdp.ChessBoard;
import br.ifes.poo2.chess.cdp.Position;
import br.ifes.poo2.chess.cdp.pieces.Piece;

/**
 *
 * @author lucas_000
 */
class StrategyRook implements Strategy {

    public boolean canAttack(Board board, Position original, Position target) {
        if ((board.getPieceAtPosition(target) == null) || (original.equals(target))) {
            return false;
        } else if (original.getLine() == target.getLine()) {
            return canMoveOnHorizontal(board, original, target);
        } else if (original.getColumn() == target.getColumn()) {
            return canMoveOnVertical(board, original, target);
        } else {
            return false;
        }
    }

    public boolean canMove(Board board, Position original, Position target) {
        if ((board.getPieceAtPosition(target) != null) || (original.equals(target))) {
            return false;
        } else if (original.getLine() == target.getLine()) {
            return canMoveOnHorizontal(board, original, target);
        } else if (original.getColumn() == target.getColumn()) {
            return canMoveOnVertical(board, original, target);
        } else {
            return false;
        }
    }

    private boolean canMoveOnHorizontal(Board board, Position original, Position target) {
        int column = original.getColumn();

        if (target.getColumn() < column) {
            return atLeftOfHorizontal(board, original, target);
        } else {
            return atRightOfHorizontal(board, original, target);
        }
    }

    private boolean atLeftOfHorizontal(Board board, Position original, Position target) {
        int line = original.getLine();
        int column = original.getColumn() - 1;

        while (column >= ChessBoard.MIN_SIZE) {
            Piece piece = board.getPieceAtPosition(new Position(column, line));

            //Se a posição estiver ocupada
            if (piece != null) {
                //E, se for a posição final
                if (target.equals(new Position(column, line))) {
                    //Retorna true, pois atingiu o objetivo
                    return true;
                } //Senão
                else {
                    //Interrompe o loop, pois existe alguma peça no caminho
                    break;
                }
            } else {
                if (target.equals(new Position(column, line))) {
                    //Retorna true, pois atingiu o objetivo
                    return true;
                }
            }
            column--;
        }
        return false;
    }

    private boolean atRightOfHorizontal(Board board, Position original, Position target) {
        int line = original.getLine();
        int column = original.getColumn() + 1;

        while (column <= ChessBoard.MAX_SIZE) {
            Piece piece = board.getPieceAtPosition(new Position(column, line));

            //Se a posição estiver ocupada
            if (piece != null) {
                //E, se for a posição final
                if (target.equals(new Position(column, line))) {
                    //Retorna true, pois atingiu o objetivo
                    return true;
                } //Senão
                else {
                    //Interrompe o loop, pois existe alguma peça no caminho
                    break;
                }
            } else {
                if (target.equals(new Position(column, line))) {
                    //Retorna true, pois atingiu o objetivo
                    return true;
                }
            }
            column++;
        }
        return false;
    }

    private boolean canMoveOnVertical(Board board, Position original, Position target) {
        int line = original.getLine();

        if (original.getLine() < line) {
            return inTopOfVertical(board, original, target);
        } else {
            return inBottomOfVertical(board, original, target);
        }
    }

    private boolean inTopOfVertical(Board board, Position original, Position target) {
        int line = original.getLine() - 1;
        int column = original.getColumn();

        while (line >= ChessBoard.MIN_SIZE) {
            Piece piece = board.getPieceAtPosition(new Position(column, line));

            //Se a posição estiver ocupada
            if (piece != null) {
                //E, se for a posição final
                if (target.equals(new Position(column, line))) {
                    //Retorna true, pois atingiu o objetivo
                    return true;
                } //Senão
                else {
                    //Interrompe o loop, pois existe alguma peça no caminho
                    break;
                }
            } else {
                if (target.equals(new Position(column, line))) {
                    //Retorna true, pois atingiu o objetivo
                    return true;
                }
            }
            line--;
        }
        return false;
    }

    private boolean inBottomOfVertical(Board board, Position original, Position target) {
        int line = original.getLine();
        int column = original.getColumn() + 1;

        while (column <= ChessBoard.MAX_SIZE) {
            Piece piece = board.getPieceAtPosition(new Position(column, line));

            //Se a posição estiver ocupada
            if (piece != null) {
                //E, se for a posição final
                if (target.equals(new Position(column, line))) {
                    //Retorna true, pois atingiu o objetivo
                    return true;
                } //Senão
                else {
                    //Interrompe o loop, pois existe alguma peça no caminho
                    break;
                }
            } else {
                if (target.equals(new Position(column, line))) {
                    //Retorna true, pois atingiu o objetivo
                    return true;
                }
            }
            column++;
        }
        return false;
    }
}
