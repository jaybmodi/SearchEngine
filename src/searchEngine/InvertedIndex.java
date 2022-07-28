/* Inverted Index - All the words in the text files generated 
   by the web crawler will be stored in HashMap. The HashMap
   will then be searched for the word provided by the user.
   All the text files containing the word will be displayed.  */

package searchEngine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class InvertedIndex {
	
	
	static File allFiles = new File("convertedWebPages");
	HashMap<String, String> textFiles;
	Scanner sc = new Scanner(System.in);
	
	public void InvertedIndexCreate() throws IOException{
		
		System.out.println("Enter the word to find the file that contains it: ");	
		String userInput = sc.next();
		userInput = userInput.toLowerCase();
		int count = 0;
		
		try{
		for(File f : allFiles.listFiles()){
			
			textFiles = new HashMap<String, String>();
			Document doc = Jsoup.parse(f, "UTF-8");
			String text = doc.text();	
			String[] str = text.split("\\W+");
			
			for(int i = 0; i< str.length; i++){
				if(!textFiles.containsKey(str[i]))
					textFiles.put(str[i].toLowerCase(), f.getName());
			}
			
			if(textFiles.containsKey(userInput)){     //check if word is found or not
				count++;
				System.out.println("'"+userInput+"' " +"found in "+"'"+textFiles.get(userInput));
				}
			}
		
		}catch(IOException e){
		System.out.println(e.getMessage());
			
		}

		if(count == 0)
			System.out.println("'"+userInput+"' "+ "not found in any of the files.");
		
	}
	
	
	public static void main(String[] args) throws IOException
	{
		InvertedIndex w= new InvertedIndex();
		w.InvertedIndexCreate();
		
	}

}

