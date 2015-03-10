
import java.io.*;
import java.util.*;

public class RunningMedian {

	public static void main(String[] args) throws IOException {

		String inputDirName = "wc_input";
		String outputDirName = "wc_output";
		
		if (args.length > 1) {
			inputDirName = args[0];
			outputDirName = args[1];
	    }
				
		List<Integer> wordsPerLineList = new ArrayList<Integer>();
		wordsPerLineList = contentFilesInDirectory(inputDirName,wordsPerLineList);
		   
		List <Double> medianList = new ArrayList<Double>();
		
		medianList = RunningMedianPerFile(wordsPerLineList);
		writeToFile(medianList,outputDirName);
		    
        
	}//main
	
	
	 public static List<Integer> contentFilesInDirectory(String dirName, List <Integer> filesContent) throws FileNotFoundException {
		 
		int numFiles = 0;
		File directory = new File(dirName);
		filesContent = new ArrayList<Integer>();

		for (File file : directory.listFiles()) {
			
			if (file.isFile()) {
				numFiles++;
				Scanner fileScanner = new Scanner(file);
				fileScanner.useDelimiter("[^A-Za-z]+");
				System.out.println(file.getName());
				
				while (fileScanner.hasNext()) {
					String numWordsPerLine = fileScanner.nextLine();
					System.out.println("The sentence has " + countWords(numWordsPerLine) + " words.");
					filesContent.add(countWords(numWordsPerLine));
				}//while
					 
			fileScanner.close();
			}//if
		    
		}// for
		
		System.out.println(numFiles);
		return filesContent;
		
	}//contentFilesInDirectory

	 
	 
	 public static int countWords(String str){
		    int count = 1;
		    for (int i=0;i<=str.length()-1;i++){
				if (str.charAt(i) == ' ' && str.charAt(i+1)!=' '){
					count++;
				}//if
		    }//for
		return count;
	}// countWords
	 
	 
	 public static <E extends Comparable<? super E>> List<E> quickSort(List<E> arr) {
		 if (!arr.isEmpty()) {
			 
			E pivot = arr.get(0); //This pivot can change to get faster results
			List<E> less = new LinkedList<E>();
			List<E> pivotList = new LinkedList<E>();
			List<E> more = new LinkedList<E>();
		  
			// Partition
			for (E i: arr) {
			     if (i.compareTo(pivot) < 0)
			         less.add(i);
			     else if (i.compareTo(pivot) > 0)
			         more.add(i);
			     else
			         pivotList.add(i);
			}//for
		  
			// Recursively sort sublists
			less = quickSort(less);
			more = quickSort(more);
			  
			// Concatenate results
			less.addAll(pivotList);
			less.addAll(more);
			return less;
		 }//if
		 
		 return arr;
	 }//quickSort
	 
	 
	 public static double getMedian(List <Integer> s) {
			int middle = s.size()/2;
			if (s.size()%2 == 1) {
			    return s.get(middle);
			} else {
			    return (s.get(middle-1) + s.get(middle)) / 2.0;
			}//if
	}//getMedian
	 

     public static void writeToFile (List <Double> medianList, String dirName ) throws IOException{

    	 boolean createDir = checkAndCreateDirectory(dirName);
    	
    	 if(createDir){
      		System.out.println("Successfully created a new directory");
      	}else{
      		System.out.println("Failed to create a new directory");
      	}// if-else success
    	 
    	 
         String filName = dirName +"/"+"/med_result.txt";
         BufferedWriter writer = null;

         try {
 	           
 			writer = new BufferedWriter(new FileWriter(filName));
 			Iterator<Double> it = medianList.iterator();
 			
 			while (it.hasNext()){
 			    double entryLine = it.next();
 			    System.out.println(entryLine);
 			writer.write(Double.toString(entryLine));
 			writer.write("\n");
 			}
 			}catch (Exception e) {
 				e.printStackTrace();
 			}finally {
 			   if (writer != null) {
 			       writer.close();
 			   }//if
 			}
         }//writeToFile
     
     
     
     public static boolean checkAndCreateDirectory(String dirName){

         File directory = new File(dirName);
         boolean success = false;
         
         if(directory.exists()){
         	System.out.println("Directory already exists.");
         }else{
         	System.out.println("Directory is not exist, creating now.");
         	success = directory.mkdir();

         }// check directory
         
         return success;
     }
     
     
     public static List<Double> RunningMedianPerFile(List<Integer> wordsPerLineList){
		

    	double median = 0;
		List <Double> medianList = new ArrayList();
		List <Integer> currentReading = new ArrayList<Integer>();
		int count = 0;
		
		
		while(count <  wordsPerLineList.size()){
			int lineCount = wordsPerLineList.get(count);
			
			System.out.println(" The element"+ lineCount);
         // for the first the item (line in the file)
			if(count == 0){
				currentReading.add(lineCount);
				//median = double(lineCount);
				double v = (double)(lineCount);
				medianList.add(v);

				count++;
				continue;
			}//if

			// median for other lines 
			if(count!=0){
				
				currentReading.add(lineCount);
				currentReading = quickSort(currentReading);
				median = getMedian(currentReading);
				medianList.add(median);
				count++;
			}//if
			
		}
			return medianList;

	}

     
}//RunningMedian