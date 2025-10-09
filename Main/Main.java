package Main;

import EquivalenceClass.EquivalenceClass;
import EquivalenceClass.EquivalenceClassGeneral;
import Mappings.Mapping;
import Operators.CartesianProduct;
import Operators.Helpers;
import Operators.PowerSet;
import Relations.EquivalenceTest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

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
        EquivalenceClass<Integer> ec =
            new EquivalenceClass<>((a, x) -> (a - x) % 3 == 0);

        ec.initSet(-3, -2, -1, 0, 1, 2, 3);
        System.out.println(ec.findEquivalenceClasses());

        // Test for relation xRy if (xy > 0) OR (x = y = 0)
        EquivalenceClass<Integer> ec2 =
            new EquivalenceClass<>((a, x) -> (a * x > 0) || ((a == 0) && (x == 0)));
        
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

        PowerSet<Integer> powerSet = new PowerSet<>(1, 2, 3);
        System.out.println(powerSet.findPowerSet());

        Mapping<Integer, Integer> myMapping = new Mapping<>((x, y) -> y == x * x);
        myMapping.initDomain(-2, -1, 0, 1, 2);
        
        // Use IntStream to initialise the codomain to all integers in a range.
        myMapping.initCodomain(IntStream
            .rangeClosed(0, 100)
            .boxed()
            .toArray(Integer[]::new));
        
        myMapping.test();

        // EXERCISe 18.1 xRy if y = l.c.m.(8, x)
        Mapping<Integer, Integer> lcmMapping = new Mapping<>((x, y) -> {
            int gcd = Helpers.gcd(8, x);
            int lcm = (8 * x) / gcd;

            return y == lcm;
        });

        lcmMapping.initDomain(IntStream
            .rangeClosed(1, 12)
            .boxed()
            .toArray(Integer[]::new));
        
        lcmMapping.initCodomain(IntStream
            .rangeClosed(1, 100)
            .boxed()
            .toArray(Integer[]::new));

        System.out.println(lcmMapping.getRelationSet());

        Mapping<Integer, Double> half = new Mapping<>((x, y) -> y == x / 2.0);
        half.initDomain(1, 2, 3, 4, 5);
        half.initCodomain(0.5, 1.0, 1.5, 2.0, 2.5);
        half.test();
        System.out.println(half.getRelationSet());
        System.out.println(half.image(1, 2, 3));

        Mapping<Set<Integer>, Set<Integer>> f = new Mapping<>((setX, setY) -> {
            Set<Integer> n = new HashSet<>(Arrays.asList(1, 2, 3));
            Set<Integer> intersection = new HashSet<>();
            
            for (Integer x : setX) {
                if (n.contains(x)) {
                    intersection.add(x);
                }
            }

            return setY.equals(intersection);
        });

        PowerSet<Integer> domain = new PowerSet<>(-3, -2, -1, 0, 1, 2, 3);
        PowerSet<Integer> codomain = new PowerSet<>(-3, -2, -1, 0, 1, 2, 3);

        f.initDomain(domain.findPowerSetArray());
        f.initCodomain(codomain.findPowerSetArray());

        f.test();

        Set<Integer> x1 = new HashSet<>(Arrays.asList(-1, 1, 2));
        Set<Integer> x2 = new HashSet<>(Arrays.asList(-2, 1));
        Set<Integer> y1 = new HashSet<>(Arrays.asList(1, 3));

        // System.out.println(f.getRelationSet());
        System.out.println("image: " + f.image(x1, x2));
        System.out.println("source: " + f.source(y1));

        Mapping<Integer, Integer> halff = new Mapping<>(x -> x / 2);

        halff.initDomain(2, 4, 6);
        halff.initCodomain(1, 2, 3, 4);

        System.out.println(halff.image(3, 4));
        System.out.println(halff.source(2));
        System.out.println(halff.getRelationSet());

        EquivalenceClassGeneral<Set<Integer>, Set<Integer>> ecpow
            = new EquivalenceClassGeneral<>((setX, setY) -> setX.equals(setY));
    
        ecpow.initSetA(domain.findPowerSetArray());
        ecpow.initSetB(codomain.findPowerSetArray());

        System.out.println(ecpow.findEquivalenceClasses());
    }
}