import Relations.RelationOnSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

/**
 * Find all equivalence classes of a general set.
 */
public class EquivalenceClassGeneral<T> extends RelationOnSet<T> {
    public EquivalenceClassGeneral(BiPredicate<T, T> relation) {
        super(relation);
    }

    /**
     * Find all equivalence classes of a given set with a defined relation on the set.
     */
    public Set<Set<T>> findEquivalenceClasses() {
        // All equivalence classes
        Set<Set<T>> equivalenceClasses = new HashSet<>();

        // Find equivalence class for each element in the set
        for (T a : super.getSet()) {
            // equivalenceClass is K(a) for each a in the set
            Set<T> equivalenceClass = new HashSet<>();

            // Check for all x in the set if aRx since K(a) = {x ∈ set | aRx}
            for (T x : super.getSet()) {
                if (hasRelation(a, x)) {
                    equivalenceClass.add(x);
                }
            }

            // Add K(a) to the set of all equivalence classes
            equivalenceClasses.add(equivalenceClass);
        }

        return equivalenceClasses;
    }

    private boolean hasRelation(T a, T x) {
        return super.getRelation().test(a, x);
    }
}