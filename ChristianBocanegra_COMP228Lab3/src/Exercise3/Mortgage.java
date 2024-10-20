package Exercise3;

public abstract class Mortgage implements MortgageConstants {
    protected String mortgageNumber;
    protected String customerName;
    protected double mortgageAmount;
    protected double interestRate;
    protected int term;

    public Mortgage(String mortgageNumber, String customerName, double mortgageAmount, double interestRate, int term) {
        this.mortgageNumber = mortgageNumber;
        this.customerName = customerName;

        if (mortgageAmount > max_amount) {
            this.mortgageAmount = max_amount;
        } else {
            this.mortgageAmount = mortgageAmount;
        }

        if (term != short_term && term != medium_term && term != long_term) {
            this.term = short_term;
        } else {
            this.term = term;
        }

        this.interestRate = interestRate;
    }

    public abstract String getMortgageInfo();

    public double calculateTotalAmountOwed() {
        return mortgageAmount + (mortgageAmount * interestRate * term);
    }
}

