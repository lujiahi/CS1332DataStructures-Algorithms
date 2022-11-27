import java.util.Arrays;
import java.util.Comparator;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    	int lastSwapped = arr.length - 1;	// data beyond this point is sorted
		int endIndex;
    	do {
    		endIndex = lastSwapped;
    		for(int j = 0; j < endIndex; j++) {	// inner loop from 0 to just before the last swapped index
    			if(comparator.compare(arr[j], arr[j + 1]) > 0) {
    				T temp = arr[j];
    				arr[j] = arr[j + 1];
    				arr[j + 1] = temp;
    				lastSwapped = j;
    			}
    		}
    	} while(lastSwapped != endIndex && lastSwapped > 0);	// continue the outer loop until there is no swaps in the current iteration or if reaches the front
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    	for(int i = arr.length - 1; i > 0; i--) {	// outer loop from index last to index 1
    		int maxInd = 0;
    		for(int j = 1; j <= i; j++) {
    			if(comparator.compare(arr[j], arr[maxInd]) > 0) {	// inner loop finds the max
    				maxInd = j;
    			}
    		}
    		T temp = arr[maxInd];	// swaps data at i with the max data
    		arr[maxInd] = arr[i];
    		arr[i] = temp;
    	}
    	
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    	for(int i = 1; i < arr.length; i++) {	// outer loop from index 1 to index last
    		int j = i;
    		while((j - 1 >= 0) && (comparator.compare(arr[j], arr[j - 1]) < 0)){	// inner loop compares and swaps each data with its previous until no swap is needed or reaches the front
    			T temp = arr[j];
    			arr[j] = arr[j - 1];
    			arr[j - 1] = temp;
    			j--;
    		}
    	}
    }
    
    /**
     * Inner class to define a simple integer comparator
     *
     */
    static class IntegerComparator implements Comparator<Integer>{
    	@Override
    	public int compare(Integer int1, Integer int2) {
    		return int1.compareTo(int2);
    	}
    }
    
    /**
     * Main method to test the sorting algorithms
     * 
     */
    public static void main(String[] args) {
    	// testing bubble sort
    	Integer[] intArr = new Integer[8];
    	intArr[0] = 7;
    	intArr[1] = 5;
    	intArr[2] = 1;
    	intArr[3] = 3;
    	intArr[4] = 4;
    	intArr[5] = 6;
    	intArr[6] = 2;
    	intArr[7] = 0;
    	System.out.println("Before sorting: " + Arrays.toString(intArr));
    	IntegerComparator integerComparator = new IntegerComparator();
    	bubbleSort(intArr, integerComparator);
    	System.out.println("After bubble sort: " + Arrays.toString(intArr));
    	
    	// testing selection sort
    	Integer[] intArr2 = new Integer[8];
    	intArr2[0] = 5;
    	intArr2[1] = 1;
    	intArr2[2] = 7;
    	intArr2[3] = 6;
    	intArr2[4] = 4;
    	intArr2[5] = 0;
    	intArr2[6] = 2;
    	intArr2[7] = 3;
    	System.out.println("Before sorting: " + Arrays.toString(intArr2));
    	selectionSort(intArr2, integerComparator);
    	System.out.println("After selection sort: " + Arrays.toString(intArr2));
    	
    	// testing insertion sort
    	Integer[] intArr3 = new Integer[8];
    	intArr3[0] = 7;
    	intArr3[1] = 6;
    	intArr3[2] = 5;
    	intArr3[3] = 4;
    	intArr3[4] = 3;
    	intArr3[5] = 2;
    	intArr3[6] = 1;
    	intArr3[7] = 0;
    	System.out.println("Before sorting: " + Arrays.toString(intArr3));
    	insertionSort(intArr3, integerComparator);
    	System.out.println("After insertion sort: " + Arrays.toString(intArr3));
    }
}