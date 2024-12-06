package nl.suriani.java21.sparks.of.design.domain.entities.course;

import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

public record CourseName(String value) {
    public CourseName {
        Guards.isNotNull(value);
    }
}
