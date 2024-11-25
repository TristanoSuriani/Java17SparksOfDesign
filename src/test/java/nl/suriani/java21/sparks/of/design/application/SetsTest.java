package nl.suriani.java21.sparks.of.design.application;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class SetsTest {

    @Test
    void test() {
        var item1 = new Item(1, "pen", 15);
        var item2 = new Item(2, "pencil", 4);
        var item3 = new Item(3, "ruler", 8);
        var item4 = new Item(4, "fineliners", 10);

        var item2a = new Item(2, "pencil", 3);
        var item3a = new Item(3, "ruler", 9);
        var item4a = new Item(4, "fineliners", 7);
        var item5a = new Item(5, "technical pen", 30);

        Set<Item> setA = Set.of(item1, item2, item3, item4);
        Set<Item> setB = Set.of(item2a, item3a, item4a, item5a);

        var result = Sets.merge(setA,
                setB,
                (first, second) -> first.id.equals(second.id),
                (first, second) -> first.price < second.price ? first : second);

        System.out.println(result);
    }

    @Test
    void test2() {
        Set<Integer> setA = Set.of(1, 3, 5, 7, 9);
        Set<Integer> setB = Set.of(2, 4, 6, 8, 10);

        var setC = Sets.merge(setA, setB, (a, b) -> 2 * a == b, Integer::sum);
        System.out.println(setC);
    }

    @Test
    void test3() {
        Set<Integer> setA = Set.of(1, 3, 5, 7, 9);
        Set<String> setB = Set.of("a", "great", "afternoon", "awaits");

        var setC = Sets.merge(
                setA,
                setB,
                (a, b) -> b.length() == a,
                (a, b) -> b + " has " + a + " letters"
        );

        System.out.println(setC);
    }

    @Test
    void testIntersection() {
        var setA = Set.of(1, 3, 5, 7, 9);
        var setB = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        var setC = Sets.intersect(setA, setB);

        System.out.println(setC);
    }

    @Test
    void testDifference() {
        var setA = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var setB = Set.of(1, 3, 5, 7, 9);

        var setC = Sets.diff(setA, setB);
        assertThat(setC).containsExactly(2, 4, 6, 8, 10);
    }

    @Test
    void joinWithMerge() {
        var map1 = new HashMap<>();
        map1.put("name", "Sjonni");
        map1.put("lastName", "Puk");
        map1.put("username", "sjonni.puk");
        map1.put("email", "sjonni@puk.nl");

        var map2 = new HashMap<>();
        map2.put("ad", "sj.onni");
        map2.put("rights", "yes,yes,no");
        map2.put("username", "sjonni.puk");

        var setA = Set.of(map1);
        var setB = Set.of(map2);
        var setC = Sets.merge(
                setA,
                setB,
                (s1, s2) -> s1.get("username").equals(s2.get("username")),
                (s1, s2) -> {
                    var result = new HashMap<>();
                    result.put("name", s1.get("username"));
                    result.put("lastName", s1.get("lastName"));
                    result.put("rights", s2.get("rights"));

                    return result;
                }
        );

        System.out.println(setC);
    }

    @Test
    void testSymmetricDifference() {
        var a = Set.of(1, 2, 3, 4, 5, 6, 7);
        var b = Set.of(4, 5, 6, 7, 8, 9, 10);

        var c = Sets.symmetricDifference(
                a,
                b,
                Integer::equals,
                a1 -> "" + a1,
                b1 -> "" + b1
        );

        System.out.println(c);
    }

    @Test
    void testUnion() {
        var a = Set.of('1', '3', '5', 'a', 'b');
        var b = Set.of(1, 2, 3, 4, 5);

        var c = Sets.union(
                a,
                b,
                (a1, b1) -> ("" + a1).equals(("" + b1)),
                a1 -> "[a] -> " + ("" + a1).toUpperCase(),
                b1 -> "[b] -> " + ("" + b1).toUpperCase(),
                (a1, b1) -> "[a, b] -> " + ("" + a1).toUpperCase()
        );

        System.out.println(c);
    }

    @Test
    void testLazySet() {
        var a = new LazySet<>(1, a1 -> a1 + 2);
        System.out.println(a.takeUntil(a1 -> a1 < 10));
    }

    @Test
    void testLazySet2() {
        var a = new LazySet2<>(1, 1, Integer::sum);
        System.out.println(a.takeUntil(a1 -> a1 < 50));
    }

    @Test
    void testRuleBasedSet() {
        var a = new RuleBasedSet<String>(a1 -> a1.contains("r") || a1.contains("R"));
        assertThat(a.contains("pear")).isTrue();
        assertThat(a.contains("apple")).isFalse();
        System.out.println(a.intersection(Set.of("pear", "apple", "banana", "rhubarb", "Rhino", "123")));
        var b = new RuleBasedSet<String>(a1 -> a1.length() > 5);
        var c = a.intersection(b);
        var d = a.union(b);
        assertThat(c.contains("banana")).isFalse();
        assertThat(d.contains("banana")).isTrue();
        System.out.println(c.intersection(Set.of("pear", "apple", "banana", "rhubarb", "Rhino", "123")));
        System.out.println(d.intersection(Set.of("pear", "apple", "banana", "rhubarb", "Rhino", "123")));
    }

    @Test
    void testRuleBasedSet2() {
        var setOfAllExternalSubscribers = new RuleBasedSet<Subscriber>(subscriber -> !subscriber.internal);
        var setOfAllExternalSubscriptions = new RuleBasedSet<Subscription>(
                subscription -> subscription.subscribers.stream()
                                    .anyMatch(sub -> !sub.internal));

        assertThat(setOfAllExternalSubscriptions.contains(
                new Subscription(1, "A subscription", "{bla: true}, ", List.of(
                        new Subscriber(10, "Piet", true),
                        new Subscriber(11, "Puk", false)
                ))
        )).isTrue();

        assertThat(setOfAllExternalSubscriptions.contains(
                new Subscription(1, "A subscription", "{bla: true}, ", List.of(
                        new Subscriber(10, "Piet", true)
                ))
        )).isFalse();
    }

    @Test
    void relations() {
        var individuals = Set.of("Tristano", "Linda", "Giulia", "Laura", "Fred", "Marga", "Monique", "Harm", "Julian", "Isabella");
        final var parentOf = "parentOf";
        var relations = Set.of(
                new Relation<>(parentOf, "Tristano", "Giulia"),
                new Relation<>(parentOf, "Tristano", "Laura"),
                new Relation<>(parentOf, "Linda", "Giulia"),
                new Relation<>(parentOf, "Linda", "Laura"),
                new Relation<>(parentOf, "Fred", "Linda"),
                new Relation<>(parentOf, "Fred", "Monique"),
                new Relation<>(parentOf, "Marga", "Linda"),
                new Relation<>(parentOf, "Marga", "Monique"),
                new Relation<>(parentOf, "Monique", "Julian"),
                new Relation<>(parentOf, "Monique", "Isabella"),
                new Relation<>(parentOf, "Harm", "Julian"),
                new Relation<>(parentOf, "Harm", "Isabella")
        );

        var parentsOfIsabella = getParentsOf(relations, "Isabella");

        System.out.println(parentsOfIsabella);

        var grandparentsOfIsabella = relations.stream()
                .filter(relation -> relation.name().equals(parentOf))
                .filter(relation -> getParentsOf(relations, "Isabella").contains(relation.object()))
                .map(Relation::subject)
                .collect(Collectors.toSet());

        System.out.println(grandparentsOfIsabella);
    }

    private Set<String> getParentsOf(Set<Relation<String, String>> relations, String individual) {
        return relations.stream()
                .filter(relation -> relation.name().equals("parentOf"))
                .filter(relation -> relation.object().equals(individual))
                .map(Relation::subject)
                .collect(Collectors.toSet());
    }

    record Subscription(Integer id, String name, String query, List<Subscriber> subscribers) {}

    record Subscriber(Integer id, String name, boolean internal) {}

    record Item(Integer id, String name, Integer price) {}
}