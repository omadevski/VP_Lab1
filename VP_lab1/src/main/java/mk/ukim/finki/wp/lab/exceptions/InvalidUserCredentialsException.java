package mk.ukim.finki.wp.lab.exceptions;

public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException() { super("Invalid username or password"); }
}
