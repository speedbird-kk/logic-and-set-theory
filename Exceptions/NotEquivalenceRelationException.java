package Exceptions;

/**
 * Exception for a non-equivalence relation where an equivalence relation is expected.
 */
public class NotEquivalenceRelationException extends Exception {
    public NotEquivalenceRelationException(String message) {
        super(message);
    }
}