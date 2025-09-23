public class ex8 {
    public static void main(String[] args) {
        checkParamsStringAndInt("hello" , 3);
    }
    public static void checkParamsStringAndInt(String hello, int a){
        for (int i = 0; i < a; i++){
            System.out.println(hello);
        }
    }
}
