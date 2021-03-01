package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList {

    private static final int NEXT = +1;
    private static final int PREV = -1;

    private final List<Integer> circularList = new ArrayList<>();
    private int index = 0;

    @Override
    public void add(final int element) {
        this.circularList.add(element);
    }

    @Override
    public int size() {
        return this.circularList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.circularList.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        return this.isEmpty()
                ? Optional.empty()
                : Optional.of(this.circularList.get(this.updatedIndex(NEXT)));
    }

    @Override
    public Optional<Integer> previous() {
        return this.isEmpty()
                ? Optional.empty()
                : Optional.of(this.circularList.get(this.updatedIndex(PREV)));
    }

    @Override
    public void reset() {
        this.index = 0;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        return Optional.empty();
    }

    private int updatedIndex(final int operation) {
        final int currentIndex = this.index;
        this.index = (this.index + operation + this.size()) % this.size();
        return operation == NEXT ? currentIndex : this.index;
    }
}
