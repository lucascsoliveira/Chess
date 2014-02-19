/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cgt;

import br.ifes.poo2.chess.cln.cdp.ChessBoard;
import br.ifes.poo2.chess.cln.cdp.Game;
import br.ifes.poo2.chess.cln.cdp.Position;
import br.ifes.poo2.chess.cln.cdp.pieces.Color;
import br.ifes.poo2.chess.cln.cdp.players.Player;
import br.ifes.poo2.chess.cln.cdp.players.PlayerType;
import br.ifes.poo2.chess.cln.cdp.players.factories.PlayerFactory;
import br.ifes.poo2.chess.util.InvalidCommandException;
import br.ifes.poo2.chess.util.InvalidMoveException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 20101BSI0534
 */
public class AplChess {

    private Game game;

    private final String CPU_NAME = "ZEUS";

    public static final String ORIGINAL_POSITION = "original";
    public static final String TARGET_POSITION = "target";

    public void newGame(String player_name) {
        Player white_player, black_player;

        white_player = PlayerFactory.build(player_name, PlayerType.HUMAN);
        black_player = PlayerFactory.build(CPU_NAME, PlayerType.COMPUTER);

        game = new Game(white_player, black_player);

    }

    public void newGame(String whitePlayerName, String blackPlayerName) {
        Player white_player, black_player;

        white_player = PlayerFactory.build(whitePlayerName, PlayerType.HUMAN);
        black_player = PlayerFactory.build(blackPlayerName, PlayerType.HUMAN);

        game = new Game(white_player, black_player);

    }

    public void play(String play) throws InvalidMoveException, InvalidCommandException {
        //TODO: Implementar método play();
        if (play.matches(RegexChess.REGEX_ATTACK)) {

        } else if (play.matches(RegexChess.REGEX_MOVE)) {

        } else if (play.matches(RegexChess.REGEX_CHECK)) {

        } else if (play.matches(RegexChess.REGEX_CHECKMATE)) {

        } else if (play.matches(RegexChess.REGEX_PROMOTION)) {

        } else if (play.matches(RegexChess.REGEX_BIG_CASTLING)) {

        } else if (play.matches(RegexChess.REGEX_SMALL_CASTLING)) {

        } else {
            throw new InvalidCommandException();
        }
    }

    public Color getCurrentTurn() {
        return game.getTurn();
    }

    public Player getPlayerOfTurn() {
        return game.getPlayer(this.getCurrentTurn());
    }

    public ChessBoard getChessBoard() {
        return game.getChessBoard();
    }

    public boolean isGameOver() {
        return game.isGameOver();
    }

    private Map<String, Position> getPositions(String play) {
        HashMap<String, Position> mapPositions = new HashMap<String, Position>();

        String[] positions;

        positions = play.replaceAll(RegexChess.REGEX_ATTACK, "$1 $2 $3 $4").split(" ");
        Position original = new Position(Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));
        Position target = new Position(Integer.parseInt(positions[2]), Integer.parseInt(positions[3]));

        mapPositions.put(ORIGINAL_POSITION, original);
        mapPositions.put(TARGET_POSITION, target);

        return mapPositions;
    }

}
