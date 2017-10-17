import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		Network net = new Network(4,1,3,4);
		double[] output = net.calculate(0.2, 0.9, 0.3, 0.4);
		System.out.println(Arrays.toString(output));
	}
}