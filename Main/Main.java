package Main;

import EquivalenceClass.EquivalenceClass;
import Exceptions.CannotInitialiseSeparateSetsException;
import Exceptions.ElementNotFoundException;
import Exceptions.NotAnOrderingException;
import Exceptions.NotEquivalenceRelationException;
import EquivalenceClass.EquivalenceClass;
import Mappings.Mapping;
import Operators.CartesianProduct;
import Operators.Helpers;
import Operators.PowerSet;
import Orderings.CartesianOrdering;
import Orderings.LexicographicOrdering;
import Orderings.Ordering;
import Recursion.NaryFunction;
import Recursion.Recursion;
import Relations.EquivalenceTest;

/**
 * Instantiate objects of helper classes and call their methods as described in the README.
 * Please refer to the README for a manual.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("ORDERINGS TEST");

        Ordering<Integer> o = new Ordering<Integer>((x, y) -> x < y);
        o.initSet(1, 2, 3, 4, 5, 6);
        o.test();
        System.out.println(o.findDirectSuccessors(2));
        System.out.println(o.maximum(1, 2, 3, 4));
        System.out.println(o.minimum(1, 2, 3));

        System.out.println("\nEXERCISE 20.8");

        Ordering<Integer> ordering = new Ordering<Integer>((x, y) -> y % x == 0);
        ordering.initSet(2, 3, 4, 6, 8, 12);
        ordering.test();
        System.out.println(ordering.findHasse());
        System.out.println(ordering.maximalElements(2, 3, 4, 6, 8, 12));
        System.out.println(ordering.minimalElements(2, 3, 4, 6, 8, 12));

        System.out.println("\nLEXICOGRAPHIC ORDERING");

        LexicographicOrdering<String, String> lo = new LexicographicOrdering<>(
            (a, b) -> a.compareTo(b) < 0,
            (a, b) -> a.compareTo(b) < 0
        );

        CartesianProduct<String, String> cp = new CartesianProduct<>();
        cp.initA("a", "b");
        cp.initB("a", "b");

        lo.initSet(cp);

        System.out.println(lo.getRelationSet());

        lo.test();
        System.out.println(lo.findHasse());
        System.out.println(lo.maximalElements(cp));
        System.out.println(lo.minimalElements(cp));
        System.out.println(lo.maximum(cp));

        CartesianOrdering<String, String> co = new CartesianOrdering<>(
            (a, b) -> a.compareTo(b) < 0,
            (a, b) -> a.compareTo(b) < 0
        );

        co.initSet(cp);

        co.test();

        System.out.println(co.getRelationSet());

        System.out.println(co.findHasse());
        System.out.println(co.maximalElements(cp));
        System.out.println(co.minimalElements(cp));
    }
}