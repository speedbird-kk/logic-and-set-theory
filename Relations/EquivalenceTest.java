package Relations;

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
    @SafeVarargs
    public final void initSet(A... elements) {
        super.initSetA(elements);
        super.initSetB(elements);
    }

    @Deprecated
    @Override
    @SafeVarargs
    public final void initSetB(A... elements) {
        throw new UnsupportedOperationException("Equivalence test only sets one set A");
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

    private boolean isReflexive() {
        for (A x : setA) {
            if (relation.test(x, x)) {
                return false;
            }
        }

        return true;
    }

    private boolean isSymmetric() {
        for (Pair<A, A> relation : relationSet) {
            if (!relationSet.contains(new Pair<>(relation.b(), relation.a()))) {
                return false;
            }
        }

        return true;
    }

    private boolean isTransitive() {
        for (Pair<A, A> x : relationSet) {
            for (Pair<A, A> z : relationSet) {
                if (x.b() == z.a()) {
                    if (!relationSet.contains(new Pair<>(x.a(), z.b()))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}