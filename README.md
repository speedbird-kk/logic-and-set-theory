# Logic and Set Theory 2IT60

Helper classes and methods for calculating various operations and testing properties related to sets, relations and mappings.

## Features
- Evaluate the cartesian product of two sets.
- Evaluate the power set of a set.
- Test a relation for reflexivity, symmetricity, transitivity and equivalence.
- Find all equivalence classes of a relation on one or two sets.
- Test a mapping for surjection, injection and bijection.
- Find the image of a mapping on a subdomain.
- Find the source of a mapping on a subcodomain.

## Manual

### Getting started

It is recommended to download this repository as a ZIP file, extract the ZIP file and open the folder on your IDE of choice. All helper classes and methods can be run from the main method in Main.java. It is also advisable to look at the source code for each of the helper classes and methods, as it can possibly give useful insights into each of the concepts.

### Cartesian product

To calculate the cartesian product of two sets, first instantiate a new `CartesianProduct` object and define types of the elements of both sets. Then, initialise both sets A and B with `.initA` and `.initB` which takes variable arguments of the type. The cartesian product is found with `.findCartesianProduct()`.

To find the cartesian product {0, 1} x {a, b, c}:
```java
CartesianProduct<Integer, String> cp = new CartesianProduct<>();
cp.initA(0, 1);
cp.initB("a", "b", "c");
System.out.println(cp.findCartesianProduct());
```

Output:
`[(1, a), (0, a), (1, b), (0, b), (1, c), (0, c)]`

### Power sets

To find the power set of a given set, first instantiate a `PowerSet` object with the type of the elements of the set. Add all elements of the set as variable arguments parameters in the constructor. Use `.findPowerSet()` to get the power set.

For example, to find the power set of the set {1, 2, 3}:
```java
PowerSet<Integer> powerSet = new PowerSet<>(1, 2, 3);
System.out.println(powerSet.findPowerSet());
```

Output:
`[[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]`

### Relation test for equivalence

To test whether a given relation on a given set is reflexive, symmetric, transitive and/or an equivalence relation, first instantiate an `EquivalenceTest` object with the type of elements of the set the relation is on and define the relation using a lambda expression in the constructor. Then, use `.initSet()` to initialise the set the relation is on with variable arguments listing all elements of the set. Call the method `.test()` to find if the relation is reflexive, symmetric, transitive and/or an equivalence relation.

For example, to test the relation ≡ (mod 3) on the set {-2, -1, 0, 1, 2}:
```java
EquivalenceTest<Integer> et =
    new EquivalenceTest<>((a, x) -> (a - x) % 3 == 0);

et.initSet(-2, -1, 0, 1, 2);
et.test();
```

Output:
`The relation is reflexive, symmetric, and transitive. Hence it is an equivalence relation.`

Moreover, it is also possible to list all the pairs R(a, x) for a relation. Use the `.getRelationSet()` method for this:
```java
System.out.println(et.getRelationSet());
```

Output:
`[(0, 0), (1, 1), (2, 2), (-1, 2), (-2, 1), (1, -2), (2, -1), (-1, -1), (-2, -2)]`

### Equivalence classes

There are two separate classes for finding all equivalence classes of a given relation on a given set. Firstly, to find all equivalence classes of a simple relation on one set A (ie. set of relations R is a subset of A x A where A is a set containing elements), use the `EquivalenceClass` class with one type and define the relation as a bi-predicate lambda expression in the constructor. Then, initialise the set A with `.initSet()` taking variable arguments of the type, and use `.findEquivalenceClasses()` to get all the equivalence classes.

For example, to print the equivalence classes of the relation ≡ (mod 3) on the set {-3, -2, -1, 0, 1, 2, 3}:
```java
EquivalenceClass<Integer> ec =
    new EquivalenceClass<>((a, x) -> (a - x) % 3 == 0);

ec.initSet(-3, -2, -1, 0, 1, 2, 3);
System.out.println(ec.findEquivalenceClasses());
```

Output:
`[[0, -3, 3], [-2, 1], [-1, 2]]`

Secondly, it is possible to define more complex relations on two sets or relations on two power sets. For this, use the `EquivalenceClassGeneral` class. When instantialising it, define types of elements for set A and set B for a relation on A x B. Define the relation as a lambda expression in the constructor. If it is a relation between sets, define the type to be `<Set<A>, Set<B>>`, where A, B are the types of the elements of either set. To set the elements of each set A and B, use `.initSetA()` and `.initSetB()` respectively, each taking variable arguments of the type. However, if the relation is on power sets, it is recommended to first use two `PowerSet` instances (refer to section on power sets) to define P(A) and P(B). Importantly, since `.initSetA()` and `.initSetB()` takes variable arguments rather than a `Set`, it is necessary to pass in `.findPowerSetArray()` into `.initSetA()` and `.initSetB()` rather than `.findPowerSet()`, as the former converts the `Set` into a regular array. Please refer to the example below.

Here, we instantiate an `EquivalenceClassGeneral` object and define the relation between sets (relation on power set) such that R(A, B) if and only if A ∪ {0} = B ∪ {0}.
```java
EquivalenceClassGeneral<Set<Integer>, Set<Integer>> ecg
    = new EquivalenceClassGeneral<>((setA, setB) -> {

        // Make copies of both sets.
        Set<Integer> setAUnion0 = new HashSet<>(setA);
        Set<Integer> setBUnion0 = new HashSet<>(setB);

        // Add the element 0 to compute A ∪ {0} and B ∪ {0} respectively.
        setAUnion0.add(0);
        setBUnion0.add(0);

        return setAUnion0.equals(setBUnion0);
    });
```

Then, we define power sets P(A) and P(B) that the relation between sets are on.
```java
PowerSet<Integer> posetA = new PowerSet<>(0, 1, 2);
PowerSet<Integer> posetB = new PowerSet<>(0, 1);
```

Finally, we set both power sets that the relation is on using `findPowerSetArray()` and then print out the set of all equivalence classes.
```java
ecg.initSetA(posetA.findPowerSetArray());
ecg.initSetB(posetB.findPowerSetArray());

System.out.println(ecg.findEquivalenceClasses());
```

Output:
`[[[], [0]], [[1], [0, 1]]]`

### Mapping tests

To test whether a relation is a mapping or not, and if a mapping is surjective, injective and/or bijective, use the `Mapping` class which takes two types, each being the type for the elements in the domain and codomain respectively. Define the mapping the same way as a relation using a lambda expression in the constructor. Use `.initDomain` to add elements to the domain with variable arguments, and similarly for `.initCodomain`. Call the method `.test()` to print the conclusion of the tests.

For example, for the mapping f: A -> B for all x ∈ A
```java
Mapping<Integer, Double> half = new Mapping<>((x, y) -> y == x / 2.0);
        half.initDomain(1, 2, 3, 4, 5);
        half.initCodomain(0.5, 1.0, 1.5, 2.0, 2.5);
        half.test();
```