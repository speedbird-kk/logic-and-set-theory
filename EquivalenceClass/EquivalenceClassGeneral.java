package EquivalenceClass;

import Relations.RelationOnSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

/**
 * Find all equivalence classes of a general set.
 */
public class EquivalenceClassGeneral<A, B> extends RelationOnSet<A, B> {
    public EquivalenceClassGeneral(BiPredicate<A, B> relation) {
        super(relation);
    }

    /**
     * Find all equivalence classes of a given set with a defined relation on the set.
     */
    public Set<Set<B>> findEquivalenceClasses() {
        // All equivalence classes
        Set<Set<B>> equivalenceClasses = new HashSet<>();

        // Find equivalence class for each element in the set
        for (A a : setA) {
            // equivalenceClass is K(a) for each a in the set
            Set<B> equivalenceClass = new HashSet<>();

            // Check for all x in the set if aRx since K(a) = {x ∈ set | aRx}
            for (B x : setB) {
                if (hasRelation(a, x)) {
                    equivalenceClass.add(x);
                }
            }

            // Add K(a) to the set of all equivalence classes
            equivalenceClasses.add(equivalenceClass);
        }

        return equivalenceClasses;
    }
}