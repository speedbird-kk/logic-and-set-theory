package Exceptions;

/**
 * Exception when finding equivalence class of element that does not exist.
 */
public class ElementNotFoundException extends Exception {
    public ElementNotFoundException(String message) {
        super(message);
    }
}
