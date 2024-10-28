import java.util.InputMismatchException;
import java.util.Scanner;


class Game {
    public enum Level {
        EASY(9), MEDIUM(6), HARD(2);
    
        private int value;
    
        private Level(int value) {
            this.value = value;
        }
    
        public int getValue() {
            return this.value;
        }
    }

    public enum Hint {
        TOO_HIGH, HIGH, MEDIUM, LOW, TOO_LOW;
    }

    private String playerName;
    private int level;
    private int attempts;
    private int secretNumber;
    private int wins;
    private int losses;
    private int scores;
    private int count;

    public Game(String playerName, int level) {
        this.playerName = playerName;
        this.level = level;
        this.setAttempts(level);
    }

    public static void welcomeMessage() {
        System.out.println("\n🎉 Welcome to the Number Guessing Game! 🎉");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("Try to guess it, and I'll let you know if you're close!");
        System.out.println("Let's start the game! 🚀\n");
    }

    public static String getValidName(Scanner scanner) {
        while (true) {
            String expression = "^[a-zA-Z\\s]+";
            System.out.print("Enter your name: ");
            try {
                String name = scanner.nextLine();
                if (!name.toLowerCase().matches(expression)) throw new Exception("Enter alphapatic only");
                return name;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getValidInput(Scanner scanner, int min, int max, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // clear buffer
                if (input < min || input > max) throw new Exception("⚠️ Enter a number between " + min + " and " + max + ".");
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // clear invalid input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
  
    public static void displayLevels() {
        Level[] levels = Level.values();

        System.out.println();
        for (int i = 0; i < levels.length; i++) {
            System.out.println((i + 1) + " --> " + levels[i].name() + " " + levels[i].getValue() + " attempts");
        }
        System.out.println();
    }

    public void play(Scanner scanner) {
        count++;

        int temp = 0;
        int userGuess;

        if (attempts == 0) return;
        else {
            System.out.println("\nYou've got " + attempts + " attempts\n");
        }
        secretNumber = secretNumber();

        for (int i = 0; i < attempts; i++) {

            String hint = getValidYesOrNo(scanner, "Do you wanna any hint " + playerName + "? [yes|no] ");

            if (hint.equals("yes") && checkHint(scanner)) {
                temp = -1;

                System.out.println("================");
                System.out.println("||| " + hint(secretNumber) + " |||");
                System.out.println("================");

                System.out.print("\nEnter your guess: ");
                userGuess = getValidInput(scanner, 1, 100, "\nEnter your guess: ");
                if (checkWinOrLose(userGuess)) {
                    displayResults();

                    if (playAgain(scanner)) {
                        temp = 0;
                        this.play(scanner);
                    } else {
                        System.out.println("Thank you for playing the Number Guessing Game! Goodbye! 👋");
                        return;
                    }
                } else {
                    if (secretNumber < userGuess) {
                        System.out.println("\nYour guess is too high! Try a lower number. ⬇️\n");
                    } else {
                        System.out.println("\nYour guess is too low! Try a higher number. ⬆️\n");
                    }
                }
            } else {
                userGuess = getValidInput(scanner, 1, 100, "\nEnter your guess: ");
                if (checkWinOrLose(userGuess)) {
                    displayResults();
                    
                    if (playAgain(scanner)) {
                        this.play(scanner);
                    } else {
                        System.out.println("Thank you for playing the Number Guessing Game! Goodbye! 👋");
                        return;
                    }
                } else {
                    if (secretNumber < userGuess) {
                        System.out.println("\nYour guess is too high! Try a lower number. ⬇️\n");
                    } else {
                        System.out.println("\nYour guess is too low! Try a higher number. ⬆️\n");
                    }
                }
            }            
        }

        if (temp == -1) {
            scores -= 2;
        }

        losses++;

        System.out.println("\nOh no! 😞 You've used all your attempts.");
        System.out.println("Better luck next time! The number was " + secretNumber + ".");
        System.out.println("Thank you for playing!\n");

        if (playAgain(scanner)) {
            play(scanner);
        } else {
            System.out.println("Thank you for playing the Number Guessing Game! Goodbye! 👋");
            return;
        }
    }

    public boolean playAgain(Scanner scanner) {
        String playAgain = getValidYesOrNo(scanner, "Would you like to play again? (yes/no) ");

        if (playAgain.equals("yes")) return true;
        else return false;
    }

    public boolean checkWinOrLose(int userGuess) {
        if (check(secretNumber, userGuess)) {
            System.out.println("🎉 Congratulations! 🎉");
            System.out.println("You guessed the number correctly! Well done!");
            System.out.println("Thanks for playing! 😊");
            wins++;
            addScores();
            return true;
        } else return false;
    }

    public void addScores() {
        if (level == 1) scores += 2;
        else if (level == 2) scores += 5;
        else scores += 10;
    }

    public boolean check(int a, int b) {
        if (a == b) return true;
        return false;
    }

    public boolean checkHint(Scanner scanner) {
        if (count == 0 || scores < 2) {
            System.out.println("\nYou've don't have enough points to view hints.");
            return false;
        }
        
        String hint = getValidYesOrNo(scanner, "If you get any hint and you won't win, your scores will be reduced. Is it okay or not? [yes|no]");
        if (hint.equals("yes")) {
            System.out.println("hello");
            scores -= 2;
            return true;
        } else {
            System.out.println("hi");
            return false;
        }
    }

    public String getValidYesOrNo(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            try {
                String userGuess = scanner.nextLine().toLowerCase();
                if (!userGuess.equals("no") && !userGuess.equals("yes")) throw new Exception("⚠️ Invalid input! Please enter a valid input [yes|no]");
                return userGuess;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int setAttempts(int num) {
        if (num == 1) {
            attempts = Level.EASY.getValue();
        } else if (num == 2) {
            attempts = Level.MEDIUM.getValue();
        } else if (num == 3) {
            attempts = Level.HARD.getValue();
        } else {
            return 1;
        }
        return 0;
    }

    public String hint(int secretNumber) {
        if (secretNumber >= 1 && 20 >= secretNumber) {
            return Hint.TOO_LOW.toString() + "[1 - 20]";
        } else if (secretNumber >= 21 && 40 >= secretNumber) {
            return Hint.LOW.toString() + "[21 - 40]";
        } else if (secretNumber >= 41 && 60 >= secretNumber) {
            return Hint.MEDIUM.toString() + "[41 - 60]";
        } else if (secretNumber >= 61 && 80 >= secretNumber) {
            return Hint.HIGH.toString() + "[61 - 80]";
        } else {
            return Hint.TOO_HIGH.toString() + "[81 - 100]";
        }
    }

    public int secretNumber() {
        return (int)Math.floor(Math.random() * 100) + 1;
    }

    public void displayResults() {
        System.out.println("\nWell played, " + this.playerName + "! The Number Guess Game crown is all yours!\n");
        System.out.println(toString() + "\n");
    }

    public String toString() {
        return "NumberGuess [Name=" + playerName + ", wins=" + wins + ", losses=" + losses
                + ", scores=" + scores + "]";
    }
}

public class NumberGuess {
    public static void main(String[] args) {
        String name;
        int level;

        Scanner scanner = new Scanner(System.in);

        Game.welcomeMessage();
        name = Game.getValidName(scanner);
        Game.displayLevels();
        level = Game.getValidInput(scanner, 1, 3, "Enter level: ");

        Game g = new Game(name, level);
        g.play(scanner);
    }
}