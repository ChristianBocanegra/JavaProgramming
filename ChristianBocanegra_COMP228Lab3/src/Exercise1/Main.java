package Exercise1;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Insurance[] insuranceArray = new Insurance[2];


        for (int i = 0; i < insuranceArray.length; i++) {
            System.out.println("Enter the type of insurance (Life or Health): ");
            String type = scanner.nextLine();

            System.out.println("Enter the monthly cost of insurance: ");
            double cost = scanner.nextDouble();
            scanner.nextLine();

            if (type.equalsIgnoreCase("Life")) {
                insuranceArray[i] = new Life();
            } else if (type.equalsIgnoreCase("Health")) {
                insuranceArray[i] = new Health();
            } else {
                System.out.println("Invalid insurance type, please try again.");
                i--;
                continue;
            }

            insuranceArray[i].setInsuranceCost(cost);
        }


        System.out.println("\nInsurance Information:");
        System.out.println();
        for (Insurance insurance : insuranceArray) {
            insurance.displayInfo();
            System.out.println();
        }
    }
}
