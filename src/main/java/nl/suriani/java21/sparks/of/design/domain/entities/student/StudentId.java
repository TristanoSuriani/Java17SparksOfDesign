package nl.suriani.java21.sparks.of.design.domain.entities.student;

import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

import java.util.UUID;

public record StudentId(UUID value) {
    public StudentId {
        Guards.isNotNull(value);
    }

    public StudentId() {
        this(UUID.randomUUID());
    }
}
