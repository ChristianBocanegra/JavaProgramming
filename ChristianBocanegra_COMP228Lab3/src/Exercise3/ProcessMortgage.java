package Exercise3;
import java.util.Scanner;
public class ProcessMortgage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mortgage[] mortgages = new Mortgage[3];

        System.out.print("Enter the current prime interest rate (as a decimal): ");
        double primeRate = scanner.nextDouble();

        for (int i = 0; i < mortgages.length; i++) {
            System.out.println("\nMortgage #" + (i + 1) + ":");
            System.out.print("Enter mortgage number: ");
            String mortgageNumber = scanner.next();
            System.out.print("Enter customer name: ");
            String customerName = scanner.next();
            System.out.print("Enter mortgage amount: ");
            double mortgageAmount = scanner.nextDouble();
            System.out.print("Enter mortgage term (1, 3, or 5 years): ");
            int term = scanner.nextInt();
            System.out.print("Is this a (1) Business or (2) Personal mortgage? ");
            int type = scanner.nextInt();

            if (type == 1) {
                mortgages[i] = new BusinessMortgage(mortgageNumber, customerName, mortgageAmount, primeRate, term);
            } else if (type == 2) {
                mortgages[i] = new PersonalMortgage(mortgageNumber, customerName, mortgageAmount, primeRate, term);
            } else {
                System.out.println("Invalid type. Defaulting to Personal mortgage.");
                mortgages[i] = new PersonalMortgage(mortgageNumber, customerName, mortgageAmount, primeRate, term);
            }
        }

        System.out.println("\nAll Mortgages:");
        for (Mortgage mortgage : mortgages) {
            System.out.println(mortgage.getMortgageInfo());
        }

        scanner.close();
    }
}

