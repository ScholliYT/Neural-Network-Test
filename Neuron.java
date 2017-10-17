import java.util.List;
import java.util.ArrayList;

public class Neuron {
	private String id;
	protected List<Connection> inputConnections;
	protected List<Connection> outputConnections;
	protected InputSummingFunction inputSummingFunction;
	protected ActivationFunction activationFunction;

	public Neuron() {
		inputConnections = new ArrayList<>();
		outputConnections = new ArrayList<>();
	}

	public double calculateOutput() {
		double totalInput = inputSummingFunction.getOutput(inputConnections);
		return activationFunction.getOutput(totalInput);
	}
}