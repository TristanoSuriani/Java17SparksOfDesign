package nl.suriani.java21.sparks.of.design.domain.commands;

import java.util.List;
import java.util.function.BiFunction;

public interface Decide<C, S, E> extends BiFunction<C, S, List<E>> {
}
