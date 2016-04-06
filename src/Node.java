
//Each node stores a key, a value and its next node.

public class Node {
	private String key = null;
	private String value = null;
	private Node nextNode = null;
	
	// initialize with key and value.
	public Node(String key, String value){
		setKey(key);
		setValue(value);
	}
	
	//Getter and setter methods.

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
}
