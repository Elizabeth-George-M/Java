import java.util.Scanner;

// Employee class
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

    

    // Method to calculate bonus 
    public double calculateBonus() {
        return 0.0;  // Base implementation, to be overridden
    }

    // Method to display detailed employee information
    public void displayEmployeeInformation() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Designation: " + designation);
    }
}

// HourlyEmployee class
class HourlyEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    // Constructor
    public HourlyEmployee(int employeeId, String employeeName, String designation, double hourlyRate, int hoursWorked) {
        super(employeeId, employeeName, designation);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    // Getters and Setters for hourlyRate and hoursWorked
    // (Getters and Setters implementation)

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

// SalariedEmployee class
class SalariedEmployee extends Employee {
    private double monthlySalary;

    // Constructor
    public SalariedEmployee(int employeeId, String employeeName, String designation, double monthlySalary) {
        super(employeeId, employeeName,designation);
        this.monthlySalary = monthlySalary;
    }

    // Getters and Setters for monthlySalary
    // (Getters and Setters implementation)

    // Method to calculate weekly salary
    public double calculateWeeklySalary() {
        return monthlySalary / 4;
    }

    // Override method to calculate bonus
    @Override
    public double calculateBonus() {
        return 0.1 * monthlySalary; // Assume 10% bonus
    }

    // Method to display detailed employee information
    @Override
    public void displayEmployeeInformation() {
        super.displayEmployeeInformation();
        System.out.println("Weekly Salary: " + calculateWeeklySalary());
    }
}

public class Payrollsystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Obtain details for HourlyEmployee
        System.out.println("Enter details for Hourly Employee:");
        System.out.print("Employee ID: ");
        int hourlyEmployeeId = input.nextInt();
        System.out.print("Employee Name: ");
        String hourlyEmployeeName = input.next();
        System.out.print("Designation: ");
        String hourlyDesignation = input.next();
        System.out.print("Hourly Rate: ");
        double hourlyRate = input.nextDouble();
        System.out.print("Hours Worked: ");
        int hoursWorked = input.nextInt();
        
        // Create HourlyEmployee object
        HourlyEmployee hourlyEmployee = new HourlyEmployee(hourlyEmployeeId, hourlyEmployeeName, hourlyDesignation, hourlyRate, hoursWorked);

        // Obtain details for SalariedEmployee
        System.out.println("\nEnter details for Salaried Employee:");
        System.out.print("Employee ID: ");
        int salariedEmployeeId = input.nextInt();
        System.out.print("Employee Name: ");
        String salariedEmployeeName = input.next();
        System.out.print("Designation: ");
        String salariedDesignation = input.next();
        System.out.print("Monthly Salary: ");
        double monthlySalary = input.nextDouble();
        
        // Create SalariedEmployee object
        SalariedEmployee salariedEmployee = new SalariedEmployee(salariedEmployeeId, salariedEmployeeName, salariedDesignation, monthlySalary);

        // Display employee information
        System.out.println("\nEmployee Information:");
        hourlyEmployee.displayEmployeeInformation();
        salariedEmployee.displayEmployeeInformation();

        input.close(); // Close the Scanner
    }
}
