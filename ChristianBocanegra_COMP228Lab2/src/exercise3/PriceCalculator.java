package exercise3;

public class PriceCalculator {


    public static double calculateTotalPrice(double price) {
        return price;
    }
    public static double calculateTotalPrice(double price, double discount) {
        return price - (price * (discount / 100));
    }
    public static double calculateTotalPrice(double price, double tax, boolean includeTax) {
        if (includeTax) {
            return price + (price * (tax / 100));
        }
        return price;
    }

    public static void main(String[] args) {

        double totalPrice1 = calculateTotalPrice(100.0);
        System.out.println("Total price of a product (without taxes or discounts): $" + totalPrice1);

        double totalPrice2 = calculateTotalPrice(100.0, 10.0);
        System.out.println("Total price of a product with a 10% discount (without taxes): $" + totalPrice2);

        double totalPrice3 = calculateTotalPrice(100.0, 15.0, true);
        System.out.println("Total price of a product with a 15% tax (without discounts): $" + totalPrice3);
    }


}

