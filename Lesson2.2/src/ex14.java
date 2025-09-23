public class ex14 {
    public static void main(String[] args) {
        int[] result_one = createArray(5,10);
        System.out.println("Первый массив: " + java.util.Arrays.toString(result_one));
        int[] result_two = createArray(2,6);
        System.out.println("Второй массив: " + java.util.Arrays.toString(result_two));
        int[] result_three = createArray(4,8);
        System.out.println("Третий массив: " + java.util.Arrays.toString(result_three));
    }
    public static int[] createArray(int len, int initialValue){
        int[] a = new int[len];
        for (int i = 0; i < len; i++){
            a[i] = initialValue;
        }
        return a;
    }
}
