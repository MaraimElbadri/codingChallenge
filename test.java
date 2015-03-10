import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Map<String, Integer> words = new HashMap<String, Integer>();
		
		Scanner wordScanner = new Scanner(new File(args[0]));
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
		
		System.out.println("Hello there!");
		
	}

}
