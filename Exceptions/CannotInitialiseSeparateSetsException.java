package Exceptions;

/**
 * Exception when trying to initialise separate sets for equivalence relations.
 */
public class CannotInitialiseSeparateSetsException extends Exception {
    public CannotInitialiseSeparateSetsException(String message) {
        super(message);
    }
}
