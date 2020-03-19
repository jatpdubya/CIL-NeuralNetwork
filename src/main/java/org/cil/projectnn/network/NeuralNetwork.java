package org.cil.projectnn.network;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NeuralNetwork {

    private final Matrix[] weights, bias;

    public NeuralNetwork(int input, int hidden, int output) {
        this.weights = new Matrix[2];
        this.bias = new Matrix[2];

        this.weights[0] = new Matrix(hidden, input);
        this.weights[1] = new Matrix(output, hidden);
        for (Matrix m : weights) {
            m.randomize();
        }
        this.bias[0] = new Matrix(hidden, 1);
        this.bias[1] = new Matrix(output, 1);
        this.bias[0].randomize();
        this.bias[1].randomize();
    }

    public double[] guess(double[] inputs) {
        Matrix input = Matrix.toMatrix(inputs);
        Matrix hidden = Matrix.multiply(this.weights[0], input);
        hidden.add(bias[0]);
        activate(hidden);
        Matrix output = Matrix.multiply(this.weights[1], hidden);
        output.add(bias[1]);
        activate(output);

        return Matrix.toArray(output);
    }


    public void train(double[] inputs, double[] targets) {
        // Feed the inputs forward to calculate the errors
        Matrix guess = Matrix.toMatrix(this.guess(inputs));
        Matrix target = Matrix.toMatrix(targets);
        Matrix outputError = Matrix.subtract(target, guess);


        Matrix tweights = this.weights[1].transpose();


        // Calculate the error of the hidden layer nodes
        Matrix hiddenErrors = Matrix.multiply(tweights, outputError);

        Matrix.print(hiddenErrors);


    }

    public Matrix activate(Matrix sums) {
        for (int i =0; i < sums.getRows(); i++) {
            for (int j = 0; j < sums.getCols(); j++) {
                double val = sums.getData()[i][j];
                sums.setData(i, j, (1 / (1 + Math.pow(Math.E, -val))));
            }
        }
        return sums;
    }


    public Matrix[] getWeights() {
        return this.weights;
    }
}
