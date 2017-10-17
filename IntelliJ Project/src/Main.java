import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		Network net = new Network(2,2,1);
		double[] output = net.calculate(0.9, 0.9);
		System.out.println(Arrays.toString(output));
	}
}