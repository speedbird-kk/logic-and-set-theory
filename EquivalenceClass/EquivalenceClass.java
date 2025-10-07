package EquivalenceClass;

import java.util.Set;
import java.util.function.BiPredicate;

/**
 * Equivalence class for relations on A x A.
 */
public class EquivalenceClass<A> extends EquivalenceClassGeneral<A, A> {
    public EquivalenceClass(BiPredicate<A, A> relation) {
        super(relation);
    }

    /**
     * Initialise both set A and set B.
     */
    @SafeVarargs
    public final void initSet(A... elements) {
        super.initSetA(elements);
        super.initSetB(elements);
    }

    @Deprecated
    @Override
    @SafeVarargs
    public final void initSetB(A... elements) {
        throw new UnsupportedOperationException(
            "Use EquivalenceClassGeneral to find equivalence classes of relations on two sets.");
    }

    @Override
    public Set<Set<A>> findEquivalenceClasses() {
        Set<Set<A>> equivalenceClasses = super.findEquivalenceClasses();
        return equivalenceClasses;
    }
}