package util;

import java.util.ArrayList;

public class SimMatrix {
	
	private int matrix[][] = null; // similarity matrix
	private ArrayList<String> items = null; // 
	
	public SimMatrix(double ma[][], ArrayList<String> items){
		this.items = items;
		this.matrix = new int[ma.length][ma.length];
		for(int i = 0; i < items.size(); i++){
			for(int j = 0; j < items.size(); j++){
				this.matrix[i][j] = ma[i][j] >= Configure.simthrehod ? 1 : 0;
			}
		}
	}
	
	public int getTwoSim(String e1, String e2){
		int m = -1;
		int n = -1;
		for (int i = 0; i< items.size(); i++){
			if(e1.equals(items.get(i))){
				m = i;
			}
			if(e2.equals(items.get(i))){
				n = i;
			}
			if(m != -1 && n != -1){
				break;
			}
		}
		if(m == -1 || n == -1){
			return Configure.lim;
		}
		return matrix[m][n];
	}

}
