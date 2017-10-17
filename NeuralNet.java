import java.util.List;
public class NeuralNet {
	private String id;
	private NeuralNetLayer inputLayer;
	private List<NeuralNetLayer> hiddenLayers;
	private NeuralNetLayer outputLayer;

	public NeuralNet(String _id, NeuralNetLayer _inputLayer, List<NeuralNetLayer> _hiddenLayers, NeuralNetLayer _outputLayer) {
		id = _id;
		inputLayer = _inputLayer;
		hiddenLayers = _hiddenLayers;
		outputLayer = _outputLayer;
	}

	public NeuralNet(String _id, NeuralNetLayer _inputLayer, NeuralNetLayer _outputLayer) {
		id = _id;
		inputLayer = _inputLayer;
		outputLayer = _outputLayer;
	}
}