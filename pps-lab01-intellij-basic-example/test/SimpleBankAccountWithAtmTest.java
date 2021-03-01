import lab01.example.model.SimpleBankAccountWithAtm;
import org.junit.jupiter.api.BeforeEach;

public class SimpleBankAccountWithAtmTest extends SimpleBankAccountTest {

    @Override
    @BeforeEach
    void beforeEach() {
        bankAccount = new SimpleBankAccountWithAtm(this.accountHolder, INITIAL_BALANCE);
    }

    @Override
    protected double getFee() {
        return SimpleBankAccountWithAtm.FEE;
    }

}
