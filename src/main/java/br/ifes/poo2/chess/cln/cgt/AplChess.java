/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.cln.cgt;

import br.ifes.poo2.chess.ciu.cih.Screen;
import br.ifes.poo2.chess.cln.cdp.ChessBoard;
import br.ifes.poo2.chess.cln.cdp.Game;
import br.ifes.poo2.chess.cln.cdp.Position;
import br.ifes.poo2.chess.cln.cdp.pieces.Color;
import br.ifes.poo2.chess.cln.cdp.pieces.PieceName;
import br.ifes.poo2.chess.cln.cdp.players.Player;
import br.ifes.poo2.chess.cln.cdp.players.Computer;
import br.ifes.poo2.chess.util.InvalidCommandException;
import br.ifes.poo2.chess.util.InvalidMoveException;
import br.ifes.poo2.chess.util.InvalidPromotionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 20101BSI0534
 */
public abstract class AplChess {

    protected List<Game> games;

    public AplChess() {
        games = new ArrayList<Game>();
    }

    
    public List<Game> getGames() {
        return games;
    }
    
    protected Game game;

    public static final String CPU_NAME = "ZEUS";

    private static final String ORIGINAL_POSITION = "original";
    private static final String TARGET_POSITION = "target";
    private static final String PROMOTION_NAME = "promotion_name";

    public abstract void newGame(String player1, String player2);

    public void play(String play) throws InvalidMoveException, InvalidCommandException, InvalidPromotionException {

        if (play.matches(RegexChess.REGEX_MOVEMENT)) {
            movement(play);
        } else if (play.matches(RegexChess.REGEX_PROMOTION)) {
            promotion(play);
        } else if (play.matches(RegexChess.REGEX_BIG_CASTLING)) {
            bigCastling();
        } else if (play.matches(RegexChess.REGEX_SMALL_CASTLING)) {
            smallCastling();
        } else if (play.equalsIgnoreCase("salvar")) {
            saveGame();
            previousTurn();
        } else if (play.equalsIgnoreCase("sair")) {
            game.stop();
        } else if (play.equalsIgnoreCase("desistir")) {
            game.giveUp(game.getTurn());
            saveGame();
            Screen.giveUp();
        } else if (play.equalsIgnoreCase("empatar")) {
            game.draw();
            saveGame();
            Screen.draw();
        } else {
            throw new InvalidCommandException();
        }

        game.nextTurn();
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

    private Map getObjects(String play) {
        HashMap<String, Object> mapObject = new HashMap<String, Object>();

        String[] objects;

        objects = play.replaceAll(RegexChess.REGEX_PROMOTION, "$1 $2 $3").split(" ");
        Position original = new Position(Integer.parseInt(objects[0]), Integer.parseInt(objects[1]));

        PieceName pieceName = getPieceName(objects[2]);

        mapObject.put(ORIGINAL_POSITION, original);
        mapObject.put(PROMOTION_NAME, pieceName);

        return mapObject;
    }

    public abstract Computer getCPU();
    
    private PieceName getPieceName(String string) {
        if (string.equalsIgnoreCase("b")) {
            return PieceName.BISHOP;
        } else if (string.equalsIgnoreCase("c")) {
            return PieceName.KNIGHT;
        } else if (string.equalsIgnoreCase("d")) {
            return PieceName.QUEEN;
        } else {
            return PieceName.ROOK;
        }
    }

    private Map<String, Position> getPositions(String play) {
        HashMap<String, Position> mapPositions = new HashMap<String, Position>();

        String[] positions;

        positions = play.replaceAll(RegexChess.REGEX_MOVEMENT, "$1 $2 $3 $4").split(" ");
        Position original = new Position(Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));
        Position target = new Position(Integer.parseInt(positions[2]), Integer.parseInt(positions[3]));

        mapPositions.put(ORIGINAL_POSITION, original);
        mapPositions.put(TARGET_POSITION, target);

        return mapPositions;
    }

    private void movement(String play) throws InvalidMoveException {
        Boolean cantMove, cantAttack;
        cantAttack = cantMove = false;

        Map map;
        map = getPositions(play);

        Position original = (Position) map.get(ORIGINAL_POSITION);
        Position target = (Position) map.get(TARGET_POSITION);

        try {
            game.getChessBoard().attack(game.getTurn(), original, target);
        } catch (InvalidMoveException ex) {
            cantAttack = true;
        }
        try {
            game.getChessBoard().move(game.getTurn(), original, target);
        } catch (InvalidMoveException ex) {
            cantMove = true;
        }

        if (cantAttack && cantMove) {
            throw new InvalidMoveException();
        }
    }

    private void promotion(String play) throws InvalidPromotionException {
        Map map;
        map = getObjects(play);

        Position original = (Position) map.get(ORIGINAL_POSITION);
        PieceName pieceName = (PieceName) map.get(PROMOTION_NAME);

        game.getChessBoard().promotion(original, pieceName);
    }

    private void bigCastling() throws InvalidMoveException {
        game.getChessBoard().bigCastling(game.getTurn());
    }

    private void smallCastling() throws InvalidMoveException {
        game.getChessBoard().smallCastling(game.getTurn());
    }

    private void saveGame() {
        if(games.contains(game)){
           games.remove(game);
        }
        games.add(game);
    }

    private void previousTurn() {
        game.nextTurn();
    }

    public Game getGame() {
        return game;
    }

}
