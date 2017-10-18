package fullyconnectednetwork;

import trianset.TrainSet;

import java.util.Arrays;

public class Main {
    /*
	public static void main(String[] args) {
		fullyconnectednetwork.Network net = new fullyconnectednetwork.Network(4,1,3,4);
		double[] input = new double[] {0.1,0.5,0.2,0.9};
		double[] target = new double[] {0,1,0,0};

		for(int i = 0; i < 1000000; i++) {
		    net.train(input, target, 0.5);
        }
        double[] output = net.calculate(input);
		System.out.println(">>>> " + Arrays.toString(output));
	}
	*/

    /*
    public static void main(String[] args) {
        Network net = new Network(2,2,1);
        double[] input = new double[] {0,0};
        double[] target = new double[] {0};

        for(int i = 0; i < 1000; i++) {
            net.train(input, target, 0.5);
        }
        System.out.println(">>>> " + Arrays.toString(net.calculate(input)));
    }
    */

    /*
    public static void main(String[] args) {
        Network net = new Network(2,2,1);

        TrainSet set = new TrainSet(2,1);
        set.addData(new double[]{0,0}, new double[]{0});
        set.addData(new double[]{1,0}, new double[]{1});
        set.addData(new double[]{0,1}, new double[]{1});
        set.addData(new double[]{1,1}, new double[]{0});

        System.out.println();
        for(int i = 0; i < set.getDataLength(); i++) {
            System.out.println("Before: " + Arrays.toString(net.calculate(set.getInput(i))));
        }
        System.out.println();

        for(int l = 0; l < 100; l++) {
            net.train(set, 100, set.getDataLength());
            System.out.println();
            for(int i = 0; i < set.getDataLength(); i++) {
                System.out.println("While: " + Arrays.toString(net.calculate(set.getInput(i))));
            }
            System.out.println();
        }

        for(int i = 0; i < set.getDataLength(); i++) {
            System.out.println("After: " + Arrays.toString(net.calculate(set.getInput(i))));
        }


        set.addData(new double[]{0,1}, new double[]{0});
        for(int l = 0; l < 10; l++) {
            net.train(set, 100, set.getDataLength());
            System.out.println();
            for(int i = 0; i < set.getDataLength(); i++) {
                System.out.println("While: " + Arrays.toString(net.calculate(set.getInput(i))));
            }
            System.out.println();
        }
        System.out.println("New: " + Arrays.toString(net.calculate(set.getInput(4))));


    }
    */

    public static void main(String[] args) {
        Network net = new Network(3,16,3);

        TrainSet set = new TrainSet(3,3);
        set.addData(new double[]{0,0,0}, new double[]{0,0,1});
        set.addData(new double[]{0,0,1}, new double[]{0,1,0});
        set.addData(new double[]{0,1,0}, new double[]{0,1,1});
        set.addData(new double[]{0,1,1}, new double[]{1,0,0});
        set.addData(new double[]{1,0,0}, new double[]{1,0,1});
        set.addData(new double[]{1,0,1}, new double[]{1,1,0});
        set.addData(new double[]{1,1,0}, new double[]{1,1,1});
        set.addData(new double[]{1,1,1}, new double[]{0,0,0});



        System.out.println();
        for(int i = 0; i < set.getDataLength(); i++) {
            System.out.println("Before: " + Arrays.toString(net.calculate(set.getInput(i))));
        }
        System.out.println();

        for(int l = 0; l < 100; l++) {
            net.train(set, 10000, set.getDataLength());
            System.out.println();
            for(int i = 0; i < set.getDataLength(); i++) {
                System.out.println("While: " + Arrays.toString(net.calculate(set.getInput(i))));
            }
            System.out.println();
        }

        for(int i = 0; i < set.getDataLength(); i++) {
            System.out.println("After: " + Arrays.toString(net.calculate(set.getInput(i))));
        }

        System.out.println("New: " + Arrays.toString(net.calculate(0,1,1)));
    }
}