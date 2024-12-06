package nl.suriani.java21.sparks.of.design.domain.entities.course;

import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

public record Course(CourseId id, CourseName name) {
    public Course {
        Guards.isNotNull(id);
        Guards.isNotNull(name);
    }
}
