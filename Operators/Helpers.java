package Operators;

/**
 * Helper methods for defining relations.
 */
public class Helpers {
    /**
     * Uses Euclidean algorithm to find the GCD of 2 integers.
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }

        return a;
    }
}
