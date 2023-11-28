package Presentation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.PreparedStatement;
import Storage.DbFunctions;

public class ScreenVoluntary {
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        do {
            con = DbFunctions.getConnection();
            do {
                System.out.print("Digite 1(filtrar) ou 2(sair): ");
                choice = INPUT.nextInt();

                if (choice < 1 || choice > 2) {
                    System.out.println("Escolha inválida! Tente Novamente.");
                }
            } while (choice < 1 || choice > 2);

            INPUT.nextLine();

            if (choice == 1) {
                System.out.print("Digite o valor de 'Name' do projeto para filtrar: ");
                String searchString = INPUT.nextLine();
                DbFunctions.searchByName(con, st, rs, searchString);
            }
        } while (choice != 2);
        System.out.println("Saindo...");
        DbFunctions.closeConnection();
    }
}
