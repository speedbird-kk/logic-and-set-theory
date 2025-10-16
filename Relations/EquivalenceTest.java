package Relations;

import Exceptions.CannotInitialiseSeparateSetsException;
import Exceptions.NotEquivalenceRelationException;
import Operators.Pair;
import java.util.function.BiPredicate;

/**
 * Finds if a relation on a set is reflexive, symmetric and/or transitive.
 */
public class EquivalenceTest<A> extends RelationOnSet<A, A> {
    public EquivalenceTest(BiPredicate<A, A> relation) {
        super(relation);
    }

    /**
     * Initialise both set A and B.
     */
    public void initSet(@SuppressWarnings("unchecked") A... elements)
        throws CannotInitialiseSeparateSetsException, NotEquivalenceRelationException {

        super.initSetA(elements);
        super.initSetB(elements);
    }

    @Deprecated
    @Override
    @SafeVarargs
    public final void initSetB(A... elements) throws CannotInitialiseSeparateSetsException {
        throw new CannotInitialiseSeparateSetsException("Use .initSet() to initialise both sets.");
    }

    @Deprecated
    @Override
    @SafeVarargs
    public final void initSetA(A... elements) throws CannotInitialiseSeparateSetsException {
        throw new CannotInitialiseSeparateSetsException("Use .initSet() to initialise both sets.");
    }

    /**
     * Tests and prints if a relation is reflexive, symmetric and/or transitive.
     * Thus, if it is an equivalence relation or not.
     */
    public void test() {
        boolean isReflexive = isReflexive();
        boolean isSymmetric = isSymmetric();
        boolean isTransitive = isTransitive();
        boolean isEquivalence = isReflexive && isSymmetric && isTransitive;
        
        String reflexiveTest = isReflexive ? "reflexive, " : "not reflexive, ";
        String symmetricTest = isSymmetric ? "symmetric, " : "not symmetric, ";
        String transitiveTest = isTransitive ? "and transitive. " : "and not transitive. ";
        String conclusion = "Hence it is "
            + (isEquivalence ? "an equivalence relation." : "not an equivalence relation.");

        System.out.println("The relation is "
            + reflexiveTest + symmetricTest + transitiveTest + conclusion);
    }

    protected boolean isReflexive() {
        for (A x : setA) {
            if (!relation.test(x, x)) {
                return false;
            }
        }

        return true;
    }

    private boolean isSymmetric() {
        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        for (Pair<A, A> relation : relationSet) {
            if (!relationSet.contains(new Pair<>(relation.b(), relation.a()))) {
                return false;
            }
        }

        return true;
    }

    protected boolean isTransitive() {
        if (relationSet.isEmpty()) {
            getRelationSet();
        }
        
        for (Pair<A, A> x : relationSet) {
            for (Pair<A, A> z : relationSet) {
                if (x.b().equals(z.a())) {
                    if (!relationSet.contains(new Pair<>(x.a(), z.b()))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    protected boolean checkEquivalence() {
        return isReflexive() && isSymmetric() && isTransitive();
    }
}