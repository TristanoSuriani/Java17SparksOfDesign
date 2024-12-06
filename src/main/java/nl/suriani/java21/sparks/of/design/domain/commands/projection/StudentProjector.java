package nl.suriani.java21.sparks.of.design.domain.commands.projection;

import nl.suriani.java21.sparks.of.design.domain.aggregates.StudentWithCourses;
import nl.suriani.java21.sparks.of.design.domain.commands.handlers.Deciders;
import nl.suriani.java21.sparks.of.design.domain.events.Event;
import nl.suriani.java21.sparks.of.design.domain.events.StudentAddedToCourse;

import java.util.List;

public class StudentProjector implements Projector<StudentWithCourses, Event> {

    @Override
    public StudentWithCourses apply(List<Event> events) {
        StudentWithCourses state = null;
        for (Event event : events) {
            switch (event) {
                case StudentAddedToCourse studentAddedToCourse -> {
                    state = Deciders.studentWithCoursesDecider.evolve(event, state);
                }
                default -> {}
            }
        }
        return state;
    }
}
