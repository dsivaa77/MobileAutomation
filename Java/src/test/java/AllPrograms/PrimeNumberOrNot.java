package AllPrograms;

import java.util.Scanner;

public class PrimeNumberOrNot {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number:");
        int n=sc.nextInt();
        int temp=n;
        int rev=0;
        while(n!=0){
            rev=rev*10+n%10;
            n=n/10;
        }
        if(temp==rev){
            System.out.println("The number is polindrom");
        }else {
            System.out.println("The number is not polindrom");
        }

    }
}
