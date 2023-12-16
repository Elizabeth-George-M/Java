import java.util.Scanner;
abstract class Robber{
void RobbingClass(){System.out.println("MScAi&ML");}

abstract int RoundHouse(int[] xxx);
abstract int RowHouse(int[] xxx);
abstract int SquareHouse(int[] xxx);
abstract int MultiHouse(int[] xxx);
void MachineLearning(){System.out.println("I Love Machine Learning");}
}

class JAVAProfessionalRobber extends Robber{
int maxrob=0;
int RowHouse(int[] arr){
    if(arr[0]+arr[2]>=arr[1]+arr[3]){
        if(arr[0]+arr[2]>=arr[0]+arr[3]){
            maxrob=arr[0]+arr[2];
        }
        else{
            maxrob=arr[0]+arr[3];
        }
    }
    else if(arr[1]+arr[3]>=arr[0]+arr[2]){
        if(arr[1]+arr[3]>=arr[0]+arr[3]){
            maxrob=arr[1]+arr[3];
        }
        else{
            maxrob=arr[0]+arr[3];
        }   
    }
    return maxrob;
}
int RoundHouse(int[] arr){
    if(arr[0]+arr[2]>=arr[1]+arr[3]){
        maxrob=arr[0]+arr[2];
    }
    else{
        maxrob=arr[1]+arr[3];
    }
    
    return maxrob;
}
int SquareHouse(int[] arr){
    if(arr[0]+arr[2]>=arr[1]+arr[3]){
        maxrob=arr[0]+arr[2];
    }
    else{
        maxrob=arr[1]+arr[3];
    }
    return maxrob;
}
int MultiHouse(int[] arr){
    if(arr[0]+arr[2]+arr[4]>=arr[1]+arr[3]+arr[5]){
        maxrob=arr[0]+arr[2]+arr[4];
    }
    else{
        maxrob=arr[1]+arr[3]+arr[5];
    }
    return maxrob;
}
}
public class MainRobber{
    public static void main(String args[]){
        int out=0;
	int[] arr= new int[6];
        JAVAProfessionalRobber jpr=new JAVAProfessionalRobber();
	jpr.MachineLearning(); 
        System.out.println("Which type of House you want to steal\n1-Row House\n2-Round House\n3-Square House\n4-Multi House\n\nEnter your choice : ");
        Scanner s = new Scanner(System.in);
	int choice=s.nextInt();
        switch(choice){
            case 1:
                System.out.println("Enter amount available in 4 houses :\n");
                for(int i=0;i<4;i++){
                    arr[i]=s.nextInt();
                }
                out=jpr.RowHouse(arr);
                break;
            case 2:
                System.out.println("Enter amount available in 4 houses :\n");
                for(int i=0;i<4;i++){
                    arr[i]=s.nextInt();
                }
                out=jpr.RoundHouse(arr);
                break;
            case 3:
                System.out.println("Enter amount available in 4 houses :\n");
                for(int i=0;i<4;i++){
                    arr[i]=s.nextInt();
                }
                out=jpr.SquareHouse(arr);
                break;
            case 4:
                System.out.println("Enter amount available in 6 houses :\n");
                for(int i=0;i<6;i++){
                    arr[i]=s.nextInt();
                }
                out=jpr.MultiHouse(arr);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    System.out.println("On stealing you can get Max Profit = "+out);
    }
}