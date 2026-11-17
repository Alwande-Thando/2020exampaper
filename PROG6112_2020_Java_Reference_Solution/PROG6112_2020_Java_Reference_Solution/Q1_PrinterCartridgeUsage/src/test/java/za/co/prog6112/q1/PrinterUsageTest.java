package za.co.prog6112.q1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrinterUsageTest {
    private final PrinterUsage data = new PrinterUsage();

    @Test void canonUsageFor2020Is33() {
        assertEquals(33, data.getUsage("Canon Z100", "2020"));
    }
    @Test void hpThreeYearTotalIs65() {
        assertEquals(65, data.totalForPrinter(0));
    }
    @Test void epsonThreeYearTotalIs49() {
        assertEquals(49, data.totalForPrinter(1));
    }
    @Test void canonThreeYearTotalIs94() {
        assertEquals(94, data.totalForPrinter(2));
    }
}
