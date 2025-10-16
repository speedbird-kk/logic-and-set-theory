package EquivalenceClass;

import Exceptions.CannotInitialiseSeparateSetsException;
import Exceptions.ElementNotFoundException;
import Exceptions.NotEquivalenceRelationException;
import Operators.Pair;
import Relations.EquivalenceTest;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

/**
 * Find all equivalence classes of a general set.
 */
public class EquivalenceClass<A> extends EquivalenceTest<A> {
    public EquivalenceClass(BiPredicate<A, A> relation) {
        super(relation);
    }

    @Override
    @SafeVarargs
    public final void initSet(A... elements)
        throws CannotInitialiseSeparateSetsException, NotEquivalenceRelationException {

        super.initSet(elements);
        
        if (!checkEquivalence()) {
            throw new NotEquivalenceRelationException("Relation must be an equivalence relation.");
        }
    }

    /**
     * Find all equivalence classes of a given set with a defined relation on the set.
     */
    public Set<Set<A>> findEquivalenceClasses() {
        // All equivalence classes
        Set<Set<A>> equivalenceClasses = new HashSet<>();

        // Find equivalence class for each element in the set
        for (A a : setA) {
            // equivalenceClass is K(a) for each a in the set
            Set<A> equivalenceClass = new HashSet<>();

            // Check for all x in the set if aRx since K(a) = {x ∈ set | aRx}
            for (A x : setB) {
                if (hasRelation(a, x)) {
                    equivalenceClass.add(x);
                }
            }

            // Add K(a) to the set of all equivalence classes
            if (!equivalenceClass.isEmpty()) {
                equivalenceClasses.add(equivalenceClass);
            }
        }

        return equivalenceClasses;
    }

    /**
     * Finds equivalence class of specific element if it exists.
     */
    public Set<A> findEquivalenceClass(A element) throws ElementNotFoundException {
        Set<A> equivalenceClass = new HashSet<>();

        if (!setA.contains(element)) {
            throw new ElementNotFoundException("Element not found in set.");
        }

        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        for (A x : setA) {
            if (relationSet.contains(new Pair<A, A>(element, x))) {
                equivalenceClass.add(x);
            }
        }

        return equivalenceClass;
    }
}