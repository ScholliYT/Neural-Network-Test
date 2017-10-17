import java.util.List;

public final class WeightedSumFunction implements InputSummingFunction {
	@Override
	public double collectOutput(List<Connection> _inputConnections) {
		double weightedSum = 0d;
		for(Connection connection : _inputConnections) {
			weightedSum += connection.getWeightedInput();
		}
		return weightedSum;
	}
}