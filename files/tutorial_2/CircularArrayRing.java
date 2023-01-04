import java.util.*;

public class CircularArrayRing<E> extends AbstractCollection<E> implements Ring<E> {
	private static final int DEFAULT_MAX_SIZE = 10;
	private int maxSize;
	private Object[] array;
	//private LinkedList<E> list;
	private int head;
	private int tail;
	
	//Default-size constructor
	public CircularArrayRing() {
		this(DEFAULT_MAX_SIZE);
	}
	
	//Constructor that takes size
	public CircularArrayRing(int size) {
		this.maxSize = size;
		this.array = new Object[maxSize];
		//this.list = new LinkedList<E>();
		this.head = 0;
		this.tail = 0;
	}

	/**
	 * add - Takes an element. Tries first to replace an existing element with LinkedList.set() - if no such element
	 * exists at tail location, will catch the exception and instead use LinkedList.add() to add the element. Tail is
	 * incremented using next(), as is head if the list is full 
	 */
	@Override
	public boolean add(E e) {
		Boolean overwrite = false;
		if (array[tail] != null) {
			overwrite = true;
		}
		array[tail] = e;
		/**
		try {
			list.set(tail, e);
			overwrite = true;
		} catch(IndexOutOfBoundsException x) {
			list.add(tail, e);
		}
		**/
		tail = next(tail);
		if (tail == head) {
			head = next(head);
		}
		return overwrite;
	}

	/**
	 * get - Takes index of element to return, returns element. If no element exists at index, the exception is thrown, 
	 * else the element is returned from the index given by subtract(), starting at previous(tail), and subtracting index,
	 * giving the element <index> places before the tail
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index > maxSize-1 || index < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			return (E) array[subtract(previous(tail), index)];
		}
		/**
		if (list.get(index) == null) {
			throw new IndexOutOfBoundsException();
		} else {
			return list.get(subtract(previous(tail),index));
		}
		**/
	}
	
	/**
	 * iterator - Returns a descending iterator on the list
	 */
	@Override
	public Iterator<E> iterator() {
		class DescendingIterator implements Iterator<E> {
			private int i = 0;

			@Override
			public boolean hasNext() {
				try {
					get(i);
					return true;
				} catch (IndexOutOfBoundsException e) {
					return false;
				}
			}

			@Override
			public E next() {
				if (hasNext()) {
					return get(i++);
				} else {
					throw new NoSuchElementException();
				}
			}
						
			public void remove() {
				array[i] = null;
			}
		}
		
		DescendingIterator iterator = new DescendingIterator();
		//Iterator<E> iterator = list.descendingIterator();
		return iterator;
	}
	
	/**
	 * size - Returns LinkedList.size()
	 */
	@Override
	public int size() {
		int i = 0;
		while (i < maxSize) {
			if (array[i] == null) {
				break;				
			}
			i++;
		}
		return i;
		//return list.size();
	}
	
	/**
	 * next - Takes current index, returns next in queue.
	 */
	private int next(int current) {
		if (current == maxSize-1) {
			return 0;
		} else {
			return ++current;
		}
	}
	
	/**
	 * previous - Takes current index, returns previous in queue
	 */
	private int previous(int current) {
		if (current == 0) {
			return maxSize-1;
		} else {
			return --current;
		}
	}
	
	/**
	 * subtract - Takes starting index and amount to 'subtract', calls previous() <sub> times and returns new index
	 */
	private int subtract(int index, int sub) {
		for (int i = 0; i < sub; i++) {
			index = previous(index);
		}
		return index;
	}
	
	public static void main(String[] args) {
		CircularArrayRing<Integer> ring = new CircularArrayRing<Integer>(10);
		for(int i=0; i<1000; ++i) {
			ring.add(i);
			ring.printArray();
			System.out.println("Head. "+ring.head);
			System.out.println("Tail. "+ring.tail);
			System.out.println("Size. "+ring.size());
			for(int j=0; j<ring.size(); ++j) {
				System.out.print("Get "+j+" = ");
				System.out.println(ring.get(j));
			}
			System.out.println();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		Iterator<Integer> it = ring.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		/**
		CircularArrayRing<Integer> ring = new CircularArrayRing<Integer>(10);
		System.out.println("Size. "+ring.size());
		for(int i=0; i<1000; ++i) {
			ring.add(i);
			ring.printArray();
			System.out.println("Head. "+ring.head);
			System.out.println("Tail. "+ring.tail);
			System.out.println();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		**/
		
		
		
	
	}
	
	public void printArray() {
		try {
			for (int i = 0; i < maxSize; i++) {
				System.out.print(array[i] + ",");
				//System.out.print(list.get(i) + ",");
			}	
		} catch(IndexOutOfBoundsException e) {
			//die
		}
		System.out.println();				
	}
	
}
