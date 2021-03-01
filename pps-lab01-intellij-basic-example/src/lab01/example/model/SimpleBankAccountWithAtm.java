package lab01.example.model;

public class SimpleBankAccountWithAtm extends SimpleBankAccount implements BankAccount {
    public static final double FEE = 1.0;

    public SimpleBankAccountWithAtm(final AccountHolder holder, final double balance) {
        super(holder, balance);
    }

    @Override
    public void deposit(final int usrID, final double amount) {
        super.deposit(usrID, amount);
        this.applyFee(usrID);
    }

    @Override
    public void withdraw(final int usrID, final double amount) {
        super.withdraw(usrID, amount);
        this.applyFee(usrID);
    }

    private void applyFee(final int usrID) {
        super.withdraw(usrID, FEE);
    }
}
