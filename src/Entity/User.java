package Entity;

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

}
