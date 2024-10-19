import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class Player {
    private String name;
    private char symbol;
    private int win;
    private int lose;
    private int draw;

    public Player(String name, char symbol) {
        this.name = name.toUpperCase();
        this.symbol = symbol;
    }

    public void addWin() {
        this.win++;
    }

    public void addLose() {
        this.lose++;
    }

    public void addDraw() {
        this.draw++;
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

    public int getLose() {
        return lose;
    }

    public int getDraw() {
        return draw;
    }
}

public class Main {
    public enum Symbols {
        O, X
    };

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        char[][] grid = new char[3][3];
        fillSpace(grid);
        int n, i = 0, temp = 0;
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
                    System.out.println("\nGoodbye, The grid will miss you!");
                    return;
                } else if (playerOneOpt < 1 || playerOneOpt > 2) throw new Exception("\n!!!Naah... Choose a correct number!!!\n");
                else {
                    Symbols p1 = (playerOneOpt == 1) ? Symbols.O : Symbols.X;
                    Symbols p2 = (p1 == Symbols.O) ? Symbols.X : Symbols.O;
                    
                    player1 = new Player(playerOneName, p1.toString().charAt(0));
                    player2 = new Player(playerSecondName, p2.toString().charAt(0));

                    System.out.println("\n" + player1.getName() + " selected: " + player1.getSymbol());
                    System.out.println("\n" + player2.getName() + " selected: " + player2.getSymbol());
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nOops! That square is off-limits. Try again!\n");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } 
        }
        
        gridWithNum();
        while (true) {
            if (i > 8) {
                String yesOrNo;

                if (i == 9) {
                    player1.addDraw();
                    player2.addDraw();
                    System.out.println("Stalemate! It’s a tie – time to go for another round!");
                }

                System.out.print("\nTime for a rematch? [yes|no] : " );
                // scanner.nextLine();
                yesOrNo = scanner.nextLine().toLowerCase();
                if (yesOrNo.equals("yes")) {
                    gridWithNum();
                    fillSpace(grid);
                    i = 0;
                    continue;
                } else if (yesOrNo.equals("no")){
                    System.out.println("\nHope you had fun, come back for more Tic Tac Toe action\n");
                    break;
                } else {
                    System.out.println("\nPlease type a valid one.\n");
                    continue;
                }
            }

            try {
                if (temp == 0) {
                    System.out.print(player1.getName() + ": Which place do you wanna to select? -> ");
                    n = scanner.nextInt();
                    if (n > 9) throw new Exception("\n!!!Naah... Choose a correct number!!!\n");
                    boolean result = check(grid, n);
                    if (result) {
                        result = symbolInsert(grid, player1, n);
                    }
                    if (!result) throw new Exception("\nThat spot’s already taken, " + player1.getName() + "! Choose another one.\n");

                    grid(grid);
                    if (algo(grid, n, player1.getSymbol())) {
                        player1.addWin();
                        player2.addLose();
                        System.out.println("Congratulations, " + player1.getName() + "! You’ve mastered the grid and claimed victory!");
                        result(player1, player2);
                        i = 10;
                        continue;
                    }
                    temp = 1;
                    i++;
                } else {
                    System.out.print(player2.getName() + ": Which place do you wanna to select? -> ");
                    n = scanner.nextInt();
                    if (n > 9) throw new Exception("\n!!!Naah... Choose a correct number!!!\n");
                    boolean result = check(grid, n);
                    if (result) {
                        result = symbolInsert(grid, player2, n);
                    }
                    if (!result) throw new Exception("\nThat spot’s already taken, " + player2.getName() + "! Choose another one.\n");

                    grid(grid);
                    if (algo(grid, n, player1.getSymbol())) {
                        player2.addWin();
                        player1.addLose();
                        System.out.println("Congratulations, " + player2.getName() + "! You’ve mastered the grid and claimed victory!");
                        result(player2, player1);
                        i = 10;
                        continue;
                    }
                    temp = 0;
                    i++;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n!!!Oops! That square is off-limits. Try again!!!\n");
                scanner.nextLine();
                continue;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
        scanner.close();
    }

    public static void grid(char[][] c) {
        System.out.println("\n-------------");
        System.out.printf("| %c | %c | %c |\n", c[0][0], c[0][1], c[0][2]);
        System.out.println("-------------");
        System.out.printf("| %c | %c | %c |\n", c[1][0], c[1][1], c[1][2]);
        System.out.println("-------------");
        System.out.printf("| %c | %c | %c |\n", c[2][0], c[2][1], c[2][2]);
        System.out.println("-------------\n");
    }

    public static void gridWithNum() {
        System.out.println("\n-------------");
        System.out.println("| 1 | 2 | 3 |");
        System.out.println("-------------");
        System.out.println("| 4 | 5 | 6 |");
        System.out.println("-------------");
        System.out.println("| 7 | 8 | 9 |");
        System.out.println("-------------\n");
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
            case 9 -> c[2][2] = p.getSymbol();
            default -> result = false;
        }

        return result;
    }

    public static void fillSpace(char[][] c) {
        for (char[] arr: c) {
            Arrays.fill(arr, ' ');
        }
    }

    public static boolean check(char[][] c, int num) {
        switch (num) {
            case 1 -> {
                if (c[0][0] == ' ') return true;
            }
            case 2 -> {
                if (c[0][1] == ' ') return true;
            }
            case 3 -> {
                if (c[0][2] == ' ') return true;
            }
            case 4 -> {
                if (c[1][0] == ' ') return true;
            }
            case 5 -> {
                if (c[1][1] == ' ') return true;
            }
            case 6 -> {
                if (c[1][2] == ' ') return true;
            }
            case 7 -> {
                if (c[2][0] == ' ') return true;
            }
            case 8 -> {
                if (c[2][1] == ' ') return true;
            }
            case 9 -> {
                if (c[2][2] == ' ') return true;
            }
        }
        return false;
    }
    
    public static boolean algo(char[][] c, int num, char symbol) {
        switch (num) {
            case 1 -> {
                if ((c[0][0] == c[0][1]) && (c[0][1] == c[0][2])) return true;
                else if ((c[0][0] == c[1][0]) && (c[1][0] == c[2][0])) return true;
                else if ((c[0][0] == c[1][1]) && (c[1][1] == c[2][2])) return true;
                else return false;
            }
            case 2 -> {
                if ((c[0][0] == c[0][1]) && (c[0][1] == c[0][2])) return true;
                else if ((c[0][1] == c[1][1]) && (c[1][1] == c[2][1])) return true;
                else return false;
            }
            case 3 -> {
                if ((c[0][0] == c[0][1]) && (c[0][1] == c[0][2])) return true;
                else if ((c[0][2] == c[1][1]) && (c[1][1] == c[2][0])) return true;
                else if ((c[0][2] == c[1][2]) && (c[1][2] == c[2][2])) return true;
                else return false;
            }
            case 4 -> {
                if ((c[1][0] == c[0][0]) && (c[1][0] == c[2][0])) return true;
                else if ((c[1][0] == c[1][1]) && (c[1][1] == c[1][2])) return true;
                else return false;
            }
            case 5 -> {
                if ((c[1][1] == c[0][0]) && (c[1][1] == c[2][2])) return true;
                else if ((c[1][1] == c[0][2]) && (c[1][1] == c[2][0])) return true;
                else if ((c[0][1] == c[1][1]) && (c[1][1] == c[2][1])) return true;
                else if ((c[1][0] == c[1][1]) && (c[1][1] == c[1][2])) return true;
                else return false;
            }
            case 6 -> {
                if ((c[0][2] == c[1][2]) && (c[1][2] == c[2][2])) return true;
                else if ((c[1][0] == c[1][1]) && (c[1][1] == c[1][2])) return true;
                else return false;
            }
            case 7 -> {
                if ((c[2][0] == c[1][1]) && (c[1][1] == c[0][2])) return true;
                else if ((c[2][0] == c[1][0]) && (c[1][0] == c[0][0])) return true;
                else if ((c[2][0] == c[2][1]) && (c[2][1] == c[2][2])) return true;
                else return false;
            }
            case 8 -> {
                if ((c[2][1] == c[1][1]) && (c[1][1] == c[0][1])) return true;
                else if ((c[2][0] == c[2][1]) && (c[2][1] == c[2][2])) return true;
                else return false;
            }
            case 9 -> {
                if ((c[2][2] == c[1][1]) && (c[1][1] == c[0][0])) return true;
                else if ((c[2][2] == c[1][2]) && (c[1][2] == c[0][2])) return true;
                else if ((c[2][0] == c[2][1]) && (c[2][1] == c[2][2])) return true;
                else return false;
            }
        }
        return false;
    }

    public static void result(Player winner, Player loser) {
        System.out.println("\nWell played, " + winner.getName() + "! The Tic Tac Toe crown is all yours!");
        System.out.println(winner.getName() + ": Wins: " + winner.getWin() + " | Losses: " + winner.getLose() + " | Draws: " + winner.getDraw());
        System.out.println("\nOops, " + loser.getName() + ", better luck next time! The grid wasn't on your side today.");
        System.out.println(loser.getName() + ": Wins: " + loser.getWin() + " | Losses: " + loser.getLose() + " | Draws: " + loser.getDraw());
    }
}
