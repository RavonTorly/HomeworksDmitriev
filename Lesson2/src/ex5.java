import java.util.Scanner;

public class ex5 {
    public static void main(String[] args) {
       boolean result = checkSumBoolean();
       System.out.println("Результат: " + result);
    }
    public static boolean checkSumBoolean(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число a:");
        int a = scanner.nextInt();
        System.out.println("Введите число b:");
        int b = scanner.nextInt();
        scanner.close();
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }
}
