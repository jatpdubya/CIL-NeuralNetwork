package org.cil.projectnn;

import org.cil.projectnn.network.Matrix;
import org.cil.projectnn.network.NeuralNetwork;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class ProjectnnApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProjectnnApplication.class, args);

		NeuralNetwork nn = new NeuralNetwork(5, 3, 2);
		double[] inputs = {1.0, 0.0, 1.0, 1.0, 0.0};
		double[] targets = {1.0, 0.0};

//		double[] inputs = new double[121];
//		for (int i = 0; i < inputs.length; i++) {
//			inputs[i] = Math.floor(Math.random() * 2);
//		}
//		double[] testGuess = nn.guess(inputs);
		nn.train(inputs, targets);
//		Matrix.print(Matrix.toMatrix(testGuess));





//		int trainingSet = 5000;
//		int testSet = 1000;
////		Point[] points = new Point[5000];
//		int count = 0;
//
//		// Train on a set number of points
//		for (int i = 0; i < trainingSet; i++) {
//			Point point = new Point();
//			neuron.train(point.getInputs(), point.getExpected());
//		}
//
//		// Use the trained weights on 1000 points, adding to the count every time the result is as expected
//		for (int i = 0; i < testSet; i++) {
//			Point point = new Point();
//			if (neuron.guess(point.getInputs()) == point.getExpected()) {
//				count++;
//			}
//			System.out.println(Arrays.toString(point.getInputs()) + " " +
//							   point.getExpected());
//		}
//
//		// Print out the final weights that were found in the training
//		System.out.println(Arrays.toString(neuron.getWeights()));
//		// Print how many points were trained, and what percent of the test set resulted as expected
//		System.out.println("After training with " + trainingSet +
//						    " points, the network guessed the correct output " +
//							((float) count / testSet) * 100 + "% of the time");
	}
}
