public class Main {
    public static void main(String[] args) {
        String[][] arrayCorrect = {
                {"6", "5", "7", "2"},
                {"3", "2", "5", "11"},
                {"2", "2", "4", "22"},
                {"2", "3", "4", "16"}
        };
        String[][] arrayUncorrect = new String[5][5];

        System.out.println("Задание 1:");
        try {
            processArray(arrayCorrect);
            processArray(arrayUncorrect);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        String[][] arrayWithDataError = {
                {"6", "5", "7", "2"},
                {"3", "2", "5", "11"},
                {"2", "2", "4", "22"},
                {"2", "3", "4", "DDDD"}
        };

        try {
            System.out.println("Сумма правильного массива: " + processArray(arrayCorrect));
            System.out.println("Сумма массива с ошибкой: " + processArray(arrayWithDataError));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        int[] numbers = {55, 11, 66, 33, 22, 44};

        try{
            System.out.println(numbers[0]);
            System.out.println(numbers[11]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Ошибка: такого индекса не существует!");
        }

    }

    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Не верное количество строк: " + array.length);
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Не верное количество столбцов: " + i + ": " + array[i].length);
            }
        }
        System.out.println("Верный массив 4х4");

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Не верные данные ячейки [" + i + "][" + i + "]: '" + array[i][j] + "'");
                }
            }
        }
        return sum;
    }
}

class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

class MyArrayDataException extends Exception {
    public MyArrayDataException(String message) {
        super(message);
    }
}