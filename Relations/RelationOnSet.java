package Relations;

import Operators.Pair;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

/**
 * Abstract class for all classes dealing with a relation on a set.
 */
public abstract class RelationOnSet<A, B> {
    protected final BiPredicate<A, B> relation;
    protected final Set<Pair<A, B>> relationSet = new HashSet<>();

    protected Set<A> setA;
    protected Set<B> setB;

    public void initSetA(@SuppressWarnings("unchecked") A... elements) {
        this.setA = new HashSet<>(Arrays.asList(elements));
    }

    public void initSetB(@SuppressWarnings("unchecked") B... elements) {
        this.setB = new HashSet<>(Arrays.asList(elements));
    }

    public RelationOnSet(BiPredicate<A, B> relation) {
        this.relation = relation;
    }

    protected Set<A> getFirstSet() {
        return setA;
    }

    protected Set<B> getSecondSet() {
        return setB;
    }

    /**
     * Gets the set of all relations R(x, y) for xRy.
     */
    public Set<Pair<A, B>> getRelationSet() {
        for (A x : getFirstSet()) {
            for (B y : getSecondSet()) {
                if (relation.test(x, y)) {
                    Pair<A, B> r = new Pair<>(x, y);
                    relationSet.add(r);
                }
            }
        }

        return relationSet;
    }

    protected boolean checkEquivalence() {
        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        

        return true;
    }

    protected boolean hasRelation(A x, B y) {
        return relation.test(x, y);
    }
}