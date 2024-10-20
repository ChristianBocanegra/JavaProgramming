package Exercise1;

public abstract class Insurance {
    protected String insuranceType;
    protected double monthlyCost;

    public Insurance(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }

    // Abstract methods to be implemented by subclasses
    public abstract void setInsuranceCost(double cost);
    public abstract void displayInfo();
}

