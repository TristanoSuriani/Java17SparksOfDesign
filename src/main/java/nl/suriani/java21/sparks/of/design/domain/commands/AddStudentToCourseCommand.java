package nl.suriani.java21.sparks.of.design.domain.commands;

import nl.suriani.java21.sparks.of.design.domain.entities.course.Course;
import nl.suriani.java21.sparks.of.design.domain.entities.student.Student;
import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

public record AddStudentToCourseCommand(Student student, Course course) {
    public AddStudentToCourseCommand {
        Guards.isNotNull(student);
        Guards.isNotNull(course);
    }
}
