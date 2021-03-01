package lab01.example.model;

public class SimpleBankAccountWithAtm extends AbstractSimpleBankAccount implements BankAccount {

    public static final double FEE = 1.0;

    public SimpleBankAccountWithAtm(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    @Override
    protected double calculateFee() {
        return FEE;
    }
}
