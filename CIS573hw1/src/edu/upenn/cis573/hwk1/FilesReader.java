package edu.upenn.cis573.hwk1;

import java.io.IOException;

/**
 * FileReader abstract class, handles different type of files input by users. 
 * @author paulhsu
 *
 */
public abstract class FilesReader {
	
	/**
	 * Inputs the single file and builds the map 
	 * @param fileName
	 * @throws IOException
	 */
	abstract void inputSingleFile(String fileName) throws IOException;
	
	/**
	 * Inputs other documents in the corpus except the one input in singleFileInput
	 * @param fileName
	 * @throws IOException
	 */
	abstract void inputFiles(String fileName) throws IOException;


}
