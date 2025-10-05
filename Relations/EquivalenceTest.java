package Relations;

import Operators.Pair;
import java.util.function.BiPredicate;

/**
 * Finds if a relation on a set is reflexive, symmetric and/or transitive.
 */
public class EquivalenceTest<T> extends RelationOnSet<T> {
    public EquivalenceTest(BiPredicate<T, T> relation) {
        super(relation);
    }

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
        for (T x : set) {
            if (relation.test(x, x)) {
                return false;
            }
        }

        return true;
    }

    private boolean isSymmetric() {
        for (Pair<T, T> relation : relationSet) {
            if (!relationSet.contains(new Pair<>(relation.b(), relation.a()))) {
                return false;
            }
        }

        return true;
    }

    private boolean isTransitive() {
        for (Pair<T, T> x : relationSet) {
            for (Pair<T, T> z : relationSet) {
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