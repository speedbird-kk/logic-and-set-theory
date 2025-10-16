package Main;

import EquivalenceClass.EquivalenceClass;
import Exceptions.CannotInitialiseSeparateSetsException;
import Exceptions.ElementNotFoundException;
import Exceptions.NotEquivalenceRelationException;
import EquivalenceClass.EquivalenceClass;
import Mappings.Mapping;
import Operators.CartesianProduct;
import Operators.Helpers;
import Operators.PowerSet;
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
        ElementNotFoundException {

        EquivalenceTest<Integer> et = new EquivalenceTest<>((x, y) -> (x - y) % 3 == 0);
        et.initSet(1, 2, 3, 4, 5, 6, 7);
        et.test();

        EquivalenceClass<Integer> ec = new EquivalenceClass<Integer>((x, y) -> (x - y) % 3 == 0);
        ec.initSet(1, 2, 3, 4, 5, 6);
        System.out.println(ec.findEquivalenceClasses());
        System.out.println(ec.findEquivalenceClass(6));
    }
}