/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.poo2.chess.ciu.cih;

import br.ifes.poo2.chess.cln.cdp.Board;

/**
 *
 * @author lucas_000
 */
public class Screen {

    public static void show(Board board) {

    }

    public static void show() {

    }

    public static void mainMenu() {
        System.out.println("--------- Menu ---------");
        System.out.println("1.  Iniciar nova partida;");
        System.out.println("2.  Dados das partidas;");
        System.out.println("3.  Sair;");
        System.out.println("");
        System.out.print("Opcao: ");
    }

    public static void selectGameMode() {
        System.out.println("---------  ---------");
        System.out.println("1. Um jogador");
        System.out.println("2. Dois jogadores");
        System.out.println("3. Voltar");

        System.out.println("");
        System.out.print("Opcao: ");
    }

    public static void inputPlayerName(int number) {
        System.out.println("--------- Jogador " + number + " ---------");
        System.out.print("Insira nome: ");
    }

    public static void inputError() {
        System.out.println("ERRO: Entrada inválida!");
    }

    public static void nameError() {
        System.out.println("ERRO: Nome inválido!");
        System.out.println("-> Nome precisa iniciar com letra, e conter ao menos um caracter!");
    }

    public static void goodbye() {
        System.out.println("Até logo..!");
    }

//    public static void main(String[] args) {
//        String name = "a_";
//        
//        System.out.println(name.matches(""));
//    }
}
