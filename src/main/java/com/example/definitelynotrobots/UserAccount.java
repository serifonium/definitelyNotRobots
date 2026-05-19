package com.example.definitelynotrobots;

/**
 * An account for a user to log into.
 */
public class UserAccount {
    /**
     * The UID of the user to be used in the DB as a primary key.
     */
    private Integer ID;
    public Integer getID() { return ID; }
    public void setID(Integer id) { ID = id; }

    /**
     * The username of the user.
     */
    private String Username;
    public String getUsername() { return Username; }
    public void setUsername(String username) { Username = username; }

    /**
     * The password of the user.
     */
    private String Password;
    public String getPassword() { return Password; }
    public void setPassword(String password) { Password = password; }

    /**
     * The first name of the user.
     */
    private String Firstname;
    public String getFirstname() { return Firstname; }
    public void setFirstname(String firstname) { Firstname = firstname; }

    /**
     * The last name of the user.
     */
    private String Lastname;
    public String getLastname() { return Lastname; }
    public void setLastname(String lastname) { Lastname = lastname; }

    /**
     * Create a user account with a UID.
     * @param id The UID of the user for the DB primary key.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param firstname The first name of the user.
     * @param lastname The last name of the user.
     */
    public UserAccount(Integer id, String username, String password, String firstname, String lastname) {
        ID = id;
        Username = username;
        Password = password;
        Firstname = firstname;
        Lastname = lastname;
    }

    /**
     * Create a user account without a UID.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param firstname The first name of the user.
     * @param lastname The last name of the user.
     */
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
