package nl.suriani.java21.sparks.of.design.domain.entities.student;

import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

public record StudentName(String value) {
    public StudentName {
        Guards.isNotNull(value);
    }
}
