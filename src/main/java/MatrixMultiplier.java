import java.util.ArrayList;

public class MatrixMultiplier extends Thread {
    private Integer[][] a;
    private Integer[][] b;
    private Integer[][] c;
    private int threadAmount;

    public MatrixMultiplier(Integer[][] a, Integer[][] b, Integer[][] c) {
        if (a[0].length == b.length) {
            this.a = a;
            this.b = b;
            this.c = c;
            threadAmount = 1;
        }
        else {
            throw new IllegalArgumentException("Dimensions of matrices is not suitable for multiplications");
        }
    }

    public void setThreadAmount(int threadAmount) {
        if (threadAmount >= 1) {
            this.threadAmount = threadAmount;
        }
        else {
            throw new IllegalArgumentException("Thread amount cannot be less than 1");
        }
    }

    public void run() {
        multiply();
    }

    public void multiply() {
        int timesMultiplying = a.length / threadAmount;
        ArrayList<MatrixMultiplierThread> matrixMultiplierThreads = new ArrayList<MatrixMultiplierThread>();
        for (int i = 0; i < threadAmount; i++) {
            MatrixMultiplierThread matrixMultiplierThread =
                    new MatrixMultiplierThread(a, b, c,
                            timesMultiplying * i,
                            i == threadAmount - 1
                                    ? timesMultiplying + a.length % threadAmount
                                    : timesMultiplying);
            matrixMultiplierThread.start();
            matrixMultiplierThreads.add(matrixMultiplierThread);
        }
        for (MatrixMultiplierThread matrixMultiplierThread : matrixMultiplierThreads) {
            try {
                matrixMultiplierThread.join();
            }
            catch (InterruptedException ex) {
                System.err.println("MatrixMultiplier thread was interrupted");
            }
        }
    }




}
