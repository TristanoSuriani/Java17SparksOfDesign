package nl.suriani.java17.sparks.of.design.match.domain;

import java.util.Arrays;

public interface Guard {
    static void isNotNull(Object o) {
        if (o == null) {
            throw new MissingPropertyException();
        }
    }

    static void areNotNull(Object... objects) {
        Arrays.stream(objects)
                .forEach(Guard::isNotNull);
    }

    static IntValue value(int value) {
        return new IntValue(value);
    }

    record IntValue(int value) {
        public Between isBetween(int from) {
            return new Between(value, from);
        }

        public void isPositive() {
            if (value < 0) {
                throw new ValueOutOfRangeException();
            }
        }
    }

    record Between(int value, int from) {
        public void and(int to) {
            if (!(value >= from && value <= to)) {
                throw new ValueOutOfRangeException();
            }
        }
    }
}
