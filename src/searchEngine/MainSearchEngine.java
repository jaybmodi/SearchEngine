package searchEngine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class MainSearchEngine {

	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException{

		//Web crawling 
		
		String links="https://www.apple.com/ca/";
		WebCrawler web=new WebCrawler();
		web.crawlPages(links);  		

		//Conversion finished from html to text

		// User Interaction start 
		Scanner userInput = new Scanner(System.in); 
		
		String more;
		
		do
		{
			
		System.out.println("------------------------------------------------");
		System.out.println("Please enter the feature to be searched: "
				+ "\n 1. Regular Expression"
				+ "\n 2. Spell Checker using edit distance"
				+ "\n 3. Inverted Index"
				+ "\n 4. Frequency Count"
				+ "\n 5. Sort Pages using Rank");
		System.out.println("------------------------------------------------");
		
		int feature = userInput.nextInt();  // Read user input
		
		switch (feature) 
		{
		
		case 1:
			RegExpression reg = new RegExpression();
			System.out.println("-----------Regular Expression------------");
			System.out.println("Choose a number!\n1 :\tEmail Adresses\n2 :\tWeb Links\n3 :\tPostal Codes\n4 :\tPhonenumbers");
			System.out.println("-----------------------------------------");
			int choice = new Scanner(System.in).nextInt();
			reg.findPatterns(choice);
			break;

		case 2:
			mergeTextFiles.mergeFiles();
			linesToWords.splitWords();
			editDistance.run();
			break;

		case 3: 
			InvertedIndex ii = new InvertedIndex();
			ii.InvertedIndexCreate();
			break;

		case 4:
			FrequencyCount.Frequency_Count();
			break;
			
		case 5:
			FrequencyCount.sortbyRank();
			break;
			
		default:
			System.out.println("Invalid choice");
			break;

		}
		
		System.out.println("\nMore Search?(yes/no)");
		Scanner sc = new Scanner(System.in);  
		 more = sc.next();
		
		}
		while(more.equalsIgnoreCase("yes"));
		
		System.out.println("\nThank You!");

	}
}
