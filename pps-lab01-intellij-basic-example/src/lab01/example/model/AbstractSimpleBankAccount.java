package lab01.example.model;

public abstract class AbstractSimpleBankAccount implements BankAccount {

    private double balance;
    private final AccountHolder holder;

    public AbstractSimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    @Override
    public AccountHolder getHolder(){
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int usrID, final double amount) {
        if (checkUser(usrID)) {
            this.balance += amount - calculateFee();
        }
    }

    @Override
    public void withdraw(final int usrID, final double amount) {
        if (checkUser(usrID) && isWithdrawAllowed(amount)) {
            this.balance -= amount + calculateFee();
        }
    }

    protected abstract double calculateFee();

    private boolean isWithdrawAllowed(final double amount){
        return this.balance >= amount - calculateFee();
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }

}
