import java.util.Arrays;
import java.io.*;

public class Complexity {
	public static void main(String[] args) throws Exception{
		PrintStream ps = new PrintStream(new File("D:/Documents/COMP1201/Tutorial 1/ColouringHeadingData.txt"));
		PrintStream ps1 = new PrintStream(new File("D:/Documents/COMP1201/Tutorial 1/ColouringData.txt"));
		for (int i = 1; i <= 11; i++) {
			ps.println(i);
			System.out.println("Nodes: " + i);
			
			double[] times = new double[5];			
			for (int j = 0; j < 5; j++) {
				long time_prev = System.nanoTime();
				
				Graph graph = new Graph(i, 0.5);
				Colouring colouring = graph.bestColouring(3);
				graph.show(colouring);
							
				double time = (System.nanoTime()-time_prev)/1000000000.0;
				times[j] = time;
			}
			Arrays.sort(times);
			ps1.println(times[2]);
			System.out.println(times[2]);
		}
		System.out.println("Done. Close graph window");
	}
}