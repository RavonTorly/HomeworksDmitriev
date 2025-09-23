import java.util.Random;

public class ex11 {
    public static void main(String[] args) {
        int[] a = new int[100];
        for(int i = 0; i < a.length; i++){
            a[i] = i + 1;
        }
        System.out.println("Заполненный массив от 1 до 100: ");
        clearArray(a);
    }
    public static void clearArray(int[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
    }
}
