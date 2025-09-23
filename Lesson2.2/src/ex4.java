import java.util.Scanner;

public class ex4 {
    public static void main(String[] args) {
        compareNumbers();
    }
    public static void compareNumbers(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значения a:");
        int a = scanner.nextInt();
        System.out.println("Введите значения b:");
        int b = scanner.nextInt();
        scanner.close();
        if(a >= b){
            System.out.println("a >= b");
        }else{
            System.out.println("a < b");
        }

    }
}
