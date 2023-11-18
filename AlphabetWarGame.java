import java.util.Scanner;

public class AlphabetWarGame {
    int w,p,b,s,m,q,d,z;
    AlphabetWarGame() {
        w = 4;
        p = 3;
        b = 2;
        s = 1;
        m = 4;
        q = 3;
        d = 2;
        z = 1;
    }
    AlphabetWarGame(String user_choice) {
        Scanner choice = new Scanner(System.in);
        if(user_choice.equals("yes")) {
            System.out.println("Enter strenght of w :");
            w = choice.nextInt();
            System.out.println("Enter strenght of p :");
            p = choice.nextInt();
            System.out.println("Enter strenght of b :");
            b = choice.nextInt();
            System.out.println("Enter strenght of s :");
            s = choice.nextInt();
            System.out.println("Enter strenght of m :");
            m = choice.nextInt();
            System.out.println("Enter strenght of q :");
            q = choice.nextInt();
            System.out.println("Enter strenght of d :");
            d = choice.nextInt();
            System.out.println("Enter strenght of z :");
            z = choice.nextInt();
        }
        else
            System.out.println("Proceeding with default values");
    }
    void AlphabetWar(String x) {
        int left_score = 0;
        int right_score = 0;
        for(char c: x.toCharArray()) {
            switch(c) {
                case 'w':
                    left_score += w;
                    break;
                case 'p':
                    left_score += p;
                    break;
                case 'b':
                    left_score += b;
                    break;
                case 's':
                    left_score += s;
                    break;
                case 'm':
                    right_score += m;
                    break;
                case 'q':
                    right_score += q;
                    break;
                case 'd':
                    right_score += d;
                    break;
                case 'z':
                    right_score += z;
                    break;
            }
        }
        if(left_score > right_score)
            System.out.println("Left side wins");
        else if (right_score > left_score)
            System.out.println("Right side wins");
        else
            System.out.println("Let's fight again");


    }
    public static void main(String[] args) {
        AlphabetWarGame play = new AlphabetWarGame();
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to change the strengths: ");
        String user_choice = input.nextLine().toLowerCase();
        AlphabetWarGame play1 = new AlphabetWarGame(user_choice);
        System.out.println("Enter Letters");
        play.AlphabetWar(input.nextLine());
    }

}