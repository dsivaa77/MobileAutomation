package AllPrograms;

public class FindSumOfDigits {
    public static void main(String args[]){
        int n=123456788;
        int sum=0;
        while(n!=0){
            int d=n%10;
            sum=sum+d;
            n=n/10;
        }
        System.out.println("The sum of digits is:"+sum);
    }
}
