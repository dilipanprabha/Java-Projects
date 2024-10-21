import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class Student {
    public enum Subject {
        TAMIL, ENGLISH, MATHS, SCIENCE, SOCIAL_SCIENCE;

        public String toString() {
            switch (this) {
                case TAMIL:
                    return "TAMIL";
                case ENGLISH:
                    return "ENGLISH";
                case MATHS:
                    return "MATHS";
                case SCIENCE:
                    return "SCIENCE";
                case SOCIAL_SCIENCE:
                    return "SOCIAL SCIENCE";
            }
            return null;
        }
    }

    private String name;
    private int age;
    private int standard;
    private int[] grade;

    public Student(String name, int age, int standard) {
        this.name = name;
        this.age = age;
        this.standard = standard;
        grade = new int[5];
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int[] getGrades() {
        return grade;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public void addGrades(int[] grade) {
        this.grade = grade;
    }

    public void setGrade(String subject, int grade) {
        int index = subjectIndex(subject);
        if (index == -1) return;
        this.grade[index] = grade;
    }

    public int subjectIndex(String subject) {
        if (checkSubject(subject)) {
            for (Subject s: Subject.values()) {
                if (s.toString().equals(subject)) return s.ordinal();
            }
        }
        return -1;
    }

    public boolean checkSubject(String subject) {
        for (Subject s: Subject.values()) {
            if (s.toString().equals(subject)) return true;
        }
        return false;
    }

    public void showSubject() {
        System.out.println();
        for (Subject c: Subject.values()) {
            System.out.println((c.ordinal() + 1) + " -> " + c);
        }
        System.out.println();
    }
    
    public float averageGrade() {
        float sum = 0;
        for (int i: grade) {
            sum += i;
        }

        return sum / Subject.values().length;
    }

    public int highestScore() {
        int highest = grade[0];

        for (int i = 1; i < grade.length; i++) {
            if (grade[i] > highest) highest = grade[i];
        }

        return highest;
    }

    public int lowestScore() {
        int lowest = grade[0];

        for (int i = 1; i < grade.length; i++) {
            if (grade[i] < lowest) lowest = grade[i];
        }

        return lowest;
    }

    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", standard=" + standard + ", grade=" + Arrays.toString(grade)
                + ", average=" + this.averageGrade() + ", highest=" + this.highestScore() + ", lowest=" + this.lowestScore() + "]";
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        String name;
        int age, standard, mark, i = 0;
        int[] grades = new int[5];

        while (true) {
            try {
                System.out.print("Enter your name: ");
                name = s.nextLine();
                System.out.print("Enter your age: ");
                age = s.nextInt();
                s.nextLine();
                if (age < 6 || age > 16) throw new Exception(name + ", Please enter correct age.");
                System.out.print("Enter your standard: ");
                standard = s.nextInt();
                s.nextLine();
                if (standard < 1 || standard > 10) throw new Exception(name + ", Please enter correct standard.");

                if (name.length() != 0 && (age > 6 && age <= 16) && (standard > 0 && standard <= 10)) break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter correct input.");
                s.nextLine();
                continue;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
        } 

        Student s1 = new Student(name, age, standard);
        s1.showSubject();

        while (i < 5) {
            System.out.print("Subject" + (i + 1) + " mark: ");
            try {
                mark = s.nextInt();
                s.nextLine();
                if (mark < 0 || mark > 100) throw new Exception("Enter correct mark.");
            } catch (InputMismatchException e) {
                System.out.println("Enter only numbers.");
                s.nextLine();
                continue;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            grades[i] = mark;
            i++;
        }
        s1.addGrades(grades);
        System.out.println(s1);
    }

    
}
