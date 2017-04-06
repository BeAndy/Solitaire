import java.io.*;
import java.util.*;

/**
 * Created by Andrew on 3/16/2017.
 */

public class Runner {
    private static final String INPUT = "input.txt";
    private static final String OUTPUT = "output.txt";
    private static final int INFINITY = Integer.MAX_VALUE;

    public static void main(String[] args) {
        try {
            writeData(multiplyOrder(getArray(INPUT)));

        } catch (IOException currentEx) {
            currentEx.printStackTrace();
        }
    }

    private static void nullifyDiagonal(int[][] currentArray) {
        for (int i = 0; i < currentArray.length; i++) {
            currentArray[i][i] = 0;
        }
        for (int i = 0; i < currentArray.length-1; i++) {
            currentArray[i][i+1] = 1;
        }


    }


    private static void outMatrix(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int multiplyOrder(int[] array) {
        int[][] multArray = new int[array.length+1][array.length+1];
        nullifyDiagonal(multArray);
        for (int i = 2; i <array.length+1; i++) {
            System.out.println(i);
            for (int j = 1; j <= array.length - i + 1; j++) {
                int current = j + i - 1;
                multArray[j][current] = INFINITY;
                for (int k = j; k < current; k++) {
                    multArray[j][current] = Math.min(multArray[j][current], multArray[j][k] + multArray[k + 1][current] + Math.abs(array[k-1] - array[current-1]));
                    outMatrix(multArray);
                }
            }
        }
        return multArray[1][array.length];
    }

    private static int[] getArray(String files) throws IOException {
        Scanner reader = new Scanner(new FileReader(new File(files)));
        int[] array = new int[Integer.parseInt(reader.next())];
        int i = 0, lastElement = 0;
        while (reader.hasNext()) {
            array[i] = Integer.parseInt(reader.next());
            i++;
        }
        System.out.println(Arrays.toString(array));
        reader.close();
        return array;
    }

    private static void writeData(int attachment) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(OUTPUT));
        fileWriter.write(String.valueOf(attachment));
        fileWriter.close();
    }

}
