package Presentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Storage.DbException;
import Storage.DbFunctions;

public class Register {
    private static final Scanner SCN = new Scanner(System.in);

    public static void main(String[] args) {
        String email, cpf, name, password;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        System.out.print("Digite seu email: ");
        email = SCN.next();

        conn = DbFunctions.getConnection();

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
            }
        }

        DbFunctions.closeResultSet(rs);
        DbFunctions.closeStatement(st);
        DbFunctions.closeConnection();
        SCN.close();
    }
}
