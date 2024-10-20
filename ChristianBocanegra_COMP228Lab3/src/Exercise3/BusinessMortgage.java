package Exercise3;

public class BusinessMortgage extends Mortgage {

    public BusinessMortgage(String mortgageNumber, String customerName, double mortgageAmount, double primeRate, int term) {
        super(mortgageNumber, customerName, mortgageAmount, primeRate + 0.01, term);
    }

    @Override
    public String getMortgageInfo() {
        return String.format("Business Mortgage: %s, Customer: %s, Amount: $%.2f, Interest Rate: %.2f%%, Term: %d years, Total Owed: $%.2f",
                    mortgageNumber, customerName, mortgageAmount, (interestRate * 100), term, calculateTotalAmountOwed());
    }

}

