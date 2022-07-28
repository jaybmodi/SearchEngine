// Searching a word in all the converted text files using Edit Distance
package searchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class mergeTextFiles {

	public static void mergeFiles() throws IOException {
		// TODO Auto-generated method stub

		// create instance of directory
        File dir = new File("convertedWebPages");
  
        // create object of PrintWriter for output file
        PrintWriter pw = new PrintWriter("D:\\Masters\\Term1\\ACC\\Web Search Engine Project\\output\\output.txt");
  
        // Get list of all the files in form of String Array
        String[] fileNames = dir.list();
  
        // loop for reading the contents of all the files 
        // in the directory GeeksForGeeks
        for (String fileName : fileNames) {
          //  System.out.println("Reading from " + fileName);
  
            // create instance of file from Name of 
            // the file stored in string Array
            File f = new File(dir, fileName);
  
            // create object of BufferedReader
            BufferedReader br = new BufferedReader(new FileReader(f));
            //pw.println("Contents of file " + fileName);
  
            // Read from current file
            String line = br.readLine();
            while (line != null) {
  
                // write to the output file
                pw.println(line);
                line = br.readLine();
            }
            pw.flush();
        }
	}
}
