import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import lab01.tdd.StrategyFactory;
import lab01.tdd.StrategyFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private static final int NUM_ELEMENTS = 10;
    private static final int MAX_OPERATIONS = 100;

    CircularList circularList;
    StrategyFactory strategyFactory = new StrategyFactoryImpl();

    @BeforeEach
    void beforeEach() {
        this.circularList = new CircularListImpl();
    }

    @Test
    void initiallyEmpty() {
        assertTrue(this.circularList.isEmpty());
        assertEquals(0, this.circularList.size());
    }

    @Test
    void addElement() {
        this.circularList.add(1);
        assertFalse(this.circularList.isEmpty());
        assertEquals(1, this.circularList.size());
    }

    @Test
    void addMoreThanOneElement() {
        this.addElement();
        this.circularList.add(1);
        assertEquals(2, this.circularList.size());
    }

    @Test
    void nextInEmptyList() {
        final Optional<Integer> next = this.circularList.next();
        assertEquals(Optional.empty(), next);
    }

    @Test
    void nextInListWithOneElement() {
        this.circularList.add(1);
        final Optional<Integer> next = this.circularList.next();
        assertEquals(1, next.get());
    }

    @Test
    void nextInListWithMoreThanOneElement() {
        this.addElementsToList();
        IntStream.range(0, NUM_ELEMENTS).boxed().forEach(i -> assertEquals(i, this.circularList.next().get()));
    }

    @Test
    void nextCircularBehaviour() {
        this.nextInListWithMoreThanOneElement();
        Optional<Integer> next = this.circularList.next();
        assertEquals(0, next.get());
    }

    @Test
    void prevInEmptyList() {
        final Optional<Integer> prev = this.circularList.previous();
        assertEquals(Optional.empty(), prev);
    }

    @Test
    void prevCircularBehaviour() {
        this.circularList.add(1);
        this.circularList.add(2);
        Optional<Integer> prev = this.circularList.previous();
        assertEquals(2, prev.get());
    }

    @Test
    void prevInListWithOneElement() {
        this.circularList.add(1);
        final Optional<Integer> prev = this.circularList.previous();
        assertEquals(1, prev.get());
    }

    @Test
    void prevInListWithMoreThanOneElement() {
        this.addElementsToList();
        IntStream.range(0, NUM_ELEMENTS)
                .boxed().map(i -> -i).sorted().map(i -> -i)
                .forEach(i -> assertEquals(i, this.circularList.previous().get()));
    }

    @Test
    void resetBeforeNextOperations() {
        this.addElementsToList();
        this.circularList.reset();
        this.nextInListWithMoreThanOneElement();
    }

    @Test
    void resetBeforePrevOperations() {
        this.addElementsToList();
        this.circularList.reset();
        this.prevInListWithMoreThanOneElement();
    }

    @Test
    void resetAfterRandomOperations() {
        this.addElementsToList();
        IntStream.range(0, new Random().nextInt() % MAX_OPERATIONS).boxed().forEach(i -> this.circularList.next());
        IntStream.range(0, new Random().nextInt() % MAX_OPERATIONS).boxed().forEach(i -> this.circularList.previous());
        this.circularList.reset();
        this.nextInListWithMoreThanOneElement();
    }

    @Test
    void nextWithStrategyEquals() {
        this.addElementsToList();
        assertEquals(Optional.of(5), this.circularList.next(strategyFactory.equalStrategy(5)));
    }

    @Test
    void nextWithStrategyOdd() {
        this.addElementsToList();
        assertEquals(Optional.of(1), this.circularList.next(strategyFactory.oddStrategy()));
    }

    @Test
    void nextWithStrategyOddMultipleOfGreaterThan() {
        this.addElementsToList();
        assertEquals(Optional.of(6), this.circularList.next(strategyFactory.composeStrategy(
                strategyFactory.evenStrategy(),
                strategyFactory.multipleOfStrategy(3),
                strategyFactory.greaterThan(0)
        )));
    }

    private void addElementsToList() {
        IntStream.range(0, NUM_ELEMENTS).forEach(this.circularList::add);
    }

}
