package Mappings;


import Relations.RelationOnSet;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;


public class Mapping<T> extends RelationOnSet<T> {
    public Mapping(BiPredicate<T, T> relation) {
        super(relation);
    }
    
    /**
     * Returns true if relation is a mapping and false otherwise.
     */
    public boolean isMapping() {
        for (T x : domain) {
            int count = 0;

            for (T y : codomain) {
                if (hasRelation(x, y)) {
                    count++;

                    if (count > 1) {
                        return false;
                    }
                }
            }

            if (count == 0) {
                return false;
            }
        }

        return true;
    }
}