package Storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;

public class DbFunctions {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {

                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }

        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try {
            FileInputStream fs = new FileInputStream("db.properties");
            Properties props = new Properties();
            props.load(fs);
            fs.close();
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static boolean validateEmail(String email, PreparedStatement st, ResultSet rs, Connection con) {

        String sql = "select *" +
                "from projeto_bonsexemplos.users where Email = ?";

        try {
            st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, email);
            rs = st.executeQuery();

            if (rs.next() || email.length() < 13 || email.indexOf("@gmail.com") == -1
                    || ((email.length() >= 13) && !email.substring(email.length() - 10).equals("@gmail.com"))) {
                // System.out.println("Email: " + rs.getString("Email"));
                return false;
            } else {
                // System.out.println("Email pode ser cadastrado.");
                return true;
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    public static boolean validateCPF(String cpf, PreparedStatement st, ResultSet rs, Connection con) {
        String sql = "select *" +
                "from projeto_bonsexemplos.users where CPF = ?";

        try {
            st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, cpf);
            rs = st.executeQuery();
            boolean onlyNumbers = cpf.matches("[0-9]+");
            if (!onlyNumbers || rs.next() || cpf.length() > 11 || cpf.length() < 11) {

                return false;
            } else {
                String[] numbers = cpf.split("");
                int firstDigit;
                int secondDigit;
                int sum = 0;
                int count = 10;

                boolean checkFirst = false;
                boolean checkSecond = false;

                for (int i = 0; i < 9; i++) {
                    sum += Integer.parseInt(numbers[i]) * count;
                    count--;
                }
                count = 11;

                if (sum % 11 < 2) {
                    firstDigit = 0;
                } else {
                    firstDigit = 11 - (sum % 11);
                }
                sum = 0;

                for (int i = 0; i < 10; i++) {
                    sum += Integer.parseInt(numbers[i]) * count;
                    count--;
                }

                if (sum % 11 < 2) {
                    secondDigit = 0;
                } else {
                    secondDigit = 11 - (sum % 11);
                }

                checkFirst = (firstDigit == Integer.parseInt(numbers[numbers.length - 2]));
                checkSecond = (secondDigit == Integer.parseInt(numbers[numbers.length - 1]));

                if (checkFirst && checkSecond) {
                    return true;
                }

                return false;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}