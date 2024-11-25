package nl.suriani.java21.sparks.of.design.application.numbers;

public sealed interface Set {
    int count();

    record Empty() implements Set {
        @Override
        public int count() {
            return 0;
        }
    }

    record Number(Set set) implements Set {
        @Override
        public int count() {
            return 1 + set.count();
        }
    }
}
