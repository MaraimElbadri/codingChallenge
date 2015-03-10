import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException{
		
		String inputFileName = "wc_input/test.txt";
		String outputFileName = "wc_output/wc_result.txt";
		
		if (args.length > 1) {
			inputFileName = args[0];
			outputFileName = args[1];
	    }
		
		WordCount wc = new WordCount();
		wc.readFileToHashMap(inputFileName);
		wc.writeOutputToFile(outputFileName);
		
        
	}
	
	
}
