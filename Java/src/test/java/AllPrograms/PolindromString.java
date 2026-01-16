package AllPrograms;

import java.util.Scanner;

public class PolindromString {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the text:");
        String str=sc.nextLine();
        System.out.println("The string is:"+str);
        String temp=str;
        String rev="";
        for(int i=str.length()-1;i>=0;i--){
            rev=rev+str.charAt(i);
        }
        if(temp.equals(rev)){
            System.out.println("The string is polindrom");
        }else {
            System.out.println("The string is not polindrom");
        }
    }
}
