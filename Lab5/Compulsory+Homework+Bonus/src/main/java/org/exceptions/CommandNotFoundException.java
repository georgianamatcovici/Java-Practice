package org.exceptions;

public class CommandNotFoundException extends Exception {
    public CommandNotFoundException() {
        super("Invalid command.");
    }
}
