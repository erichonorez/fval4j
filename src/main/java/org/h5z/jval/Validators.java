package org.h5z.jval;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.h5z.jval.JVal.*;

import static org.h5z.jval.JVal.*;

public final class Validators {

    /** The class cannot be instanciated */
    private Validators() {}

    /**
     * Create a validator that validates if a value is greater than another one.
     * If the value is lower than the reference value then call the provider supplier to get an instance of the error and return it in a list.
     */
    public static <T extends Comparable<T>, E> Validator<T, E> gt(T b, Supplier<E> s) {
        return v -> {
            List<E> result = new ArrayList<>();
            if (v.compareTo(b) < 1) {
                result.add(s.get());
                return result;
            }
            return result;
        };
    }

    public static <T extends Comparable<T>, E> Validator<T, E> eq(T b, Supplier<E> s) {
        return v -> {
            List<E> result = new ArrayList<>();
            if (v.compareTo(b) != 0) {
                result.add(s.get());
                return result;
            }
            return result;
        };
    }

    public static <T extends Comparable<T>, E> Validator<T, E> lt(T b, Supplier<E> s) {
        return sequentially(eq(b, s), gt(b, s));
    }

    public static <T, E> Validator<T, E> cond(Function<T, Boolean> s, Supplier<E> e) {
        return v -> {
            if (!s.apply(v)) {
                invalid(e.get());
            }
            return valid(v);
        };
    }

    // write required and optional

}
