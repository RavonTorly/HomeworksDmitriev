import java.util.Scanner;

public class Methods {
    //Ex1
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    //Ex2
    public static void checkSumSign() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число а:");
        int a = scanner.nextInt();
        System.out.println("Введите число b:");
        int b = scanner.nextInt();
        int sum = a + b;
        if (sum >= 0) {
            System.out.println(sum + "\n" + "Сумма положительная!");
        } else {
            System.out.println(sum + "\n" + "Сумма отрицательная!");
        }
    }

    //Ex3
    public static void printColor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число value:");
        int value = scanner.nextInt();
        if (value <= 0) {
            System.out.println(value + "\n" + "Красный");
        } else if (value <= 100) {
            System.out.println(value + "\n" + "Желтый");
        } else {
            System.out.println(value + "\n" + "Зеленый");
        }

    }

    //Ex4
    public static void compareNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значения a:");
        int a = scanner.nextInt();
        System.out.println("Введите значения b:");
        int b = scanner.nextInt();
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }

    }

    //Ex5
    public static boolean checkSumBoolean() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число a:");
        int a = scanner.nextInt();
        System.out.println("Введите число b:");
        int b = scanner.nextInt();
        scanner.close();
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    //Ex6
    public static void checkParam(int a) {
        if (a >= 0) {
            System.out.println(a + " - Положительное число");
        } else {
            System.out.println(a + " - Отрицательное число");
        }
    }

    //Ex7
    public static boolean checkBooleanParam(int a) {
        System.out.println(a);
        return a < 0;
    }

    //Ex8
    public static void checkParamsStringAndInt(String hello, int a) {
        for (int i = 0; i < a; i++) {
            System.out.println(hello);
        }
    }

    //Ex9
    public static boolean checkIsLeapYear(int year) {
        System.out.println(year);
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return year % 4 == 0;
        }
    }

    //Ex10
    public static void checkIntArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int num = a[i];
            System.out.print(num + " ");
        }
    }

    //Ex11
    public static void clearArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    //Ex12
    public static void checkArrayLowSix(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int result = a[i];
            System.out.print(result + " ");
        }
    }

    //Ex13
    public static void createTwoArray(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Ex14
    public static int[] createArray(int len, int initialValue) {
        int[] a = new int[len];
        for (int i = 0; i < len; i++) {
            a[i] = initialValue;
        }
        return a;
    }

    public static void main(String[] args) {
        //Ex 1
        {
            System.out.println("\nЗадание 1:");
            printThreeWords();
        }
        //Ex 2
        {
            System.out.println("\nЗадание 2:");
            checkSumSign();
        }
        //Ex 3
        {
            System.out.println("\nЗадание 3:");
            printColor();
        }
        //Ex 4
        {
            System.out.println("\nЗадание 4:");
            compareNumbers();
        }
        //Ex 5
        {
            System.out.println("\nЗадание 5:");
            boolean result = checkSumBoolean();
            System.out.println("Результат: " + result);
        }
        //Ex 6
        {
            System.out.println("\nЗадание 6:");
            checkParam(10);
            checkParam(0);
            checkParam(-10);
        }

        //Ex 7
        {
            System.out.println("\nЗадание 7:");
            System.out.println("Результат: " + checkBooleanParam(0));
            System.out.println("Результат: " + checkBooleanParam(-1));
            System.out.println("Результат: " + checkBooleanParam(1));
        }
        //Ex 8
        {
            System.out.println("\nЗадание 8:");
            checkParamsStringAndInt("hello", 3);
            System.out.println();
        }

        //Ex 9
        {
            System.out.println("\nЗадание 9:");
            System.out.println("Результат: " + checkIsLeapYear(2000));
            System.out.println("Результат: " + checkIsLeapYear(2004));
            System.out.println("Результат: " + checkIsLeapYear(2005));
            System.out.println("Результат: " + checkIsLeapYear(2020));
            System.out.println("Результат: " + checkIsLeapYear(2024));
            System.out.println("Результат: " + checkIsLeapYear(1800));
            System.out.println();
        }

        //Ex 10
        {
            System.out.println("\nЗадание 10:");
            int[] a = {0, 1, 0, 1, 0, 1, 1, 1, 0, 1};
            System.out.println("Исходный массив: ");
            checkIntArray(a);
            for (int i = 0; i < a.length; i++) {
                if (a[i] == 0) {
                    a[i] = 1;
                } else {
                    a[i] = 0;
                }
            }
            System.out.println("\nИзмененный массив: ");
            checkIntArray(a);
            System.out.println();
        }

        //Ex 11
        {
            System.out.println("\nЗадание 11:");
            int[] a = new int[100];
            for (int i = 0; i < a.length; i++) {
                a[i] = i + 1;
            }
            System.out.println("Заполненный массив от 1 до 100: ");
            clearArray(a);
            System.out.println();
        }

        //Ex 12
        {
            System.out.println("\nЗадание 12:");
            int[] a = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
            System.out.println("Исходные данные: ");
            checkArrayLowSix(a);
            for (int i = 0; i < a.length; i++) {
                if (a[i] < 6) {
                    a[i] *= 2;
                }
            }
            System.out.println("\nУмноженные данные на 2: ");
            checkArrayLowSix(a);
            System.out.println();
        }

        {
            //Ex13
            System.out.println("\nЗадание 13:");
            int[][] a = new int[5][5];
            System.out.println("Исходные значения: ");
            createTwoArray(a);
            for (int i = 0; i < a.length; i++) {
                a[i][i] = 1;
            }
            System.out.println("\nМассив с диагональю: ");
            createTwoArray(a);
            System.out.println();
        }

        {
            //Ex14
            System.out.println("Задание 14:");
            int[] result_one = createArray(5, 10);
            System.out.println("Первый массив: " + java.util.Arrays.toString(result_one));
            int[] result_two = createArray(2, 6);
            System.out.println("Второй массив: " + java.util.Arrays.toString(result_two));
            int[] result_three = createArray(4, 8);
            System.out.println("Третий массив: " + java.util.Arrays.toString(result_three));
        }
    }


}
