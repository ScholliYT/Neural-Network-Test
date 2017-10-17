import java.util.List;
import java.util.ArrayList;

public class NeuralNetLayer {
	private String id;
	protected List<Neuron> neurons;

	public NeuralNetLayer(Stirng _id) {
		id = _id;
		neurons = new ArrayList<>();
	}

	public NeuralNetLayer(Stirng _id, List<Neuron> _neurons) {
		id = _id;
		neurons = _neurons;
	}
}