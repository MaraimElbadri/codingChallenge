import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class WordCount{
	Map<String, Integer> words = new HashMap<String, Integer>();
	
	//Function to read words from a file into HashMap and count them
	public void readFileToHashMap(String fileName) throws FileNotFoundException{
		
		Scanner wordScanner = new Scanner(new File(fileName));
		wordScanner.useDelimiter("[^A-Za-z]+");
		while (wordScanner.hasNext()) {
		    String word = wordScanner.next().toLowerCase();  
		    if(words.containsKey(word)){
		    	int count = words.get(word);
		    	words.put(word, count+1);
		    }else{
		    	words.put(word, 1);
		    }//if-else 
		}//while
		
		wordScanner.close();
		
	}//readFileToHashMap
	
	
	
	
	// Writing the result into file
	public void writeOutputToFile(String outputFileName) throws IOException{
	
		String dirName = "wc_output";
        File directory = new File(dirName);
        boolean success = false;
        String filName =  outputFileName;
        BufferedWriter writer = null;
 
        if(directory.exists()){
        	System.out.println("Directory already exists.");
        }else{
        	System.out.println("Directory is not exist, creating now.");
        	success = directory.mkdir();
        	
	        	if(success){
	        		System.out.println("Successfully created a new directory");
	        	}else{
	        		System.out.println("Failed to create a new directory");
	        	}// if-else success
        }// check directory
        
        
    
        try {
			writer = new BufferedWriter(new FileWriter(filName));
			
			// Sorting the hash-map 
			Set<String> keys = words.keySet();
			
			//Sort keys
			TreeSet<String> sortedKeys = new TreeSet <String>(keys);
			System.out.println(" \nmap contains \nKey \t\tValue");
		    
			//Generate output for each key in the map
			for(String key : sortedKeys){
				 System.out.printf("%-10s%10s\n", key, words.get(key));
				 words.put(key, words.get(key));
				 writer.write(key+"\t\t"+words.get(key));
				 writer.write("\n");
	    	}//for
			
		
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
			   if (writer != null) {
			       writer.close();
			   }// if close-file
			}// finally
        
	}//writeOutputToFile function
	
}//WordCount