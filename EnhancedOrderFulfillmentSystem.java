import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class EnhancedOrderFulfillmentSystem {
    private static final Map<Integer, Order> orderHistory = new HashMap<>();
    private static final Map<Integer, Product> Inventory = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    static void startProcessing(int id, int qty) {
        if (Inventory.containsKey(id)) {
            Product product = Inventory.get(id);
            Order order = orderHistory.get(id);
            if (product.quantity >= qty) {
                product.quantity -= qty;
                order.status = "Order Placed";
                System.out.println("Order status Updated!");

            } else {
                order.status = "Order Failed (Insufficient Quantity)";
                System.out.println("Order status Updated!");
            }
        } else {
            System.out.println("No product Found!");
        }

    }

    public static void main(String[] args) throws InterruptedException {

        Inventory.put(1, new Product("Phone", 10));
        Inventory.put(2, new Product("Earphone", 20));
        Inventory.put(3, new Product("Laptop", 4));

        displayInventory();

        int choice;

        do {
            System.out.println("\nInventory Menu:");
            System.out.println("1. Place Order");
            System.out.println("2. Update Order");
            System.out.println("3. Inventery Availability");
            System.out.println("4. Display All Products");
            System.out.println("5. Order Status");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    placeOrder();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    qtyAvailability();
                    break;
                case 4:
                    displayInventory();
                    break;

                case 5:
                    trackOrder();
                    break;
                case 6:
                    System.out.println("Goodbye!");

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice < 6);
    }

    private static void placeOrder() throws InterruptedException {

        System.out.print("Enter product id: ");
        int pdId = scanner.nextInt();

        Product product = Inventory.get(pdId);
        String pdName = product.name;

        System.out.print("Enter quantity: ");
        int pdQty = scanner.nextInt();

        String pdStatus = "Initiated";
        Order newOrder = new Order(pdName, pdQty, pdStatus);
        orderHistory.put(pdId, newOrder);
        System.out.println("Order placed successfully!");

        Thread t1 = new Thread(() -> {
            {
                startProcessing(pdId, pdQty);
            }
        });
        t1.start();
        t1.join();
    }

    private static void updateProduct() {
        System.out.print("Enter product id to update: ");
        int pdId = scanner.nextInt();

        if (Inventory.containsKey(pdId)) {
            System.out.print("Enter the new name: ");
            String pdName = scanner.nextLine();
            System.out.print("Enter the new quantity: ");
            int pdQty = scanner.nextInt();
            Product product = Inventory.get(pdId);
            product.name = pdName;
            product.quantity = pdQty;
            Inventory.put(pdId, product);
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found!");
        }
    }

    private static void qtyAvailability() {
        System.out.print("Enter product id to retrieve: ");
        int pdId = scanner.nextInt();

        Product product = Inventory.get(pdId);
        int qty = product.quantity;
        System.out.println("--------------------");
        System.out.println("Available Quantity: " + qty);

    }

    private static void trackOrder() {
        System.out.print("Enter product id to retrieve: ");
        int pdId = scanner.nextInt();

        Order order = orderHistory.get(pdId);
        String sts = order.status;
        System.out.println("--------------------");
        System.out.println("Order Status of (" + pdId + ") : " + sts);

    }

    public static void displayInventory() {
        System.out.println("Inventory:");

        for (Map.Entry<Integer, Product> entry : Inventory.entrySet()) {
            int productId = entry.getKey();
            Product product = entry.getValue();

            System.out.println("Product ID: " + productId);
            System.out.println("Product Details: " + product.name);
            System.out.println("--------------------");
        }
    }

}

class Order {
    String name;
    int quantity;
    String status;

    public Order(String name, int quantity, String status) {
        this.name = name;
        this.quantity = quantity;
        this.status = status;
    }

    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

class Product {
    String name;
    int quantity;

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;

    }

    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}