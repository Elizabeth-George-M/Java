import java.util.Scanner;

public class Performance {
    
    private int[] marks;

    
    public Performance() {
        marks = new int[10];
    }

    public void readMarks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter marks for 10 students:");
        for (int i = 0; i < 10; i++) {
            marks[i] = scanner.nextInt();
        }
    }
    public int highestMark() {
        int max = marks[0];
        for (int i = 1; i < marks.length; i++) {
            if (marks[i] > max) {
                max = marks[i];
            }
        }
        return max;
    }

    public int leastMark() {
        int min = marks[0];
        for (int i = 1; i < marks.length; i++) {
            if (marks[i] < min) {
                min = marks[i];
            }
        }
        return min;
    }

    public int getMode() {
        int mode = marks[0];
        int maxFrequency = 1;

        for (int i = 0; i < marks.length; i++) {
            int currentMark = marks[i];
            int currentFrequency = 1;

            for (int j = i + 1; j < marks.length; j++) {
                if (marks[j] == currentMark) {
                    currentFrequency++;
                }
            }

            if (currentFrequency > maxFrequency || (currentFrequency == maxFrequency && currentMark > mode)) {
                mode = currentMark;
                maxFrequency = currentFrequency;
            }
        }

        return mode;
    }
    public int getFreqAtMode() {
        int mode = getMode();
        int frequency = 0;

        for (int i = 0; i < marks.length; i++) {
            if (marks[i] == mode) {
                frequency++;
            }
        }

        return frequency;
    }
    public void display() {
        System.out.println("Highest Mark: " + highestMark());
        System.out.println("Least Mark: " + leastMark());
        System.out.println("Mode: " + getMode());
        System.out.println("Mode Frequency: " + getFreqAtMode());
    }

    public static void main(String[] args) {
        Performance performance = new Performance();
        performance.readMarks();
        performance.display();
    }
}
