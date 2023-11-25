package Presentation;

import java.util.Scanner;

public class ScreenAdm {
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        int escolha;

        do {
            System.out.print("Digite 1(adiconar) ou 2(filtrar): ");
            escolha = INPUT.nextInt();

            if (escolha > 2 || escolha < 1) {
                System.out.println("Escolha inválida!");
            }
        } while (escolha > 2 || escolha < 1);

        System.out.println("Entrou");
    }
}
