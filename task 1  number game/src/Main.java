import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;40

class GuessTheNumber {
    private final Scanner input;
    private final Random generator;
    private int score;

    public GuessTheNumber() {
        this.input = new Scanner(System.in);
        this.generator = new Random();
        this.score = 0;
    }

    public void startGame() {
        boolean continuePlaying = true;

        while (continuePlaying) {
            initiateRound();
            System.out.print("Would you like another round? (yes/no): ");
            continuePlaying = input.nextLine().trim().equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing! Your final score: " + score);
        input.close();
    }

    private void initiateRound() {
        int secretNumber = generator.nextInt(100) + 1;
        int attempts = 0, maxTries = 5;
        List<Integer> guesses = new ArrayList<>();

        System.out.println("\n🔢 A number between 1 and 100 has been chosen. Try to guess it!");

        while (attempts < maxTries) {
            System.out.print("Enter your guess: ");
            int userGuess = getValidatedInput();

            guesses.add(userGuess);
            attempts++;

            if (evaluateGuess(userGuess, secretNumber)) {
                score++;
                System.out.println("Your previous guesses: " + guesses);
                return;
            }
        }

        System.out.println("❌ You've used all attempts! The correct number was " + secretNumber);
    }

    private int getValidatedInput() {
        while (true) {
            try {
                int guess = Integer.parseInt(input.nextLine().trim());
                if (guess < 1 || guess > 100) {
                    System.out.println("⚠️ Please enter a number between 1 and 100.");
                } else {
                    return guess;
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid input! Please enter a number.");
            }
        }
    }

    private boolean evaluateGuess(int guess, int target) {
        switch (Integer.compare(guess, target)) {
            case 0 -> {
                System.out.println("🎉 Correct! You found the number " + target + "!");
                return true;
            }
            case -1 -> System.out.println("📉 Too low! Try a higher number.");
            case 1 -> System.out.println("📈 Too high! Try a lower number.");
        }
        return false;
    }

    public static void main(String[] args) {
        new GuessTheNumber().startGame();
    }
}
