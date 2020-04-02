public class MatrixMultiplierThread extends Thread {
    private Integer[][] a;
    private Integer[][] b;
    private Integer[][] c;
    private int startIndex;
    private int timesMultiplying;

    public MatrixMultiplierThread(Integer[][] a, Integer[][] b, Integer[][] c, int startIndex, int timesMultiplying) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.startIndex = startIndex;
        this.timesMultiplying = timesMultiplying;
    }

    protected void multiply() {
        for (int i = startIndex; i < startIndex + timesMultiplying; i++) {
            for (int j = 0; j < b[0].length; j++) {
                c[i][j] = 0;
                for (int k = 0; k < a[0].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        /*
        for (int i = startIndex; i < startIndex + timesMultiplying; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    c[i][j] = a[i][k] * b[k][j];
                }
            }
        }*/
    }

    public void run() {
        multiply();
    }

}
