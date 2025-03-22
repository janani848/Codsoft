import java.util.Random;
import java.util.Scanner;

class NumberGuessingGame {
    private int score;
    private final Scanner scanner;
    private final Random random;

    public NumberGuessingGame() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.score = 0;
    }

    public void play() {
        boolean playAgain = true;

        while (playAgain) {
            playRound();
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.nextLine().equalsIgnoreCase("yes");
        }

        System.out.println("Game over! Your final score: " + score);
        scanner.close();
    }

    private void playRound() {
        int targetNumber = random.nextInt(100) + 1;
        int attempts = 0, maxAttempts = 5;

        System.out.println("\nI have selected a number between 1 and 100. Try to guess it!");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            scanner.nextLine();

            attempts++;
            if (userGuess == targetNumber) {
                System.out.println("🎉 Correct! The number was " + targetNumber + ". Attempts: " + attempts);
                score++;
                return;
            } else {
                System.out.println(userGuess < targetNumber ? "Too low! 🔽" : "Too high! 🔼");
            }
        }
        System.out.println("❌ You've run out of attempts! The correct number was " + targetNumber + ".");
    }

    public static void main(String[] args) {
        new NumberGuessingGame().play();
    }
}

