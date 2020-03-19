package org.cil.projectnn.network;

public class Neuron {

    private final float[] weights;
    private final float learningRate;

    public Neuron(int size, float learningRate) {
        this.learningRate = learningRate;
        this.weights = this.initializeWeights(size);
    }

    // Initialize an array of random weights based on the given size.
    private float[] initializeWeights(int size) {
        float[] weights = new float[size];
        for (int i = 0; i < size; i++) {
            weights[i] = (float) (Math.random() - Math.random());
        }
        return weights;
    }
    // Train by first making a guess then adjusting the weights based on the error given the expected outcome
    public void train(float[] inputs, int target) {
        int guess = guess(inputs);
        int error = target - guess;

        // Tune the weights based on the error and the learning rate
        for (int i = 0; i < this.weights.length; i++) {
            this.weights[i] += error * inputs[i] * this.learningRate;
        }
    }
    // Guess
    public int guess(float[] inputs) {
        float sum = 0;
        for (int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * this.weights[i];
        }
        // Guess 1 if the sum is above 0, -1 if it is equal or below
        return activate(sum);
    }

    public int activate(float sum) {
        if (sum > 0) return 1;
        else return -1;
    }

    public float[] getWeights() {
        return this.weights;
    }
}

