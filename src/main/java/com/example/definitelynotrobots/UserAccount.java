package com.example.definitelynotrobots;

public class UserAccount {
    private Integer ID;
    public Integer getID() { return ID; }
    public void setID(Integer id) { ID = id; }

    private String Username;
    public String getUsername() { return Username; }
    public void setUsername(String username) { Username = username; }

    private String Password;
    public String getPassword() { return Password; }
    public void setPassword(String password) { Password = password; }

    private String Firstname;
    public String getFirstname() { return Firstname; }
    public void setFirstname(String firstname) { Firstname = firstname; }

    public UserAccount(Integer id, String username, String password, String firstname) {
        ID = id;
        Username = username;
        Password = password;
        Firstname = firstname;
    }

    public UserAccount(String username, String password, String firstname) {
        Username = username;
        Password = password;
        Firstname = firstname;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
            "ID=" + ID +
            ", Username='" + Username + '\'' +
            ", Password='" + Password + '\'' +
            ", Firstname='" + Firstname + '\'' +
            '}';
    }
}
