//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.HashMap;
import java.util.Scanner;

class CurrencyConverter {
    private final HashMap<String, Double> exchangeRates;

    public CurrencyConverter() {
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.91);
        exchangeRates.put("INR", 83.5);
        exchangeRates.put("GBP", 0.78);
        exchangeRates.put("JPY", 149.3);
    }

    public double convert(String from, String to, double amount) {
        if (exchangeRates.containsKey(from) && exchangeRates.containsKey(to)) {
            return (amount / exchangeRates.get(from)) * exchangeRates.get(to);
        } else {
            throw new IllegalArgumentException("Invalid currency code!");
        }
    }
}

 class CurrencyConverterOOP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyConverter converter = new CurrencyConverter();

        System.out.print("Enter base currency (e.g., USD, INR): ");
        String base = scanner.next().toUpperCase();

        System.out.print("Enter target currency (e.g., USD, INR): ");
        String target = scanner.next().toUpperCase();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        try {
            double convertedAmount = converter.convert(base, target, amount);
            System.out.printf("Converted Amount: %.2f %s%n", convertedAmount, target);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
