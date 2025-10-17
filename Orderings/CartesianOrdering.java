package Orderings;

import Operators.CartesianProduct;
import Operators.Pair;
import java.util.Set;
import java.util.function.BiPredicate;

/**
 * Defines a lexicographic ordering from two orderings.
 */
public class CartesianOrdering<A, B> extends Ordering<Pair<A, B>> {
    private final BiPredicate<A, A> r1;
    private final BiPredicate<B, B> r2;

    /**
     * Define a cartesian ordering with two bi-predicates for r1 and r2.
     */
    public CartesianOrdering(BiPredicate<A, A> r1, BiPredicate<B, B> r2) {
        super((p, q) -> {
            A x1 = p.a();
            B y1 = p.b();
            A x2 = q.a();
            B y2 = q.b();

            return r1.test(x1, x2) && r2.test(y1, y2);
        });

        this.r1 = r1;
        this.r2 = r2;
    }

    /**
     * Initialises the set to the cartesian product.
     */
    public void initSet(CartesianProduct<A, B> cp) throws Exception {
        super.initSet(getCpArray(cp));
    }

    public final Set<Pair<A, B>> maximalElements(CartesianProduct<A, B> cp) {
        return super.maximalElements(getCpArray(cp));
    }

    public final Set<Pair<A, B>> minimalElements(CartesianProduct<A, B> cp) {
        return super.minimalElements(getCpArray(cp));
    }

    public final Pair<A, B> maximum(CartesianProduct<A, B> cp) {
        return super.maximum(getCpArray(cp));
    }

    public final Pair<A, B> minimum(CartesianProduct<A, B> cp) {
        return super.minimum(getCpArray(cp));
    }

    @Override
    public boolean hasRelation(Pair<A, B> p, Pair<A, B> q) {
        A x1 = p.a();
        B y1 = p.b();
        A x2 = q.a();
        B y2 = q.b();

        return r1.test(x1, x2) && r2.test(y1, y2);
    }

    private Pair<A, B>[] getCpArray(CartesianProduct<A, B> cp) {
        Set<Pair<A, B>> pairs = cp.findCartesianProduct();

        @SuppressWarnings("unchecked")
        Pair<A, B>[] pairsArray = pairs.toArray(new Pair[0]);

        return pairsArray;
    }
}
