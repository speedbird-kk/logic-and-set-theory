package Operators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class for calculating the power set.
 */
public class PowerSet<T> {
    private Set<T> set;

    public PowerSet(@SuppressWarnings("unchecked") T... elements) {
        this.set = new HashSet<>(Arrays.asList(elements));
    }

    /**
     * Finds set of all subsets of set using binary counter.
     * 
     * 1 << setSize (bit shift left) gives 1 * 2 ^ setSize, corresponding
     * to the size of the power set. Looping through each element of the
     * power set,
     * 1 << j gives 1 * 2 ^ j which has 1 only at bit j, to create a mask
     * i & (1 << j) (bitwise AND) returns conjunction of each bit
     * 
     * For set with setSize = 3, {x, y, z}
     * i = 000, 1 << 0 = 001, i & (1 << j) = 000 -> empty set
     * i = 001, 1 << 0 = 001, i & (1 << j) = 001 -> add x {x}
     * i = 010, 1 << 1 = 010, i & (1 << j) = 010 -> add y {y}
     * ...
     * i = 110, 1 << 1 = 010, i & (1 << j) = 010 -> add y {y}
     * i = 110, 1 << 2 = 100, i & (1 << j) = 100 -> add z {y, z}
     * i = 111, 1 << 0 = 001, i & (1 << j) = 001 -> add x {x}
     * i = 111, 1 << 1 = 010, i & (1 << j) = 010 -> add y {x, y}
     * i = 111, 1 << 2 = 100, i & (1 << j) = 100 -> add z {x, y, z}
     */
    public Set<Set<T>> findPowerSet() {
        Set<Set<T>> powerSet = new HashSet<>();
        List<T> setList = new ArrayList<>(set);
        int setSize = set.size();
        
        for (int i = 0; i < (1 << setSize); i++) {
            Set<T> subset = new HashSet<>();
            for (int j = 0; j < setSize; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(setList.get(j));
                }
            }

            powerSet.add(subset);
        }

        return powerSet;
    }

    @SuppressWarnings("unchecked")
    public Set<T>[] findPowerSetArray() {
        return findPowerSet().toArray(new Set[0]);
    }
}
