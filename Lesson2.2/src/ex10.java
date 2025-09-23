import java.util.Arrays;

public class ex10 {
    public static void main(String[] args) {
        int[] a = {0,1,0,1,0,1,1,1,0,1};
        System.out.println("Исходный массив: ");
        checkIntArray(a);
        for(int i = 0; i < a.length; i++){
            if(a[i] == 0){
                a[i] = 1;
            }else{
                a[i] = 0;
            }
        }
        System.out.println("\nИзмененный массив: ");
        checkIntArray(a);
    }
    public static void checkIntArray(int[] a){
        for(int i = 0; i < a.length; i++){
            int num = a[i];
            System.out.print(num + " ");
        }
    }
}
