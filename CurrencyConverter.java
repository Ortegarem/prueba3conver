import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;  // Añadir la librería para manejar JSON (puedes usar cualquier parser de JSON).

public class CurrencyConverter {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/b4d850f642da3b9a92676610/latest/";
    private static Map<String, Double> exchangeRates = new HashMap<>();

    public static double convert(String from, String to, double amount) {
        if (from.equals(to)) {
            return amount; // No hace conversión si es la misma moneda
        }

        try {
            double rate = getExchangeRate(from, to);
            return amount * rate;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error fetching conversion rate.");
        }
    }

    private static double getExchangeRate(String from, String to) throws Exception {
        if (exchangeRates.containsKey(from + "_" + to)) {
            return exchangeRates.get(from + "_" + to);  // Usar la tasa de cambio almacenada si existe
        }

        // Realiza la solicitud a la API
        URL url = new URL(API_URL + from);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        // Parsear la respuesta JSON
        JSONObject json = new JSONObject(content.toString());
        JSONObject rates = json.getJSONObject("conversion_rates");

        double rate = rates.getDouble(to);  // Obtiene la tasa para la moneda de destino

        // Almacena la tasa de cambio para evitar futuras solicitudes
        exchangeRates.put(from + "_" + to, rate);
        return rate;
    }
}

