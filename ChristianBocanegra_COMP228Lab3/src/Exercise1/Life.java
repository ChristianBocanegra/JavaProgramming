package Exercise1;

public class Life extends Insurance {

    public Life() {
        super("Life Insurance");
    }

    @Override
    public void setInsuranceCost(double cost) {
        this.monthlyCost = cost;
    }

    @Override
    public void displayInfo() {
        System.out.println("Insurance Type: " + getInsuranceType());
        System.out.println("Monthly Cost: $" + getMonthlyCost());
        System.out.println("Description: Offers financial protection to the employee's beneficiaries in the event of the employee's death. \n" +
                "It typically provides a lump sum payment to help cover funeral expenses, debts, and future financial needs for the family.");
    }
}

