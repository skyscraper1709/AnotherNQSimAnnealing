/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anothernqsimann;

/**
 *
 * @author Safat
 */

import java.util.*;
import java.lang.Math;
import java.util.Date;

public class AnotherNQSimAnn
{

	static int maxLength = 66;	
	
	static int currentSolution[][] = new int[maxLength][maxLength];
	static int bestSolution[][] = new int[maxLength][maxLength];
	static int workingSolution[][] = new int [maxLength][maxLength];
	
	static int workingEnergy;
	static int bestEnergy;
	static int currentEnergy;
	
	static boolean setRandomNumberGenerator = false;
	static Random random;

	
	
	
	
	static double initialTemperature = 30;
	static double finalTemperature = 0.1;
	static double alpha = 0.99;
	static double stepsPerChange = 100;

	public static void main ( String[] args )
	{

		random = new Random( System.currentTimeMillis());
		
		//initialize variables
		int timer = 0;
		int steps;
		int step = 0;
		boolean solution = false;
		boolean done1= false;
		boolean done2 = false;
		boolean useNew = false;
		int accepted = 0;
		double temperature = initialTemperature;
		int x,flag,y;
		
	
            for ( int i=0; i<maxLength; i++){

			for ( int j=0; j<maxLength; j++){
				workingSolution[i][j]=0;
			}
		
            }
		
		
		for ( int i=0; i<maxLength; i++){
			
			do{
			x = getLargeRandom( maxLength );
			flag=0;
			for ( int k=0; k<maxLength; k++){
				if (workingSolution[k][x]==1)
						flag=1;
				}
			}while (flag==1);
			if (workingSolution[i][x]!=1)
				workingSolution[i][x]=1;
			}
		
		while (!done1){
		x = getLargeRandom( maxLength );
		y = getLargeRandom( maxLength );
		if (workingSolution[x][y]!=1){
			workingSolution[x][y]=1;
		done1=true;}}
		while (!done2){
			x = getLargeRandom( maxLength );
			y = getLargeRandom( maxLength );
			if (workingSolution[x][y]!=1){
				workingSolution[x][y]=2;
			done2=true;}}
		
		
		

			
for (  int i=0; i<maxLength; i++){
	

	for (  int j=0; j<maxLength; j++){
		currentSolution[i][j]=workingSolution[i][j];
		bestSolution[i][j]=workingSolution[i][j];
	}
  }
			
			
System.out.println("Initial state");

for (  int i=0; i<maxLength; i++){
	

	for (  int j=0; j<maxLength; j++){
		if (workingSolution[i][j]==1)System.out.print(" o");
		if (workingSolution[i][j]==2)System.out.print(" s");
		if (workingSolution[i][j]==0)System.out.print(" *");
        }
	System.out.println();
  }	
	
		
		

		workingEnergy = computeEnergy ( workingSolution );
		currentEnergy = computeEnergy( currentSolution );
		bestEnergy = computeEnergy ( bestSolution );
//		System.out.print("Initial Energy:");
		System.out.println(workingEnergy);
		System.out.println("Solution...");
		
	steps=0;
	 long startTime = System.currentTimeMillis();
		//main loop
		while ( temperature > finalTemperature ){
			steps++;
			
			accepted = 0;
			
			//randomizing step
			for ( step = 0; step < stepsPerChange; step++){
			
				useNew = false;
				tweakSolution ( workingSolution );
				
				workingEnergy = computeEnergy ( workingSolution );
				

				if ( workingEnergy < currentEnergy ){
				
					useNew = true;
				
				}else{
					//if workingEnergy more than current try this test
					// P(dE) = exp ( -dE/T )
					// epitrepei xeirwteres periptwseis se ypshles thermokrasies gia thn apofygh topikwn elaxistwn
					double randomTest = getSmallRandom();
					double delta = workingEnergy - currentEnergy;
					double calculation = Math.exp( -delta/temperature );
					
					if ( calculation > randomTest ){
					
						accepted++;
						useNew = true;
					}
				}
				
		
				if ( useNew ){
					

					useNew = false;
					currentEnergy = workingEnergy;
					
					for (  int i=0; i<maxLength; i++){
						

						for (  int j=0; j<maxLength; j++){
							currentSolution[i][j]=workingSolution[i][j];
							
						}
					  }
					

					if ( currentEnergy < bestEnergy ){
					
						bestEnergy = currentEnergy;
						
						for (  int i=0; i<maxLength; i++){
							

							for (  int j=0; j<maxLength; j++){
								bestSolution[i][j]=currentSolution[i][j];
							}
						  }
						
					}
				}else{
					  
					workingEnergy = currentEnergy;
					
					for (  int i=0; i<maxLength; i++){
						

						for (  int j=0; j<maxLength; j++){
							workingSolution[i][j]=currentSolution[i][j];
						}
					  }
					
				}
				
			}
			
			temperature *= alpha;
			

			if ( bestEnergy < 1.0 ) {
			
				solution = true;
				break; 
			} 	
				
		}
	
	
		
		long endTime = System.currentTimeMillis();
	    System.out.println("Execution time is:"+ ((endTime-startTime)/60)+"s:"+((endTime-startTime)-((endTime-startTime)/60)*60)+"ms");
		System.out.println("Execution steps:"+steps);
		if ( solution ){
			
			dumpSolution();	
		}
		else{
	    dumpSolution();
//		System.out.println("--not a solution--");
		}
	
	}



	



	//return a random number between 0 and 1
	static double getSmallRandom()
	{
		return random.nextDouble();
	}
	
	
	//return a number between zero and x
	static int getLargeRandom( int maximum )
	{
		return random.nextInt ( maximum );
	}
	
	
	
	
	
	
	
	
	
	

	static void tweakSolution ( int array[][] )
	{
		int tempValue, x, y=0,z,h,queens;
		int[] y1;
		int sk=0;
		y1=new int[2];
		int check;
		//y1[0]=0;
		//y1[1]=0;
		
		queens=0;



		x = getLargeRandom( maxLength );
		for (  int j=0; j<maxLength; j++){
			
			if (array[x][j]==1) {
				queens++;
				y1[queens-1]=j;
				y=j;
				}
		}
		
		
		if (queens==2){		
			
			check = getLargeRandom(10);
			if (check>5) y=y1[0];
			

			
			do{
		z = getLargeRandom( maxLength );
		h = getLargeRandom( maxLength );		
		}while(array[z][h]!=0);
		
		array[z][h]=1;
		array[x][y]=0;
		
		}
		
		else if (queens==1){
			sk=0;
			do{
			z = getLargeRandom( maxLength );
			for (  int j=0; j<maxLength; j++){
				if (array[j][z]==1) sk++;
			}
				
			}while((array[x][z]!=0)||sk==2);

		
				array[x][z]=1;
				array[x][y]=0;
			
			
		}
		
		x = getLargeRandom( maxLength );
		y = getLargeRandom( maxLength );
		if (array[x][y]==2){


			do{ 
			z = getLargeRandom( maxLength );
			h = getLargeRandom( maxLength );}
			while(array[z][h]!=0);
			
		
		array[z][h]=2;
		array[x][y]=0;				
		}
		
		

		
		


			
			
  /*	System.out.println(computeEnergy(array));
		for (  int i=0; i<maxLength; i++){
			
			//check that queens are in separate rows
			for (  int j=0; j<maxLength; j++){
				if (array[i][j]==1)System.out.print(" o");
				if (array[i][j]==2)System.out.print(" s");
				if (array[i][j]==0)System.out.print(" *");
			}
			System.out.println();
		  }	*/
		
	}
	
	

	static int computeEnergy ( int array[][] )
	{
		int row, column,soldier=0;
		int board[][] = new int[maxLength][maxLength];
		int conflicts = 0;
		
				

		for (  int i=0; i<maxLength; i++){
			

			for (  int j=0; j<maxLength; j++){
				
				for (  int k=0; k<maxLength; k++){
					soldier=0;
					if ((array[i][j]==1&&array[i][k]==1)){
					if (j<k){
					for (  int d=j; d<k; d++){
						if (array[i][d]==2) soldier=1;
					}
					if (soldier==0)conflicts++;
					}
					if (j>k){
						for (  int d=k; d<j; d++){
						if (array[i][d]==2) soldier=1;
						}
						if (soldier==0)conflicts++;
					}

						
					}				

				}
				
			}
			
			
		  }	
		
		
		
		

		for (  int i=0; i<maxLength; i++){
			

			for (  int j=0; j<maxLength; j++){
				
				for (  int k=0; k<maxLength; k++){
					soldier=0;
					if ((array[j][i]==1&&array[k][i]==1)){
					if (j<k){
					for (  int d=j; d<k; d++){
						if (array[d][i]==2) soldier=1;
					}
					if (soldier==0)conflicts++;
					}
					if (j>k){
						for (  int d=k; d<j; d++){
						if (array[d][i]==2) soldier=1;
						}
						if (soldier==0)conflicts++;
					}

						
					}				

				}
				
			}
			
			
		  }	
		
		
		int v=0;
		int a=0;
		int flag;
		

		for (  int i=0; i<maxLength; i++){
			

			for (  int j=0; j<maxLength; j++){
				
				
					

			for (  int i2=0; i2<maxLength; i2++){
				

				for (  int j2=0; j2<maxLength; j2++){
					v=0;
					v=Math.abs(i-i2)-Math.abs(j-j2);				
					if ((array[i][j]==1&&array[i2][j2]==1)&&v==0&&i!=i2&&j!=j2){
						a=1;
						flag=0;
						if (i<i2&&j<j2){
							while (a<Math.abs(j-j2)){
								if((array[i+a][j+a]==1)||(array[i+a][j+a]==2)) flag=1;	
								a++;
							}
						}
						
						if (i>i2&&j>j2){
							while (a<Math.abs(j-j2)){
							if((array[i2+a][j2+a]==1)||(array[i2+a][j2+a]==2)) flag=1;					
							    a++;}


							}
						

						if (i<i2&&j>j2){
							while (a<Math.abs(j-j2)){
							if((array[i+a][j-a]==1)||(array[i+a][j-a]==2))
								flag=1;					
							    a++;}

							}
						if (i>i2&&j<j2){
							while (a<Math.abs(j-j2)){
							if((array[i-a][j+a]==1)||(array[i-a][j+a]==2))
								flag=1;					
							    a++;}

							}
					    if (flag!=1) conflicts++;

					}
					
					
					
						}	
					}				

			}
		
		
		}

		return conflicts/2;
	}
	
	
	

	static void dumpSolution ()
	{System.out.println("Energy:"+bestEnergy);
		for (  int i=0; i<maxLength; i++){
			
			for (  int j=0; j<maxLength; j++){
				if (bestSolution[i][j]==1)System.out.print(" o");
//				if (bestSolution[i][j]==2)System.out.print(" s");
				if (bestSolution[i][j]==0)System.out.print(" *");


			}
			System.out.println();
		  }	
		
		
	}
	

}