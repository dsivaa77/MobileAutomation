package AllPrograms;

import java.util.Scanner;

public class ReverseAString {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("This is the String: ");
        String str=sc.nextLine();
        System.out.println(str);
        String rev="";

        //Logic #1
//        for(int i=str.length()-1;i>=0;i--){
//            rev=rev+str.charAt(i);
//        }

        //Logic #2
        StringBuilder sb=new StringBuilder(str);
        StringBuilder revst=sb.reverse();
        System.out.println("Reverse of a String is :"+revst.toString());
    }
}
