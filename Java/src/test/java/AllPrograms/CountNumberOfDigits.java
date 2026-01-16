package AllPrograms;

public class CountNumberOfDigits {
    public static void main(String args[]) {
        int n = 123464;
        int count=0;
        while(n!=0){
            int d=n%10;
            count++;
            n=n/10;
        }
        System.out.println("The digits count:"+count);
    }

}
