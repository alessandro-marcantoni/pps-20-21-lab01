package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CircularListImpl implements CircularList {

    private static final int NEXT_UPDATE = +1;
    private static final int PREV_UPDATE = -1;

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
        return this.get(NEXT_UPDATE);
    }

    @Override
    public Optional<Integer> previous() {
        return this.get(PREV_UPDATE);
    }

    @Override
    public void reset() {
        this.index = 0;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        OptionalInt optional = Stream.of(
                IntStream.range(this.index, this.size()),
                IntStream.range(0, this.index)
        ).flatMapToInt(i -> i)
                .map(this.circularList::get)
                .filter(strategy::apply).findFirst();
        return optional.isPresent() ? Optional.of(optional.getAsInt()) : Optional.empty();
    }

    private Optional<Integer> get(final int operation) {
        return this.isEmpty()
                ? Optional.empty()
                : Optional.of(this.circularList.get(this.getIndexAndUpdateIt(operation)));
    }

    private int getIndexAndUpdateIt(final int update) {
        final int currentIndex = this.index;
        this.index = (this.index + update + this.size()) % this.size();
        return update == NEXT_UPDATE ? currentIndex : this.index;
    }
}
