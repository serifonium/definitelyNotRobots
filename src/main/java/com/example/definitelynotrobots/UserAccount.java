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

    private String Lastname;
    public String getLastname() { return Lastname; }
    public void setLastname(String lastname) { Lastname = lastname; }

    public UserAccount(Integer id, String username, String password, String firstname, String lastname) {
        ID = id;
        Username = username;
        Password = password;
        Firstname = firstname;
        Lastname = lastname;
    }

    public UserAccount(String username, String password, String firstname, String lastname) {
        Username = username;
        Password = password;
        Firstname = firstname;
        Lastname = lastname;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
            "ID=" + ID +
            ", Username='" + Username + '\'' +
            ", Password='" + Password + '\'' +
            ", Firstname='" + Firstname + '\'' +
            ", Lastname='" + Lastname + '\'' +
            '}';
    }
}
