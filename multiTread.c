#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include "omp.h"
//fast factorial
double fF(int n){

	//base case
	if(n == 0){
		return 1.0;
	}

	//uses an approximation for n!
	double tmp = n * (log((double)n/M_E));
	double constant = 2.0 * M_PI * (double) n;
	tmp = pow(M_E,tmp) * pow(constant,0.5);
	return tmp;
}

//finds the combinations with rep of two ints with an approximation
double combinationWithRepition(int n, int q){

	double top = fF(n + q-1);
	double bottom = fF(q) * fF(n-1);
	top = top/bottom;
	return top;
}

//give the average value of an array
double averageValue(int solid[]){

	double start = 0.0;
	for(int x = 0; x<70;x++){
		start = start + solid[x];
	}

	return start/70;
}

//calculates the variance of a probability distribution
double variance(int solid[], double aveVal){

	double rollingSum = 0.0;
	for(int x = 0; x<70;x++){
		rollingSum = rollingSum + pow((aveVal-solid[x]),2);
	}

	rollingSum = rollingSum/70;
	return rollingSum;
}

// 2 Einstein solids interacting within a given timespan
int systems(int oscA, int oscB, int energy){

	//set up the arrays calculate probability distribution
	double solidA[energy];
	double solidB[energy];
	double system[energy];
	double state = combinationWithRepition((oscA+oscB),energy);
	for(int x = 0; x <= energy; x++){
		solidA[x] = combinationWithRepition(oscA,x);
		solidB[x] = combinationWithRepition(oscB,energy-x);
		system[x]= (solidA[x]*solidB[x])/state;
	}

	//distribute energy
	double rando;
	int initA = energy;
	int initB = energy - initA;
	double sum;
	int index;
	double scale;
	for(int x =0; x<100;x++){

		//find the state of your system
		rando = (double) rand()/(RAND_MAX);
		sum = 0;
		index = 0;
		scale = 0;
		for(int x = 0; x < energy;x++){
			scale = scale + system[x];
		}

		while(sum<rando){
			if(energy == 0){
				break;
			}
			sum = sum + (system[index]/scale);
			index++;
		}

		//distribute the energy based on the state above
		if((index > initA)&&(initB != 0)){
			initA = initA + 1;
		}
		else if((index < initA)&&(initA != 0)){
			initA = initA - 1;
		}

		initB = energy - initA;
	}

	return initA;
}

//this is for a linear assortment of solids each with 2 oscillators
int main(int argc, char **argv){

	//output file
	FILE * fp;
	fp = fopen("test.txt","w+");

	int amount = 10;
	int solids[10] = {}; //energy in the solids

	//initialize the array
	solids[0] = 50;
	solids[9] = 50;

	long timy = 100;
	for(long t = 0; t < timy; t++){

		//for every other element
		if(t%2==0){
			#pragma omp parallel for
				for(int x=0;x<(amount-1)/2;x++){
					int index = x*2;
					int total = solids[index]+solids[index+1];
					solids[index] = systems(20,20,total);
					solids[index+1] = total - solids[index];
				}
		//getting other half
		}else{
			#pragma omp parallel for
				for(int x=0;x<(amount-1)/2;x++){
					int index = (x*2)+1;
					int total = solids[index]+solids[index+1];
					solids[index] = systems(20,20,total);
					solids[index+1] = total - solids[index];
				}
		}

		//outputs to text file

		if(t%1==0){
			fprintf(fp,"%lu ",t);
			for(int x = 0; x<amount;x++){
				fprintf(fp,"%d ", solids[x]);
			}

			fprintf(fp,"\n");
		}

	}

	//end the program
	fclose(fp);
	return 0;
}
