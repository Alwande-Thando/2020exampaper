package za.co.prog6112.q1;

/** Stores the supplied yearly cartridge usage for each printer. */
public class PrinterUsage {
    private final String[] printers = {"HP Deskjesk 650", "Epson K750", "Canon Z100"};
    private final String[] years = {"2018", "2019", "2020"};
    // Rows are printers; columns are years in chronological order.
    private final int[][] usage = {
        {10, 35, 20},
        {15, 22, 12},
        {30, 31, 33}
    };

    public String[] getPrinters() { return printers.clone(); }
    public String[] getYears() { return years.clone(); }

    public int getUsage(String printer, String year) {
        int printerIndex = find(printers, printer);
        int yearIndex = find(years, year);
        if (printerIndex < 0 || yearIndex < 0) {
            throw new IllegalArgumentException("Select a valid printer and year.");
        }
        return usage[printerIndex][yearIndex];
    }

    public int totalForPrinter(int printerIndex) {
        int total = 0;
        for (int amount : usage[printerIndex]) total += amount;
        return total;
    }

    public String createYearlyReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < printers.length; i++) {
            report.append(String.format("Total cartridge usage for %s is %d%n",
                    printers[i], totalForPrinter(i)));
        }
        return report.toString();
    }

    private int find(String[] values, String value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(value)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new PrinterUsage().createYearlyReport());
    }
}
