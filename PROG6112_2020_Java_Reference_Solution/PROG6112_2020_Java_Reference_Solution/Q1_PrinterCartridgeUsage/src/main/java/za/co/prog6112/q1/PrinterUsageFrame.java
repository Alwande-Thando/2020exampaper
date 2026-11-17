package za.co.prog6112.q1;

import javax.swing.*;
import java.awt.*;

/** GUI for displaying cartridge usage and the three-year printer report. */
public class PrinterUsageFrame extends JFrame {
    private final PrinterUsage data = new PrinterUsage();
    private final JComboBox<String> printerBox = new JComboBox<>(data.getPrinters());
    private final JComboBox<String> yearBox = new JComboBox<>(data.getYears());

    public PrinterUsageFrame() {
        super("Cartridge Usage");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 220);
        setLocationRelativeTo(null);

        JPanel selections = new JPanel(new GridLayout(2, 2, 10, 10));
        selections.add(new JLabel("Select Printer:")); selections.add(printerBox);
        selections.add(new JLabel("Select Year:")); selections.add(yearBox);

        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> submitSelection());
        JPanel bottom = new JPanel();
        bottom.add(submit);

        add(selections, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> dispose());
        file.add(exit);

        JMenu tools = new JMenu("Tools");
        JMenuItem submit = new JMenuItem("Submit");
        JMenuItem report = new JMenuItem("Yearly Usage");
        submit.addActionListener(e -> submitSelection());
        report.addActionListener(e -> showReport());
        tools.add(submit); tools.add(report);
        bar.add(file); bar.add(tools);
        return bar;
    }

    private void submitSelection() {
        String printer = (String) printerBox.getSelectedItem();
        String year = (String) yearBox.getSelectedItem();
        int amount = data.getUsage(printer, year);
        JOptionPane.showMessageDialog(this,
                "PRINTER: " + printer + "\nYEAR: " + year + "\nUSAGE: " + amount,
                "Cartridge Usage", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showReport() {
        JOptionPane.showMessageDialog(this, data.createYearlyReport(),
                "Yearly Cartridge Usage", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PrinterUsageFrame().setVisible(true));
    }
}
