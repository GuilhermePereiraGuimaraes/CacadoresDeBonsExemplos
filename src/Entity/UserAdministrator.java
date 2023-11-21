package Entity;

public class UserAdministrator extends User {

    public UserAdministrator(String email, String password, String name, String cpf) {
        super(email, password, name, cpf, 1);
    }

}
