// Define the BankInterface
interface BankInterface {
    double getBalance();
    double getInterestRate();
    double calculateInterest();
}

// Create BankA implementing the BankInterface
class BankA implements BankInterface {
    private double balance;

    public BankA() {
        this.balance = 10000;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.0; // % interest rate
    }

    @Override
    public double calculateInterest() {
        return balance * (getInterestRate() / 100.0);
    }
}

// Create BankB implementing BankInterface
class BankB implements BankInterface {
    private double balance;

    public BankB() {
        this.balance = 150000;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.4; // % interest rate
    }

    @Override
    public double calculateInterest() {
        return balance * (getInterestRate() / 100.0);
    }
}

// Create BankC implementing the BankInterface
class BankC implements BankInterface {
    private double balance;

    public BankC() {
        this.balance = 200000;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.9; // % interest rate
    }

    @Override
    public double calculateInterest() {
        return balance * (getInterestRate() / 100.0);
    }
}

// Main class to instantiate and display bank details
public class Bank {
    public static void main(String[] args) {
        BankInterface bankA = new BankA();
        BankInterface bankB = new BankB();
        BankInterface bankC = new BankC();

        System.out.println("BankA Balance: " + bankA.getBalance() + ",Interest Rate: " +bankA.getInterestRate() +",Interest Amount: " + bankA.calculateInterest());
        System.out.println("BankB Balance: " + bankB.getBalance() +",Interest Rate: " +bankB.getInterestRate() +", Interest Amount: " + bankB.calculateInterest());
        System.out.println("BankC Balance: " + bankC.getBalance() + ",Interest Rate: " +bankC.getInterestRate()+", Interest Amount: " + bankC.calculateInterest());
    }
}