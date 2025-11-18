import java.io.*;
import java.util.*;

public class MathPuzzle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Player info
        System.out.print("Enter your name: ");
        String playerName = sc.nextLine();

        System.out.print("Select difficulty (easy, medium, hard): ");
        String difficulty = sc.nextLine();

        int score = 0;

        // Read questions from file
        try (Scanner fileScanner = new Scanner(new File("questions.txt"))) {
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(",");
                String question = parts[0];
                int correctAnswer = Integer.parseInt(parts[1]);

                System.out.print(question + " = ");
                int userAnswer = sc.nextInt();

                if (userAnswer == correctAnswer) {
                    System.out.println("Correct!");
                    score += 10;
                } else {
                    System.out.println("Wrong! Correct answer: " + correctAnswer);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Questions file not found.");
        }

        // Show score
        System.out.println(playerName + ", your final score is: " + score);

        // Save score
        try (PrintWriter out = new PrintWriter(new FileWriter("scores.txt", true))) {
            out.println(playerName + " - " + difficulty + " - Score: " + score);
            System.out.println("Score saved to scores.txt");
        } catch (IOException e) {
            System.out.println("Error saving score.");
        }

        sc.close();
    }
}
