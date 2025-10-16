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
import Orderings.Orderings;
import Recursion.NaryFunction;
import Recursion.Recursion;
import Relations.EquivalenceTest;

/**
 * Instantiate objects of helper classes and call their methods as described in the README.
 * Please refer to the README for a manual.
 */
public class Main {
    public static void main(String[] args)
        throws NotEquivalenceRelationException,
        CannotInitialiseSeparateSetsException,
        ElementNotFoundException,
        NotAnOrderingException {

        System.out.println("ORDERINGS TEST");

        Orderings<Integer> o = new Orderings<Integer>((x, y) -> x < y);
        o.initSet(1, 2, 3, 4, 5, 6);
        o.test();
        System.out.println(o.findDirectSuccessors(2));
        System.out.println(o.maximum(1, 2, 3, 4));
        System.out.println(o.minimum(1, 2, 3));

        System.out.println("\nEXERCISE 20.8");

        Orderings<Integer> ordering = new Orderings<Integer>((x, y) -> y % x == 0);
        ordering.initSet(2, 3, 4, 6, 8, 12);
        System.out.println(ordering.findHasse());
        System.out.println(ordering.maximalElements(2, 3, 4, 6, 8, 12));
        System.out.println(ordering.minimalElements(2, 3, 4, 6, 8, 12));
    }
}