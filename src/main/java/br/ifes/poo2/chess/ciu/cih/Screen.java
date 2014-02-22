/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.ciu.cih;

import br.ifes.poo2.chess.cln.cdp.ChessBoard;

/**
 *
 * @author lucas_000
 */
public class Screen {

    public static void show(ChessBoard chessBoard) {
        System.out.println(chessBoard);
    }

    public static void show(String string) {
        System.out.println(string);
    }

    public static void mainMenu() {
        System.out.println("--------- Menu ---------");
        System.out.println("1.  Iniciar nova partida;");
        System.out.println("2.  Retomar uma partida;");
        System.out.println("3.  Dados das partidas;");
        System.out.println("4.  Sair;");
        System.out.println("");
        System.out.print("Opcao: ");
    }

    public static void selectGameMode() {
        System.out.println("---------  ---------");
        System.out.println("1. Player vs ZEUS (CPU)");
        System.out.println("2. Player vs Player");
        System.out.println("3. Voltar");

        System.out.println("");
        System.out.print("Opcao: ");
    }

    public static void inputPlayerName(int number) {
        System.out.println("--------- Player " + number + " ---------");
        System.out.print("Insira nome: ");
    }

    public static void inputError() {
        System.out.println("ERRO: Entrada inválida!");
        System.out.println("-> Leia atentamente as opções!");
    }

    public static void nameError() {
        System.out.println("ERRO: Nome inválido!");
        System.out.println("-> Nome precisa iniciar com letra, e conter ao menos um caracter!");
    }

    public static void goodbye() {
        System.out.println("Até logo..!");
    }

    public static void inputPlay() {
        System.out.print("Insira sua jogada: ");
    }

    static void playInvalidMoveErro() {
        System.out.println("Erro: O movimento não é válido, ou esta peça não é sua.");
    }

    static void playInvalidCommandErro() {
        System.out.println("Erro: O comando inserido é inválido!");
    }
}
