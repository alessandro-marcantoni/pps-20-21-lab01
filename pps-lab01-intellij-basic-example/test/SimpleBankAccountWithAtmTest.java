import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccountWithAtm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lab01.example.model.SimpleBankAccountWithAtm.FEE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleBankAccountWithAtmTest extends SimpleBankAccountTest {

    @Override
    @BeforeEach
    void beforeEach() {
        this.setAccountHolder();
        bankAccount = new SimpleBankAccountWithAtm(this.accountHolder, 0);
    }

    @Override
    @Test
    void testWithdraw() {
        final int amount = 70;
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.getId(), amount);
        assertEquals(DEPOSIT_AMOUNT - amount - SimpleBankAccountWithAtm.FEE, bankAccount.getBalance());
    }

}
