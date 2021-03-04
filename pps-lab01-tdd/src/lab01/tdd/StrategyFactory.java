package lab01.tdd;

public interface StrategyFactory {

    SelectStrategy evenStrategy();

    SelectStrategy oddStrategy();

    SelectStrategy greaterThan(int n);

    SelectStrategy multipleOfStrategy(int n);

    SelectStrategy equalStrategy(int n);

    SelectStrategy composeStrategy(SelectStrategy... strategies);

}
