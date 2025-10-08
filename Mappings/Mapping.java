package Mappings;

import Operators.Pair;
import Relations.RelationOnSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

/**
 * Defines a mapping f: A -> B.
 */
public class Mapping<A, B> extends RelationOnSet<A, B> {
    public Mapping(BiPredicate<A, B> relation) {
        super(relation);
    }

    @SafeVarargs
    public final void initDomain(A... elements) {
        super.initSetA(elements);
    }

    @SafeVarargs
    public final void initCodomain(B... elements) {
        super.initSetB(elements);
    }
    
    /**
     * Returns true if relation is a mapping and false otherwise.
     */
    private boolean isMapping() {
        for (A x : setA) {
            int count = 0;

            for (B y : setB) {
                if (hasRelation(x, y)) {
                    count++;

                    if (count > 1) {
                        return false;
                    }
                }
            }

            if (count == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns true if the mapping is surjective and false otherwise.
     */
    private boolean isSurjective() {
        for (B y : setB) {
            boolean hasIncomingArrow = false;

            for (A x : setA) {
                if (hasRelation(x, y)) {
                    hasIncomingArrow = true;
                    break;
                }
            }

            if (!hasIncomingArrow) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns true if the mapping is injective and false otherwise.
     */
    private boolean isInjective() {
        for (B y : setB) {
            int count = 0;

            for (A x : setA) {
                if (hasRelation(x, y)) {
                    count++;
                }

                if (count > 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public void test() {
        boolean isMapping = isMapping();
        boolean isSurjective = isSurjective();
        boolean isInjective = isInjective();
        boolean isBijective = isSurjective && isInjective;

        String mapping = isMapping ? "a mapping" : "not a mapping";
        String surjective = isSurjective ? "surjective" : "not surjective";
        String injective = isInjective ? "injective" : "not injective";
        String bijective = isBijective ? "bijective" : "not bijective";

        System.out.println("The relation is " + mapping
            + ". Moreover, it is " + surjective
            + " and " + injective
            + ". Therefore, it is " + bijective + ".");
    }

    /**
     * Evaluate the image of f under subdomain A'.
     */
    @SafeVarargs
    public final Set<B> image(A... subDomainElements) {
        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        Set<A> subDomain = new HashSet<>(Arrays.asList(subDomainElements));
        Set<B> image = new HashSet<>();

        for (B b : setB) {
            for (A a : subDomain) {
                if (relationSet.contains(new Pair<A, B>(a, b))) {
                    image.add(b);
                }
            }
        }

        return image;
    }

    /**
     * Evaluate the source of subset of codomain B' on under f.
     */
    @SafeVarargs
    public final Set<A> source(B... subCodomainElements) {
        if (relationSet.isEmpty()) {
            getRelationSet();
        }

        Set<B> subCodomain = new HashSet<>(Arrays.asList(subCodomainElements));
        Set<A> source = new HashSet<>();

        for (A a : setA) {
            for (B b : subCodomain) {
                if (relationSet.contains(new Pair<A, B>(a, b))) {
                    source.add(a);
                }
            }
        }

        return source;
    }
}