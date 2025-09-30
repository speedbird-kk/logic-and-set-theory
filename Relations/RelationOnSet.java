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
    private Set<T> set;
    private final BiPredicate<T, T> relation;
    private final Set<Pair<T, T>> relationSet = new HashSet<>();

    public void initSet(@SuppressWarnings("unchecked")T... elements) {
        this.set = new HashSet<>(Arrays.asList(elements));
    }

    public RelationOnSet(BiPredicate<T, T> relation) {
        this.relation = relation;
    }

    public Set<T> getSet() {
        return set;
    }

    public BiPredicate<T, T> getRelation() {
        return relation;
    }

    /**
     * Gets the set of all relations R(x, y) for xRy.
     */
    public Set<Pair<T, T>> getRelationSet() {
        for (T x : set) {
            for (T y : set) {
                if (getRelation().test(x, y)) {
                    Pair<T, T> relation = new Pair<>(x, y);
                    relationSet.add(relation);
                }
            }
        }

        return relationSet;
    }
}