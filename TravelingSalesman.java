/**
* TravelingSalesman computes the shortest route between a set of n Cartesian points
* @author Elizabeth Song and Shreyaa Raghavan
* @version 12.21.18
*/

import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class TravelingSalesman {
	private int[] x;
	private int[] y;
	private int num;
	private double[][] allDist;
	private double min;
	private List<Integer> arr;
	private Integer[] shortestPath;
	
	//constructor that initializes the values of x and y coordinates, number of points, and a distance matrix
	public TravelingSalesman(int[] xcoordinates, int[] ycoordinates, int numPoints) {
		x = xcoordinates;
		y = ycoordinates;
		num = xcoordinates.length;
		allDist = new double[num][num];
		min = -1;
		Integer[] temp = new Integer[numPoints];
		for (int i = 0; i < numPoints; i++){
			temp[i] = i;
		}
		arr = Arrays.asList(temp);
	}

	//method that populates and returns a 2D array which contains all possible distances between any two points
	public double[][] setDistance( ){
		for (int i = 0; i < num; i++){
			for (int j = 0; j < num; j++){
				allDist[i][j] = distance(x[i], y[i], x[j], y[j]);
			}
		}
		
		return allDist;
	}
	
	//method that runs through all permutations and finds the shortest path
	public void permute(int k){
    	if (k == arr.size() -1){		//base case for recursion
    		Integer [] permutation = new Integer[arr.size()];
    		permutation = arr.toArray(permutation);
    		
    		double cost = 0;
    		for (int i = 0; i < num - 1; i++){ 		//accumulator pattern that computes the total cost of a permutation 
    			cost += allDist[permutation[i]][permutation[i+1]];
    		}
    		if (min < 0 || cost < min){			//updates the value of min and shortest path						
    			min = cost;
    			shortestPath = permutation;
    		}
        }
        else{
        	for(int i = k; i < arr.size(); i++){
            	Collections.swap(arr, i, k);
            	permute(k+1); 		//recursion
            	Collections.swap(arr, k, i);	
       		}
        }
    }
    
    //method that returns the minimum cost
    public double getMin(){
    	return min;
    }
    
    //method that returns the order of points that make up the shortest path
    public Integer[] getShortestPath(){
    	return shortestPath;
    }
	
	//method that calculates the distance between any two points
	public double distance(int x1, int y1, int x2, int y2) {		
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	
}
