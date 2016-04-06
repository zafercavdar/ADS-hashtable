import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Test {

	public static final String inputFile = "input.txt";
	public static final String outputFile= "output.txt";
	
	public static HashTable myHashTable = new HashTable();
	
	// Read input file, do operation after each line.
	public static void main(String[] args) {
		readInput();
	}
	
	private static void readInput(){
		
		BufferedReader reader = null;
		try {
			 reader = new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(outputFile));
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		
		boolean inputs = true;
		
		while(true){
			//Read each line.
			String line = null;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (line == null)
				break;
			
			//Empty line represents ends of key,value inputs.
			//Print all key,value pairs to the file with hashes.
			if (line.equals("")){
				inputs = false;
				try {
					line = reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				printHashesToFile(pw);
			}
			
			
			if (inputs){
				//First token = key
				//Other tokens = value
				StringTokenizer tokenizer = new StringTokenizer(line, " ");
				String key = tokenizer.nextToken();
				String value = "";
				
				while (tokenizer.hasMoreTokens()){
					value += tokenizer.nextToken()+" ";
				}
								
				myHashTable.put(key, value);
			}
			else{
				//First token = command
				//Other tokens = differs from command to command
				
				StringTokenizer tokenizer = new StringTokenizer(line, " ");
				String command = tokenizer.nextToken();
				
				//Read key & value. Then put it to map.
				if (command.toLowerCase().equals("put")){
					String key = tokenizer.nextToken();
					String value ="";
					
					while (tokenizer.hasMoreTokens()){
						value += tokenizer.nextToken()+" ";
					}
					
					myHashTable.put(key, value);
					printHashesToFile(pw);
				}
				
				//Print size of the map to the file.
				else if (command.toLowerCase().equals("size")){
					pw.println("Size of the table is:" + myHashTable.size()+"\n\n\n");
					pw.println("\n");
				}
				
				//Print whether map contains that key or not.
				else if (command.toLowerCase().equals("containskey")){
					String key = tokenizer.nextToken();
					pw.println(myHashTable.containsKey(key) + "\n");
					pw.println("\n");
				}
				
				//Other possible commands based on methods.
				else if (command.toLowerCase().equals("remove")){
					String key = tokenizer.nextToken();
					myHashTable.remove(key);
				}
				else if (command.toLowerCase().equals("keyset")){
					myHashTable.keySet();
				}
				else if (command.toLowerCase().equals("empty")){
					myHashTable.Empty();
				}
				else if (command.toLowerCase().equals("hash")){
					String key = tokenizer.nextToken();
					myHashTable.hash(key);
				}
				else
					System.out.println("Program did not understand command:" + command);
			}
		}
		pw.close();
	}
	
	// Using Iterator on keySet, find all keys and values with hashes.
	// Print them to the output file.
	// This method is called after finding the first empty line and after all put operation.
	
	private static void printHashesToFile(PrintWriter pw){
		
		Iterator<String> keyIterator = myHashTable.keySet().iterator();
		
		while (keyIterator.hasNext()){
			String key = keyIterator.next();
			int hashIndex = myHashTable.hash(key);
			String value = myHashTable.get(key);
			System.out.println(hashIndex+":"+key+" " + value);
			pw.println(hashIndex+":"+key+" " + value+"\n");	
		}
		
		System.out.println();
		pw.println("\n");
	}
}
