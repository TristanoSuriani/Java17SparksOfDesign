package nl.suriani.java21.sparks.of.design.domain.aggregates;

import nl.suriani.java21.sparks.of.design.domain.entities.course.Course;
import nl.suriani.java21.sparks.of.design.domain.entities.student.Student;
import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

import java.util.HashSet;
import java.util.Set;

public record CourseWithStudents(Course course, Set<Student> students) {
    public CourseWithStudents {
        Guards.isNotNull(course);
        Guards.isNotNull(students);
        students = Set.copyOf(students);
    }

    public CourseWithStudents addStudent(Student student) {
        var students = new HashSet<>(this.students);
        students.add(student);
        return new CourseWithStudents(course, students);
    }
}
