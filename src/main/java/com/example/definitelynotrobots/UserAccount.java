package com.example.definitelynotrobots;

public class UserAccount {
    private Integer ID;
    public Integer getID() { return ID; }

    private String Username;
    public String getUsername() { return Username; }
    public void setUsername(String username) { Username = username; }

    private String Password;
    public String getPassword() { return Password; }
    public void setPassword(String password) { Password = password; }

    public UserAccount(Integer id, String username, String password) {
        ID = id;
        Username = username;
        Password = password;
    }

    public UserAccount(String username, String password) {
        Username = username;
        Password = password;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
            "ID=" + ID +
            ", Username='" + Username + '\'' +
            ", Password='" + Password + '\'' +
            '}';
    }
}
