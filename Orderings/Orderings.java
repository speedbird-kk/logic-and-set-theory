package Orderings;

import Exceptions.ElementNotFoundException;
import Exceptions.NotAnOrderingException;
import Operators.Pair;
import Relations.EquivalenceTest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;

/**
 * Class defining reflexive and irreflexive orderings.
 */
public class Orderings<A> extends EquivalenceTest<A> {
    public Orderings(BiPredicate<A, A> relation) {
        super(relation);
    }

    /**
     * Tests properties to find reflexive or irreflexive ordering or neither.
     */
    public void test() {
        System.out.println("The relation:");

        boolean[] properties = {
            isTransitive(), isReflexive(), isIrreflexive(),
            isAntisymmetric(), isStrictlyAntisymmetric(), isLinear()
        };
        
        String[] propertyNames = {
            "transitive", "reflexive", "irreflexive",
            "antisymmetric", "strictly antisymmetric", "linear"
        };
        
        for (int i = 0; i < properties.length; i++) {
            if (properties[i]) {
                System.out.println("\tis " + propertyNames[i]);
            } else {
                System.out.println("\tis not " + propertyNames[i]);
            }
        }

        if (properties[0] && properties[1] && properties[3]) {
            System.out.println("\nSince it is reflexive, transitive and antisymmetric, "
                + "it is a reflexive ordering.");
        } else if (properties[0] && properties[2] && properties[4]) {
            System.out.println("\nSince it is irreflexive, transitive "
                + "and strictly antisymmetric, it is an irreflexive ordering");
        } else if (properties[0] && properties[1]) {
            System.out.println("\nSince it is reflexive and transitive, it is a quasi-ordering.");
        } else {
            System.out.println("\nTherefore, the relation is not an ordering.");
        }
    }

    private boolean isIrreflexive() {
        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        for (Pair<A, A> relation : relationSet) {
            if (relation.a().equals(relation.b())) {
                return false;
            }
        }

        return true;
    }

    private boolean isAntisymmetric() {
        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        for (Pair<A, A> relation : relationSet) {
            if (relationSet.contains(new Pair<>(relation.b(), relation.a()))) {
                if (!relation.b().equals(relation.a())) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isStrictlyAntisymmetric() {
        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        for (Pair<A, A> relation : relationSet) {
            if (relationSet.contains(new Pair<>(relation.b(), relation.a()))) {
                return false;
            }
        }

        return true;
    }

    private boolean isLinear() {
        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        for (A a : setA) {
            for (A b : setA) {
                Pair<A, A> xRy = new Pair<>(a, b);
                Pair<A, A> yRx = new Pair<>(b, a);

                if (!(relationSet.contains(xRy)
                    || relationSet.contains(yRx)
                    || a.equals(b))) {
                    
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isReflexiveOrdering() {
        return isTransitive() && isReflexive() && isAntisymmetric();
    }

    private boolean isIrreflexiveOrdering() {
        return isTransitive() && isIrreflexive() && isStrictlyAntisymmetric();
    }

    /**
     * Finds all the direct successors of an element x.
     */
    public Set<A> findDirectSuccessors(A x)
        throws ElementNotFoundException, NotAnOrderingException {
        
        if (!setA.contains(x)) {
            throw new ElementNotFoundException("Element not found in set.");
        }

        if (!(isReflexiveOrdering() || isIrreflexiveOrdering())) {
            throw new NotAnOrderingException("The relation is not an ordering, "
                + "so direct successors cannot be found.");
        }

        Set<Pair<A, A>> candidates = new HashSet<>();
        Set<A> setZ = new HashSet<>();
        Set<A> directSuccessors = new HashSet<>();

        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        for (Pair<A, A> relation : relationSet) {
            if (relation.a().equals(x) && !relation.b().equals(x)) {
                candidates.add(relation);
            }
        }

        Set<Pair<A, A>> successorRelations = new HashSet<>(candidates);

        for (Pair<A, A> relation : candidates) {
            setZ.add(relation.b());
        }

        for (A z : setZ) {
            for (Pair<A, A> candidate: candidates) {
                if (relationSet.contains(new Pair<A, A>(z, candidate.b()))
                    && !z.equals(candidate.b()) && !z.equals(x)) {

                    successorRelations.remove(candidate);
                }
            }
        }

        for (Pair<A, A> relation : successorRelations) {
            directSuccessors.add(relation.b());
        }

        return directSuccessors;
    }

    /**
     * Returns Hasse map of relation as a map.
     * Keys represent nodes and values represent its children.
     */
    public Map<A, Set<A>> findHasse()
        throws ElementNotFoundException, NotAnOrderingException {
        
        Map<A, Set<A>> hasse = new HashMap<>();

        for (A x : setA) {
            hasse.put(x, findDirectSuccessors(x));
        }

        return hasse;
    }

    /**
     * Finds the maximal elements in a subset.
     */
    @SafeVarargs
    public final Set<A> maximalElements(A... subset) {
        Set<A> maximalElements = new HashSet<>();

        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        for (A m : subset) {
            boolean isMaximal = true;

            for (A x : subset) {
                if (relationSet.contains(new Pair<A, A>(m, x))
                    && !m.equals(x)) {
                    
                    isMaximal = false;
                    break;
                }
            }

            if (isMaximal) {
                maximalElements.add(m);
            }
        }

        return maximalElements;
    }

    /**
     * Finds the minimal elements in a subset.
     */
    @SafeVarargs
    public final Set<A> minimalElements(A... subset) {
        Set<A> minimalElements = new HashSet<>();

        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        for (A m : subset) {
            boolean isMinimal = true;

            for (A x : subset) {
                if (relationSet.contains(new Pair<A, A>(x, m))
                    && !x.equals(m)) {
                    
                    isMinimal = false;
                    break;
                }
            }

            if (isMinimal) {
                minimalElements.add(m);
            }
        }

        return minimalElements;
    }

    /**
     * Finds the maximum element in a subset.
     */
    @SafeVarargs
    public final A maximum(A... subset) {
        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        for (A m : subset) {
            boolean isMaximum = true;

            for (A x : subset) {
                boolean condition = isReflexive()
                    ? !relationSet.contains(new Pair<A, A>(x, m))
                    : !x.equals(m) && !relationSet.contains(new Pair<A, A>(x, m));

                if (condition) {
                    isMaximum = false;
                    break;
                }
            }

            if (isMaximum) {
                return m;
            }
        }

        return null;
    }

    /**
     * Finds the minimum element in a subset.
     */
    @SafeVarargs
    public final A minimum(A... subset) {
        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        for (A m : subset) {
            boolean isMinimum = true;

            for (A x : subset) {
                boolean condition = isReflexive()
                    ? !relationSet.contains(new Pair<A, A>(m, x))
                    : !m.equals(x) && !relationSet.contains(new Pair<A, A>(m, x));

                if (condition) {
                    isMinimum = false;
                    break;
                }
            }

            if (isMinimum) {
                return m;
            }
        }

        return null;
    }
}