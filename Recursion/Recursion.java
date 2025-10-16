package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Defines a recursively defined sequence.
 */
public class Recursion<T> {
    private List<T> initialConditions;
    private NaryFunction<T> recurrence;

    /**
     * Sets the initial conditions and the recurrence.
     */
    @SafeVarargs
    public Recursion(NaryFunction<T> f, T... conditions) {
        this.initialConditions = new ArrayList<>(Arrays.asList(conditions));
        this.recurrence = f;
    }

    /**
     * Calculate recurrence.
     */
    public List<T> calcRecursion(int n) {
        List<T> sequence = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i < initialConditions.size()) {
                sequence.add(initialConditions.get(i));
            }

            List<T> arguments = sequence.subList(i - initialConditions.size(), i);
            sequence.add(recurrence.apply(arguments));
        }

        return sequence;
    }
}