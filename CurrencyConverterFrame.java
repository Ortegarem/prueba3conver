import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverterFrame extends JFrame {
    private JTextField txtAmount;
    private JComboBox<String> cmbFromCurrency;
    private JComboBox<String> cmbToCurrency;
    private JLabel lblResult;

    public CurrencyConverterFrame() {
        // Configuración de la ventana
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Componentes
        JLabel lblAmount = new JLabel("Amount:");
        txtAmount = new JTextField();

        JLabel lblFrom = new JLabel("From:");
        cmbFromCurrency = new JComboBox<>(new String[]{"USD", "EUR", "COP"});

        JLabel lblTo = new JLabel("To:");
        cmbToCurrency = new JComboBox<>(new String[]{"USD", "EUR", "COP"});

        lblResult = new JLabel("Result: 0.0");
        JButton btnConvert = new JButton("Convert");

        // Acción de conversión
        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        // Añadir componentes al Frame
        add(lblAmount);
        add(txtAmount);
        add(lblFrom);
        add(cmbFromCurrency);
        add(lblTo);
        add(cmbToCurrency);
        add(new JLabel()); // Espaciador
        add(btnConvert);
        add(lblResult);

        setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(txtAmount.getText());
            String fromCurrency = (String) cmbFromCurrency.getSelectedItem();
            String toCurrency = (String) cmbToCurrency.getSelectedItem();

            double result = CurrencyConverter.convert(fromCurrency, toCurrency, amount);
            lblResult.setText(String.format("Result: %.2f %s", result, toCurrency));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }
}

