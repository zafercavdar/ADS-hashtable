public class HashTable {
	static final int TABLESIZE = 53;
	
	private LinkedList[] hashTable;
	private int size;
	
	
	//Initialize LinkedList array and set size to 0.
	public HashTable(){
		this.hashTable = new LinkedList[TABLESIZE];
		
		for (int i= 0; i <TABLESIZE; i++){
			hashTable[i] = new LinkedList();
		}
		
		this.size = 0;
	}
	
	// Returns number of elements in the Map.
	public int size(){
		return this.size;
	}
	
	// Empty all LinkedLists in the Map.
	public boolean Empty(){
		for (int i = 0; i < TABLESIZE; i++){
			hashTable[i].clear();
		}
		return true;
	}
	
	// Calculate hash for a given key.
	public int hash(String key){
		int hash = 0;
		int p = 23;
		for (int i = 0; i < key.length(); i++){
			hash = (p * hash + key.charAt(i)) % TABLESIZE;
		}
		return hash;
	}
	
	// If key exists, delete it from the Map and decrement size by 1.
	// Returns last value of the key.
	public String remove(String key){
		int hashIndex = hash(key);
		String value = hashTable[hashIndex].get(key);
		
		if (value != null){
			hashTable[hashIndex].delete(key);
			size--;
		}
				
		return value;
		
	}
	
	// Find the corresponding value for given key.
	public String get(String key){
		int hashIndex = hash(key);
		String value = hashTable[hashIndex].get(key);
		return value;
	}
	 
	// If key exists in the Map, update it with new value.
	// Else, add to the List and increment size by 1.
	public String put(String key, String value){
		int hashIndex = hash(key);
				
		String prevValue = hashTable[hashIndex].get(key);
		hashTable[hashIndex].add(key, value);
		
		if (prevValue == null){
			size++;
			return value;
		}
		else
			return prevValue;
		
	}
	
	// Return all the keys in the map in a LinkedList.(Also this list iterable.)
	public LinkedList keySet(){
		LinkedList keys = new LinkedList();
		
		for (int i = 0; i < TABLESIZE; i++){
			Iterator<String> keyIterator = hashTable[i].iterator();
			while (keyIterator.hasNext()){
				String key = keyIterator.next();
				//System.out.println("has next in " + i + " with " + key);
				keys.add(key,get(key));
			}
		}
		
		return keys;
	}
	
	// Check whether this Map contains given key in any one of the Lists or not.
	public boolean containsKey(String key){
		int hashIndex = hash(key);
		
		Iterator<String> keyIterator = hashTable[hashIndex].iterator();
		while (keyIterator.hasNext()){
			String iteratedKey = keyIterator.next().toString();
			if (key.equals(iteratedKey)){
				return true;
			}
		}
				
		return false;
	}
}
