package mk.ukim.finki.wp.lab.model;

import java.util.Objects;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User() {}
    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName; this.lastName = lastName;
        this.username = username; this.password = password;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void setFirstName(String v) { this.firstName = v; }
    public void setLastName(String v)  { this.lastName = v; }
    public void setUsername(String v)  { this.username = v; }
    public void setPassword(String v)  { this.password = v; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User u = (User) o;
        return Objects.equals(username, u.username);
    }
    @Override public int hashCode() { return Objects.hash(username); }
}
