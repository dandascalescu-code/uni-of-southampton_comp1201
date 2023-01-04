import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue {
	private static final int INITIAL_ARRAY_SIZE = 10;
	private int arraySize;
	private int[] array;
	private int head;
	private int tail;
	private boolean full;
	
	//Constructor, sets size to constant default
	public CircularArrayQueue() {
		this.arraySize = INITIAL_ARRAY_SIZE;
		this.array = new int[arraySize];
		this.head = 0;
		this.tail = 0;
		this.full = false;
	}

	/**
	 * enqueue - Takes integer to enqueue. If the array is full - resizes, adds integer to tail, increments tail
	 * using next(), sets full to false. If array is not full, adds integer to tail, increments, and sets full to true
	 * if head=tail.
	 */
	@Override
	public void enqueue(int in) {
		if (full) {
			resizeArray();
			array[tail] = in;
			tail = next(tail);
			full = false;			
		} else {
			array[tail] = in;
			tail = next(tail);
			if (head == tail) {
				full = true;
			}
		}
		
	}

	/**
	 * dequeue. If array is empty, throws exception, else, returns integer at <head>
	 */
	@Override
	public int dequeue() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			int i = array[head];
			//array[head] = -1;
			head = next(head);
			full = false;
			return i;
		}
	}

	/**
	 * noItems. If array is empty return 0, else calculate number of items and return number
	 */
	@Override
	public int noItems() {
		if (this.isEmpty()) return 0;
		if (tail > head) {
			return tail - head;
		} else {
			return arraySize - head + tail;
		}
	}

	/**
	 * isEmpty - returns true if head=tail and array is not full
	 */
	@Override
	public boolean isEmpty() {
		if (head == tail && !full) return true;
		else return false;
	}
	
	/**
	 * getCapacityLeft - returns remaining spaces in array
	 */
	public int getCapacityLeft() {
		return arraySize - this.noItems();
	}
	
	/**
	 * next - Takes current index, returns next in queue.
	 */
	private int next(int current) {
		if (current == arraySize-1) {
			return 0;
		} else {
			return ++current;
		}
	}	
	
	/**
	 * resizeArray. Adds all elements from array, from head to tail, to tempArray which is double the size, then sets array:=tempArray
	 */
	private void resizeArray() {
		int[] tempArray = new int[arraySize*2];
		int tempTail = 0;
		int i = head;
		boolean x = true;
		while (i != tail || x) {
			x = false;
			tempArray[tempTail] = array[i];
			tempTail++;
			i = next(i);
		}
		arraySize *= 2;
		array = new int[arraySize];
		array =	tempArray;
		tail = tempTail;
		head = 0;
	}
	
}
