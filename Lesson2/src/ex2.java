import java.util.Random;
import java.util.Scanner;

public class ex2 {
    public static void main(String[] args) {
        checkSumSign();
    }
    public static void checkSumSign(){
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Введите число а:");
        int a = scanner.nextInt();
        System.out.println("Введите число b:");
        int b = scanner.nextInt();
        scanner.close();
        int sum = a + b;
        if(sum >= 0){
            System.out.println(sum + "\n" + "Сумма положительная!");
        }else{
            System.out.println(sum + "\n" + "Сумма отрицательная!");
        }
    }
}
