package org.cil.projectnn.network;

public class NeuralNetwork {

    private final Matrix[] weights, bias;
    private final double lr;

    public NeuralNetwork(String input, String hidden, String output) {
        this.lr = .25;
        this.weights = new Matrix[2];
        this.bias = new Matrix[2];

        this.weights[0] = new Matrix( Integer.parseInt(hidden), Integer.parseInt(input));
        this.weights[1] = new Matrix( Integer.parseInt(output), Integer.parseInt(hidden));
        for (Matrix m : weights) {
            m.randomize();
        }
        this.bias[0] = new Matrix(Integer.parseInt(hidden), 1);
        this.bias[1] = new Matrix(Integer.parseInt(output), 1);
        this.bias[0].randomize();
        this.bias[1].randomize();
    }

    public void train(double[] inputs, double[] targets) {
        // Convert the targets to a matrix opject for later
        Matrix target = Matrix.toMatrix(targets);

        // Feed the inputs into the network using the first weights matrix
        Matrix input = Matrix.toMatrix(inputs);
        Matrix hidden = Matrix.multiply(this.weights[0], input);
        hidden.add(this.bias[0]);
        activate(hidden);

        // Feed the activated hidden layer outputs forward using the second weights matrix
        Matrix output = Matrix.multiply(this.weights[1], hidden);
        output.add(this.bias[1]);
        activate(output);

        // Calculate the error of the guessed output
        Matrix outputErrors = Matrix.subtract(target, output);

        // Calculate the error of the hidden layer nodes
        Matrix hiddenErrors = Matrix.multiply(this.weights[1].transpose(), outputErrors);

        // Tune the weights in the last layer
        Matrix gradient = Matrix.derivative(output);
        Matrix.multiplyElements(gradient, outputErrors);
        gradient.multiply(this.lr);
        Matrix weight1Deltas = Matrix.multiply(gradient, hidden.transpose());
        this.weights[1].add(weight1Deltas);


        // tune the weights in the first layer
        Matrix hiddenGradient =  Matrix.derivative(hidden);
        Matrix.multiplyElements(hiddenGradient, hiddenErrors);
        hiddenGradient.multiply(this.lr);
        Matrix weight0Deltas = Matrix.multiply(hiddenGradient, input.transpose());
        this.weights[0].add(weight0Deltas);
    }

    public double[] guess(double[] inputs) {
        Matrix input = Matrix.toMatrix(inputs);
        Matrix hidden = Matrix.multiply(this.weights[0], input);
        hidden.add(this.bias[0]);
        activate(hidden);
        Matrix output = Matrix.multiply(this.weights[1], hidden);
        output.add(this.bias[1]);
        activate(output);

        return Matrix.toArray(output);
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
