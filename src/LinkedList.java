
//LinkedList knows its first and last element, and its size.

public class LinkedList implements Iterable<String>{
	private Node first;
	private Node last;
	int size ;
	
	public LinkedList(){
		size = 0;
		first = null;
		last = null;
	}
	
	public Node first(){
		return first;
	}
	
	public Node last(){
		return last;
	}
	
	// Returns value for a given key.
	// If that key does not exist, returns null.
	public String get(String key){
		Node p = first;
		while(p != null){
			if (key.equals(p.getKey())){
				return p.getValue();
			}
			else
				p = p.getNextNode();
		}
		
		return null;
	}
	
	// If key does not exists, add it to the last location.
	// If key exists in the list, update it with new value.
	public void add(String key, String value){
		Node node = new Node(key,value);
		String val = get(key);
		
		// This case: Add new node to the list.
		if (val == null){
			if (first == null){
				first = node;
				last = node;
				//System.out.println("added to the first " + key + " " + value);
			}
			else{
				last.setNextNode(node);
				last = node;
				//System.out.println("added to the last " + key + " " + value);

			}
			size++;
		}
		//This case: Update existing node.
		else{
			Node p = first;
			while(p!=null){
				if (key.equals(p.getKey())){
					p.setValue(value);
					//System.out.println("updated " + key + " " + value);
					break;
				}
				p = p.getNextNode();
			}
		}		
	}
	
	// If user wants to delete first node, make second node the first node.
	// Else, find the node with given key, delete it and update previous node's attributes.
	
	public void delete(String key){
		Node p = first;
		Node previous = null;
		
		
		//Case 1: Delete first node
		if (key.equals(first.getKey())){
			Node temp = first;
			first = first.getNextNode();
			temp.setKey(null);
			temp.setValue(null);
			temp.setNextNode(null);
			size--;
		}
		//Case 2: Delete a node which is not first node.
		else{
			while(p!= null){
				if (key.equals(p.getKey())){
					
					if (key.equals(last.getKey())){
						last = previous;
					}
				
					previous.setNextNode(p.getNextNode());
					
					p.setNextNode(null);
					p.setKey(null);
					p.setValue(null);
					size--;
					break;
				}
				
				previous = p;
				p = p.getNextNode();
			}
		}
	}
	
	
	// Prints all the keys and values in the list to the console.
	// This method is used to test put&delete methods.
	public void printList(){
		Node p = first;
		
		if (size >0){
			while(p!=null){
				System.out.println(p.getKey() + "-" + p.getValue());
				p = p.getNextNode();
			}
		}
		else if (size == 0)
			System.out.println("List is empty.");
		else
			System.out.println("Error.Size cannot be negative.");
	}
	
	//Set all attributes of all nodes to the NULL.
	public void clear(){
		Node p = first;
		Node previous = null;
		while(p!=null){
			previous = p;
			p = p.getNextNode();
			previous.setNextNode(null);
			previous.setKey(null);
			previous.setValue(null);
			size--;
		}
		first = null;
		last = null;
	}

	// This method should be implemented due to the Iterable interface.
	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		Iterator<String> iterator = new Iterator<String>() {
			
			private Node currentNode = first;
			// Return next element.
			@Override
			public String next() {
				String returnableKey = currentNode.getKey();
				currentNode = currentNode.getNextNode();
				return returnableKey;
			}
			
			// Return whether currentNode(not visited yet) is null or not. 
			@Override
			public boolean hasNext() {
					return (currentNode != null);
			}
		}; 
		return iterator;
	}
}
