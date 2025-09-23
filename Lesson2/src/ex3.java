import java.util.Scanner;

public class ex3 {
    public static void main(String[] args) {
        printColor();
    }
    public static void printColor(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число value:");
        int value = scanner.nextInt();
        scanner.close();
        if(value <= 0){
            System.out.println(value + "\n" + "Красный");
        }else if(value > 0 && value <= 100){
            System.out.println(value + "\n" + "Желтый");
        }else if(value > 100){
            System.out.println(value + "\n" + "Зеленый");
        }

    }
}
