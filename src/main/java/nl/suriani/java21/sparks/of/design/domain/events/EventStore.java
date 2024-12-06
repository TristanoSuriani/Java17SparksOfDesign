package nl.suriani.java21.sparks.of.design.domain.events;

import nl.suriani.java21.sparks.of.design.domain.entities.course.CourseId;
import nl.suriani.java21.sparks.of.design.domain.entities.student.StudentId;

import java.util.List;

public interface EventStore<E> {
    List<E> findByStudentId(StudentId studentId);
    List<E> findByCourseId(CourseId courseId);
    List<E> findByCourseIdOrStudentId(CourseId courseId, StudentId studentId);
    void publish(E event);
}
