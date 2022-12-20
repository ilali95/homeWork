// Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив,
// каждый элемент которого равен разности элементов двух входящих массивов в той же ячейке. 
// Если длины массивов не равны, необходимо как-то оповестить пользователя.
package HomeWork;

import java.util.Random;

public class Task02 {
	public static void main(String[] args) {
        int[] firstArray = getFilledArray(10);
        int[] secondArray = getFilledArray(10);

        int[] divArray = divArrays(firstArray, secondArray);
        printArray(divArray);

        int[] badArray = divArrays(null, secondArray);
    }

    public static int[] divArrays(int[] firstArray, int[] secondArray) {
        if (firstArray == null || secondArray == null) throw new RuntimeException("Нет массивов!!!");
        if(firstArray.length != secondArray.length) throw new RuntimeException("Длины массивов не равны!!!");


        int[] resultArr = new int[firstArray.length];
        for (int i = 0; i < resultArr.length; i++) {
            resultArr[i] = firstArray[i]/secondArray[i];
        }
        return resultArr;
    }

    public static int[] getFilledArray(int length){
        int[] array = new int[length];
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = rand.nextInt(10);
        }
        return array;
    }

    public static void printArray(int[] array){
        for (int number: array){
            System.out.println(number);
        }
    }
}
