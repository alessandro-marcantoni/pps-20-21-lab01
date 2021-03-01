import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private static final int NUM_ELEMENTS = 3;

    CircularList circularList;

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
        this.addMoreElements();
        IntStream.range(0, NUM_ELEMENTS).boxed().forEach(i -> {
            assertEquals(i, this.circularList.next().get());
        });
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
        this.addMoreElements();
        IntStream.range(0, NUM_ELEMENTS).boxed().map(i -> -i).sorted().map(i -> -i).forEach(i -> {
            assertEquals(i, this.circularList.previous().get());
        });
    }

    private void addMoreElements() {
        IntStream.range(0, NUM_ELEMENTS).forEach(this.circularList::add);
    }

}
