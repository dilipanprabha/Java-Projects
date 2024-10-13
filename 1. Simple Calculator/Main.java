import java.util.Scanner;

class Calculator {
    public static int add(int a, int b) {
        return a + b;
    }
    public static int sub(int a, int b) {
        return a - b;
    }
    public static int mul(int a, int b) {
        return a * b;
    }
    public static int div(int a, int b) {
        return a / b;
    }
}

public class Main {
    public static void main(String[] args) {
        int num1, num2, operation;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("==============================");
            System.out.println("======Simple Calculator=======");
            System.out.println("==============================");
            System.out.println("          0 -> EXIT        ");
            System.out.println("          1 -> +           ");
            System.out.println("          2 -> -           ");
            System.out.println("          3 -> *           ");
            System.out.println("          4 -> /           ");
            System.out.print("Select a number: ");
            operation = input.nextInt();
            if (operation > 4) {
                System.out.println("Naah...Enter a correct number.");
                break;
            } else if (operation == 0) {
                System.out.println("-----Bye-----");
                break;
            }
            System.out.print("Enter first number: ");
            num1 = input.nextInt();
            System.out.print("Enter second number: ");
            num2 = input.nextInt();

            switch (operation) {
                case 1 -> System.out.println("\n-----   " + num1 + " + " + num2  + " = " + Calculator.add(num1, num2) + "   -----\n");
                case 2 -> System.out.println("\n-----   " + num1 + " - " + num2  + " = " + Calculator.sub(num1, num2) + "   -----\n");
                case 3 -> System.out.println("\n-----   " + num1 + " * " + num2  + " = " + Calculator.mul(num1, num2) + "   -----\n");
                case 4 -> System.out.println("\n-----   " + num1 + " / " + num2  + " = " + Calculator.div(num1, num2) + "   -----\n");
                default -> System.out.println("Naah...Enter a correct number.");
            }
        }

        input.close();
    }
}
