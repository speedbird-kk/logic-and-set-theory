package Recursion;

import java.util.List;

/**
 * Functional interface for an N-ary function.
 */
@FunctionalInterface
public interface NaryFunction<T> {
    T apply(List<T> args);
}