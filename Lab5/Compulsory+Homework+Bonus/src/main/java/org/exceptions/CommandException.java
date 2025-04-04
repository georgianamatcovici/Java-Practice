package org.exceptions;

public class CommandException extends Exception {
    public CommandException() {
        super("Couldn't execute command.");
    }
    public CommandException(String message) {
        super(message);
    }
}
