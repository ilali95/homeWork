package HomeWork;

public class Task01 {
    public static void main(String[] args) {
        String[] sArray = { "first str", null, "third str" };
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        division(10, 2);
        printValueByIndex(array, 7);
        printStringArray(sArray);
    }

    // division by zero
    public static void division(double firstNum, double secondNum) {
        if (secondNum == 0)
            throw new ArithmeticException("На ноль делить нельзя!");

        double result = firstNum / secondNum;
        System.out.printf("Результат деления %f на %f равен %f.\n", firstNum, secondNum, result);
    }

    // out of bounds
    public static void printValueByIndex(int[] array, int index) {
        if (index > array.length - 1)
            throw new ArrayIndexOutOfBoundsException("Элемента под таким индексом не существует");
        else
            System.out.printf("Элемент с индексом %d равен %d.\n", index, array[index]);
    }

    // Null pointer exception
    public static void printStringArray(String[] sArray) {
        for (String item : sArray) {
            if (item == null)
                throw new NullPointerException("Элемент отсутствует!!!");
            System.out.println(item);
        }
    }
}