package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {

    private static final int PRICE = 50;
    private TicketMachine machine;

    @BeforeEach
    void setUp() {
        machine = new TicketMachine(PRICE);
    }

    @Test // S1
    void priceIsCorrectlyInitialized() {
        assertEquals(PRICE, machine.getPrice());
    }

    @Test // S2
    void insertMoneyChangesBalance() {
        machine.insertMoney(10);
        machine.insertMoney(20);
        assertEquals(50, machine.getBalance());
    }

    @Test // S3
    void cannotPrintTicketIfBalanceTooLow() {
        machine.insertMoney(20);
        assertFalse(machine.printTicket());
    }

    @Test // S4 & S5 & S6
    void canPrintTicketIfEnoughMoneyAndUpdatesTotalAndBalance() {
        machine.insertMoney(100);
        assertTrue(machine.printTicket());
        assertEquals(100 - PRICE, machine.getBalance());
        assertEquals(PRICE, machine.getTotal());
    }

    @Test // S7 & S8
    void refundReturnsBalanceAndResetsItToZero() {
        machine.insertMoney(30);
        int refund = machine.refund();
        assertEquals(30, refund);
        assertEquals(0, machine.getBalance());
    }

    @Test // S9
    void insertingNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> machine.insertMoney(-10));
    }

    @Test // S10
    void cannotCreateMachineWithNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new TicketMachine(-1));
    }
}
