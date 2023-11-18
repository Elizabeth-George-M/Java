public class Alphabetwar{
    private static String left_letters = "wpsb";
    private static String right_letters = "mqdz";

    private static  int strength_w = 4;
    private static  int strength_p = 3;
    private static  int strength_b = 2;
    private static  int strength_s = 1;
    private static  int strength_m = 4;
    private static  int strength_q = 3;
    private static  int strength_d = 2;
    private static  int strength_z = 1;

    public Alphabetwar() {
    }
    public Alphabetwar(int w, int p, int b, int s, int m, int q, int d, int z) {
        strength_w = w;
        strength_p = p;
        strength_b = b;
        strength_s = s;
        strength_m = m;
        strength_q = q;
        strength_d = d;
        strength_z = z;
    }
    public String battle(String word) {
        int leftStrength = 0;
        int rightStrength = 0;

        for (char letter : word.toCharArray()) {
            if (left_letters.indexOf(letter) != -1) {
                leftStrength += getStrength(letter);
            } else if (right_letters.indexOf(letter) != -1) {
                rightStrength += getStrength(letter);
            }
        }

        if (leftStrength > rightStrength) {
            return "Left side wins!";
        } else if (rightStrength > leftStrength) {
            return "Right side wins!";
        } else {
            return "Let's fight again!";
        }
    }

    public String battle(String leftWord, String rightWord) {
        int leftStrength = Strength(leftWord, left_letters);
        int rightStrength = Strength(rightWord, right_letters);

        if (leftStrength > rightStrength) {
            return "Left side wins!";
        } else if (rightStrength > leftStrength) {
            return "Right side wins!";
        } else {
            return "Let's fight again!";
        }
    }

    private int Strength(String word, String sideLetters) {
        int strength = 0;

        for (char letter : word.toCharArray()) {
            if (sideLetters.indexOf(letter) != -1) {
                strength += getStrength(letter);
            }
        }

        return strength;
    }

    private int getStrength(char letter) {
        switch (letter) {
            case 'w':
                return strength_w;
            case 'p':
                return strength_p;
            case 'b':
                return strength_b;
            case 's':
                return strength_s;
            case 'm':
                return strength_m;
            case 'q':
                return strength_q;
            case 'd':
                return strength_d;
            case 'z':
                return strength_z;
            default:
                return 0; 
        }
    }

    public static void main(String[] args) {
        Alphabetwar game = new Alphabetwar();
        System.out.println(game.battle("z"));         
        System.out.println(game.battle("zdqmwpbs")); 
        System.out.println(game.battle("wwwwwwz"));   
    }
}
