package helpers;

import java.util.ArrayList;

public class Utils {

    // Create a 2D matrix of integers from an ArrayList
    public static int[][] ArrayListTo2Dint(ArrayList<Integer> list, int ySize, int xSize) {
        int[][] newArray = new int[ySize][xSize];

        for (int j = 0; j < newArray.length; j++) {
            for (int i = 0; i < newArray[j].length; i++) {
                int index = j * ySize + i;
                newArray[j][i] = list.get(index);
            }
        }

        return newArray;
    }

    // Create a 1D matrix from 2D matrix
    public static int[] TwoDTo1DintArray(int[][] twoDimArray) {
        int[] oneDimArray = new int[twoDimArray.length * twoDimArray[0].length];

        for (int j = 0; j < twoDimArray.length; j++) {
            for (int i = 0; i < twoDimArray[j].length; i++) {
                int index = j * twoDimArray.length + i;
                oneDimArray[index] = twoDimArray[j][i];
            }
        }

        return oneDimArray;
    }

}
