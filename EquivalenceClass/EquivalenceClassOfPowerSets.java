package EquivalenceClass;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Find all equivalence classes of a power set.
 */
public class EquivalenceClassOfPowerSets {
    private static Set<Set<Integer>> powerSet = new HashSet<>();

    static {
        powerSet.add(Collections.emptySet());
        powerSet.add(new HashSet<>(Arrays.asList(0)));
        powerSet.add(new HashSet<>(Arrays.asList(1)));
        powerSet.add(new HashSet<>(Arrays.asList(0, 1)));
    }

    private static Set<Set<Set<Integer>>> findEquivalenceClasses(Set<Set<Integer>> powerSet) {
        // All equivalence classes
        Set<Set<Set<Integer>>> equivalenceClasses = new HashSet<>();

        // Find equivalence class for each set in the power set
        for (Set<Integer> a : powerSet) {
            // equivalenceClass is K(a) for each a in the power set
            Set<Set<Integer>> equivalenceClass = new HashSet<>();

            // Check for all x in the power set if aRx since K(a) = {x ∈ powerSet | aRx}
            for (Set<Integer> x : powerSet) {
                if (hasRelation(a, x)) {
                    equivalenceClass.add(x);
                }
            }

            // Add K(a) to the set of all equivalence classes
            equivalenceClasses.add(equivalenceClass);
        }

        return equivalenceClasses;
    }

    private static boolean hasRelation(Set<Integer> a, Set<Integer> x) {
        Set<Integer> aUnion0 = new HashSet<>(a);
        Set<Integer> xUnion0 = new HashSet<>(x);

        aUnion0.add(0);
        xUnion0.add(0);

        return aUnion0.equals(xUnion0);
    }

    public static void main(String[] args) {
        System.out.println(findEquivalenceClasses(powerSet));
    }
}