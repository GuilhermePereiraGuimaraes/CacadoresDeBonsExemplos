package Presentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Entity.UserVoluntary;
import Storage.DbException;
import Storage.DbFunctions;

public class Register {
    private static final Scanner SCN = new Scanner(System.in);

    public static void main(String[] args) {
        String email, cpf, name, password;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        conn = DbFunctions.getConnection();

        do {
            System.out.print("Digite seu nome: ");
            name = SCN.nextLine();
            // System.out.println(name);
            if (name.length() < 3) {
                System.out.println("Nome pequeno demais.");
            }
        } while (name.length() < 3);

        System.out.print("Digite seu email: ");
        email = SCN.next();
        boolean checkEmail = DbFunctions.validateEmail(email, st, rs, conn);
        if (!checkEmail) {
            System.out.println("Email inválido!");
        } else {
            System.out.println("Email pode ser cadastrado!");

            System.out.print("Digite seu CPF(apenas números): ");
            cpf = SCN.next();

            boolean checkCpf = DbFunctions.validateCPF(cpf, st, rs, conn);

            if (!checkCpf) {
                System.out.println("CPF inválido ou já cadastrado");
            } else {
                System.out.println("CPF válido");

                System.out.print("Digite sua senha (pelo menos 7 digitos): ");
                password = SCN.next();

                if (password.length() < 7) {
                    System.out.println("Senha inválida!");
                } else {
                    UserVoluntary userV = new UserVoluntary(email, password, name, cpf);
                    userV.registerYourself(st, conn);
                }
            }

        }

        DbFunctions.closeResultSet(rs);
        DbFunctions.closeStatement(st);
        DbFunctions.closeConnection();
        SCN.close();
    }
}
