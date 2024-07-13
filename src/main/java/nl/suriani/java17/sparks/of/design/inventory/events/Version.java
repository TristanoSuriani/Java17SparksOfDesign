package nl.suriani.java17.sparks.of.design.inventory.events;

import nl.suriani.java17.sparks.of.design.inventory.validation.ValueOutOfRangeException;

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
