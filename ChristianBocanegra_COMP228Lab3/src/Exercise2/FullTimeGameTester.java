package Exercise2;

class FullTimeGameTester extends GameTester{
    private static final double Full_time_salary = 3000.0;

    public FullTimeGameTester(String name) {
        super(name, true);
    }

    // Implement determineSalary for full-time testers
    @Override
    public double determineSalary() {
        return Full_time_salary;
    }
}
