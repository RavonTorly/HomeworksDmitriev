public class ex13 {
    public static void main(String[] args) {
        int[][] a = new int[5][5];
        System.out.println("Исходные значения: ");
        createTwoArray(a);
        for(int i = 0; i < a.length; i++){
           a[i][i] = 1;
        }
        System.out.println("\nМассив с диагональю: ");
        createTwoArray(a);
    }
    public static void createTwoArray(int[][] a){
        for(int i = 0; i < a.length; i++){
            for (int j = 0; j < a[i].length; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}
