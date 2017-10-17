public class Connection {
	// ========== Fields ==========
	protected Neuron fromNeuron;
	public Neuron getFromNeuron() {
		return fromNeuron;
	}

	protected Neuron toNeuron;
	public Neuron getToNeuron() {
		return toNeuron;
	}

	protected double weight;
	public double getWeight() {
		return weight;
	}
	public double setWeight(double _weight) {
		weight = _weight;
	}

	// ========== Constructors ==========
	public NeuronsConnection(Neuron _fromNeuron, Neuron _toNeuron) {
		fromNeuron = _fromNeuron;
		toNeuron = _toNeuron;
		weight = Math.random();
	}

	public NeuronsConnection(Neuron _fromNeuron, Neuron _toNeuron, double _weight) {
		fromNeuron = _fromNeuron;
		toNeuron = _toNeuron;
		weight = _weight;
	}

	// ========== Methods ==========
	public double getInput() {
		return fromNeuron.calculateOutput();
	}

	public getWeightedInput() {
		return getInput() * weight;
	}

}