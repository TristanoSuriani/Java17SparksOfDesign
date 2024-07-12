package nl.suriani.java17.sparks.of.design.inventory.validation;

public interface Guards {
    static void isNotNull(Object o) {
        if (o == null) {
            throw new RequiredFieldIsMissingException();
        }
    }

    static void isBiggerThan0(int i) {
        if (i < 0) {
            throw new ValueOutOfRangeException();
        }
    }
}
