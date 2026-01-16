package AllPrograms;

import java.util.Scanner;

public class RemoveWhiteSpaces {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the value:");
        String str=sc.nextLine();
        System.out.println("Before remove the white spaces:"+str);
        str=str.replace(" ","");

        System.out.println("After remove the white spaces:"+str);

    }
}
