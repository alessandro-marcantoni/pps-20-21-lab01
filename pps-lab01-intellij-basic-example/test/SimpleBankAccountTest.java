import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private static final String HOLDER_NAME = "Mario";
    private static final String HOLDER_SURNAME = "Rossi";
    private static final int HOLDER_ID = 1;
    private static final double INITIAL_BALANCE = 0;
    protected static final double DEPOSIT_AMOUNT = 100;

    protected AccountHolder accountHolder;
    protected BankAccount bankAccount;

    @BeforeEach
    void setAccountHolder() {
        accountHolder = new AccountHolder(HOLDER_NAME, HOLDER_SURNAME, HOLDER_ID);
    }

    @BeforeEach
    void beforeEach(){
        this.setAccountHolder();
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        final int wrongId = 2;
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        bankAccount.deposit(wrongId, 50);
        assertNotEquals(wrongId, accountHolder.getId());
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        final int amount = 70;
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.getId(), amount);
        assertEquals(DEPOSIT_AMOUNT - amount, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        final int wrongId = 2;
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(wrongId, 70);
        assertNotEquals(wrongId, accountHolder.getId());
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }
}
