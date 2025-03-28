package org.example;

public class ImageNotFoundException extends Exception {
    public ImageNotFoundException(Exception ex) {
        super("Image not found.", ex);
    }
}
