package Exercise2;

class PartTimeGameTester extends GameTester {
    private static final double hourly_rate = 20.0;
    private int hoursWorked;

    public PartTimeGameTester(String name, int hoursWorked) {
        super(name, false);
        this.hoursWorked = hoursWorked;
    }

    // Implement determineSalary for part-time testers
    @Override
    public double determineSalary() {
        return hoursWorked * hourly_rate;
    }
}
