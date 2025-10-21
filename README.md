# Logic and Set Theory 2IT60

Helper classes and methods for calculating various operations and testing properties related to sets, relations and mappings.

**DISCLAIMER:** This program is based on the course Logic and Set Theory 2IT60 in its features and concepts, however it is not an official part of the course. The program may contain mistakes.

**UPDATE (17/10):** Ordering, Lexicographic ordering and Cartesian ordering have been added. Manual has also been modified to include how to use these new features. Please refer to [Ordering](#ordering), [Lexicographic ordering](#lexicographic-ordering) and [Cartesian ordering](#cartesian-ordering) for these. Please note that the [Equivalence classes](#equivalence-classes) and [Equivalence classes for relations on two (power) sets](#equivalence-classes-for-relations-on-two-power-sets) sections in the manual are severely outdated and still need to be updated according to the recent fixes and improvements made to the features concerned.

Major updates are also coming eventually to fix the structure of the code to conform more to SOLID principles from a programming perspective...

## Features
- Evaluate the cartesian product of two sets.
- Evaluate the power set of a set.
- Test a relation for reflexivity, symmetricity, transitivity and equivalence.
- Find all equivalence classes of a relation on one or two sets.
- Test a mapping for surjection, injection and bijection.
- Find the image of a mapping on a subdomain.
- Find the source of a mapping on a subcodomain.
- (NEW !) Testing a relation for reflexive, irreflexive ordering, quasi-ordering, linear relation, antisymmetric, strictly antisymmetric
- (NEW !) Find the direct successor of an element in a set for an ordering
- (NEW !) Find the children of each node of a Hasse diagram for an ordering
- (NEW !) Find the maximal, minimal elements and maximum and minimum of an ordering
- (NEW !) Make a lexicographic ordering
- (NEW !) Make a cartesian ordering
- (COMING SOON !) Recursion calculator

## Table of Contents
- [Getting started](#getting-started)
- [Cartesian product](#cartesian-product)
- [Power sets](#power-sets)
- [Relation test for equivalence](#relation-test-for-equivalence)
- [Equivalence classes](#equivalence-classes) (NEEDS UPDATE)
- [Equivalence classes for relations on two (power) sets](#equivalence-classes-for-relations-on-two-power-sets) (NEEDS UPDATE)
- [Mapping tests](#mapping-tests)
- [Image and source](#image-and-source)
- [Ordering](#ordering)
- [Lexicographic ordering](#lexicographic-ordering)
- [Cartesian ordering](#cartesian-ordering)

## Manual

### Getting started

It is recommended to download this repository as a ZIP file, extract the ZIP file and open the folder on your IDE of choice. All helper classes and methods can be run from the main method in Main.java. It is also advisable to look at the source code for each of the helper classes and methods, as it can possibly give useful insights into each of the concepts. If you spot any mistakes in the code or have any suggestions, please send an email to la.anaheim.halos@gmail.com.

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
PowerSet<Integer> ps = new PowerSet<>(1, 2, 3);
System.out.println(ps.findPowerSet());
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

There are two separate classes for finding all equivalence classes of a given relation on a given set. In this section, we find all equivalence classes of a simple relation on one set A (ie. set of relations R is a subset of A x A where A is a set containing elements). For this, use the `EquivalenceClass` class with one type and define the relation as a bi-predicate lambda expression in the constructor. Then, initialise the set A with `.initSet()` taking variable arguments of the type, and use `.findEquivalenceClasses()` to get all the equivalence classes.

For example, to print the equivalence classes of the relation ≡ (mod 3) on the set {-3, -2, -1, 0, 1, 2, 3}:
```java
EquivalenceClass<Integer> ec =
    new EquivalenceClass<>((a, x) -> (a - x) % 3 == 0);

ec.initSet(-3, -2, -1, 0, 1, 2, 3);
System.out.println(ec.findEquivalenceClasses());
```

Output:
`[[0, -3, 3], [-2, 1], [-1, 2]]`

### Equivalence classes for relations on two (power) sets

It is possible to define more complex relations on two sets or relations on two power sets. For this, use the `EquivalenceClassGeneral` class. When instantialising it, define types of elements for set A and set B for a relation on A x B. Define the relation as a lambda expression in the constructor. If it is a relation between sets, define the type to be `<Set<A>, Set<B>>`, where A, B are the types of the elements of either set. To set the elements of each set A and B, use `.initSetA()` and `.initSetB()` respectively, each taking variable arguments of the type. However, if the relation is on power sets, it is recommended to first use two `PowerSet` instances (see [Power sets](#power-sets)) to define P(A) and P(B). Importantly, since `.initSetA()` and `.initSetB()` takes variable arguments rather than a `Set`, it is necessary to pass in `.findPowerSetArray()` into `.initSetA()` and `.initSetB()` rather than `.findPowerSet()`, as the former converts the `Set` into a regular array. Please refer to the example below.

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

For example, for the mapping f: A -> B for all x ∈ A where f(x) = x / 2:
```java
Mapping<Integer, Double> f = new Mapping<>((x, y) -> y == x / 2.0);
f.initDomain(1, 2, 3, 4, 5);
f.initCodomain(0.5, 1.0, 1.5, 2.0, 2.5);
f.test();
```

Output:
`The relation is a mapping. Moreover, it is surjective and injective. Therefore, it is bijective.`

Note that `Mapping` relations can be defined not only with bi-predicates as above but also with an explicit function lambda expression as so:
```java
Mapping<Integer, Double> f = new Mapping<>(x -> x / 2.0);
```

Furthermore, if you wish to set a domain or codomain with a very large number of elements, such as all integers within a certain range, it is useful to do this with java streams. (Make sure `java.util.stream.*` is imported.) For example, to set the domain to all integers from 0 to 100 inclusive:
```java
f.initDomain(IntStream
    .rangeClosed(0, 100)
    .boxed()
    .toArray(Integer[]::new));
```

In the `Helpers` class, a method to calculate the greatest common denominator (gcd) is available. It can be used to define a mapping such as f(x) = l.c.m.(8, x) where l.c.m. is the lowest common multiple. (see exercise 18.1)
```java
Mapping<Integer, Integer> f = new Mapping<>(x -> {
    // lcm(a, b) = (a * b) / gcd(a, b)
    int gcd = Helpers.gcd(8, x);
    int lcm = (8 * x) / gcd;

    return lcm;
});
```

More methods for other similarly common mathematical functions may be available in the `Helpers` class in the future.

Finally, mappings between sets, on in other words mappings on powersets f: P(A) -> P(B) are defined in a similar manner as relations between sets as described in the previous section on equivalence classes (see [Equivalence classes for relations...](#equivalence-classes-for-relations-on-two-power-sets)). For example, to define a mapping f: P(A) -> P(B) for all X ∈ P(A) where f(X) = X ∩ N and N = {1, 2, 3}:
```java
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
```

We define the domain and the codomain in the same manner as we did to initialise the set A and set B when finding the equivalence classes of a relation between sets. (see [Equivalence classes for relations...](#equivalence-classes-for-relations-on-two-power-sets))
```java
PowerSet<Integer> domain = new PowerSet<>(-3, -2, -1, 0, 1, 2, 3);
PowerSet<Integer> codomain = new PowerSet<>(-3, -2, -1, 0, 1, 2, 3);

f.initDomain(domain.findPowerSetArray());
f.initCodomain(codomain.findPowerSetArray());
```

The method `.getRelationSet()` is also available for mappings and works the same way as when it is run on any relation. (see [Relation test for equivalence](#relation-test-for-equivalence))

### Image and source

All kinds of mappings and ways to define mappings described in the previous section (see [Mapping tests](#mapping-tests)) can be used to then find the image or source of the mapping under a subset of the domain or a subset of the codomain respectively. For example, for the mapping defined with:
```java
Mapping<Integer, Integer> f = new Mapping<>(x -> x / 2);
f.initDomain(2, 4, 6);
f.initCodomain(1, 2, 3, 4);
```

The image can be found by using the `.image()` method which takes variable arguments defining the subset under which to take the image.
```java
System.out.println(f.image(2, 4));
```

Output:
`[1, 2]`

Similarly, the source can be found with the method `.source()`.
```java
System.out.println(f.source(2, 3));
```

Output:
`[4, 6]`

For the mapping f: P(A) -> P(B) for all X ∈ P(A) where f(X) = X ∩ N and N = {1, 2, 3} given in the previous section (see [Mapping tests](#mapping-tests)), since the mapping is on power sets, it can be useful to define specific sets under which to take the image or source of the mapping, then pass those into the arguments of the `.image()` method as follows:
```java
Set<Integer> x1 = new HashSet<>(Arrays.asList(-1, 1, 2));
Set<Integer> x2 = new HashSet<>(Arrays.asList(-2, 1));

System.out.println(f.image(x1, x2));
```

Output:
`[[1], [1, 2]]`

### Ordering

Orderings can also be defined as a relation using a lambda expression and initialising the set that the ordering is on. The method `.test()` is called to test the relation for transitivity, reflexivity, irreflexivity, antisymmetricity, strict antisymmetricity and linearity.

For example, we define the structure $\langle {1, 2, 3, 4, 5, 6}, < \rangle$ in the following way and run `.test()` on it.
```Java
Ordering<Integer> o = new Ordering<Integer>((x, y) -> x < y);
o.initSet(1, 2, 3, 4, 5, 6);
o.test();
```

Output:
```
The relation:
        is transitive
        is not reflexive
        is irreflexive
        is antisymmetric
        is strictly antisymmetric
        is linear

Since it is irreflexive, transitive, strictly antisymmetric and linear, it is an irreflexive linear ordering.
```

Moreover, the direct successors of a specific element in the set can be found with `.findDirectSuccessors()`.

```Java
System.out.println(o.findDirectSuccessors(2));
```

Output:
`[3]`

The maximum and the minimum of a subset can be found similarly with `.maximum()` and `minimum()`.
```Java
System.out.println("Maximum: " + o.maximum(1, 2, 3, 4));
System.out.println("Minimum: " + o.minimum(1, 2, 3));
```

Output:
```
Maximum: 4
Minimum: 1
```

We now define a structure $\langle {2, 3, 4, 6, 8, 12}, | \rangle$ from exercise 20.8 as follows.
```Java
Ordering<Integer> ordering = new Ordering<Integer>((x, y) -> y % x == 0);
ordering.initSet(2, 3, 4, 6, 8, 12);
```

Maximal elements can be found using the `maximalElements()` method and similarly for the minimal elements.
```Java
System.out.println("Maximal: " + ordering.maximalElements(2, 3, 4, 6, 8, 12));
System.out.println("Minimal: " + ordering.minimalElements(2, 3, 4, 6, 8, 12));
```

Output:
```
Maximal: [8, 12]
Minimal: [2, 3]
```

The Hasse diagram is represented as a map with key value pairs where the keys represent each node in the Hasse diagram and the values represent the children of the node.
```Java
System.out.println(ordering.findHasse());
```

Output:
`{2=[4, 6], 3=[6], 4=[8, 12], 6=[12], 8=[], 12=[]}`

### Lexicographic Ordering

Lexicographic orderings are defined with two bi-predicates, corresponding to $R_1$ and $R_2$ for the lexicographic ordering $R_3$. For an alphabetical dictionary ordering, for example, we can use the function `.compareTo()` on strings.
```Java
LexicographicOrdering<String, String> lo = new LexicographicOrdering<>(
    (a, b) -> a.compareTo(b) < 0,
    (a, b) -> a.compareTo(b) < 0
);
```

To initialise the set of pairs $A \times B$ that the lexicographic ordering is on, first create a `CartesianProduct` object and initialise both sets A and B.

```Java
CartesianProduct<String, String> cp = new CartesianProduct<>();
cp.initA("a", "b");
cp.initB("a", "b");
```

Then, simply pass the `CartesianProduct` object into `initSet()` method of the lexicographic ordering.
```Java
lo.initSet(cp);
```

We can use `.getRelationSet()` to see all the relations in the lexicographic ordering.
```Java
System.out.println(lo.getRelationSet());
```

Output:
`[((a, a), (a, b)), ((b, a), (b, b)), ((a, a), (b, b)), ((a, a), (b, a)), ((a, b), (b, b)), ((a, b), (b, a))]`

All methods demonstrated in the ordering section (see [Ordering](#ordering)) are available.
```Java
lo.test();
System.out.println("\nHasse: " + lo.findHasse());
System.out.println("Maximal: " + lo.maximalElements(cp));
System.out.println("Minimal: " + lo.minimalElements(cp));
System.out.println("Maximum: " + lo.maximum(cp));
```

Output:
```
The relation:
        is transitive
        is not reflexive
        is irreflexive
        is antisymmetric
        is strictly antisymmetric
        is linear

Since it is irreflexive, transitive, strictly antisymmetric and linear, it is an irreflexive linear ordering.

Hasse: {(a, a)=[(a, b)], (b, b)=[], (a, b)=[(b, a)], (b, a)=[(b, b)]}
Maximal: [(b, b)]
Minimal: [(a, a)]
Maximum: (b, b)
```

### Cartesian Ordering

Cartesian orderings are instantialised very similarly to lexicographic orderings. (see [Lexicographic Ordering](#lexicographic-ordering)).
```Java
CartesianOrdering<String, String> co = new CartesianOrdering<>(
    (a, b) -> a.compareTo(b) < 0,
    (a, b) -> a.compareTo(b) < 0
);

CartesianProduct<String, String> cp = new CartesianProduct<>();
cp.initA("a", "b", "c");
cp.initB("a", "b", "c");

co.initSet(cp);
```

The exact same methods can be called on the `CartesianOrdering` object as lexicographic orderings (see [Lexicographic Ordering](#lexicographic-ordering)).

```Java
co.test();
System.out.println("\nRelations: " + co.getRelationSet());
System.out.println("\nHasse: " + co.findHasse());
System.out.println("\nMaximal: " + co.maximalElements(cp));
System.out.println("Minimal: " + co.minimalElements(cp));
```

Output:
```
The relation:
        is transitive
        is not reflexive
        is irreflexive
        is antisymmetric
        is strictly antisymmetric
        is not linear

Since it is irreflexive, transitive and strictly antisymmetric, it is an irreflexive ordering

Relations: [((a, a), (b, c)), ((b, a), (c, c)), ((a, a), (b, b)), ((a, a), (c, c)), ((b, b), (c, c)), ((a, b), (b, c)), ((b, a), (c, b)), ((a, a), (c, b)), ((a, b), (c, c))]

Hasse: {(a, a)=[(b, b), (b, c), (c, b)], (b, b)=[(c, c)], (c, c)=[], (a, b)=[(c, c), (b, c)], (b, c)=[], (a, c)=[], (c, a)=[], (b, a)=[(c, c), (c, b)], (c, b)=[]}

Maximal: [(c, c), (b, c), (a, c), (c, a), (c, b)]
Minimal: [(a, a), (a, b), (a, c), (c, a), (b, a)]
```