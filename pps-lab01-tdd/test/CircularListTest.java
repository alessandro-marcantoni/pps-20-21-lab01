import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

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
    void nextInNotEmptyList() {
        this.circularList.add(1);
        final Optional<Integer> next = this.circularList.next();
        assertEquals(1, next.get());
    }

}
