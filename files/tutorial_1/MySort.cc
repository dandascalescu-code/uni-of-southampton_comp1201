#include <iostream>
#include <fstream>
#include <iterator>
#include <vector>
#include <algorithm>
#include <iostream>
#include <fstream>
#include <stdio.h>
#include <time.h>

using namespace std;

int main(int argc, char *argv[]) {
	int N;
	clock_t t1,t2;
	ofstream headingsFile;
	headingsFile.open("D:/Documents/COMP1201/Tutorial 1/CPPHeadingData.txt");
	ofstream dataFile;
	dataFile.open("D:/Documents/COMP1201/Tutorial 1/CPPTimesData.txt");
	
	for (unsigned int i = 2000000; i <= 10000000; i += 1000000) {
		N = i;
		cout<<N<<"\n";
		headingsFile << N;
		headingsFile << "\n";
		
		vector<double> data(N);
		for(unsigned int j=0; j<N; j++) {
			data[j] = rand()/(RAND_MAX+1.0);
		}
		
		vector<float> times(5);
		for(unsigned int k=0; k<5; k++) {
			t1 = clock();
			sort(data.begin(), data.end());
			t2 = clock();
			float diff ((float)t2-(float)t1);
			float seconds = diff / CLOCKS_PER_SEC;
			times[k] = seconds;			
		}
		float time = times[2];
		
		dataFile << time;
		dataFile << "\n";
	}
	
	headingsFile.close();
	dataFile.close();	
	
	cout<<N<<endl;
}