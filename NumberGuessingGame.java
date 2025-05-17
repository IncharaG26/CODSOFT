import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxRound = 6;
        int score = 0;
        System.out.println("------- Welcome to the Number Guessing Game -------");
        System.out.println("            ----Instructions----");
        System.out.println("You have to guess a number between 1 to 100");
        System.out.println("You have only 6 attempts to guess the  number");
        System.out.println("Let's Start");

        int round = 0;
        boolean playAgain = true;

        while(playAgain && round < maxRound){
            round++;
            int numberToGuess = random.nextInt(100)+1;
            int attemptsLeft = 6;
            boolean correctGuess = false;
            while(attemptsLeft>0){
                System.out.println("Round " + round);
                System.out.println("Enter your guess: ");
                int guess = scanner.nextInt();
                if(guess == numberToGuess){
                    System.out.println("Correct You have guessed the number right.");
                    score +=(6-attemptsLeft);
                    correctGuess = true;
                }
                else if(guess<numberToGuess){
                    System.out.println("Too low! Try a greater number.");
                }
                else{
                    System.out.println("Too high! Try a lesser number ");
                }

                attemptsLeft--;
                System.out.println("Attempts left: "+ attemptsLeft);
            }
            if(!correctGuess){
                System.out.println("You have no attempts left! The number was: " + numberToGuess);
            }
            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }
            System.out.println("\nGame Over!");
        System.out.println("Total Score: " + score);
        System.out.println("Thanks for playing!");
        scanner.close();
        }

    }
