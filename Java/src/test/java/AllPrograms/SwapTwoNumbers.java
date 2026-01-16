package AllPrograms;

public class SwapTwoNumbers {
    public static void main(String args[]){
        //Logic #1
        int a = 10, b = 20;
//        int c;
//        c=a;
//        a=b;
//        b=c;

        //Logic #2 without 3 rd variable
        a = a+b;
        b = a-b;
        a = a-b;

        System.out.println("a value " +a);
        System.out.println("b value "+b);

    }
}
