package nl.suriani.java21.sparks.of.design.domain.commands.handlers;

import nl.suriani.java21.sparks.of.design.domain.aggregates.CourseWithStudents;
import nl.suriani.java21.sparks.of.design.domain.aggregates.StudentWithCourses;
import nl.suriani.java21.sparks.of.design.domain.commands.AddStudentToCourseCommand;
import nl.suriani.java21.sparks.of.design.domain.commands.Decider;
import nl.suriani.java21.sparks.of.design.domain.events.Event;
import nl.suriani.java21.sparks.of.design.domain.events.StudentAddedToCourse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Deciders {
    Decider<AddStudentToCourseCommand, StudentWithCourses, Event> studentWithCoursesDecider =
            new Decider<>() {
                @Override
                public List<Event> decide(AddStudentToCourseCommand command, StudentWithCourses state) {
                    if (state != null && Policies.studentCanOnlyEnroolToNCourses.negate().test(state, 3)) {
                        throw new IllegalStateException("Student can only be enrolled in 3 courses");
                    }
                    return List.of(new StudentAddedToCourse(command.student(), command.course()));
                }

                @Override
                public StudentWithCourses evolve(Event event, StudentWithCourses state) {
                    return switch (event) {
                        case StudentAddedToCourse studentAddedToCourse -> {
                            if (state == null) {
                                yield new StudentWithCourses(studentAddedToCourse.student(), Set.of(studentAddedToCourse.course()));
                            }

                            yield state.addCourse(studentAddedToCourse.course());
                        }
                        default -> state;
                    };
                }
            };

    Decider<AddStudentToCourseCommand, CourseWithStudents, Event> courseWithStudentsDecider =
            new Decider<>() {
                @Override
                public List<Event> decide(AddStudentToCourseCommand command, CourseWithStudents state) {
                    if (state != null && Policies.courseCanOnlyHaveNStudents.negate().test(state, 4)) {
                        throw new IllegalStateException("Course can only allow 4 students");
                    }
                    return List.of(new StudentAddedToCourse(command.student(), command.course()));
                }

                @Override
                public CourseWithStudents evolve(Event event, CourseWithStudents state) {
                    return switch (event) {
                        case StudentAddedToCourse studentAddedToCourse -> {
                            if (state == null) {
                                yield  new CourseWithStudents(studentAddedToCourse.course(), Set.of(studentAddedToCourse.student()));
                            }
                            yield state.addStudent(studentAddedToCourse.student());
                        }
                        default -> state;
                    };
                }
            };
}
