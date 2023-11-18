import java.util.Scanner;

//instance variable
public class Performance{
    private int[] marks;

//constructor
    public Performance(){
        marks = new int [10];
    }

//readMarks() method
    public void readMarks(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the marks of 10 Students: ");
        for (int i=0 ; i<10 ; i++){
            System.out.print("Student"+(i + 1) + ":");
            marks[i] = scanner.nextInt();
    }
}

//highestMarks() method
    public int highestMark(){
        int max = marks[0];
        for (int i=0 ; i < marks.length ; i++ ){
            if (marks[i] > max) {
                max = marks[i];
            }
    }
    return max;
}

//leastMarks() method
    public int leastMark(){
        int min = marks[0];
        for (int i=1 ; i < marks.length ; i++ ){
            if (marks[i] < min) {
                min = marks[i];
            }
    }
    return min;
}

//getMode() method
    public int getMode(){
        int mode = marks[0];
        int maxFreq = 1;

        for (int i = 0; i < marks.length; i++){
            int currentMark = marks[i];
            int currentFreq = 1;

        for (int j = i + 1; j < marks.length; j++ ){
            if(marks[j] == currentMark){
                currentFreq++;
            }
        }

        if (currentFreq > maxFreq || (currentFreq == maxFreq && currentMark > mode)){
            mode = currentMark;
            maxFreq = currentFreq;
        }
      }
     return mode;
            }

//getFreqAtMode() method
    public int getFreqAtMode(){
        int freq = 0;
        int mode = getMode();

        for(int i = 0; i < marks.length; i++){
            if(marks[i] == mode){
                freq++;
            }
        }
        return freq;
        }
    
//display() method
    public void display(){
        System.out.println("Maximum Marks: " + highestMark());
        System.out.println("Minimum Marks: " + leastMark());
        System.out.println("Mode : "+ getMode());
        System.out.println("Frequency at Mode: "+ getFreqAtMode());
    }

//main()method
    public static void main(String args[]){
        Performance performance = new Performance();
        performance.readMarks();
        performance.display();
    }
}