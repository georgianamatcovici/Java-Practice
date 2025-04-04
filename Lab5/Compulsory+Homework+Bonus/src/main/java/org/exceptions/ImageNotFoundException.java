package org.exceptions;

public class ImageNotFoundException extends Exception {
    public ImageNotFoundException() {
        super("Image not found.");
    }
}
