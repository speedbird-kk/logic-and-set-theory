package Main;

import EquivalenceClass.EquivalenceClass;
import EquivalenceClass.EquivalenceClassGeneral;
import Mappings.Mapping;
import Operators.CartesianProduct;
import Operators.Helpers;
import Operators.PowerSet;
import Relations.EquivalenceTest;

/**
 * Instantiate objects of helper classes and call their methods as described in the README.
 * Please refer to the README for a manual.
 */
public class Main {
    public static void main(String[] args) {
        //Write your code here.

        EquivalenceTest<Integer> et = new EquivalenceTest<>((x, y) -> y == x + 3);
        et.initSet(8, 9, 10, 11, 12, 13, 14, 15);
        et.test();
    }
}