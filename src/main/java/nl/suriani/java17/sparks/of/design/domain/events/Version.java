package nl.suriani.java17.sparks.of.design.domain.events;

import nl.suriani.java17.sparks.of.design.domain.validation.ValueOutOfRangeException;

public record Version(int value) {
    public Version {
        if (value < 1) {
            throw new ValueOutOfRangeException();
        }
    }

    public Version() {
        this(1);
    }
}
