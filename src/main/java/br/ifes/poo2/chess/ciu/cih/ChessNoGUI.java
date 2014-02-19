/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.ciu.cih;

import br.ifes.poo2.chess.ciu.cci.ControllerChess;
import java.util.Iterator;
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

    private void singlePlayer() {
        Scanner input = new Scanner(System.in);
        String name;

        do {
            Screen.inputPlayerName(1);
            name = input.nextLine();

            if (!name.matches("[a-zA-Z]+\\w*")) {
                Screen.nameError();
            }
        } while (!name.matches("[a-zA-Z]+\\w*"));

        controller.newSinglePlayerGame(name);
    }

    private void multiPlayer() {
        Scanner input = new Scanner(System.in);
        String[] namePlayer = new String[2];

        for (int counter = 0; counter < 2; counter++) {
            do {
                Screen.inputPlayerName(counter + 1);
                namePlayer[counter] = input.nextLine();

                if (!namePlayer[counter].matches("[a-zA-Z]+\\w*")) {
                    Screen.nameError();
                }
            } while (!namePlayer[counter].matches("[a-zA-Z]+\\w*"));
        }

        controller.newMultiPlayerGame(namePlayer[0], namePlayer[1]);
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

    public void start() {
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
        new ChessNoGUI().start();
    }

}
