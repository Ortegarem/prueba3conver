public class CurrencyConverterApp {
    public static void main(String[] args) {
        // Iniciar la aplicación de conversión
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConverterFrame();  // Llama a la ventana principal
            }
        });
    }

}
