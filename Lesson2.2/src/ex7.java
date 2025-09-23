public class ex7 {
    public static void main(String[] args) {
        System.out.println("Результат: " + checkBooleanParam(0));
        System.out.println("Результат: " + checkBooleanParam(-1));
        System.out.println("Результат: " + checkBooleanParam(1));
    }
    public static boolean checkBooleanParam(int a){
        System.out.println(a);
        if(a < 0){
            return true;
        }else{
            return false;
        }
    }
}
