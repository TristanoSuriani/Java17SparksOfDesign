package nl.suriani.java21.sparks.of.design.domain.entities.course;

import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

import java.util.UUID;

public record CourseId(UUID value) {
    public CourseId {
        Guards.isNotNull(value);
    }

    public CourseId() {
        this(UUID.randomUUID());
    }
}
