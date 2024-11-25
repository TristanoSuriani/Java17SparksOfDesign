package nl.suriani.java21.sparks.of.design.application;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public record LazySet<T> (T firstElement, UnaryOperator<T> getNextElement) {
    List<T> takeUntil(Predicate<T> areThereMoreElements) {
        var nextElement = firstElement;
        var result = new ArrayList<T>();
        while (areThereMoreElements.test(nextElement)) {
            result.add(nextElement);
            nextElement = getNextElement.apply(nextElement);
        }
        return result;
    }
}
