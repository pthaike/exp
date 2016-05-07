package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Tool {
	public static ArrayList<String> readSeq(String fname){
		ArrayList<String> sequences = new ArrayList<>();
		String line = null;
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(fname));
			while((line = br.readLine()) != null){
				sequences.add(line);
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return sequences;
	}
	
	public static double[][] similarityMatrix(int d){
		double[][] matrix = new double[d][d];
		String line = null;
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(Configure.simfilename));
			int m = 0;
			while((line = br.readLine()) != null){
				String[] l = line.split(",");
				for (int n = 0; n < l.length; n++){
					double v = Double.valueOf(l[n]);
					matrix[m][n] = v;
				}
				m ++;
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return matrix;
	}
	
}
