package Presentation;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Storage.DbFunctions;

public class Login {
    private static final Scanner SCN = new Scanner(System.in);

    public static void main(String[] args) {
        String email, password;

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        System.out.print("Digite seu email: ");
        email = SCN.nextLine();
        System.out.print("Digite sua senha: ");
        password = SCN.nextLine();

        String sql = "SELECT *" +
                "FROM projeto_bonsexemplos.users WHERE Email = ? AND Password = ?";
        try {
            con = DbFunctions.getConnection();
            st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, email);
            st.setString(2, password);
            rs = st.executeQuery();

            if (rs.next()) {
                System.out.println("Logou!");
            } else {
                System.out.println("Login ou senha incorretos.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbFunctions.closeResultSet(rs);
            DbFunctions.closeStatement(st);
            DbFunctions.closeConnection();
            SCN.close();
        }
    }
}
