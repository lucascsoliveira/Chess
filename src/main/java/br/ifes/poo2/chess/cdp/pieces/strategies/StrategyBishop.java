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
class StrategyBishop implements Strategy {

    public boolean canAttack(Board board, Position original, Position target) {
        if ((board.getPieceAtPosition(target) == null) || (original.equals(target))) {
            return false;
        } else {
            return inMainDiagonal(board, original, target) || inSecondaryDiagonal(board, original, target);
        }
    }

    public boolean canMove(Board board, Position original, Position target) {
        if ((board.getPieceAtPosition(target) != null) || (original.equals(target))) {
            return false;
        } else {
            return inMainDiagonal(board, original, target) || inSecondaryDiagonal(board, original, target);
        }
    }

    private boolean inMainDiagonal(Board board, Position original, Position target) {
        int originalLine, targetLine, originalColumn, targetColumn;

        originalLine = original.getLine();
        originalColumn = original.getColumn();
        targetLine = target.getLine();
        targetColumn = target.getColumn();

        //Se está na parte superior da diagonal
        if ((targetLine < originalLine) && (targetColumn < originalColumn)) {
            return inTopOfMainDiagonal(board, original, target);
        } //Se está na parte inferior da diagonal
        else if ((targetLine > originalLine) && (targetColumn > originalColumn)) {
            return inBottomOfMainDiagonal(board, original, target);
        }

        //Retorna false, pois a posição final não faz parte de nenhuma das diagonais
        return false;
    }

    private boolean inTopOfMainDiagonal(Board board, Position original, Position target) {
        int originaLine = original.getLine() - 1;
        int originalColumn = original.getColumn() - 1;

        //Enquanto a linha e a coluna estiverem dentro do tabuleiro..
        while (originaLine >= ChessBoard.MIN_SIZE && originalColumn >= ChessBoard.MIN_SIZE) {
            Piece piece = board.getPieceAtPosition(new Position(originalColumn, originaLine));

            //Se a posição estiver ocupada
            if (piece != null) {
                //Verifica se já chegou a posição final
                if (target.equals(new Position(originalColumn, originaLine))) {
                    //Se sim, retorna true
                    return true;
                } else {
                    //Se não, interrompe o loop, pois existe alguma peça no caminho
                    break;
                }
            } //Se a posição estiver vazia
            else {
                //Verifica se já chegou na posição final
                if (target.equals(new Position(originalColumn, originaLine))) {
                    //Se sim, retorna true
                    return true;
                }
                //Se não, continua as iterações..
            }
            originaLine--;
            originalColumn--;
        }
        return false;
    }

    private boolean inBottomOfMainDiagonal(Board board, Position original, Position target) {
        int originalLine = original.getLine() + 1;
        int originalColumn = original.getColumn() + 1;
        //Enquanto a linha e a coluna estiverem dentro do tabuleiro..
        while (originalLine <= ChessBoard.MAX_SIZE && originalColumn <= ChessBoard.MAX_SIZE) {
            Piece piece = board.getPieceAtPosition(new Position(originalColumn, originalLine));

            //Se a posição estiver ocupada
            if (piece != null) {
                //Verifica se já chegou a posição final
                if (target.equals(new Position(originalColumn, originalLine))) {
                    //Se sim, retorna true
                    return true;
                } else {
                    //Se não, interrompe o loop, pois existe alguma peça no caminho
                    break;
                }
            } //Se a posição estiver vazia
            else {
                //Verifica se já chegou na posição final
                if (target.equals(new Position(originalColumn, originalLine))) {
                    //Se sim, retorna true
                    return true;
                }
                //Se não, continua as iterações..
            }

            originalLine++;
            originalColumn++;
        }
        return false;
    }

    private boolean inSecondaryDiagonal(Board board, Position original, Position target) {
        int originalLine, targetLine, originalColumn, targetColumn;

        originalLine = original.getLine();
        originalColumn = original.getColumn();
        targetLine = target.getLine();
        targetColumn = target.getColumn();

        //Se está na parte superior da diagonal
        if ((targetLine < originalLine) && (targetColumn > originalColumn)) {
            return inTopOfSecondaryDiagonal(board, original, target);
        } //Se está na parte inferior da diagonal
        else if ((targetLine > originalLine) && (targetColumn < originalColumn)) {
            return inBottomOfSecondaryDiagonal(board, original, target);
        }

        //Retorna false, pois a posição final não está em nenhuma das diagonais
        return false;
    }

    private boolean inTopOfSecondaryDiagonal(Board board, Position posIni, Position posFin) {
        int originalLine = posIni.getLine() - 1;
        int originalColumn = posIni.getColumn() + 1;

        //Enquanto a linha e a coluna estiverem dentro do tabuleiro..
        while (originalLine >= ChessBoard.MIN_SIZE && originalColumn <= ChessBoard.MAX_SIZE) {
            Piece piece = board.getPieceAtPosition(new Position(originalColumn, originalLine));

            //Se a posição estiver ocupada
            if (piece != null) {
                //Verifica se já chegou a posição final
                if (posFin.equals(new Position(originalColumn, originalLine))) {
                    //Se sim, retorna true
                    return true;
                } else {
                    //Se não, interrompe o loop, pois existe alguma peça no caminho
                    break;
                }
            } //Se a posição estiver vazia
            else {
                //Verifica se já chegou na posição final
                if (posFin.equals(new Position(originalColumn, originalLine))) {
                    //Se sim, retorna true
                    return true;
                }
                //Se não, continua as iterações..
            }
            originalLine--;
            originalColumn++;
        }
        return false;
    }

    private boolean inBottomOfSecondaryDiagonal(Board board, Position original, Position target) {
        int originalLine = original.getLine() + 1;
        int originalColumn = original.getColumn() - 1;

        //Enquanto a linha e a coluna estiverem dentro do tabuleiro..
        while (originalLine <= ChessBoard.MAX_SIZE && originalColumn >= ChessBoard.MIN_SIZE) {
            Piece piece = board.getPieceAtPosition(new Position(originalColumn, originalLine));

            //Se a posição estiver ocupada
            if (piece != null) {
                //Verifica se já chegou a posição final
                if (target.equals(new Position(originalColumn, originalLine))) {
                    //Se sim, retorna true
                    return true;
                } else {
                    //Se não, interrompe o loop, pois existe alguma peça no caminho
                    break;
                }
            } //Se a posição estiver vazia
            else {
                //Verifica se já chegou na posição final
                if (target.equals(new Position(originalColumn, originalLine))) {
                    //Se sim, retorna true
                    return true;
                }
                //Se não, continua as iterações..
            }
            originalLine++;
            originalColumn--;
        }
        return false;
    }

}
