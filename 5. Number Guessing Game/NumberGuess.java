import java.util.InputMismatchException;
import java.util.Scanner;

enum Level {
    EASY(9), MEDIUM(6), HARD(2);

    private int value;

    private Level(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

class Game {
    public enum Hint {
        TOO_HIGH, HIGH, MEDIUM, LOW, TOO_LOW;
    }

    private String playerName;
    private int level;
    private int attempts;
    private int random;
    private int wins;
    private int losses;
    private int scores;

    public Game(String playerName, int level) {
        this.playerName = playerName;
        this.level = level;
    }

    public void play(Scanner scanner) {
        int userSelect;
        int temp = 0;

        for (int i = 0; i < attempts; i++) {
            random = random();

            System.out.print("Do you wanna any hint " + playerName + "? [yes|no]");
            String hint = getValidHint(scanner);
            if (hint == "yes" && checkHint(scanner)) {
                temp = -1;
                System.out.println("=======================");
                System.out.println("||| " + hint(random) + " |||");
                System.out.println("=======================");
                addScores(level);
                return;
            } else {
                userSelect = getValidNumber(scanner);
                if (check(random, userSelect)) {
                    wins++;
                    addScores(level);
                    return;
                }
            }            
        }
        if (temp == -1) {
            scores -= 2;
        }
        losses++;
    }

    public void displayLevels() {
        Level[] levels = Level.values();

        for (int i = 0; i < levels.length; i++) {
            System.out.println((i + 1) + " --> " + levels[i].name());
        }
    }

    public void addScores(int level) {
        if (level == 1) scores += 2;
        else if (level == 2) scores += 5;
        else scores += 10;
    }

    public boolean check(int a, int b) {
        if (a == b) return true;
        return false;
    }

    public boolean checkHint(Scanner scanner) {
        System.out.println("If you get any hint and you won't win, your scores will be reduced. Is it okay or not? [yes|no]");
        String hint = getValidHint(scanner);
        if (hint == "yes") {
            return true;
        } else if (scores < 2) {
            return false;
        } else {
            return false;
        }
    }

    public int getValidNumber(Scanner scanner) {
        while (true) {
            try {
                int userSelect = scanner.nextInt();
                if (userSelect < 1 || 100 < userSelect) throw new Exception("Number should be between 1 and 100.");
                return userSelect;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getValidLevel(Scanner scanner) {
        while (true) {
            try {
                int userSelect = scanner.nextInt();
                if (userSelect < 1 || 3 < userSelect) throw new Exception("Number should be between 1 and 3.");
                return userSelect;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getValidHint(Scanner scanner) {
        while (true) {
            try {
                String userSelect = scanner.nextLine().toLowerCase();
                if (!(userSelect == "yes") || !("no" == userSelect)) throw new Exception("Enter only yes or no");
                return userSelect;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int setAttempts(int num) {
        if (level == 1) {
            attempts = Level.EASY.getValue();
        } else if (level == 2) {
            attempts = Level.MEDIUM.getValue();
        } else if (level == 3) {
            attempts = Level.HARD.getValue();
        } else {
            return 1;
        }
        return 0;
    }

    public String hint(int random) {
        if (random >= 1 && 20 >= random) {
            return Hint.TOO_LOW.toString();
        } else if (random >= 21 && 40 >= random) {
            return Hint.LOW.toString();
        } else if (random >= 41 && 60 >= random) {
            return Hint.MEDIUM.toString();
        } else if (random >= 61 && 80 >= random) {
            return Hint.HIGH.toString();
        } else {
            return Hint.TOO_HIGH.toString();
        }
    }

    public int random() {
        return (int)Math.floor(Math.random() * 100) + 1;
    }

    public String toString() {
        return "NumberGuess [Name=" + playerName + ", level=" + level + ", wins=" + wins + ", losses=" + losses
                + ", scores=" + scores + "]";
    }
}

public class NumberGuess {
    public static void main(String[] args) {
        
    }
}