import java.util.Scanner;

// Base Class: Employee
class Employee {
    private int employeeId;
    private String employeeName;
    private String designation;

    // Constructor
    public Employee(int employeeId, String employeeName, String designation) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
    }

    // Getters and Setters for attributes
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    // Method to calculate bonus
    public double calculateBonus() {
        return 0.0; // Base implementation, to be overridden
    }

    // Method to display detailed employee information
    public void displayEmployeeInformation() {
        System.out.println("\nEmployee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Designation: " + designation);
    }
}

// Derived Class: HourlyEmployee
class HourlyEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    // Constructor
    public HourlyEmployee(int employeeId, String employeeName, String designation, double hourlyRate, int hoursWorked) {
        super(employeeId, employeeName, designation);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    // Getters and Setters for additional attributes
    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    // Method to calculate weekly salary
    public double calculateWeeklySalary() {
        return hourlyRate * hoursWorked;
    }

    // Override method to calculate bonus
    @Override
    public double calculateBonus() {
        return 0.05 * calculateWeeklySalary(); // Assume 5% bonus
    }

    // Method to display detailed employee information
    @Override
    public void displayEmployeeInformation() {
        super.displayEmployeeInformation();
        System.out.println("Weekly Salary: " + calculateWeeklySalary());
    }
}

// Derived Class: SalariedEmployee
class SalariedEmployee extends Employee {
    private double monthlySalary;

    // Constructor
    public SalariedEmployee(int employeeId, String employeeName, String designation, double monthlySalary) {
        super(employeeId, employeeName, designation);
        this.monthlySalary = monthlySalary;
    }

    // Getters and Setters for additional attributes
    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    // Method to calculate weekly salary
    public double calculateWeeklySalary() {
        return monthlySalary / 4; // Assuming a 4-week month
    }

    // Override method to calculate bonus
    @Override
    public double calculateBonus() {
        return 0.10 * monthlySalary; // Assume 10% bonus
    }

    // Method to display detailed employee information
    @Override
    public void displayEmployeeInformation() {
        super.displayEmployeeInformation();
        System.out.println("Weekly Salary: " + calculateWeeklySalary());
    }
}

// Extended Class: ExecutiveEmployee
class ExecutiveEmployee extends SalariedEmployee {
    private double bonusPercentage;

    // Constructor
    public ExecutiveEmployee(int employeeId, String employeeName, String designation, double monthlySalary, double bonusPercentage) {
        super(employeeId, employeeName, designation, monthlySalary);
        this.bonusPercentage = bonusPercentage;
    }

    // Getters and Setters for additional attribute
    public double getBonusPercentage() {
        return bonusPercentage;
    }

    public void setBonusPercentage(double bonusPercentage) {
        this.bonusPercentage = bonusPercentage;
    }

    // Override method to calculate bonus
    @Override
    public double calculateBonus() {
        return bonusPercentage / 100 * getMonthlySalary(); // Calculating bonus based on the given percentage
    }
}

public class PayrollSystem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an Hourly Employee
        System.out.println("Enter details for Hourly Employee:");
        System.out.print("Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Designation: ");
        String designation = scanner.nextLine();
        System.out.print("Hourly Rate: ");
        double hourlyRate = scanner.nextDouble();
        System.out.print("Hours Worked: ");
        int hoursWorked = scanner.nextInt();
        HourlyEmployee hourlyEmployee = new HourlyEmployee(id, name, designation, hourlyRate, hoursWorked);
        hourlyEmployee.displayEmployeeInformation();
        System.out.println("Bonus: " + hourlyEmployee.calculateBonus());

        // Create a Salaried Employee
        System.out.println("\nEnter details for Salaried Employee:");
        System.out.print("Employee ID: ");
        id = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Employee Name: ");
        name = scanner.nextLine();
        System.out.print("Designation: ");
        designation = scanner.nextLine();
        System.out.print("Monthly Salary: ");
        double salary = scanner.nextDouble();
        SalariedEmployee salariedEmployee = new SalariedEmployee(id, name, designation, salary);
        salariedEmployee.displayEmployeeInformation();
        System.out.println("Bonus: " + salariedEmployee.calculateBonus());

        // Create an Executive Employee
        System.out.println("\nEnter details for Executive Employee:");
        System.out.print("Employee ID: ");
        id = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Employee Name: ");
        name = scanner.nextLine();
        System.out.print("Designation: ");
        designation = scanner.nextLine();
        System.out.print("Monthly Salary: ");
        salary = scanner.nextDouble();
        System.out.print("Bonus Percentage: ");
        double bonusPercentage = scanner.nextDouble();
        ExecutiveEmployee executiveEmployee = new ExecutiveEmployee(id, name, designation, salary, bonusPercentage);
        executiveEmployee.displayEmployeeInformation();
        System.out.println("Bonus: " + executiveEmployee.calculateBonus());

        // Close the scanner
        scanner.close();
    }
}
 
