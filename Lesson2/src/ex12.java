public class ex12 {
    public static void main(String[] args) {
        int[] a = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Исходные данные: ");
        checkArrayLowSix(a);
        for(int i = 0; i < a.length; i++){
            if(a[i] < 6){
                a[i] *= 2;
            }
        }
        System.out.println("\nУмноженные данные на 2: ");
        checkArrayLowSix(a);
    }
    public static void checkArrayLowSix(int[] a){
        for(int i = 0; i < a.length; i++){
            int result = a[i];
            System.out.print(result + " ");
        }
    }
}
