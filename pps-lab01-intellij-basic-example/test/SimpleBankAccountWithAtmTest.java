import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccountWithAtm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lab01.example.model.SimpleBankAccountWithAtm.FEE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleBankAccountWithAtmTest extends SimpleBankAccountTest {

    private final double FEE = SimpleBankAccountWithAtm.FEE;

    @Override
    @BeforeEach
    void beforeEach() {
        bankAccount = new SimpleBankAccountWithAtm(this.accountHolder, INITIAL_BALANCE);
    }

    @Override
    protected double getFee() {
        return this.FEE;
    }
}
