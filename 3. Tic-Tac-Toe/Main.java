import java.util.InputMismatchException;
import java.util.Scanner;

class Player {
    private String name;
    private char symbol;
    private int win;
    private int lose;
    private int draw;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }
}

public class Main {
    public enum Symbols {
        O, X
    };

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        char[][] grid = new char[3][3];
        int n1, n2, i = 0, temp = 0;
        String playerOneName, playerSecondName;
        int playerOneOpt;
        Player player1, player2;

        while (true) {
            try {
                // Get name from players
                System.out.print("Enter First Player Name: ");
                playerOneName = scanner.nextLine();
                System.out.print("Enter Second Player Name: ");
                playerSecondName = scanner.nextLine();

                // System.out.println("hi");
                if (playerOneName.equals(playerSecondName)) throw new Exception("\n!!!Name shouldn't be same.!!!\n");
                else if (playerOneName.isEmpty() || playerSecondName.isEmpty()) throw new Exception("\n!!!Enter name correctly.!!!\n");
                // Get option from players (O or X)
                System.out.println("\n--- CHOOSE ---");
                System.out.println("\n0 -> Exit");
                System.out.printf("1 -> %s", Symbols.O);
                System.out.printf("\n2 -> %s", Symbols.X);
                System.out.print("\n\nPlayer One: ");

                playerOneOpt = scanner.nextInt();
                scanner.nextLine();

                if (playerOneOpt == 0) {
                    System.out.println("\n---BYE---");
                    return;
                } else if (playerOneOpt < 1 || playerOneOpt > 2) throw new Exception("\n!!!Naah... Choose a correct number!!!\n");
                else {
                    Symbols p1 = (playerOneOpt == 1) ? Symbols.O : Symbols.X;
                    Symbols p2 = (p1 == Symbols.O) ? Symbols.X : Symbols.O;
                    
                    player1 = new Player(playerOneName, p1.toString().charAt(0));
                    player2 = new Player(playerSecondName, p2.toString().charAt(0));

                    System.out.println("\nPlayer one selected: " + player1.getSymbol());
                    System.out.println("Player two selected: " + player2.getSymbol());
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n!!!Naah... Enter correct input!!!\n");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } 
        }

        while (true) {
            if (i == 8) break;
            gridWithNum();

            try {
                if (temp == 0) {
                    System.out.print("Player1: Which place do you wanna to select? -> ");
                    n1 = scanner.nextInt();
                    if (n1 > 9) throw new Exception("\n!!!Naah... Choose a correct number!!!\n");
                    boolean result = symbolInsert(grid, player1, n1);
                    if (!result) throw new Exception("\n!!!Naah... Enter correct number!!!\n");
                    temp = 1;
                    i++;
                } else {
                    System.out.print("Player2: Which place do you wanna to select? -> ");
                    n2 = scanner.nextInt();
                    if (n2 > 9) throw new Exception("\n!!!Naah... Choose a correct number!!!\n");
                    boolean result = symbolInsert(grid, player2, n2);
                    if (!result) throw new Exception("\n!!!Naah... Enter correct number!!!\n");
                    temp = 0;
                    i++;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n!!!Naah... Enter correct input!!!\n");
                continue;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
        }

        grid(grid);

        scanner.close();
    }

    public static void grid(char[][] c) {
        System.out.println("----------");
        System.out.printf("| %c | %c | %c |\n", c[0][0], c[0][1], c[0][2]);
        System.out.println("----------");
        System.out.printf("| %c | %c | %c |\n", c[1][0], c[1][1], c[1][2]);
        System.out.println("----------");
        System.out.printf("| %c | %c | %c |\n", c[2][0], c[2][1], c[2][2]);
        System.out.println("----------");
    }

    public static void gridWithNum() {
        System.out.println("-------------");
        System.out.println("| 1 | 2 | 3 |");
        System.out.println("-------------");
        System.out.println("| 4 | 5 | 6 |");
        System.out.println("-------------");
        System.out.println("| 7 | 8 | 8 |");
        System.out.println("-------------");
    }


    public static boolean symbolInsert(char[][] c, Player p, int num) {
        boolean result = true;
        switch (num) {
            case 1 -> c[0][0] = p.getSymbol();
            case 2 -> c[0][1] = p.getSymbol();
            case 3 -> c[0][2] = p.getSymbol();
            case 4 -> c[1][0] = p.getSymbol();
            case 5 -> c[1][1] = p.getSymbol();
            case 6 -> c[1][2] = p.getSymbol();
            case 7 -> c[2][0] = p.getSymbol();
            case 8 -> c[2][1] = p.getSymbol();
            case 9 -> c[3][2] = p.getSymbol();
            default -> result = false;
        }

        return result;
    }


    // public static boolean check(char[][] c, char symbol) {
        
    // }
}
