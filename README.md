# Logic and Set Theory 2IT60

Helper classes and methods for calculating various operations and testing properties related to sets, relations and mappings.

## Features
- Evaluate the cartesian product of two sets.
- Evaluate the power set of a set.
- Find all equivalence classes of a relation on one or two sets.
- Test a relation for reflexivity, symmetricity, transitivity and equivalence.
- Test a mapping for surjection, injection and bijection.
- Find the image of a mapping on a subdomain.
- Find the source of a mapping on a subcodomain.

## Manual

### Getting started

It is recommended to download this repository as a ZIP file, extract the ZIP file and open the folder on your IDE of choice. All helper classes and methods can be run from the main method in Main.java.

### Cartesian product

To calculate the cartesian product of two sets, first instantiate a new CartesianProduct object and define types of the elements of both sets. Then, initialise both sets A and B with `.initA` and `.initB` which takes variable arguments of the type. The cartesian product is found with `.findCartesianProduct()`.

```java
CartesianProduct<Integer, String> cp = new CartesianProduct<>();
cp.initA(0, 1);
cp.initB("a", "b", "c");
System.out.println(cp.findCartesianProduct());
```

Output:
`[(1, a), (0, a), (1, b), (0, b), (1, c), (0, c)]`

### Coming soon...