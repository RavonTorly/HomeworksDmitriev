import java.util.Date;

public class ex9 {
    public static void main(String[] args) {
        System.out.println("Результат: " + checkIsLeapYear(2000));
        System.out.println("Результат: " + checkIsLeapYear(2004));
        System.out.println("Результат: " + checkIsLeapYear(2005));
        System.out.println("Результат: " + checkIsLeapYear(2020));
        System.out.println("Результат: " + checkIsLeapYear(2024));
        System.out.println("Результат: " + checkIsLeapYear(1800));
    }
    public static boolean checkIsLeapYear(int year){
        System.out.println(year);
        if(year % 400 == 0){
            return true;
        }else if(year % 100 == 0){
            return false;
        }else{
            return year % 4 == 0;
        }
    }
}
