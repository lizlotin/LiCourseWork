package sample;

public class Admin {
    private String password;
    private String login;

    public Admin(String password, String login) {
        this.password = password;
        this.login = login;
    }
    public Admin(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
