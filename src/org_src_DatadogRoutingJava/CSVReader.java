package org_src_DatadogRoutingJava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * 
 * A seperate class for reading the contents of the .csv files with the necessary imports and exceptions.
 * @author Vytenis Sliogeris
 *
 */
public class CSVReader {
	/**
	 * Returns a two-dimensional string corresponding to the .csv file excluding the labels if given the
	 * string which represents the location of the file
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public String[][] parseCSV(String filename) throws IOException {
		/**
		 * Using an arrayList, as the ability to append to a list is necessary because there is no way to
		 * know the total size of the CSV file
		 */
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String in;
		while((in = customRead(reader))!=null) {
			lines.add(in);
		}
		reader.close();
		
		
		/**
		 * Converting it to a String matrix for lower memory usage.
		 */
		int listSize = lines.size()-1;
		int entrySize = lines.get(0).split(",").length;
		String [][] output = new String [listSize][entrySize];
		for(int i = 0; i < listSize; i++) {
			output[i] = lines.get(i+1).split(",");
		}
		return output;
	}
	
	/**
	 * Custom read function to deal with \n in the cells messing up the default Readline in the BufferedReader
	 * @return
	 * @throws IOException 
	 */
	public String customRead(BufferedReader reader) throws IOException {
		String output = "";
		boolean inCel = false;
		int in = 0;
		while((in=reader.read())!=10 || inCel) {
			if(in == 34) { // 34 is the ascii code for '"', hence we should ignore newlines in them
				if(inCel == true) {
					inCel = false;
				}else {
					inCel = true;
				}
				output+=(char)in;
			}else {
				output += (char)in;
			}
			if(in == -1) {
				return null;
			}
		}
		return output;
	}
	public void printMatrix(String[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + "|");
			}
			System.out.println("|||");
		}
	}
}
