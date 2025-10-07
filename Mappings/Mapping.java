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
    public boolean isMapping() {
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
     * Evaluate the image of f under subdomain A'.
     */
    public Set<B> image(@SuppressWarnings("unchecked") A... subDomainElements) {
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
    public Set<A> source(@SuppressWarnings("unchecked") B... subCodomainElements) {
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