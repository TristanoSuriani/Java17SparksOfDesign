package nl.suriani.java21.sparks.of.design.domain.commands;

import java.util.List;

public interface Decider<C, S, E> {
    List<E> decide(C command, S state);
    S evolve(E event, S state);
}
