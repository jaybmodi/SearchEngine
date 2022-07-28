// Searching a word in all the converted text files using Edit Distance

package searchEngine;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class editDistance {

    private static dictionary dict;
    
    
    final static String filePath = "output/output.txt";
    final static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public editDistance() {
        dict = new dictionary();
        dict.build(filePath);

    }

    public static void run() {
    	dict = new dictionary();
        dict.build(filePath);
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        String input;

        System.out.print("\nEnter a word: ");
        input = scan.nextLine();

        if (dict.contains(input)) {
            System.out.println("\n" + input + " is spelled correctly");
        } else {

            System.out.print("\n" + input +" is not found in the collection, ");
            System.out.println();
            System.out.println(printSuggestions(input));
        }
    }

    public static String printSuggestions(String input) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> print = makeSuggestions(input);
        if (print.size() == 0) {
            return "and no relatable words were found.\n";
        }
        sb.append("Perhaps you meant:\n");
        for (String s : print) {
            sb.append("\n  -" + s);
        }
        return sb.toString();
    }

    private static ArrayList<String> makeSuggestions(String input) {
    	
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.addAll(charAppended(input));
        toReturn.addAll(charMissing(input));
        toReturn.addAll(charsSwapped(input));
        
        return toReturn;
    }

    private static ArrayList<String> charAppended(String input) { 
        ArrayList<String> toReturn = new ArrayList<>();
        for (char c : alphabet) {
            String atFront = c + input;
            String atBack = input + c;
            if (dict.contains(atFront)) {
                toReturn.add(atFront);
            }
            if (dict.contains(atBack)) {
                toReturn.add(atBack);
            }
        }
        return toReturn;
    }

    private static ArrayList<String> charMissing(String input) {   
        ArrayList<String> toReturn = new ArrayList<>();

        int len = input.length() - 1;
        //try removing char from the front
        if (dict.contains(input.substring(1))) {
            toReturn.add(input.substring(1));
        }
        for (int i = 1; i < len; i++) {
            //try removing each char between (not including) the first and last
            String working = input.substring(0, i);
            working = working.concat(input.substring((i + 1), input.length()));
            if (dict.contains(working)) {
                toReturn.add(working);
            }
        }
        if (dict.contains(input.substring(0, len))) {
            toReturn.add(input.substring(0, len));
        }
        return toReturn;
    }

    private static ArrayList<String> charsSwapped(String input) {   
        ArrayList<String> toReturn = new ArrayList<>();

        for (int i = 0; i < input.length() - 1; i++) {
            String working = input.substring(0, i);// System.out.println("    0:" + working);
            working = working + input.charAt(i + 1);  //System.out.println("    1:" + working);
            working = working + input.charAt(i); //System.out.println("    2:" + working);
            working = working.concat(input.substring((i + 2)));//System.out.println("    FIN:" + working); 
            if (dict.contains(working)) {
                toReturn.add(working);
            }
        }
        return toReturn;
    }


}


