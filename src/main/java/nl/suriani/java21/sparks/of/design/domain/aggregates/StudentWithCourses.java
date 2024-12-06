package nl.suriani.java21.sparks.of.design.domain.aggregates;

import nl.suriani.java21.sparks.of.design.domain.entities.course.Course;
import nl.suriani.java21.sparks.of.design.domain.entities.student.Student;
import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

import java.util.HashSet;
import java.util.Set;

public record StudentWithCourses(Student student, Set<Course> courses) {
    public StudentWithCourses {
        Guards.isNotNull(student);
        Guards.isNotNull(courses);
        courses = Set.copyOf(courses);
    }

    public StudentWithCourses addCourse(Course course) {
        var courses = new HashSet<>(this.courses);
        courses.add(course);
        return new StudentWithCourses(student, courses);
    }
}
