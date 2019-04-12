#include <math.h>
#include <stdio.h>
#include <stdlib.h>

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

double combinationWithRepition(int n, int q){
	double top = fF(n + q-1);
	double bottom = fF(q) * fF(n-1);
	top = top/bottom;
	return top;
}

double averageValue(int solid[]){
	double start = 0.0;
	for(int x = 0; x<70;x++){
		start = start + solid[x];
	}
	return start/70;
}

double variance(int solid[], double aveVal){
	double rollingSum = 0.0;
	for(int x = 0; x<70;x++){
		rollingSum = rollingSum + pow((aveVal-solid[x]),2);
	}

	rollingSum = rollingSum/70;
	return rollingSum;
}

int systems(int oscA, int oscB, int energy){
	double solidA[energy];
	double solidB[energy];
	double system[energy];
	double state = combinationWithRepition((oscA+oscB),energy);
	for(int x =0; x<= energy; x++){
		solidA[x] = combinationWithRepition(oscA,x);
		solidB[x] = combinationWithRepition(oscB,energy-x);
		system[x]= (solidA[x]*solidB[x])/state;
		//printf("%f %f %f %d %d\n",system[x],solidA[x],solidB[x],x,energy-x);
	}

	double rando;
	int initA = energy;
	int initB = energy - initA;
	for(int x =0; x<100;x++){
		rando = (double) rand()/(RAND_MAX);
		double sum = 0;
		int index = 0;
		while(sum<rando){
			sum = sum + system[index];
			index++;
		}

		//printf("%d %d\n", initA, initB);
		if (index>initA&&initB!=0){
			initA = initA + 1;
		}else if(index < initA && initA != 0){
			initA = initA -1;
		}
		initB = energy - initA;
	}
	//double ave = averageValue(states);
	//double var = variance(states,ave);
	//printf("%d %d %d %f %f\n",oscA,oscB,energy,var,ave);
	return initA;
}

//this is for a linear assortment of solids each with 2 oscillators
int main(int argc, char **argv){
	//systems(5,5,10);
	int amount = 10;
	int solids[10] = {}; //energy in the solids

	solids[0] = 50;
	solids[9] = 50;
	FILE * fp;
	fp = fopen("test.txt","w+");

	int timy = 10;
	for(int t = 0; t < timy; t++){
		if(t%2==0){
			//printf("%d",t);
			#pragma omp for
			for(int x=0;x<amount;x=x+2){
				int total = solids[x]+solids[x+1];
				solids[x] = systems(20,20,total);
				solids[x+1] = total - solids[x];
			}
		}else{
			#pragma omp for
			for(int x=1;x<amount-1;x=x+2){
				//printf("%d ",x);
				int total = solids[x]+solids[x+1];
				solids[x] = systems(20,20,total);
				solids[x+1] = total - solids[x];
			}
		}
		if(t%1==0){
			fprintf(fp,"%d ",t);
			for(int x = 0; x<amount;x++){
				fprintf(fp,"%d ", solids[x]);
			}
			fprintf(fp,"\n");
		}
	}
	//for(int x = 1;x<50;x++){
	//	systems(x,10,30);
	//}
	return 0;
}
