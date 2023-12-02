import java.util.Scanner;

public class ShareTrader {
    static int maxProfit;
    static int[] trans;
    static int n;

    ShareTrader() {
        maxProfit = 0;
    }

    static void findMaxProfit() {
        int i,l1,l2;
        int k =0;
        int m = 1;
        int[] Buy = new int[n];
        int[] Sell = new int[n];
l1=l2=0;
        for (i = 0; k < n; i++) {
            
            Buy[i] = trans[k];
            k += 2;
            l1++;
        }

        for (i = 0;  m< n; i++) {
            Sell[i] = trans[m];
            m += 2;
            l2++;
        }

k = l1;
m= l2;
        for (i = 1; i < k - 1; i++) {
            for (int j = 1; j < k - i - 1; j++) {
                if (Buy[j] > Buy[j + 1]) {
                    int temp = Buy[j];
                    Buy[j] = Buy[j + 1];
                    Buy[j + 1] = temp;
                }
            }
        }
        System.out.println(" Buy :");
        for( i=0;i<k;i++)
        System.out.println(Buy[i]);
                System.out.println(" Sell :");
        for( i=0;i<m;i++)
        System.out.println(Sell[i]);
        

        int s1 = Math.abs(Buy[0] - Sell[0]);
        int s2 = Math.abs(Buy[1] - Sell[m - 1]);
        int sum = s1 + s2;

        System.out.println("Trade earns " + sum + " as a sum of " + s1 + " ," + s2);
        System.out.println("Buy at " + Buy[0] + ", " + Sell[0]);
        System.out.println("Buy at " + Buy[1] + ", " + Sell[m - 1]);
    }

    void Input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the transaction:");
        n = scanner.nextInt();
        trans = new int[n];
        System.out.println("Enter the transaction elements in Buy-Sell format");
        for (int i = 0; i < n; i++)
            trans[i] = scanner.nextInt();
        scanner.close();
    }

    public static void main(String args[]) {
        ShareTrader t = new ShareTrader();
        t.Input();
        t.findMaxProfit();
    }
}