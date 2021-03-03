package sample;

public class User {
    private String FirstName;
    private String SerName;
    private String LastName;
    private String Age;
    private String password;
    private String login;
    private String gender;

    public User(String firstName, String serName, String lastName, String age, String password, String login, String gender) {
        FirstName = firstName;
        SerName = serName;
        LastName = lastName;
        Age = age;
        this.password = password;
        this.login = login;
        this.gender = gender;
    }

    public User() {

    }

    public String getFirstName() {
        return FirstName;
    }

    public String getSerName() {
        return SerName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getAge() {
        return Age;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getGender() {
        return gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}