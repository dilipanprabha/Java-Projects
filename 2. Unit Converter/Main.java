import java.util.InputMismatchException;
import java.util.Scanner;

// Length Class has all methods to do length unit conversions
class Length {

    // Prints the list of option available in Length Class
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

    // Convert from millimeter to meter
    public static double mmToM(double num) {
        return num / 1000;
    }

    // Convert from meter to millimeter
    public static double mToMM(double num) {
        return num * 1000;
    }

    // Convert from centimeter to meter
    public static double cmToM(double num) {
        return num / 100;
    }
    
    // Convert from meter to centimeter
    public static double mToCM(double num) {
        return num * 100;
    }
    
    // Convert from kilometer to meter
    public static double kmToM(double num) {
        return num * 1000;
    }
    
    // Convert from meter to kilometer
    public static double mToKM(double num) {
        return num / 1000;
    }
    
    // Convert from inch to meter
    public static double inchToM(double num) {
        return num * 0.0254;
    }
    
    // Convert from meter to inch
    public static double mToInch(double num) {
        return num / 0.0254;
    }
    
    // Convert from foot to meter
    public static double footToM(double num) {
        return num / 3.281;
    }
    
    // Convert from meter to foot
    public static double mToFoot(double num) {
        return num * 3.281;             // 0.3048
    }
    
    // Convert from yard to meter
    public static double yardToM(double num) {
        return num * 1.094;
    }
    
    // Convert from meter to yard
    public static double mToYard(double num) {
        return num / 1.094;             // 0.9144
    }
    
    // Convert from mile to kilometer
    public static double mileToKM(double num) {
        return num * 1.609;
    }
    
    // Convert from kilometer to mile
    public static double kmToMile(double num) {
        return num / 1.609;
    }
    
}

// Temperature Class has all methods to do temperature unit conversions
class Temperature {

    // Prints the list of option available in Temperature Class
    public static void list() {
        System.out.println("\n===Temperature Conversions===\n");
        System.out.println("1. Celsius    -> Fahrenheit");
        System.out.println("2. Fahrenheit -> Celsius");
        System.out.println("3. Celsius    -> Kelvin");
        System.out.println("4. Kelvin     -> Celsius");
        System.out.println("5. Fahrenheit -> Kelvin");
        System.out.println("6. Kelvin     -> Fahrenheit\n\n");
    }

    // Convert from celsius to fahrenheit
    public static double celToFar(double num) {
        return (num * 9/5) + 32;
    }

    // Convert from fahrenheit to celsius
    public static double farToCel(double num) {
        return (num - 32) * 5/9;
    }

    // Convert from celsius to kelivn
    public static double celToKel(double num) {
        return num + 273.15;
    }

    // Convert from kelvin to celsius
    public static double kelToCel(double num) {
        return num - 273.15;
    }

    // Convert from fahrenheit to kelvin
    public static double farToKel(double num) {
        return (num - 32) * 5/9 + 273.15;
    }

    // Convert from kelvin to fahrenheit
    public static double kelToFar(double num) {
        return (num - 273.15) * 9/5 + 32;
    }


}

// Area Class has all methods to do area unit conversions
class Area {

    // Prints the list of option available in Area Class
    public static void list() {
        System.out.println("\n===Area Conversions===\n");
        System.out.println("01. Square Meter     -> Square Kilometer");
        System.out.println("02. Square Kilometer -> Square Meter");
        System.out.println("03. Square Foot      -> Square Meter");
        System.out.println("04. Square Meter     -> Square Foot");
        System.out.println("05. Square Yard      -> Square Meter");
        System.out.println("06. Square Meter     -> Square Yard");
        System.out.println("07. Acre             -> Square Meter");
        System.out.println("08. Square Meter     -> Acre");
        System.out.println("09. Hectare          -> Square Meter");
        System.out.println("10. Square Meter     -> Hectare\n\n");
    }

    // Convert from square meter to square kilometer
    public static double sqMtToSqKm(double num) {
        return Math.pow(num, 2) / 1_000_000;
    }

    // Convert from square kilometer to square meter
    public static double sqKmToSqMt(double num) {
        return Math.pow(num, 2) * 1_000_000;
    }

    // Convert from square foot to square meter
    public static double sqFtToSqMt(double num) {
        return Math.pow(num, 2) / 10.764;
    }

    // Convert from square meter to square foot
    public static double sqMtToSqFt(double num) {
        return Math.pow(num, 2) * 10.764;
    }

    // Convert from square yard to square meter
    public static double sqYardToSqMt(double num) {
        return Math.pow(num, 2) / 1.196;
    }

    // Convert from square meter to square yard
    public static double sqMtToSqYard(double num) {
        return Math.pow(num, 2) * 1.196;
    }

    // Convert from acre to square meter
    public static double acreToSqMt(double num) {
        return Math.pow(num, 2) * 4046.856;
    }

    // Convert from square meter to square acre
    public static double sqMtToAcre(double num) {
        return Math.pow(num, 2) / 4046.856;
    }

    // Convert from hectare to square meter
    public static double hecToSqMt(double num) {
        return num * 10_000;
    }

    // Convert from square meter to hectare
    public static double sqMtToHec(double num) {
        return Math.pow(num, 2) / 10_000;
    }
}

// Volume Class has all methods to do volume unit conversions
class Volume {

    // Prints the list of option available in Area Class
    public static void list() {
        System.out.println("\n===Volume Conversions===\n");
        System.out.println("01. Liter       -> Milliliter");
        System.out.println("02. Milliliter  -> Liter");
        System.out.println("03. Liter       -> Cubic Meter");
        System.out.println("04. Cubic Meter -> Liter");
        System.out.println("05. Gallon (US) -> Liter");
        System.out.println("06. Liter       -> Gallon (US)");
        System.out.println("07. Quart (US)  -> Liter");
        System.out.println("08. Liter       -> Quart (US)");
        System.out.println("09. Pint (US)   -> Liter");
        System.out.println("10. Liter       -> Pint (US)");
        System.out.println("11. Cubic Foot  -> Cubic Meter");
        System.out.println("12. Cubic Meter -> Cubic Foot\n\n");
    }

    // Convert from liter to milliliter
    public static double ltToMm(double num) {
        return num * 1_000;
    }

    // Convert from milliliter to liter
    public static double mlToLt(double num) {
        return num / 1_000;
    }

    // Convert from liter to cubic meter
    public static double ltToCubMt(double num) {
        return num / 1_000;
    }

    // Convert from cubic meter to liter
    public static double cubMtToLt(double num) {
        return Math.pow(num, 3) * 1_000;
    }

    // Convert from gallon to liter
    public static double galToLt(double num) {
        return num * 3.785;
    }

    // Convert from liter to gallon
    public static double ltToGal(double num) {
        return num / 3.785;
    }

    // Convert from quart to liter
    public static double quarToLt(double num) {
        return num * 0.946;
    }

    // Convert from liter to quart
    public static double ltToQuar(double num) {
        return num / 0.946;
    }

    // Convert from pint to liter
    public static double pintToLt(double num) {
        return num * 0.473;
    }

    // Convert from liter to pint
    public static double ltToPint(double num) {
        return num / 0.473;
    }

    // Convert from cubic foot to cubic meter
    public static double cubFtToCubMt(double num) {
        return Math.pow(num, 3) / 35.315;
    }

    // Convert from cubic meter to cubic foot
    public static double cubMtToCuFt(double num) {
        return Math.pow(num, 3) * 35.315;
    }
}

// Weight Class has all methods to do weight unit conversions
class Weight {

    // Prints the list of option available in Area Class
    public static void list() {
        System.out.println("\n===Weight Conversions===\n");
        System.out.println("01. Milligram  -> Gram");
        System.out.println("02. Gram       -> Milligram");
        System.out.println("03. Gram       -> Kilogram");
        System.out.println("04. Kilogram   -> Gram");
        System.out.println("05. Kilogram   -> Metric Ton");
        System.out.println("06. Metric Ton -> Kilogram");
        System.out.println("07. Ounce      -> Gram");
        System.out.println("08. Gram       -> Ounce");
        System.out.println("09. Pound      -> Kilogram");
        System.out.println("10. Kilogram   -> Pound\n\n");
    }

    // Convert from milligram to gram
    public static double mgToGram(double num) {
        return num / 1_000;
    }

    // Convert from gram to milligram
    public static double gramToMg(double num) {
        return num * 1_000;
    }

    // Convert from gram to kilogram
    public static double gramToKg(double num) {
        return num / 1_000;
    }

    // Convert from kilogram to gram
    public static double kgToGram(double num) {
        return num * 1_000;
    }

    // Convert from kilogram to metric ton
    public static double kgToMetTon(double num) {
        return num / 1_000;
    }

    // Convert from metric ton to kilogram
    public static double metTonToKg(double num) {
        return num * 1_000;
    }

    // Convert from ounce to gram
    public static double ounceToGram(double num) {
        return num * 28.3495;
    }

    // Convert from gram to ounce
    public static double gramToOunce(double num) {
        return num / 28.3495;
    }

    // Convert from pound to kilogram
    public static double poundToKg(double num) {
        return num / 2.205;
    }

    // Convert from kilogram to pound
    public static double kgToPound(double num) {
        return num * 2.205;
    }
}


public class Main{
    // List of options
    public enum Units {
        LENGTH, TEMPERATURE, AREA, VOLUME, WEIGHT
    };

    public static void main(String[] args) {
        double num;
        int choice1, choice2;

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nPlease enter the value you'd like to convert, followed by the source unit and target unit.");
            desc();

            // Check critical statements
            try {
                choice1 = input.nextInt();
                if (choice1 > 5) {
                    System.out.println("\n!!!Naah...Enter a correct number.!!!\n");
                    continue;
                } else if (choice1 == 0) {
                    System.out.println("-----Bye-----");
                    break;
                }
                choice1(choice1);
                System.out.print("Choose a number: ");
                choice2 = input.nextInt();
                input.nextLine();
                System.out.print("Enter a number: ");
                num = input.nextDouble();
                if (num < 0) {
                    System.out.println("Value cannot be negative.");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n!!!Naah... We've only accepted numbers.!!!");
                input.nextLine();
                continue;
            } 

            choice2(choice1, choice2,num);
            
        }
        input.close();

    }

    // Show descrition using enum and forEach loop
    public static void desc() {
        System.out.println("\n==============================");
        System.out.println("========Unit Converter========");
        System.out.println("==============================\n");
        System.out.println("          0 -> EXIT           ");
        for (Units unit : Units.values()) {
            System.out.printf("          %d -> %s         \n", unit.ordinal() + 1, unit);
        }
        System.out.print("\nSelect Conversion: ");
    }

    // Check first input (choice1) and show list based on the choice
    public static void choice1(int choice) {
        switch (choice) {
            case 1 -> Length.list();
            case 2 -> Temperature.list();
            case 3 -> Area.list();
            case 4 -> Volume.list();
            case 5 -> Weight.list();
            default -> System.out.println("\n!!!Naah...Enter a correct number.!!!\n");
        }
    }

    // Check second input (choice2) and pick correct method based on the choice
    public static void choice2(int choice1, int choice2,double num) {
        switch (choice1) {
            case 1 -> lengthChoice(choice2, num);
            case 2 -> temperatureChoice(choice2, num);
            case 3 -> areaChoice(choice2, num);
            case 4 -> volumeChoice(choice2, num);
            case 5 -> weightChoice(choice2, num);
            default -> System.out.println("\n!!!Naah...Enter a correct number.!!!\n");
        }
    }

    // Perform actual math in the Length class based on the user choice
    public static void lengthChoice(int value, double num) {
        switch (value) {
            case 1 -> System.out.printf("\n----->   %.2f millimeter -> %4.2f meter  <-----\n", num, Length.mmToM(num));
            case 2 -> System.out.printf("\n----->   %.2f meter -> %4.2f millimeter  <-----\n", num, Length.mToMM(num));
            case 3 -> System.out.printf("\n----->   %.2f centimeter -> %4.2f meter  <-----\n", num, Length.cmToM(num));
            case 4 -> System.out.printf("\n----->   %.2f meter -> %4.2f centimeter  <-----\n", num, Length.mToCM(num));
            case 5 -> System.out.printf("\n----->   %.2f kilometer -> %4.2f meter  <-----\n", num, Length.kmToM(num));
            case 6 -> System.out.printf("\n----->   %.2f meter -> %4.2f kilometer  <-----\n", num, Length.mToKM(num));
            case 7 -> System.out.printf("\n----->   %.2f inch -> %4.2f meter  <-----\n", num, Length.inchToM(num));
            case 8 -> System.out.printf("\n----->   %.2f meter -> %4.2f inch  <-----\n", num,Length.mToInch(num));
            case 9 -> System.out.printf("\n----->   %.2f foot -> %4.2f meter  <-----\n", num,Length.footToM(num));
            case 10 -> System.out.printf("\n----->   %.2f meter -> %4.2f foot  <-----\n",num, Length.mToFoot(num));
            case 11 -> System.out.printf("\n----->   %.2f yard -> %4.2f meter  <-----\n", num, Length.yardToM(num));
            case 12 -> System.out.printf("\n----->   %.2f meter -> %4.2f yard  <-----\n", num, Length.mToYard(num));
            case 13 -> System.out.printf("\n----->   %.2f mile -> %4.2f kilometer  <-----\n", num, Length.mileToKM(num));
            case 14 -> System.out.printf("\n----->   %.2f kilometer ->  mile  <-----\n", num, Length.kmToMile(num));
            default -> System.out.println("\n!!!Naah...Enter a correct number.!!!\n");
        }
    }

    // Perform actual math in the Temperature class based on the user choice
    public static void temperatureChoice(int value, double num) {
        switch (value) {
            case 1 -> System.out.printf("\n----->   %.2f celsius -> %4.2f fahrenheit  <-----\n", num, Temperature.celToFar(num));
            case 2 -> System.out.printf("\n----->   %.2f fahrenheit -> %4.2f celsius  <-----\n", num, Temperature.farToCel(num));
            case 3 -> System.out.printf("\n----->   %.2f celsius -> %4.2f kelvin  <-----\n", num, Temperature.celToKel(num));
            case 4 -> System.out.printf("\n----->   %.2f kelvin -> %4.2f celsius  <-----\n", num, Temperature.kelToCel(num));
            case 5 -> System.out.printf("\n----->   %.2f fahrenheit -> %4.2f kelvin  <-----\n", num, Temperature.farToKel(num));
            case 6 -> System.out.printf("\n----->   %.2f kelvin -> %4.2f fahrenheit  <-----\n", num, Temperature.kelToFar(num));
            default -> System.out.println("\n!!!Naah...Enter a correct number.!!!\n");
        }
    }

    // Perform actual math in the Area class based on the user choice
    public static void areaChoice(int value, double num) {
        switch (value) {
            case 1 -> System.out.printf("\n----->   %.2f square meter -> %4.2f square kilometer  <-----\n", num, Area.sqMtToSqKm(num));
            case 2 -> System.out.printf("\n----->   %.2f square kilometer -> %4.2f square meter  <-----\n", num, Area.sqKmToSqMt(num));
            case 3 -> System.out.printf("\n----->   %.2f square foot -> %4.2f square meter  <-----\n", num, Area.sqFtToSqMt(num));
            case 4 -> System.out.printf("\n----->   %.2f square meter -> %4.2f square foot  <-----\n", num, Area.sqMtToSqFt(num));
            case 5 -> System.out.printf("\n----->   %.2f square yard  <-> %4.2f square meter  <-----\n", num, Area.sqYardToSqMt(num));
            case 6 -> System.out.printf("\n----->   %.2f square meter -> %4.2f square yard  <-----\n", num, Area.sqMtToSqYard(num));
            case 7 -> System.out.printf("\n----->   %.2f acre -> %4.2f square meter  <-----\n", num, Area.acreToSqMt(num));
            case 8 -> System.out.printf("\n----->   %.2f square meter -> %4.2f acre  <-----\n", num,Area.sqMtToAcre(num));
            case 9 -> System.out.printf("\n----->   %.2f hectare -> %4.2f square meter  <-----\n", num,Area.hecToSqMt(num));
            case 10 -> System.out.printf("\n----->   %.2f square meter -> %4.2f hectare  <-----\n",num, Area.sqMtToHec(num));
            default -> System.out.println("\n!!!Naah...Enter a correct number.!!!\n");
        }
    }

    // Perform actual math in the Volume class based on the user choice
    public static void volumeChoice(int value, double num) {
        switch (value) {
            case 1 -> System.out.printf("\n----->   %.2f liter -> %4.2f milliliter  <-----\n", num, Volume.ltToMm(num));
            case 2 -> System.out.printf("\n----->   %.2f milliliter -> %4.2f liter  <-----\n", num, Volume.mlToLt(num));
            case 3 -> System.out.printf("\n----->   %.2f liter -> %4.2f cubic meter  <-----\n", num, Volume.ltToCubMt(num));
            case 4 -> System.out.printf("\n----->   %.2f cubic meter -> %4.2f liter  <-----\n", num, Volume.cubMtToLt(num));
            case 5 -> System.out.printf("\n----->   %.2f gallon (US) -> %4.2f liter  <-----\n", num, Volume.galToLt(num));
            case 6 -> System.out.printf("\n----->   %.2f liter -> %4.2f gallon (US)  <-----\n", num, Volume.ltToGal(num));
            case 7 -> System.out.printf("\n----->   %.2f quart (US) -> %4.2f liter  <-----\n", num, Volume.quarToLt(num));
            case 8 -> System.out.printf("\n----->   %.2f liter -> %4.2f quart (US)  <-----\n", num, Volume.ltToQuar(num));
            case 9 -> System.out.printf("\n----->   %.2f pint (US) -> %4.2f liter  <-----\n", num, Volume.pintToLt(num));
            case 10 -> System.out.printf("\n----->   %.2f liter -> %4.2f pint (US)  <-----\n",num, Volume.ltToPint(num));
            case 11 -> System.out.printf("\n----->   %.2f cubic foot -> %4.2f cubic meter  <-----\n", num, Volume.cubFtToCubMt(num));
            case 12 -> System.out.printf("\n----->   %.2f cubic meter -> %4.2f cubic foot  <-----\n", num, Volume.cubMtToCuFt(num));
            default -> System.out.println("\n!!!Naah...Enter a correct number.!!!\n");
        }
    }

    // Perform actual math in the Weight class based on the user choice
    public static void weightChoice(int value, double num) {
        switch (value) {
            case 1 -> System.out.printf("\n----->   %.2f milligram -> %4.2f gram  <-----\n", num, Weight.mgToGram(num));
            case 2 -> System.out.printf("\n----->   %.2f gram -> %4.2f milligram  <-----\n", num, Weight.gramToMg(num));
            case 3 -> System.out.printf("\n----->   %.2f gram -> %4.2f kilogram  <-----\n", num, Weight.gramToKg(num));
            case 4 -> System.out.printf("\n----->   %.2f kilogram -> %4.2f gram  <-----\n", num, Weight.kgToGram(num));
            case 5 -> System.out.printf("\n----->   %.2f kilogram -> %4.2f metric ton  <-----\n", num, Weight.kgToMetTon(num));
            case 6 -> System.out.printf("\n----->   %.2f metric ton -> %4.2f kilogram  <-----\n", num, Weight.metTonToKg(num));
            case 7 -> System.out.printf("\n----->   %.2f ounce -> %4.2f gram  <-----\n", num, Weight.ounceToGram(num));
            case 8 -> System.out.printf("\n----->   %.2f gram -> %4.2f ounce  <-----\n", num, Weight.gramToOunce(num));
            case 9 -> System.out.printf("\n----->   %.2f pound -> %4.2f kilogram  <-----\n", num, Weight.poundToKg(num));
            case 10 -> System.out.printf("\n----->   %.2f kilogram -> %4.2f pound  <-----\n",num, Weight.kgToPound(num));
            default -> System.out.println("\n!!!Naah...Enter a correct number.!!!\n");
        }
    }
}