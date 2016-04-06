
//Iterator interface simply has two methods, hasNext returns whether there exists next element.
// Next method returns the next element during iteration.
public interface Iterator<E> {
	
	boolean hasNext();
	E next();
	
}
