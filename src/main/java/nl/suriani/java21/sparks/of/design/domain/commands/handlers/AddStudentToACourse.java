package nl.suriani.java21.sparks.of.design.domain.commands.handlers;

import nl.suriani.java21.sparks.of.design.domain.commands.AddStudentToCourseCommand;
import nl.suriani.java21.sparks.of.design.domain.commands.projection.CourseProjector;
import nl.suriani.java21.sparks.of.design.domain.commands.projection.StudentProjector;
import nl.suriani.java21.sparks.of.design.domain.events.Event;
import nl.suriani.java21.sparks.of.design.domain.events.EventStore;

import java.util.HashSet;

public class AddStudentToACourse {

    private EventStore<Event> eventStore;
    private StudentProjector studentProjector;
    private CourseProjector courseProjector;

    public AddStudentToACourse(EventStore<Event> eventStore, StudentProjector studentProjector, CourseProjector courseProjector) {
        this.eventStore = eventStore;
        this.studentProjector = studentProjector;
        this.courseProjector = courseProjector;
    }

    public void apply(AddStudentToCourseCommand command) {
        var eventStream = eventStore.findByCourseIdOrStudentId(command.course().id(), command.student().id());
        var studentWithCourses = studentProjector.apply(eventStream);
        var courseWithStudents = courseProjector.apply(eventStream);

        var studentEvents = Deciders.studentWithCoursesDecider.decide(command, studentWithCourses);
        var courseEvents = Deciders.courseWithStudentsDecider.decide(command, courseWithStudents);

        var events = new HashSet<>(studentEvents);
        events.addAll(courseEvents);

        var newStudentWithCourses = studentWithCourses;
        for (var event : events) {
            newStudentWithCourses = Deciders.studentWithCoursesDecider.evolve(event, newStudentWithCourses);
        }

        var newCourseWithStudents = courseWithStudents;
        for (var event : events) {
            newCourseWithStudents = Deciders.courseWithStudentsDecider.evolve(event, newCourseWithStudents);
        }

        events.forEach(eventStore::publish);
    }
}
