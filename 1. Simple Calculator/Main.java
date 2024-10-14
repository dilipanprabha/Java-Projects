import java.util.InputMismatchException;
import java.util.Scanner;

class Calculator {
    public static double add(double a, double b) {
        return a + b;
    }
    public static double sub(double a, double b) {
        return a - b;
    }
    public static double mul(double a, double b) {
        return a * b;
    }
    public static double div(double a, double b) {
        return a / b;
    }
}

public class Main {
    public static void main(String[] args) {
        double num1, num2;
        int operation;
        Scanner input = new Scanner(System.in);
        while (true) {
            desc();
            try {
                operation = input.nextInt();
                if (operation > 4) {
                    System.out.println("\n!!!Naah...Enter a correct number.!!!\n");
                    continue;
                } else if (operation == 0) {
                    System.out.println("-----Bye-----");
                    break;
                }
                System.out.print("Enter first number: ");
                num1 = input.nextDouble();
                System.out.print("Enter second number: ");
                num2 = input.nextDouble();
                if (operation == 4 || num2 == 0) throw new ArithmeticException("Zero -> Not acceptable");
            } catch (ArithmeticException e) {
                System.out.println("Naah... " + e.getMessage());
                continue;
            } catch (InputMismatchException e) {
                System.out.println("Naah... We've only accepted numbers.");
                input.nextLine();
                continue;
            } 

            operation(operation, num1, num2);
        }
        input.close();
    }

    public static void desc() {
        System.out.println("==============================");
            System.out.println("======Simple Calculator=======");
            System.out.println("==============================");
            System.out.println("          0 -> EXIT        ");
            System.out.println("          1 -> +           ");
            System.out.println("          2 -> -           ");
            System.out.println("          3 -> *           ");
            System.out.println("          4 -> /           ");
            System.out.print("Select a number: ");
    }

    public static void operation(int value, double num1, double num2) {
        switch (value) {
            case 1 -> System.out.println("\n-----   " + num1 + " + " + num2  + " = " + Calculator.add(num1, num2) + "   -----\n");
            case 2 -> System.out.println("\n-----   " + num1 + " - " + num2  + " = " + Calculator.sub(num1, num2) + "   -----\n");
            case 3 -> System.out.println("\n-----   " + num1 + " * " + num2  + " = " + Calculator.mul(num1, num2) + "   -----\n");
            case 4 -> System.out.println("\n-----   " + num1 + " / " + num2  + " = " + Calculator.div(num1, num2) + "   -----\n");
            default -> System.out.println("Naah...Enter a correct number.");
        }
    }
}
