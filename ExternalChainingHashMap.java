import java.util.NoSuchElementException;

/**
 * Your implementation of a ExternalChainingHashMap.
 */
public class ExternalChainingHashMap<K, V> {

	/*
	 * The initial capacity of the ExternalChainingHashMap when created with the
	 * default constructor.
	 *
	 * DO NOT MODIFY THIS VARIABLE!
	 */
	public static final int INITIAL_CAPACITY = 13;

	/*
	 * The max load factor of the ExternalChainingHashMap.
	 *
	 * DO NOT MODIFY THIS VARIABLE!
	 */
	public static final double MAX_LOAD_FACTOR = 0.67;

	/*
	 * Do not add new instance variables or modify existing ones.
	 */
	private ExternalChainingMapEntry<K, V>[] table;
	private int size;

	/**
	 * Constructs a new ExternalChainingHashMap with an initial capacity of INITIAL_CAPACITY.
	 */
	public ExternalChainingHashMap() {
		//DO NOT MODIFY THIS METHOD!
		table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
	}

	/**
	 * Adds the given key-value pair to the map. If an entry in the map
	 * already has this key, replace the entry's value with the new one
	 * passed in.
	 *
	 * In the case of a collision, use external chaining as your resolution
	 * strategy. Add new entries to the front of an existing chain, but don't
	 * forget to check the entire chain for duplicate keys first.
	 *
	 * If you find a duplicate key, then replace the entry's value with the new
	 * one passed in. When replacing the old value, replace it at that position
	 * in the chain, not by creating a new entry and adding it to the front.
	 *
	 * Before actually adding any data to the HashMap, you should check to
	 * see if the table would violate the max load factor if the data was
	 * added. Resize if the load factor (LF) is greater than max LF (it is
	 * okay if the load factor is equal to max LF). For example, let's say
	 * the table is of length 5 and the current size is 3 (LF = 0.6). For
	 * this example, assume that no elements are removed in between steps.
	 * If another entry is attempted to be added, before doing anything else,
	 * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
	 * It is, so you would trigger a resize before you even attempt to add
	 * the data or figure out if it's a duplicate. Be careful to consider the
	 * differences between integer and double division when calculating load
	 * factor.
	 *
	 * When regrowing, resize the length of the backing table to
	 * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
	 *
	 * @param key   The key to add.
	 * @param value The value to add.
	 * @return null if the key was not already in the map. If it was in the
	 *         map, return the old value associated with it.
	 * @throws java.lang.IllegalArgumentException If key or value is null.
	 */
	public V put(K key, V value) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		if(key == null || value == null) {	// check if key and value are valid
			throw new IllegalArgumentException();
		} else {
			if(((double) (size + 1)) / table.length > MAX_LOAD_FACTOR) {	// check if max load factor has been reached
				resizeBackingTable(table.length * 2 + 1);	// call private method to resize the table
			}
			int index = getIndex(key);
			if(table[index] == null) {	// if the index is null, simply add to the index
				table[index] = new ExternalChainingMapEntry<K, V>(key, value);
				size++;
				return null;
			} else {
				ExternalChainingMapEntry<K,V> current = table[index];	// else, loop through the chain to look for duplicates first
				while(current != null) {
					if(key.equals(current.getKey())) {	// if a duplicate is found, set the value to new value and return the old value
						V oldValue = current.getValue();
						current.setValue(value);
						return oldValue;
					}
					current = current.getNext();
				}
				ExternalChainingMapEntry<K,V> toAdd = new ExternalChainingMapEntry<K,V>(key, value);	// if a duplicate is not found, add the new key value pair to the front of the chain
				toAdd.setNext(table[index]);
				table[index] = toAdd;
				size++;
				return null;
			}
		}
	}

	/**
	 * Removes the entry with a matching key from the map.
	 *
	 * @param key The key to remove.
	 * @return The value associated with the key.
	 * @throws java.lang.IllegalArgumentException If key is null.
	 * @throws java.util.NoSuchElementException   If the key is not in the map.
	 */
	public V remove(K key) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		if(key == null) {
			throw new IllegalArgumentException();
		} else {
			int index = getIndex(key);
			if(table[index] == null) {	// if the index is null, key is not found
				throw new NoSuchElementException();
			} else if (key.equals(table[index].getKey())) {	// if the key is found at the index, return the value
				V value = table[index].getValue();
				table[index] = table[index].getNext();
				size--;
				return value;
			} else {
				ExternalChainingMapEntry<K,V> current = table[index];	// else, loop through the chain to find the key
				while(current.getNext() != null) {
					if(current.getNext().getKey() != null && key.equals(current.getNext().getKey())) {
						V value = current.getNext().getValue();
						current.setNext(current.getNext().getNext());
						size--;
						return value;
					}
				}
				throw new NoSuchElementException();	// if we've reached the end of the chain and can't find key, key is not in the map
			}
		}
	}


	/**
	 * Helper method stub for resizing the backing table to length.
	 *
	 * This method should be called in put() if the backing table needs to
	 * be resized.
	 *
	 * You should iterate over the old table in order of increasing index and
	 * add entries to the new table in the order in which they are traversed.
	 *
	 * Since resizing the backing table is working with the non-duplicate
	 * data already in the table, you won't need to explicitly check for
	 * duplicates.
	 *
	 * Hint: You cannot just simply copy the entries over to the new table.
	 *
	 * @param Length The new length of the backing table.
	 */
	private void resizeBackingTable(int length) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		ExternalChainingMapEntry<K,V>[] tempTable = (ExternalChainingMapEntry<K,V>[]) new ExternalChainingMapEntry[length];	// create a temp table
		for(int i = 0; i < table.length; i++) {
			if(table[i] != null) {
				ExternalChainingMapEntry<K,V> current = table[i];
				while(current != null) {
					int newIndex = Math.abs(current.getKey().hashCode() % length);	// get the key, hash it, compress it
					tempTable[newIndex] = new ExternalChainingMapEntry<K,V>(current.getKey(), current.getValue());	// add current key value pair to the temp table
					current = current.getNext();
				}
			}
		}
		table = tempTable;
	}
	
	/**
	 * Returns the hashed index given a key
	 * 
	 * @param key
	 * @return
	 */
	private int getIndex(K key) {
		return Math.abs(key.hashCode() % table.length);	// hash the key, compress it, take the absolute to get the index
	}

	/**
	 * Returns the table of the map.
	 *
	 * For grading purposes only. You shouldn't need to use this method since
	 * you have direct access to the variable.
	 *
	 * @return The table of the map.
	 */
	public ExternalChainingMapEntry<K, V>[] getTable() {
		// DO NOT MODIFY THIS METHOD!
		return table;
	}

	/**
	 * Returns the size of the map.
	 *
	 * For grading purposes only. You shouldn't need to use this method since
	 * you have direct access to the variable.
	 *
	 * @return The size of the map.
	 */
	public int size() {
		// DO NOT MODIFY THIS METHOD!
		return size;
	}
	
	public String toString() {
		String mapString = "[";
		for(int i = 0; i < table.length; i++) {
			if(table[i] != null) {
				ExternalChainingMapEntry<K,V> current = table[i];
				while(current != null) {
					mapString += current.toString();
					current = current.getNext();
				}
			} else {
				mapString += "null";
			}
			mapString += "\n";
		}
		mapString += "]";
		return mapString;
	}
	
	public static void main(String[] args) {
		ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
		map.put(0, 0);	// add at index 0
		System.out.println(map);
		map.put(13, 13);
		System.out.println(map);	// add at index 0 in front of (0,0)	
		System.out.println("size: " + map.size());	// size should be 2
		map.put(2, 2);	// add at index 2
		map.put(15, 15);	// add at index 2 in front of (2,2)
		map.put(2, -2);	// update the value associated with key 2 to -2
		map.put(3, 3);
		map.put(4, 4);
		map.put(5, 5);
		map.put(6, 6);
		System.out.println(map);	// no resize at this point (load factor = 8/13 = 0.62)
		map.put(7, 7);
		System.out.println(map);	// max load factor is reached (load factor = 9/13 = 0.69 > 0.67). RESIZE and reallocate the k,v pairs
		System.out.println("size: " + map.size());	// size should be 9
		System.out.println("table length: " + map.getTable().length);	// table length should be 13*2+1 = 27
		map.put(27, 27);	// add 27 at index 0 in front of (0,0)
		map.put(54, 54);	// add 54 at index 0 in front of (27,27)
		System.out.println(map);
		System.out.println("size: " + map.size());	// size should be 11
		map.remove(54);
		System.out.println(map);	// remove 54 from the table, 27 and 0 should remain
		System.out.println("size: " + map.size());	// size should be 10 
		map.remove(0);	// remove 0 from the end of the chain, 27 should remain
		System.out.println(map);
		map.remove(27);	// remove 27 from the table, leaving a null at index 0
		System.out.println(map);
		map.remove(29);	// 29 is not present, throw an exception
	}
}