/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cgt;

/**
 *
 * @author lucas_000
 */
public class RegexChess {

    private static final String LINE = "([1-8])";
    private static final String COLUMN = "([1-8])";
    private static final String POSITION = COLUMN + LINE;

    private static final String ATTACK = "[xX]";
    private static final String PIECES_PROMOTION = "([bBcCdDtT])";
    private static final String CHECK = "[+]";
    private static final String CHECKMATE = "[#]";
    private static final String PROMOTION = "[=]";

    //REGEX
    public static final String REGEX_MOVE = POSITION + POSITION;
    public static final String REGEX_ATTACK = POSITION + ATTACK + POSITION;
    public static final String REGEX_BIG_CASTLING = "[oO]-[oO]-[oO]";
    public static final String REGEX_SMALL_CASTLING = "[oO]-[oO]";
    public static final String REGEX_PROMOTION = POSITION + PROMOTION + PIECES_PROMOTION;
    public static final String REGEX_CHECK = POSITION + ATTACK + "?" + POSITION + CHECK;
    public static final String REGEX_CHECKMATE = POSITION + ATTACK + "?" + POSITION + CHECKMATE;

    private RegexChess() {
    }

}
