package nl.suriani.java21.sparks.of.design.snapshot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static shadow.org.assertj.core.api.Assertions.assertThat;

public class SnapshotTest {

    @Test
    void test() throws Exception {
        Snapshot.of("snapshot.json")
                .compareAsJsonStringWith(new Dinges("Tristanou", "Suriello", 42,
                        List.of(new Dinges("Giualia", "Suriani", 7, List.of()),
                                new Dinges("Laura", "Suriani", 5, List.of()))));
    }

    record Dinges(String name, String lastname, int age, List<Dinges> children) {}

    @Test
    void sum() {
        assertThat(add(2, 3)).isEqualTo(5);
        assertThat(mul(2, 3)).isEqualTo(6);
    }

    int add (int a, int b) {
        return b == 0
                ? a
                : add (a + 1, b - 1);
    }

    int mul (int a, int b) {
        System.out.println(String.format("a: %d, b: %d", a, b));
        return b == 1
                ? a
                : mul(add (a, b - 1), b- 1);
    }
}
