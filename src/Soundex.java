/* 	Author: Angelo Chung                
*	Instructor: Bryan Green                                  
* 	Purpose: 	To make a soundex algorithm. Soundex is a phonetic index and not a strictly alphabetical one. 
* 			 The key feature is that it codes names, surnames (last names) primarily, 
*			 based on the way a name sounds rather than on how it is spelled.
*	@author Angelo Chung
*	@version 2.0 
*/
import java.io.*;
import java.util.Scanner;
public class Soundex
{
	// Main Method
    public static void main(String[] args){
        boolean cont = true;
        try{
	        while (cont){
	        	run();
	            System.out.println("Would you like to do this again?\n");
	            Scanner input = new Scanner(System.in);
	            if(input.next().toLowerCase().charAt(0) != 'y')
	            	cont = false;
	        }
	        System.out.println("Program Finished! yay!");
        }
        catch(Exception e){
        	System.out.println("\nI don't like what you're doing; program will terminate.");
        }
    }
    
    /*
     * Find out if two last names sounds the same
     */
    private static void run(){
    	String n1 = getLastName("a last name");
        String s1 = doProcedure(n1);
        String n2 = getLastName("another last name");
        String s2 = doProcedure(n2);
        compareTheNames(n1,s1,n2,s2); 		// compare 
    }
    
    /*
     * Get last name input from the user
     * 
     * @param text 	specify what to enter
     * @return 		lastName a last name string
     */
    private static String getLastName(String text){     
        System.out.print("Enter " + text +" : ");
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    
    /*
     * Produce the soundex of a lastname
     * 
     * @param lastName 	a last name
     * @return string 	converted last name to soundex
     */
    private static String doProcedure(String lastName){
        String encodedName = encode(lastName);           				// get codes for whole
        String noRepeats = deleteRepeats(encodedName); 					// encodeName to delete repeating the initial letter
        String str = lastName.charAt(0) + noRepeats.substring(1);		// soundes now has initial letter and the rest are numbers
        str = (str.replace("0", "") + "000").substring(0,4);			// remove all zeros, add "000" at the end, and then take only the first 4 characters
        
        return str;														// this is the final soundex
    }
    
    /*
     * Encode any string s into a soundex
     * 
     * @param s 	the string wanted to be converted into soundex 
     * @return 		string converted into soundex
     */
    public static String encode(String s){
        s = s.toLowerCase();
        String encode = "";
        char letter;
        for(int i = 0; i < s.length(); i++){
            letter = s.charAt(i);
            if(letter == 'a'|| letter == 'e'|| letter == 'h'|| letter == 'i'|| letter == 'o'|| letter == 'u'|| letter == 'y'|| letter == 'w')
            	encode += "0";
            if(letter == 'b'|| letter == 'p'|| letter == 'f'|| letter == 'v')
            	encode += "1";
            if(letter == 'c'|| letter == 's'|| letter == 'k'|| letter == 'g'|| letter == 'j'|| letter == 'q'|| letter == 'x'|| letter == 'z')
            	encode += "2";
            if(letter == 'd'|| letter == 't')
            	encode += "3";
            if(letter == 'l')
            	encode += "4";
            if(letter == 'm'|| letter == 'n')
            	encode += "5";
            if(letter == 'r')
            	encode += "6";   
        }
        return encode;
    }
    
    /*
     * Remove repeating letters that are adjacent to each other of the last name
     * 
     * @param s 	the last name
     * @return 		string last name without repeating letters
     */
    public static String deleteRepeats(String s){
    	String temp = s.charAt(0)+"";
    	if(s.length() != 1){
	    	for(int i = 0; i<s.length()-1; i++){
	    		if(s.charAt(i)!=s.charAt(i+1))	// return when initial letter is not repeating anymore
	    			temp += s.charAt(i+1)+"";
	    	}
	    	return temp;
    	}
	    else
	    	return s; // default; return input
    }
    
    /*
     * Compares two last names if they're the same. Announces if two last names are the same otherwise, different.
     * 
     * @param n1 	1st last name
     * @param n2 	2nd last name
     * @param s1 	soundex code of the 1st last name
     * @param s2 	soundex code of the 2nd last name
     */
    public static void compareTheNames(String n1, String s1, String n2, String s2){
        s1 = s1.toUpperCase();
        s2 = s2.toUpperCase();
        if (s1.equals(s2)){
            System.out.println("The name or lastname " +n1+ " you entered have a soundex of " +s1);
            System.out.println("compared to the name or lastname "+n2+ " with a soundex of " +s2);
            System.out.println("means that they sound the same!\n");
        }
        else{
            System.out.println("The name or lastname " +n1+ " you entered have a soundex of " +s1);
            System.out.println("compared to the name or lastname "+n2+ " with a soundex of " +s2);
            System.out.println("means that they sound different!\n");
        }
    }
}