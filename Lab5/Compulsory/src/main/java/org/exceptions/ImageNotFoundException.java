package org.example;

public class ImageNotFoundException extends Exception {
    public ImageNotFoundException() {
        super("Image not found.");
    }
}
