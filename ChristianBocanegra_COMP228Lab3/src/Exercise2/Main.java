package Exercise2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the game tester:");
        String name = scanner.nextLine();

        System.out.println("Select tester type: 1 for Full-time, 2 for Part-time");
        int testerType = scanner.nextInt();

        GameTester tester;

        if (testerType == 1) {
            // Create full-time tester
            tester = new FullTimeGameTester(name);
        } else if (testerType == 2) {
            // Create part-time tester and ask for hours worked
            System.out.println("Enter the number of hours worked:");
            int hoursWorked = scanner.nextInt();
            tester = new PartTimeGameTester(name, hoursWorked);
        } else {
            System.out.println("Invalid choice.");
            scanner.close();
            return;
        }

        // Display tester details and salary
        System.out.println("Game Tester Name: " + tester.name);
        System.out.println("Tester Type: " + (tester.isFullTime ? "Full-time" : "Part-time"));
        System.out.println("Salary: $" + tester.determineSalary());

        scanner.close();

    }
}
