package org.cil.projectnn.network;
import java.util.Arrays;
import java.util.stream.Stream;

public class Matrix {

    private final int rows;
    private final int cols;
    private final double[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public double[][] getData() {
        return this.data;
    }

    public void setData(int row, int col, double val) {
        this.data[row][col] = val;
    }

    public void randomize() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.data[i][j] = (Math.random() * 2 - 1);
            }
        }
    }
    public static Matrix multiplyElements(Matrix a, Matrix b) {
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                a.data[i][j] *= b.data[i][j];
            }
        }
        return a;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        if (a.cols != b.rows) {
            System.out.println("Cols of matrix A must match " +
                               "Rows of Matrix B");
            return null;
        }

        Matrix result = new Matrix(a.rows, b.cols);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                double sum = 0;
                for (int k = 0; k < a.cols; k++) {
                    sum += a.data[i][k] * b.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }

        return result;
    }

    public static Matrix multiply(Matrix a, double[] b) {
        Matrix bm = new Matrix(b.length, 1);
        for (int i = 0; i < b.length; i++) {
            bm.setData(i, 0, b[i]);
        }
        return multiply(a, bm);
    }

    public void multiply(double n) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.data[i][j] *= n;
            }
        }
    }

    public void add(double n) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.data[i][j] += n;
            }
        }
    }

    public void add(Matrix m) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.data[i][j] += m.data[i][j];
            }
        }
    }

    public void subtract(double n) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.data[i][j] -= n;
            }
        }
    }

    public void subtract(Matrix m) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.data[i][j] += m.data[i][j];
            }
        }
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                a.data[i][j] -= b.data[i][j];
            }
        }
        return a;
    }

    public Matrix transpose() {
        Matrix temp = new Matrix(this.cols, this.rows);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                temp.data[j][i] = this.data[i][j];
            }
        }

        return temp;
    }

    public static Matrix toMatrix(double[] arr) {
        Matrix result = new Matrix(arr.length, 1);
        for (int i = 0; i < arr.length; i++) {
            result.setData(i, 0, arr[i]);
        }
        return result;
    }

    public static double[] toArray(Matrix m) {
        return Stream.of(m.getData())
                .flatMapToDouble(Arrays::stream)
                .toArray();
    }

    public static Matrix derivative(Matrix m) {
        Matrix result = new Matrix(m.rows, m.cols);
        for (int i = 0; i < result.rows; i ++) {
            for (int j = 0; j < result.cols; j++) {
                result.data[i][j] = m.data[i][j] * (1 - m.data[i][j]);
            }
        }
        return result;
    }

    public static void print(Matrix m) {
        System.out.println("__________");
        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                System.out.print(m.data[i][j] + " ");
            }
            System.out.println("");
        }
    }
}