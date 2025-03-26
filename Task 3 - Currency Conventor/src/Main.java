//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class ExchangeService {
    private final Map<String, Double> rates;

    public ExchangeService() {
        // Using TreeMap instead of HashMap (ensures sorted keys)
        rates = new TreeMap<>();
        rates.put("USD", 1.0);
        rates.put("EUR", 0.91);
        rates.put("INR", 83.5);
        rates.put("GBP", 0.78);
        rates.put("JPY", 149.3);
    }

    // Checks if the currency is valid
    private boolean isCurrencyAvailable(String currency) {
        return rates.containsKey(currency);
    }

    // Convert between two currencies
    public double convertCurrency(String from, String to, double amount) throws IllegalArgumentException {
        if (!isCurrencyAvailable(from) || !isCurrencyAvailable(to)) {
            throw new IllegalArgumentException("Invalid currency code! Please use valid currency names.");
        }
        return (amount / rates.get(from)) * rates.get(to);
    }
}

 class CurrencyExchangeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeService exchangeService = new ExchangeService();

        try {
            System.out.print("Enter source currency (e.g., USD, INR): ");
            String baseCurrency = scanner.next().toUpperCase();

            System.out.print("Enter destination currency (e.g., EUR, GBP): ");
            String targetCurrency = scanner.next().toUpperCase();

            System.out.print("Enter the amount to convert: ");
            double inputAmount = scanner.nextDouble();

            double convertedValue = exchangeService.convertCurrency(baseCurrency, targetCurrency, inputAmount);
            System.out.printf("Converted Amount: %.2f %s%n", convertedValue, targetCurrency);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred. Please check your inputs.");
        } finally {
            scanner.close();
        }
    }
}

