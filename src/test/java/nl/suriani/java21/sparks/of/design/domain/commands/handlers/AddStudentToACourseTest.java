package nl.suriani.java21.sparks.of.design.domain.commands.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import nl.suriani.java21.sparks.of.design.domain.commands.AddStudentToCourseCommand;
import nl.suriani.java21.sparks.of.design.domain.commands.projection.CourseProjector;
import nl.suriani.java21.sparks.of.design.domain.commands.projection.StudentProjector;
import nl.suriani.java21.sparks.of.design.domain.entities.course.Course;
import nl.suriani.java21.sparks.of.design.domain.entities.course.CourseId;
import nl.suriani.java21.sparks.of.design.domain.entities.course.CourseName;
import nl.suriani.java21.sparks.of.design.domain.entities.student.Student;
import nl.suriani.java21.sparks.of.design.domain.entities.student.StudentId;
import nl.suriani.java21.sparks.of.design.domain.entities.student.StudentName;
import nl.suriani.java21.sparks.of.design.domain.events.Event;
import nl.suriani.java21.sparks.of.design.domain.events.EventStore;
import nl.suriani.java21.sparks.of.design.domain.events.StudentAddedToCourse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AddStudentToCourseTest {
    private EventStore<Event> eventStore;
    private StudentProjector studentProjector;
    private CourseProjector courseProjector;
    private AddStudentToACourse handler;

    @Captor
    private ArgumentCaptor<Event> eventArgumentCaptor;

    @BeforeEach
    void setup() {
        eventStore = mock(EventStore.class);
        studentProjector = new StudentProjector();
        courseProjector = new CourseProjector();
        handler = new AddStudentToACourse(eventStore, studentProjector, courseProjector);
    }

    @Test
    void testApplyCommand() {
        var student = new Student(new StudentId(), new StudentName("Gianni"));
        var course = new Course(new CourseId(), new CourseName("Music"));
        var command = new AddStudentToCourseCommand(student, course);

        var eventStream = List.<Event>of();
        when(eventStore.findByCourseIdOrStudentId(course.id(), student.id())).thenReturn(eventStream);

        handler.apply(command);

        verify(eventStore, times(1)).publish(eventArgumentCaptor.capture());
        var event = eventArgumentCaptor.getValue();
        System.out.println(event);
    }

    @Test
    void testTooManyCoursesForStudent() {
        var student = new Student(new StudentId(), new StudentName("Gianni"));
        var course = new Course(new CourseId(), new CourseName("Music"));
        var command = new AddStudentToCourseCommand(student, course);

        var eventStream = List.<Event>of(
                new StudentAddedToCourse(student, new Course(new CourseId(), new CourseName("Math"))),
                new StudentAddedToCourse(student, new Course(new CourseId(), new CourseName("Science"))),
                new StudentAddedToCourse(student, new Course(new CourseId(), new CourseName("Art")))
        );
        when(eventStore.findByCourseIdOrStudentId(course.id(), student.id())).thenReturn(eventStream);

        var exception = assertThrows(IllegalStateException.class, () -> handler.apply(command));
        assertEquals("Student can only be enrolled in 3 courses", exception.getMessage());
    }

    @Test
    void testTooManyStudentsForCourse() {
        var student = new Student(new StudentId(), new StudentName("Gianni"));
        var course = new Course(new CourseId(), new CourseName("Music"));
        var command = new AddStudentToCourseCommand(student, course);

        var eventStream = List.<Event>of(
                new StudentAddedToCourse(new Student(new StudentId(), new StudentName("John")), course),
                new StudentAddedToCourse(new Student(new StudentId(), new StudentName("Jane")), course),
                new StudentAddedToCourse(new Student(new StudentId(), new StudentName("Alex")), course),
                new StudentAddedToCourse(new Student(new StudentId(), new StudentName("Emma")), course)
        );
        when(eventStore.findByCourseIdOrStudentId(course.id(), student.id())).thenReturn(eventStream);

        var exception = assertThrows(IllegalStateException.class, () -> handler.apply(command));
        assertEquals("Course can only allow 4 students", exception.getMessage());
    }

}
