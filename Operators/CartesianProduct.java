package Operators;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to calculate cartesian products.
 */
public class CartesianProduct<K, V> {
    private Set<K> setA;
    private Set<V> setB; 

    public void initA(@SuppressWarnings("unchecked")K... elements) {
        this.setA = new HashSet<>(Arrays.asList(elements));
    }

    public void initB(@SuppressWarnings("unchecked")V... elements) {
        this.setB = new HashSet<>(Arrays.asList(elements));
    }

    /**
     * Evaluates the cartesian product of A x B.
     */
    public Set<Pair<K, V>> findCartesianProduct() {
        Set<Pair<K, V>> cartesianProduct = new HashSet<>();

        for (K a : setA) {
            for (V b : setB) {
                Pair<K, V> pair = new Pair<>(a, b);
                cartesianProduct.add(pair);
            }
        }

        return cartesianProduct;
    }
}
