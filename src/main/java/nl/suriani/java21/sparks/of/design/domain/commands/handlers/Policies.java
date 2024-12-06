package nl.suriani.java21.sparks.of.design.domain.commands.handlers;

import nl.suriani.java21.sparks.of.design.domain.aggregates.CourseWithStudents;
import nl.suriani.java21.sparks.of.design.domain.aggregates.StudentWithCourses;

import java.util.function.BiPredicate;

public interface Policies {
    BiPredicate<CourseWithStudents, Integer> courseCanOnlyHaveNStudents = (course, n) -> course.students().size() < n;
    BiPredicate<StudentWithCourses, Integer> studentCanOnlyEnroolToNCourses = (student, n) -> student.courses().size() < n;
}
