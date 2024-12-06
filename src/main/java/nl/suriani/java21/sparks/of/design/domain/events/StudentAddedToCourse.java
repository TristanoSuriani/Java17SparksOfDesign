package nl.suriani.java21.sparks.of.design.domain.events;

import nl.suriani.java21.sparks.of.design.domain.entities.course.Course;
import nl.suriani.java21.sparks.of.design.domain.entities.student.Student;
import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

public record StudentAddedToCourse(Student student, Course course) implements Event {
    public StudentAddedToCourse {
        Guards.isNotNull(student);
        Guards.isNotNull(course);
    }
}
