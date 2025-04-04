package org.exceptions;

public class InvalidRepositoryException extends Exception{
    public InvalidRepositoryException(Exception ex) {
        super("Invalid repository.", ex);
    }
    public InvalidRepositoryException(String message) {
        super(message);
    }
}
