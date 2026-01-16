package AllPrograms;

import java.util.Arrays;

public class ReverseEachWordInString {
    public static void main(String args[]) {
        String str = "Welcome to my world";
        String[] words = str.split(" ");

       // System.out.print("The String words: "+Arrays.toString(words));
        String revString="";
        for(String w:words){
//           // Logic #1
//            String rev="";
//            for(int i=w.length()-1;i>=0;i--){
//                rev=rev+w.charAt(i);
//            }
//
//            revString=revString+rev+" ";

            //Logic #2
            StringBuilder sb=new StringBuilder(w);
            StringBuilder rev=sb.reverse();

            revString=revString+rev.toString()+" ";
        }
        System.out.println("Reverse of String Words: "+revString);
    }
}
