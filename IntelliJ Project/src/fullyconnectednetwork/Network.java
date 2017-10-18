package fullyconnectednetwork;

import trianset.TrainSet;

public class Network {
    public final int[] NETWORK_LAYER_SIZES;
    public final int INPUT_SIZE;
    public final int OUTPUT_SIZE;
    public final int NETWORK_SIZE;

    //LAYER:NEURON
    private double[][] output;
    //LAYER:NEURON:PREVNEURON
    private double[][][] weights;
    //LAYER:NEURON
    private double[][] bias;

    private double[][] error_signal;
    private double[][] output_derivates;


    public Network(int... _NETWORK_LAYER_SIZES) {
        NETWORK_LAYER_SIZES = _NETWORK_LAYER_SIZES;
        INPUT_SIZE = NETWORK_LAYER_SIZES[0];
        NETWORK_SIZE = NETWORK_LAYER_SIZES.length;
        OUTPUT_SIZE = NETWORK_LAYER_SIZES[NETWORK_LAYER_SIZES.length - 1];

        output = new double[NETWORK_SIZE][];
        weights = new double[NETWORK_SIZE][][];
        bias = new double[NETWORK_SIZE][];
        error_signal = new double[NETWORK_SIZE][];
        output_derivates = new double[NETWORK_SIZE][];


        for (int i = 0; i < NETWORK_SIZE; i++) {
            output[i] = new double[NETWORK_LAYER_SIZES[i]];
            error_signal[i] = new double[NETWORK_LAYER_SIZES[i]];
            output_derivates[i] = new double[NETWORK_LAYER_SIZES[i]];

            bias[i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i], 0.3, 0.7);

            if (i > 0) { // not for input layer
                weights[i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i], NETWORK_LAYER_SIZES[i - 1], -0.3, 0.5);
            }
        }

    }

    public double[] calculate(double... _input) {
        if (_input.length != INPUT_SIZE) { //correct amount of input data
            return null;
        }
        output[0] = _input; //set inputlayer
        for (int layer = 1; layer < NETWORK_SIZE; layer++) {
            for (int neuron = 0; neuron < NETWORK_LAYER_SIZES[layer]; neuron++) {
                double sum = bias[layer][neuron];
                for (int prevNeuron = 0; prevNeuron < NETWORK_LAYER_SIZES[layer - 1]; prevNeuron++) {
                    sum += output[layer - 1][prevNeuron] * weights[layer][neuron][prevNeuron]; // O * W
                }
                output[layer][neuron] = sigmoid(sum); //apply activation function
                output_derivates[layer][neuron] = output[layer][neuron] * (1 - output[layer][neuron]); // Oj * (1 - Oj)
            }
        }
        return output[NETWORK_SIZE - 1];
    }

    public void train(TrainSet _set, int _loops, int _batch_size) {
        if(_set.INPUT_SIZE != INPUT_SIZE || _set.OUTPUT_SIZE != OUTPUT_SIZE) {
            return;
        }
        for(int i = 0 ; i < _loops; i++) {
            TrainSet batch = _set.extractBatch(_batch_size);
            for(int b = 0; b < _batch_size; b++) {
                train(batch.getInput(b), batch.getOutput(b), 0.3);
            }
        }
    }

    public void train(double[] _input, double[] _target, double _eta) {
        if (_input.length != INPUT_SIZE || _target.length != OUTPUT_SIZE) {
            return;
        }
        calculate(_input);
        backpropError(_target);
        updateWeights(_eta);
    }

    public void backpropError(double[] _target) {
        for (int neuron = 0; neuron < NETWORK_LAYER_SIZES[NETWORK_SIZE - 1]; neuron++) {
            error_signal[NETWORK_SIZE - 1][neuron] = (output[NETWORK_SIZE - 1][neuron] - _target[neuron]) * output_derivates[NETWORK_SIZE - 1][neuron]; // (Oj - Tj) * Oj * (1 - Oj)
        }
        for (int layer = NETWORK_SIZE - 2; layer > 0; layer--) {
            for (int neuron = 0; neuron < NETWORK_LAYER_SIZES[layer]; neuron++) {
                double sum = 0d;
                for (int nextNeuron = 0; nextNeuron < NETWORK_LAYER_SIZES[layer + 1]; nextNeuron++) {
                    sum += weights[layer + 1][nextNeuron][neuron] * error_signal[layer + 1][nextNeuron];
                }
                error_signal[layer][neuron] = sum * output_derivates[layer][neuron];
            }
        }
    }

    public void updateWeights(double _eta) {
        for (int layer = 1; layer < NETWORK_SIZE; layer++) {
            for (int neuron = 0; neuron < NETWORK_LAYER_SIZES[layer]; neuron++) {

                double delta = -_eta * error_signal[layer][neuron];
                bias[layer][neuron] += delta;

                for (int prevNeuron = 0; prevNeuron < NETWORK_LAYER_SIZES[layer - 1]; prevNeuron++) {
                    // weights[layer][neuron][prevNeuron]
                    weights[layer][neuron][prevNeuron] += output[layer - 1][prevNeuron] * delta;
                }

            }
        }
    }


    private double sigmoid(double x) {
        return 1d / (1 + Math.exp(-x));
    }
}