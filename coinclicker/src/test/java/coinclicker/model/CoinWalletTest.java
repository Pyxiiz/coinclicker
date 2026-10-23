package coinclicker.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoinWalletTest {

    @Test void addIncreasesBalance() {
        Wallet w = new CoinWallet();
        w.add(5);
        assertEquals(5, w.getBalance());
    }

    @Test void addRejectsNegativeAmounts() {
        Wallet w = new CoinWallet();
        assertThrows(IllegalArgumentException.class, () -> w.add(-1));
    }

    @Test void spendFailsWhenBroke() {
        Wallet w = new CoinWallet();
        assertFalse(w.trySpend(5));
        assertEquals(0, w.getBalance());
    }

    @Test void spendSucceedsAndDeducts() {
        Wallet w = new CoinWallet();
        w.add(10);
        assertTrue(w.trySpend(4));
        assertEquals(6, w.getBalance());
    }
}
