import java.util.Scanner;
interface WaterConservationSystem {
    int calculateTrappedWater(int[] blockHeights);
}

 abstract class RainySeasonConservation implements WaterConservationSystem {
    // Add any common methods or properties here

    @Override
    public abstract int calculateTrappedWater(int[] blockHeights);
}

class CityBlockCons extends RainySeasonConservation {
    @Override
    public int calculateTrappedWater(int[] blockHeights) {
        int n = blockHeights.length;
        if (n <= 2) {
            return 0; // There can be no trapped water with less than 3 blocks
        }

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = blockHeights[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], blockHeights[i]);
        }

        rightMax[n - 1] = blockHeights[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], blockHeights[i]);
        }

        int trappedWater = 0;
        for (int i = 0; i < n; i++) {
            trappedWater += Math.max(0, Math.min(leftMax[i], rightMax[i]) - blockHeights[i]);
        }

        return trappedWater;
    }
}



public class WCS{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of blocks: ");
        int n = scanner.nextInt();
        
        System.out.println("Enter the heights of the blocks:");
        int[] blockHeights = new int[n];
        for (int i = 0; i < n; i++) {
            blockHeights[i] = scanner.nextInt();
        }
        
        WaterConservationSystem conservationSystem = new CityBlockCons();
        int trappedWaterVolume = conservationSystem.calculateTrappedWater(blockHeights);
        System.out.println("Total volume of water that can be conserved: " + trappedWaterVolume);
        
        scanner.close();
    }
}