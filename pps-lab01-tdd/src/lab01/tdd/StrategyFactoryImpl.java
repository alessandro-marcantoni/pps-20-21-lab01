package lab01.tdd;

import java.util.Arrays;

public class StrategyFactoryImpl implements StrategyFactory {
    @Override
    public SelectStrategy evenStrategy() {
        return this.multipleOfStrategy(2);
    }

    @Override
    public SelectStrategy oddStrategy() {
        return this.invert(this.multipleOfStrategy(2));
    }

    @Override
    public SelectStrategy greaterThan(final int n) {
        return e -> e > n;
    }

    @Override
    public SelectStrategy multipleOfStrategy(final int n) {
        return e -> e % n == 0;
    }

    @Override
    public SelectStrategy equalStrategy(final int n) {
        return e -> e == n;
    }

    @Override
    public SelectStrategy composeStrategy(SelectStrategy... strategies) {
        return e -> Arrays.stream(strategies).allMatch(s -> s.apply(e));
    }

    private SelectStrategy invert(final SelectStrategy strategy) {
        return e -> !strategy.apply(e);
    }
}
