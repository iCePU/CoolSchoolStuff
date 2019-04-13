/* Dining Philosophers problem
   By: Marco Flores and Lewis Johnson
   Compiler command: gcc -o dphil dphil.c -lpthread
*/



#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

typedef struct{
     int numOfPhilo;
     int tid;					//id of the thread
	int isEating;				//status of thread
	int totalCalories;			//number of times eaten
	pthread_mutex_t chopSticks[];	//locks
} threadData;

void *eat(void *data);

int main(int argc, char* argv[]){


     if(argc != 3){
          printf("Usage: ./dphil 10 7\n");
          return -1;
     }

     int numOfPhilosophers = atoi(argv[2]);

     pthread_t dphils[numOfPhilosophers];
     pthread_mutex_t chopsticks[numOfPhilosophers];
     pthread_attr_t attr[numOfPhilosophers];
	threadData yo[numOfPhilosophers];
     for (int i = 0; i < numOfPhilosophers; i++){
	     pthread_attr_init(&(attr[i]));
	}

     for (int i = 0; i < numOfPhilosophers; i++){
          int result = pthread_mutex_init(&chopsticks[i], NULL);
		printf("%d\n",result);
     }

	for (int i = 0; i < numOfPhilosophers; i++){
		threadData td;
		td.tid = i;
          td.numOfPhilo = numOfPhilosophers;
		for(int x=0;x<numOfPhilosophers;x++){
			td.chopSticks[x] = chopsticks[x];
		}
		td.totalCalories = 0;
		yo[i] = td;
     }

     for (int i = 0; i < numOfPhilosophers; i++){
		void * su =  &yo[i];
          pthread_create(&(dphils[i]), &attr[i], eat, su);
     }
	sleep(10);
	for(int x = 0; x<numOfPhilosophers; x++){
		int result = pthread_join(dphils[x], NULL);
		printf("%d\n",result);
	}

	for(int x = 0; x<numOfPhilosophers; x++){
		//printf("Got here\n");

		pthread_mutex_destroy(&chopsticks[x]);
	}

	exit(0);
}

void *eat(void *sdata){
	threadData *data = sdata;

	int isEven;
	if(data->tid%2==0){
		isEven=1;
	}else{
		isEven=-1;
	}

	while(data->totalCalories < 1){
		printf("%d\n",data->totalCalories);
		/*
		int left  = pthread_mutex_lock(&(data->chopSticks[(data->tid)+isEven]));
		int right = pthread_mutex_lock(&(data->chopSticks[(data->tid)-isEven]));
		*/
		int left = 1;
		int right = 1;
		int index = data->tid;
		int nIndex = (index + isEven)%(data->numOfPhilo);

     	//pthread_mutex_unlock(&(data->chopSticks[nIndex]));
     	//pthread_mutex_unlock(&(data->chopSticks[index]));
		left  = pthread_mutex_trylock(&(data->chopSticks[index]));
		right = pthread_mutex_trylock(&(data->chopSticks[nIndex]));
		if(right == 22){
			right = 0;
		}
		if(left == 22){
			left = 0;
		}
		printf("%s %s\n",strerror(left),strerror(right) );
     	//printf("Left: %d; Right: %d; nIndex: %d; index: %d\n", left, right, nIndex, index);

     	if(left == 0 && right == 0){
			//pthread_mutex_lock(&(data->chopSticks[index]));
			//pthread_mutex_lock(&(data->chopSticks[nIndex]));
			data->totalCalories++;
          	//thread_mutex_unlock(&(data->chopSticks[nIndex]));
          	//pthread_mutex_unlock(&(data->chopSticks[index]));
			printf("Philosopher %d is eating\n", data->tid);
		}else{
			printf("Philosopher %d is thinking\n", data->tid);
		}
		if(left==0){
			pthread_mutex_unlock(&(data->chopSticks[index]));
		}
		if(right==0){
			pthread_mutex_unlock(&(data->chopSticks[nIndex]));
		}
	}
	pthread_exit(0);
}
