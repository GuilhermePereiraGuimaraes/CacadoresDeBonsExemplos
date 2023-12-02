package Presentation;

import java.util.Scanner;

public class Program {
    private static final Scanner SCN = new Scanner(System.in);

    public static void main(String[] args) {
        String escolha = "";

        do {
            System.out.print("Digite 1-Entrar ou 2-Cadastrar ou 3-Sair: ");
            escolha = SCN.next();

            if (!escolha.equals("1") && !escolha.equals("2") && !escolha.equals("3")) {
                System.out.println("Valor inválido tente novamente.");
            } else {
                if (escolha.equals("1")) {
                    Login.main(args);
                } else if (escolha.equals("2")) {
                    Register.execute();
                }
            }

        } while (!escolha.equals("1") && !escolha.equals("2") && !escolha.equals("3"));
    }
}
