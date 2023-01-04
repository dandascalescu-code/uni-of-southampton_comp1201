import java.util.Arrays;
import java.io.*;

//modified
public class TestSortModified {
    public static void main(String[] args) throws Exception{
		PrintStream ps = new PrintStream(new File("D:/Documents/COMP1201/Tutorial 1/HeadingData.txt"));
		PrintStream ps1 = new PrintStream(new File("D:/Documents/COMP1201/Tutorial 1/InsertionData.txt"));
		PrintStream ps2 = new PrintStream(new File("D:/Documents/COMP1201/Tutorial 1/ShellData.txt"));
		PrintStream ps3 = new PrintStream(new File("D:/Documents/COMP1201/Tutorial 1/QuickData.txt"));
		for (int i = 1000; i <= 100000; i += 1000) {
			ps.println(i);
			System.out.println(i + " elements");
			int N = i;
			double[] data = new double[N];
			for (int j = 0; j < N; j++) {
				data[j] = Math.random();
			}
			
			double totalTime = 0;			
			for (int k = 0; k < 3; k++) {
				double[] data1 = (double[])data.clone();
				long time_prev = System.nanoTime();
				InsertionSort(data1);
				double time = (System.nanoTime()-time_prev)/1000000000.0;
				totalTime += time;
			}
			ps1.println(totalTime/3);
			
			totalTime = 0;
			for (int k = 0; k < 3; k++) {
				double[] data2 = (double[])data.clone();
				long time_prev = System.nanoTime();
				ShellSort(data2);
				double time = (System.nanoTime()-time_prev)/1000000000.0;
				totalTime += time;
			}
			ps2.println(totalTime/3);
			
			totalTime = 0;
			for (int k = 0; k < 3; k++) {
				double[] data3 = (double[])data.clone();
				long time_prev = System.nanoTime();
				Arrays.sort(data3);
				double time = (System.nanoTime()-time_prev)/1000000000.0;
				totalTime += time;
			}
			ps3.println(totalTime/3);
		}
		/**	
		int N = 1000;
		double[] data = new double[N];
		for (int i = 0; i < N; i++)
			data[i] = Math.random();
		double[] data1 = (double[])data.clone();
		double[] data2 = (double[])data.clone();
		double[] data3 = (double[])data.clone();
		long time_prev = System.nanoTime();
		InsertionSort(data1);
		double time = (System.nanoTime()-time_prev)/1000000000.0;
		System.out.println("Insertion Sort\nTime= " + time);
		time_prev = System.nanoTime();
		ShellSort(data2);
		time = (System.nanoTime()-time_prev)/1000000000.0;
		System.out.println("Shell Sort\nTime= " + time);
		time_prev = System.nanoTime();
		Arrays.sort(data3);
		time = (System.nanoTime()-time_prev)/1000000000.0;
		System.out.println("Quick Sort\nTime= " + time);
		System.out.println("\tPresorted\tInsertion\t\t Shell\t\t Quick");
		for (int i=0; i<data.length; i++)
			System.out.println(data[i] + " " + data1[i] + " " + data2[i] + " " + data3[i]);
		**/
    }

    // I changed this as the previous code was terribly inefficient

    public static void InsertionSort(double[] data) {
		for (int i = 1; i < data.length; i++) {
			if (data[i]<data[i-1]) {
			double temp = data[i];
			int j = i;
			do {
				data[j] = data[j-1];
				j--;
			} while (j>0 && data[j-1] > temp);
			data[j] = temp;
			}
		}
    }

    public  static void ShellSort(double[] a) {
		int increment = a.length / 3 + 1;
		
		// Sort by insertion sort at diminishing increments.
		while ( increment > 1 )
			{
			for ( int start = 0; start < increment; start++ )
				insertSort(a, start, increment );
			 
			increment = increment / 3 + 1;
			} 

		// Do a final pass with an increment of 1.
		// (This has to be outside the previous loop because
		// the formula above for calculating the next
		// increment will keep generating 1 repeatedly.)
		insertSort(a, 0, 1 );
    }
	
	
    public static void insertSort(double[] a, int start, int increment ) {
		int j, k;
		double temp;

		for ( int i = start + increment; i < a.length; i += increment )
			{
			j = i;
			k = j - increment;
			if ( a[j] < a[k] )
				{
				// Shift all previous entries down by the current
				// increment until the proper place is found.
				temp = a[j];
				do
					{
					a[j] = a[k];
					j = k;
					k = j - increment;
					} while ( j != start && a[k] > temp );
				a[j] = temp;
				}
			}
    }
	
	
    public static double median(double[] a) {
		Arrays.sort(a);
		return a[a.length/2];
    }
}