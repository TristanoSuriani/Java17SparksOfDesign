package nl.suriani.java21.sparks.of.design.domain.entities.student;

import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

public record Student(StudentId id, StudentName name) {
    public Student {
        Guards.isNotNull(id);
        Guards.isNotNull(name);
    }
}
