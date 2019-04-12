#include <math.h>
#include <stdio.h>
#include <stdlib.h>
/*
int * fastDist(int n, int q){

}
*/

//fast factorial
double fF(int n){
	if(n == 0){
		return 1.0;
	}

	double tmp = n * (log((double)n/M_E));
	double constant = 2.0 * M_PI * (double) n;
	tmp = pow(M_E,tmp) * pow(constant,0.5);
	return tmp;
}

//slow factorial
int fact(int n){
	int sump = 1;
	if(n ==0){
		return 1;
	}

	for(int i = 1; i<=n; i++){
		sump = sump*i;
	}

	return sump;
}

double combinationWithRepition(int n, int q){
	double top = fF(n + q-1);
	double bottom = fF(q) * fF(n-1);
	top = top/bottom;
	return top;
}

void distrubution(int n, double *dist){
	double states = pow(2,n);
	for(int i = 0;i<n;i++){
		dist[i] = (fF(n)/(fF(n-i)*fF(i)))/states;
		printf("%f ",dist[i]);
	}
	double sum = 0;
	for(int x =0;x<n;x++){
		sum = sum + dist[x];
	}
	printf("\n %f \n",sum);
}

int main(int argc, char **argv){
	double states[100];
	int posStates[100];
	distrubution(100,states);
	//populate(100,posStates);
	printf("\n");
	double tmp = fF(1);
	printf("\n %f \n",tmp);
	return 0;
}
