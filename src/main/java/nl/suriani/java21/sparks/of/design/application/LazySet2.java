package nl.suriani.java21.sparks.of.design.application;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public record LazySet2<T>(T first, T second, BinaryOperator<T> getNextElement) {
    public List<T> takeUntil(Predicate<T> areThereMoreElements) {
        var tMin2 = first;
        var tMin1 = second;
        var result = new ArrayList<T>();
        while (areThereMoreElements.test(tMin1)) {
            var t = getNextElement.apply(tMin2, tMin1);
            result.add(t);
            tMin2 = tMin1;
            tMin1 = t;
        }
        return result;
    }
}
