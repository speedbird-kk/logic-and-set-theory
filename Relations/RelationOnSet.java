package Relations;

import Operators.Pair;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

/**
 * Abstract class for all classes dealing with a relation on a set.
 */
public abstract class RelationOnSet<T> {
    protected Set<T> set;
    protected final BiPredicate<T, T> relation;
    protected final Set<Pair<T, T>> relationSet = new HashSet<>();

    protected Set<T> domain;
    protected Set<T> codomain;

    public void initSet(@SuppressWarnings("unchecked") T... elements) {
        this.set = new HashSet<>(Arrays.asList(elements));
    }

    public void initDomain(@SuppressWarnings("unchecked") T... elements) {
        this.domain = new HashSet<>(Arrays.asList(elements));
    }

    public void initCodomain(@SuppressWarnings("unchecked") T... elements) {
        this.codomain = new HashSet<>(Arrays.asList(elements));
    }

    public RelationOnSet(BiPredicate<T, T> relation) {
        this.relation = relation;
    }

    /**
     * Gets the set of all relations R(x, y) for xRy.
     */
    public Set<Pair<T, T>> getRelationSet() {
        for (T x : set) {
            for (T y : set) {
                if (relation.test(x, y)) {
                    Pair<T, T> r = new Pair<>(x, y);
                    relationSet.add(r);
                }
            }
        }

        return relationSet;
    }

    protected boolean hasRelation(T x, T y) {
        return relation.test(x, y);
    }
}