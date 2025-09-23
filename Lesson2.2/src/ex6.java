import java.util.Scanner;

public class ex6 {
    public static void main(String[] args) {
        checkParam(10);
        checkParam(0);
        checkParam(-10);
    }
    public static void checkParam(int a){
        if(a >= 0){
            System.out.println(a + " - Положительное число");
        }else{
            System.out.println(a + " - Отрицательное число");
        }
    }
}
