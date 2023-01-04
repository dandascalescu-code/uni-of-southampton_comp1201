import java.io.*;
import java.util.*;

public class MySort
{
    public static void main(String[] args) throws Exception {
		PrintStream ps = new PrintStream(new File("D:/Documents/COMP1201/Tutorial 1/MySortHeadingData.txt"));
		PrintStream ps1 = new PrintStream(new File("D:/Documents/COMP1201/Tutorial 1/MySortData.txt"));
		PrintStream ps2 = new PrintStream(new File("D:/Documents/COMP1201/Tutorial 1/MySortALData.txt"));
		for (int i = 2000000; i <= 10000000; i += 1000000) {
			ps.println(i);
			System.out.println(i + " elements");
			
			int N = i;
			double[] data = new double[N];
			List<Double> dataList = new ArrayList<Double>(N);
			for (int j=0; j<N; j++) {
				data[j] = Math.random();
				dataList.add(Math.random());
			}		
			
			double[] times = new double[5];	
			for (int k = 0; k < 5; k++) {
				long time_prev = System.nanoTime();
				Arrays.sort(data);
				double time = (System.nanoTime()-time_prev)/1000000000.0;
				times[k] += time;
			}
			ps1.println(times[2]);
			
			times = new double[5];	
			for (int k = 0; k < 5; k++) {
				long time_prev = System.nanoTime();
				Collections.sort(dataList);
				double time = (System.nanoTime()-time_prev)/1000000000.0;
				times[k] += time;
			}
			ps2.println(times[2]);
		}
		
    }
}