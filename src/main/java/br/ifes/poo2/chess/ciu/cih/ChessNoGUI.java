/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.ciu.cih;

import br.ifes.poo2.chess.ciu.cci.ControllerChess;
import br.ifes.poo2.chess.util.InvalidCommandException;
import br.ifes.poo2.chess.util.InvalidMoveException;
import java.util.Scanner;

/**
 *
 * @author lucas_000
 */
public class ChessNoGUI {

    private ControllerChess controller;

    public ChessNoGUI() {
        controller = new ControllerChess();
    }

    private String readPlayerName(int player) {
        Scanner input = new Scanner(System.in);
        String name;

        do {
            Screen.inputPlayerName(player);
            name = input.nextLine();

            if (!name.matches("[a-zA-Z]+\\w*")) {
                Screen.nameError();
            }
        } while (!name.matches("[a-zA-Z]+\\w*"));

        return name;
    }

    private void singlePlayer() {
        String name = readPlayerName(1);

        controller.newSinglePlayerGame(name);
        startGame();
    }

    private void multiPlayer() {
        String[] namePlayer = new String[2];

        for (int counter = 0; counter < 2; counter++) {
            namePlayer[counter] = readPlayerName(counter + 1);
        }

        controller.newMultiPlayerGame(namePlayer[0], namePlayer[1]);
        startGame();
    }

    private void selectPlayers() {
        Scanner input = new Scanner(System.in);
        String option;

        do {
            Screen.selectGameMode();
            option = input.nextLine();

            if (option.equals("1")) {
                singlePlayer();
                break;
            } else if (option.equals("2")) {
                multiPlayer();
                break;
            } else if (option.equals("3")) {
                //Voltar
            } else {
                Screen.inputError();
            }
        } while (!option.equals("3"));
    }

    private void listDataOfPreviousMatches() {
        controller.dataOfPreviousMatches();
    }

    private void startGame() {
        Scanner input = new Scanner(System.in);

        while (!controller.isGameOver()) {
            //Exibe o turno (Cor - Player)
            controller.showTurn();
            //Exibe o tabuleiro
            controller.showBoard();

            try {
                //Lê jogada
                Screen.inputPlay();
                controller.play(input.nextLine());
            } catch (InvalidMoveException ex) {
                Screen.playInvalidMoveErro();
            } catch (InvalidCommandException ex) {
                Screen.playInvalidCommandErro();
            }
        }
    }

    public void run() {
        String option;
        Scanner input = new Scanner(System.in);

        do {
            Screen.mainMenu();
            option = input.nextLine();
            if (option.equals("1")) {
                selectPlayers();
            } else if (option.equals("2")) {
                listDataOfPreviousMatches();
            } else if (option.equals("3")) {
                Screen.goodbye();
            } else {
                Screen.inputError();
            }
        } while (!option.equals("3"));

    }

    public static void main(String[] args) {
        new ChessNoGUI().run();
    }
}
