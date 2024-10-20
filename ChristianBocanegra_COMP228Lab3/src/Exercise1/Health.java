package Exercise1;

public class Health extends Insurance {

    public Health() {
        super("Health Insurance");
    }

    @Override
    public void setInsuranceCost(double cost) {
        this.monthlyCost = cost;
    }

    @Override
    public void displayInfo() {
        System.out.println("Insurance Type: " + getInsuranceType());
        System.out.println("Monthly Cost: $" + getMonthlyCost());
        System.out.println("Description: Provides coverage for medical expenses, including hospital stays, doctor's visits, surgeries, and medications. \n" +
                "It helps employees manage the costs of healthcare and ensures they receive necessary treatments.");
    }
}

