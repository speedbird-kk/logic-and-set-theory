import Operators.CartesianProduct;
import Relations.EquivalenceTest;

/**
 * Use this Main class for finding equivalence classes of general relations on a set.
 * 
 * How to use:
 * Initialise a new EquivalenceClassGeneral object with generic type
 * and define the relation in the constructor.
 * Use the .initSet(T... elements) to add all elements of your set.
 * Run .findEquivalenceClasses() to find all equivalence classes.
 * 
 * Initialise a new CartesianProduct object and define relation with generic types
 * in constructor. Set both sets with .initA(K... elements) and .initB (V... elements)
 * then use findCartesianProduct()
 * 
 * Initialise a new EquivalenceTest object with generic type
 * and define the relation in the constructor.
 * Use .initSet(T... elements) to add all elements of your set.
 * .getRelationSet() gets the array of all relations R(x, y)
 * .test() prints out if the relation is reflexive, symmetric and/or transitive.
 * 
 * See example uses below:
 */
public class Main {
    public static void main(String[] args) {
        // Test case (Relation is ≡ (mod 3) defined in constructor)
        EquivalenceClassGeneral<Integer> ec =
            new EquivalenceClassGeneral<>((a, x) -> (a - x) % 3 == 0);

        ec.initSet(-3, -2, -1, 0, 1, 2, 3);
        // System.out.println(ec.findEquivalenceClasses());

        // Test for relation xRy if (xy > 0) OR (x = y = 0)
        EquivalenceClassGeneral<Integer> ec2 =
            new EquivalenceClassGeneral<>((a, x) -> (a * x > 0) || ((a == 0) && (x == 0)));
        
        ec2.initSet(-4, -3, -2, -1, 0, 1, 2, 3, 4);
        System.out.println(ec2.findEquivalenceClasses());

        CartesianProduct<Integer, Integer> cp = new CartesianProduct<>();
        cp.initA(0, 1);
        cp.initB(3, 5, 7);
        System.out.println(cp.findCartesianProduct());

        EquivalenceTest<Integer> et =
            new EquivalenceTest<>((a, x) -> (a - x) % 3 == 0);
        
        et.initSet(-2, -1, 0, 1, 2);
        System.out.println(et.getRelationSet());
        et.test();

        EquivalenceClassGeneral<Integer> ec3 =
            new EquivalenceClassGeneral<>((a, b) -> (a + b) % 2 == 0);
        
        ec3.initSet(0, 1, 2, 3, 4, 5, 6);

        System.out.println("\n\nEquivalence classes for 'xRy iff x + y is even': "
            + ec3.findEquivalenceClasses());
    }
}