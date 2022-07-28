//Use regular expressions to match Emails, Web links, Postal Codes, Phonenumbers
package searchEngine;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.reflect.*;


public class RegExpression {
	
	// CHANGE HERE!
	public static String folderlocation = "convertedWebPages";
	
	public static boolean findEmailAdresses(String textfile) {

	     // Create a Pattern object
	     Pattern r = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
	     Boolean valuefound = false;

	     // Now create matcher object.
	     Matcher m = r.matcher(textfile);
		     while (m.find( )) {
		         System.out.println("Found Email Address : " + m.group(0));
		         valuefound = true;
		     }
		     return valuefound;
	     
	 }

	 public static boolean findWebLinks(String textfile) {

	     // Create a Pattern object
	     Pattern r = Pattern.compile("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
	     Boolean valuefound = false;
	     // Now create matcher object.
	     Matcher m = r.matcher(textfile);
	     while (m.find( )) {
	         System.out.println("Found WebLink : " + m.group(0));
	         valuefound = true;
	     } 
		 return valuefound;
	 }
	 
	 public static boolean findPostalCodes(String textfile) {

	     // Create a Pattern object
	     Pattern r = Pattern.compile("(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$");
	     Boolean valuefound = false;

	     // Now create matcher object.
	     Matcher m = r.matcher(textfile);
	     while (m.find( )) {
	         System.out.println("Found Postal Code : " + m.group(0));
	         valuefound = true;
	     } 
		 return valuefound;
	 }

	 public static boolean findPhoneNumbers(String textfile) {
		 String pattern = "(\\()?(\\d){3}(\\))?[- ](\\d){3}-(\\d){4}";
		 Boolean valuefound = false;

	     // Create a Pattern object
	     Pattern r = Pattern.compile(pattern);

	     // Now create matcher object.
	     Matcher m = r.matcher(textfile);
	     while (m.find( )) {
	         System.out.println("Found Phone Number : " + m.group(0));
	         valuefound = true;
	     } 
		 return valuefound;
	 }	 
	 
	 public static void findPatterns(int choice) throws NoSuchMethodException,  
	 InvocationTargetException, IllegalAccessException, IOException {
		
		// create hashmap to store the values
		HashMap<Integer, Method> methodMap = new HashMap<Integer, Method>();
		Boolean valuefound = false;
		final File newfolderstructure = new File(folderlocation);
		
		// store the Methods in the HashMap
		try {
				Class[] cArg = new Class[1];
				cArg[0] = String.class;
				methodMap.put(1, RegExpression.class.getMethod("findEmailAdresses", cArg));
				methodMap.put(2, RegExpression.class.getMethod("findWebLinks", cArg));
				methodMap.put(3, RegExpression.class.getMethod("findPostalCodes", cArg));
				methodMap.put(4, RegExpression.class.getMethod("findPhoneNumbers", cArg));
				
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		for (final File fileEntry : newfolderstructure.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	String pathname = fileEntry.getPath();
	        	FileReader in = new FileReader(pathname);
	        	
	        	
	        	 try (BufferedReader br = new BufferedReader(new FileReader(pathname)))
	             {
	                 String line;
	                 while ((line = br.readLine()) != null) {
	                	 try { 
	                		 // call the appropriate method!
	                		 Boolean value = (Boolean) methodMap.get(choice).invoke(null, line);
	                		 if(value) {
	                			 valuefound=true;
	                		 }
	                	 } catch (Exception e) {
	                		 e.printStackTrace();
	                	 }
	                	 }
	                
	             } catch (IOException e) {
	                 e.printStackTrace();
	             }
	        	 
	        	
	        }
	    }
		if(!valuefound) {
       	 System.out.println("Oops ! No value found!");
        }
		
	}
	public static void main(String args[]) throws NoSuchMethodException,  
    InvocationTargetException, IllegalAccessException, IOException {
		System.out.println("Choose a number!\n1 :\tEmail Adresses\n2 :\tWeb Links\n3 :\tPostal Codes\n4 :\tPhonenumbers\n");
		int choice = new Scanner(System.in).nextInt();
		findPatterns(choice);
	}
}
