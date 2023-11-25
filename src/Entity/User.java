package Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class User {
    String email;
    String password;
    String name;
    String cpf;
    int type;

    User(String email, String password, String name, String cpf, int type) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.type = type;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getType() {
        return this.type;
    }

    public void registerYourself(PreparedStatement st, Connection con) {
        String sql = "INSERT INTO users" +
                "(Email, Password, Name, CPF, Type)" +
                "VALUES" +
                "(?, ?, ?, ?, ?)";

        try {
            st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, email);
            st.setString(2, password);
            st.setString(3, name);
            st.setString(4, cpf);
            st.setInt(5, type);
            st.executeUpdate();

            System.out.println("Usuário cadastrado.");

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
