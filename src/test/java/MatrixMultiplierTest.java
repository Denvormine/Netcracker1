import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixMultiplierTest {

    @Test
    public void Run_Two10By10MatrixWithThreeThreads_GettingCorrectResult() throws InterruptedException {
        int matrixDimensions = 10;
        Integer[][] matrix1 = new Integer[matrixDimensions][matrixDimensions];
        Integer[][] matrix2 = new Integer[matrixDimensions][matrixDimensions];
        Integer[][] resultMatrix = new Integer[matrixDimensions][matrixDimensions];
        Integer[][] expectedMatrix = new Integer[matrixDimensions][matrixDimensions];

        for (int i = 0; i < matrixDimensions; i++) {
            for (int j = 0; j < matrixDimensions; j++) {
                matrix1[i][j] = 10;
                matrix2[i][j] = 3;
                expectedMatrix[i][j] = 300;
            }
        }
        MatrixMultiplier multiplier = new MatrixMultiplier(matrix1, matrix2, resultMatrix);
        multiplier.setThreadAmount(3);
        multiplier.start();
        multiplier.join();

        Assert.assertArrayEquals(expectedMatrix, resultMatrix);
    }

    @Test
    public void Run_15By10MatrixAnd10By15MatrixWith4Threads_GettingCorrectResult() throws InterruptedException {
        Integer[][] matrix1 = new Integer[15][10];
        Integer[][] matrix2 = new Integer[10][15];
        Integer[][] resultMatrix = new Integer[15][15];
        Integer[][] expectedMatrix = new Integer[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 10; j++) {
                matrix1[i][j] = 10;
                matrix2[j][i] = 3;
            }
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                expectedMatrix[i][j] = 300;
            }
        }
        MatrixMultiplier multiplier = new MatrixMultiplier(matrix1, matrix2, resultMatrix);
        multiplier.setThreadAmount(4);
        multiplier.start();
        multiplier.join();

        Assert.assertArrayEquals(resultMatrix, expectedMatrix);
    }

    @Test
    public void TestingPerformanceWith4And1Threads() throws InterruptedException {
        int matrixDimensions = 1000;
        Integer[][] matrix1 = new Integer[matrixDimensions][matrixDimensions];
        Integer[][] matrix2 = new Integer[matrixDimensions][matrixDimensions];
        Integer[][] resultMatrix = new Integer[matrixDimensions][matrixDimensions];
        Integer[][] expectedMatrix = new Integer[matrixDimensions][matrixDimensions];

        for (int i = 0; i < matrixDimensions; i++) {
            for (int j = 0; j < matrixDimensions; j++) {
                matrix1[i][j] = 10;
                matrix2[i][j] = 3;
                expectedMatrix[i][j] = 300;
            }
        }
        MatrixMultiplier multiplier = new MatrixMultiplier(matrix1, matrix2, resultMatrix);
        multiplier.setThreadAmount(4);
        long timeBefore = System.currentTimeMillis();

        multiplier.start();
        multiplier.join();

        System.out.println("Time spent with 4 threads: " + (System.currentTimeMillis() - timeBefore));

        multiplier = new MatrixMultiplier(matrix1, matrix2, resultMatrix);
        multiplier.setThreadAmount(1);
        timeBefore = System.currentTimeMillis();

        multiplier.start();
        multiplier.join();

        System.out.println("Time spent with 1 thread: " + (System.currentTimeMillis() - timeBefore));
    }
}