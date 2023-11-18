import java.util.*;

public class TopKNumbers {

    // Static variable to store the input array
    private static int[] numbers;

    // Static method to find and print the top K numbers with the highest occurrences
    public static void findTopKNumbers(int[] array, int k) {
        // Set the static variable
        numbers = array;

        // Create a map to store the frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Calculate the frequency of each number in the array
        for (int num : numbers) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Create a priority queue to store the numbers based on their frequencies and values
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() != b.getValue() ?
                        b.getValue() - a.getValue() :
                        b.getKey() - a.getKey()
        );

        // Add entries from the frequency map to the priority queue
        pq.addAll(frequencyMap.entrySet());

        // Print the top K numbers with the highest occurrences
        System.out.println("Top " + k + " numbers with the highest occurrences:");

        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> entry = pq.poll();
            assert entry != null;
            System.out.println(entry.getKey() + " - Occurrences: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        // Scanner to get input from the user
        Scanner scanner = new Scanner(System.in);

        // Get the size of the array from the user
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        // Get the array elements from the user
        int[] inputArray = new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            inputArray[i] = scanner.nextInt();
        }

        // Get the value of K from the user
        System.out.print("Enter the value of K: ");
        int k = scanner.nextInt();

        // Close the scanner
        scanner.close();

        // Call the method to find and print the top K numbers
        findTopKNumbers(inputArray, k);
    }
}
