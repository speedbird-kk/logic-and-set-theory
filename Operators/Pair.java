package Operators;

/**
 * Defines the generic pair (a, b).
 */
public record Pair<K, V>(K a, V b) {
    @Override
    public final String toString() {
        return "(" + a + ", " + b + ")";
    }
}