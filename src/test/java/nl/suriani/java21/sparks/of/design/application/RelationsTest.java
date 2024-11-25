package nl.suriani.java21.sparks.of.design.application;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class RelationsTest {
    @Test
    void test() {
        final var primarySubscriberOfRelation = "primarySubscriberOf";
        final var familyOfRelation = "familyOf";
        final var featureOfRelation = "featureOf";
        final var referredSubscriberOfRelation = "referredSubscriberOf";
        final var referralDiscountOfRelation = "referralDiscountOf";

        final var primaryPlan = new Plan("primary", 1);
        final var basicFamilyPlan = new Plan("basicFamily", 3);
        final var premiumFamilyPlan = new Plan("premiumFamily", 5);

        var relations = new HashSet<Relation>();

        var primarySubscriber = new Individual("Tristano");
        var primarySubscribersFamily = Set.of(
                new Individual("Linda"),
                new Individual("Giulia"),
                new Individual("Laura")
        );

        var familyOfRelations = primarySubscribersFamily.stream()
                .map(individual -> new Relation(familyOfRelation, individual, primarySubscriber))
                .collect(Collectors.toSet());

        relations.addAll(familyOfRelations);

        assertThat(canSubscriberJoinFamilyPlan(primarySubscriber, relations, basicFamilyPlan)).isFalse();
        assertThat(canSubscriberJoinFamilyPlan(primarySubscriber, relations, premiumFamilyPlan)).isTrue();
    }

    private boolean canSubscriberJoinFamilyPlan(Individual primarySubscriber, Set<Relation> relations, Plan plan) {
        var familyOfRelations = Relations.findByName(relations, "familyOf");
        return familyOfRelations.stream()
                .filter(relation -> relation.object().equals(primarySubscriber))
                .count() < plan.maxFamilyMembers();
    }
    
    private record Individual(String name) {}

    private record Plan(String name, int maxFamilyMembers) {}

    private record Feature(String name) {}
}
