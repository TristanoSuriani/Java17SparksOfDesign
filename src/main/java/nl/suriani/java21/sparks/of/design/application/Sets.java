package nl.suriani.java21.sparks.of.design.application;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;


public class Sets {

    public static <A, B, C> Set<C> merge(Set<A> a, Set<B> b, BiPredicate<A, B> p, BiFunction<A, B, C> f) {
        Set<C> c = new HashSet<>();
        for (A a1 : a) {
            for (B b1 : b) {
                if (p.test(a1, b1)) {
                    c.add(f.apply(a1, b1));
                }
            }
        }
        return c;
    }

    public static <A, B> Set<A> intersect(Set<A> a, Set<B> b) {
        return merge(
                a,
                b,
                Object::equals,
                (a1, b1) -> a1
        );
    }

    public static <A, B, C> Set<C> symmetricDifference(
            Set<A> a,
            Set<B> b,
            BiPredicate<A, B> equivalenceFunction,
            Function<A, C> aToCMapperFunction,
            Function<B, C> bToCMapperFunction) {

        var c = new HashSet<C>();

        a.stream()
                .filter(a1 -> b.stream().noneMatch(b1 -> equivalenceFunction.test(a1, b1)))
                .map(aToCMapperFunction)
                .forEach(c::add);

        b.stream()
                .filter(b1 -> a.stream().noneMatch(a1 -> equivalenceFunction.test(a1, b1)))
                .map(bToCMapperFunction)
                .forEach(c::add);

        return c;
    }

    public static <A, B, C> Set<C> union(
            Set<A> a,
            Set<B> b,
            BiPredicate<A, B> equivalenceFunction,
            Function<A, C> aToCMapperFunction,
            Function<B, C> bToCMapperFunction,
            BiFunction<A, B, C> aEnBToCMapperFunction) {

        var c = Sets.symmetricDifference(
                a,
                b,
                equivalenceFunction,
                aToCMapperFunction,
                bToCMapperFunction
        );

        c.addAll(Sets.merge(
                a,
                b,
                equivalenceFunction,
                aEnBToCMapperFunction
        ));

        return c;
    }

    public static <A, B> Set<A> diff(Set<A> a, Set<B> b) {
        return merge(
                a,
                Set.of(false),
                (a1, ignore) -> !b.contains(a1),
                (a1, b1) -> a1
        );
    }
}
