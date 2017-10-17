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

	public Network(int... _NETWORK_LAYER_SIZES) {
		NETWORK_LAYER_SIZES = _NETWORK_LAYER_SIZES;
		INPUT_SIZE = NETWORK_LAYER_SIZES[0];
		NETWORK_SIZE = NETWORK_LAYER_SIZES.length;
		OUTPUT_SIZE = NETWORK_LAYER_SIZES[NETWORK_LAYER_SIZES.length - 1];

		output = new double[NETWORK_SIZE][];
		weights = new double[NETWORK_SIZE][][];
		bias = new double[NETWORK_SIZE][];

		for(int i = 0; i < NETWORK_SIZE; i++) {
			output[i] = new double[NETWORK_LAYER_SIZES[i]];
			bias[i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i], 0.3, 0.7);

			if(i > 0) { // not for input layer
				weights[i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i], NETWORK_LAYER_SIZES[i - 1], -0.3, 0.5);
			}
		}
	}

	public double[] calculate(double... _input) {
		if(_input.length != INPUT_SIZE) { //correct amount of input data
			return null;
		}
		output[0] = _input; //set inputlayer
		for(int layer = 1; layer < NETWORK_SIZE; layer++) {
			for(int neuron = 0; neuron < NETWORK_LAYER_SIZES[layer]; neuron++) {
				double sum = bias[layer][neuron];
				for(int prevNeuron = 0; prevNeuron < NETWORK_LAYER_SIZES[layer - 1]; prevNeuron++) {
					sum+=output[layer-1][prevNeuron] * weights[layer][neuron][prevNeuron]; // O * W
				}
				output[layer][neuron] = sigmoid(sum); //apply activation function
				
			}
		}
		return output[NETWORK_SIZE - 1];
	}

	private double sigmoid(double x) {
		return 1d / (1 + Math.exp(-x));
	}
}