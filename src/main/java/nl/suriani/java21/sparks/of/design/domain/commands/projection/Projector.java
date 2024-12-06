package nl.suriani.java21.sparks.of.design.domain.commands.projection;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface Projector <S, E> extends Function<List<E>, S> {
}
