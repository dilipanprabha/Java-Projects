import java.util.InputMismatchException;
import java.util.Scanner;

class Length {

    public static void list() {
        System.out.println("\n===Length Unit Converter===\n");
        System.out.println("01. millimeter -> meter");
        System.out.println("02. meter      -> millimeter");
        System.out.println("03. centimeter -> meter");
        System.out.println("04. meter      -> centimeter");
        System.out.println("05. kilometer  -> meter");
        System.out.println("06. meter      -> kilometer");
        System.out.println("07. inch       -> meter");
        System.out.println("08. meter      -> inch");
        System.out.println("09. foot       -> meter");
        System.out.println("10. meter      -> foot");
        System.out.println("11. yard       -> meter");
        System.out.println("12. meter      -> yard");
        System.out.println("13. mile       -> kilometer");
        System.out.println("14. kilometer  -> mile\n\n");

    }

    public static double mmToM(double num) {
        return num / 1000;
    }

    public static double mToMM(double num) {
        return num * 1000;
    }

    public static double cmToM(double num) {
        return num / 100;
    }
    
    public static double mToCM(double num) {
        return num * 100;
    }
    
    public static double kmToM(double num) {
        return num * 1000;
    }
    
    public static double mToKM(double num) {
        return num / 1000;
    }
    
    public static double inchToM(double num) {
        return num * 2.54 * Math.pow(10, -2);
    }
    
    public static double mToInch(double num) {
        return num / 2.54 * 100;
    }
    
    public static double footToM(double num) {
        return num * 0.3048;
    }
    
    public static double mToFoot(double num) {
        return num / 0.3048;
    }
    
    public static double yardToM(double num) {
        return num * 0.9144;
    }
    
    public static double mToYard(double num) {
        return num / 0.9144;
    }
    
    public static double mileToKM(double num) {
        return num * 1.609344;
    }
    
    public static double kmToMile(double num) {
        return num / 1.609344;
    }
    
}


public class Main{
    public static void main(String[] args) {
        double num;
        int choice1, choice2;
        Scanner input = new Scanner(System.in);
        while (true) {
            desc();
            try {
                choice1 = input.nextInt();
                if (choice1 > 5) {
                    System.out.println("\n!!!Naah...Enter a correct number.!!!\n");
                    continue;
                } else if (choice1 == 0) {
                    System.out.println("-----Bye-----");
                    break;
                }
                Length.list();
                System.out.print("Choose a number: ");
                choice2 = input.nextInt();
                input.nextLine();
                System.out.print("Enter a number: ");
                num = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Naah... We've only accepted numbers.");
                input.nextLine();
                continue;
            } 

            choice(choice2, num);
        }
        input.close();

    }

    public static void desc() {
        System.out.println("==============================");
        System.out.println("========Unit Converter========");
        System.out.println("==============================");
        System.out.println("          0 -> EXIT           ");
        System.out.println("          1 -> Length         ");
        System.out.println("          2 -> Temperature    ");
        System.out.println("          3 -> Area           ");
        System.out.println("          4 -> Volume         ");
        System.out.println("          5 -> Weight         ");
        System.out.print("Select a number: ");
    }

    public static void choice(int value, double num) {
        switch (value) {
            case 1 -> System.out.printf("\n-----   %.2f millimeter -> %4.2f meter  -----\n", num, Length.mmToM(num));
            case 2 -> System.out.printf("\n-----   %.2f meter -> %4.2f millimeter  -----\n", num, Length.mToMM(num));
            case 3 -> System.out.printf("\n-----   %.2f centimeter -> %4.2f meter  -----\n", num, Length.cmToM(num));
            case 4 -> System.out.printf("\n-----   %.2f meter -> %4.2f centimeter  -----\n", num, Length.mToCM(num));
            case 5 -> System.out.printf("\n-----   %.2f kilometer -> %4.2f meter  -----\n", num, Length.kmToM(num));
            case 6 -> System.out.printf("\n-----   %.2f meter -> %4.2f kilometer  -----\n", num, Length.mToKM(num));
            case 7 -> System.out.printf("\n-----   %.2f inch -> %4.2f meter  -----\n", num, Length.inchToM(num));
            case 8 -> System.out.printf("\n-----   %.2f meter -> %4.2f inch  -----\n", num,Length.mToInch(num));
            case 9 -> System.out.printf("\n-----   %.2f foot -> %4.2f meter  -----\n", num,Length.footToM(num));
            case 10 -> System.out.printf("\n-----   %.2f meter -> %4.2f foot  -----\n",num, Length.mToFoot(num));
            case 11 -> System.out.printf("\n-----   %.2f yard -> %4.2f meter  -----\n", num, Length.yardToM(num));
            case 12 -> System.out.printf("\n-----   %.2f meter -> %4.2f yard  -----\n", num, Length.mToYard(num));
            case 13 -> System.out.printf("\n-----   %.2f mile -> %4.2f kilometer  -----\n", num, Length.mileToKM(num));
            case 14 -> System.out.printf("\n-----   %.2f kilometer ->  mile  -----\n", num, Length.kmToMile(num));
            default -> System.out.println("Naah...Enter a correct number.");
        }
    }
}