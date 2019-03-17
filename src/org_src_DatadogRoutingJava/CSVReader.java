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
		while((in = reader.readLine())!=null) {
			lines.add(in);
		}
		reader.close();
		
		
		/**
		 * Converting it to a String matrix for lower memory usage.
		 */
		int listSize = lines.size();
		int entrySize = lines.get(1).split(";").length;
		String [][] output = new String [listSize][entrySize];
		for(int i = 1; i < listSize; i++) {
			output[i] = lines.get(i).split(";");
		}
		return output;
	}
}
