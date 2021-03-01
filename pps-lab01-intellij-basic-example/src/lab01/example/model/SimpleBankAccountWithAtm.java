package lab01.example.model;

public class SimpleBankAccountWithAtm extends SimpleBankAccount implements BankAccount {
    public static final double FEE = 1;

    public SimpleBankAccountWithAtm(final AccountHolder accountHolder, final int i) {
        super(accountHolder, i);
    }

    @Override
    public void withdraw(final int usrID, final double amount) {
        super.withdraw(usrID, amount);
        super.withdraw(usrID, FEE);
    }
}
