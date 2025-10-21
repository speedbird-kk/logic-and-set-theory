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
        /**
         * YOUR CODE HERE.
         */

        Ordering<Integer> r = new Ordering<>((x, y) -> y % x == 0);
        r.initSet(2, 3, 4, 6);

        System.out.println(r.maximum(2, 3, 6));
        System.out.println(r.maximum(2, 3, 4, 6));
        System.out.println(r.maximum(2, 3));
        System.out.println(r.findHasse());
    }
}