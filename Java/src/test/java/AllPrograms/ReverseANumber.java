package AllPrograms;

public class ReverseANumber {
    public static void main(String args[]){
        int n=123456;
        int rev=0;
        while(n!=0){
            rev=rev*10+n%10;
            n=n/10;
        }
        System.out.println(rev);
    }
}
