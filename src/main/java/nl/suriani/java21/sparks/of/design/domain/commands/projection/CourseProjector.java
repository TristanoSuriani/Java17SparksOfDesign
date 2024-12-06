package nl.suriani.java21.sparks.of.design.domain.commands.projection;

import nl.suriani.java21.sparks.of.design.domain.aggregates.CourseWithStudents;
import nl.suriani.java21.sparks.of.design.domain.commands.handlers.Deciders;
import nl.suriani.java21.sparks.of.design.domain.events.Event;
import nl.suriani.java21.sparks.of.design.domain.events.StudentAddedToCourse;

import java.util.List;

public class CourseProjector implements Projector<CourseWithStudents, Event> {
    @Override
    public CourseWithStudents apply(List<Event> events) {
        CourseWithStudents state = null;
        for (Event event : events) {
            switch (event) {
                case StudentAddedToCourse studentAddedToCourse -> {
                    state = Deciders.courseWithStudentsDecider.evolve(event, state);
                }
                default -> {}
            }
        }
        return state;
    }
}
