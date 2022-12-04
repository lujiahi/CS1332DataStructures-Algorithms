import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
	 * Implement merge sort.
	 *
	 * It should be:
	 * out-of-place
	 * stable
	 * not adaptive
	 *
	 * Have a worst case running time of: O(n log n)
	 * And a best case running time of: O(n log n)
	 *
	 * You can create more arrays to run merge sort, but at the end, everything
	 * should be merged back into the original T[] which was passed in.
	 *
	 * When splitting the array, if there is an odd number of elements, put the
	 * extra data on the right side.
	 *
	 * Hint: You may need to create a helper method that merges two arrays
	 * back into the original T[] array. If two data are equal when merging,
	 * think about which subarray you should pull from first.
	 *
	 * You may assume that the passed in array and comparator are both valid
	 * and will not be null.
	 *
	 * @param <T>        Data type to sort.
	 * @param arr        The array to be sorted.
	 * @param comparator The Comparator used to compare the data in arr.
	 */
	public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		mergeSortH(arr, comparator);
	}

	private static <T> T[] mergeSortH(T[] arr, Comparator<T> comparator) {
		if(arr.length <= 1) {	// base case: array has a single element
			return arr;
		} else {
			// split
			int midIndex = arr.length / 2;	// recursively split the array into halves until we reach the base case
			T[] leftArr = (T[]) new Object[midIndex];
			T[] rightArr = (T[]) new Object[arr.length - midIndex];
			for(int i = 0; i < midIndex; i++) {
				leftArr[i] = arr[i];
			}
			for(int i = 0; i < arr.length - midIndex; i++) {
				rightArr[i] = arr[i + midIndex];
			}
			leftArr = mergeSortH(leftArr, comparator);
			rightArr = mergeSortH(rightArr, comparator);
			
			//System.out.println("left: " + Arrays.toString(leftArr));
			//System.out.println("right: " + Arrays.toString(rightArr));

			// merge
			int leftPointer = 0;
			int rightPointer = 0;
			int mainPointer = 0;
			while(leftPointer < leftArr.length && rightPointer < rightArr.length) {	// compare the data until we have exhausted either the left or the right subarray
				if(comparator.compare(leftArr[leftPointer], rightArr[rightPointer]) <= 0) {	// if data pointed by the left pointer is not larger, copy it to the original array
					arr[mainPointer] = leftArr[leftPointer];
					leftPointer++;
				} else {	// if data pointed by the right pointer is smaller, copy it to the original array
					arr[mainPointer] = rightArr[rightPointer];
					rightPointer++;
				}
				mainPointer++;
			}
			if(leftPointer >= leftArr.length) {	// copy what's left in the right subarray to the original array
				for(int i = rightPointer; i < rightArr.length; i++) {
					arr[mainPointer] = rightArr[i];
					mainPointer++;
				}
			} else if(rightPointer >= leftArr.length) {	// copy what's left in the left subarray to the original array
				for(int i = leftPointer; i < leftArr.length; i++) {
					arr[mainPointer] = leftArr[i];
					mainPointer++;
				}
			}
			return arr;
		}
	}
	

	/**
	 * Implement LSD (least significant digit) radix sort.
	 *
	 * It should be:
	 * out-of-place
	 * stable
	 * not adaptive
	 *
	 * Have a worst case running time of: O(kn)
	 * And a best case running time of: O(kn)
	 *
	 * Feel free to make an initial O(n) passthrough of the array to
	 * determine k, the number of iterations you need.
	 *
	 * At no point should you find yourself needing a way to exponentiate a
	 * number; any such method would be non-O(1). Think about how how you can
	 * get each power of BASE naturally and efficiently as the algorithm
	 * progresses through each digit.
	 *
	 * You may use an ArrayList or LinkedList if you wish, but it should only
	 * be used inside radix sort and any radix sort helpers. Do NOT use these
	 * classes with merge sort. However, be sure the List implementation you
	 * choose allows for stability while being as efficient as possible.
	 *
	 * Do NOT use anything from the Math class except Math.abs().
	 *
	 * You may assume that the passed in array is valid and will not be null.
	 *
	 * @param arr The array to be sorted.
	 */
	public static void lsdRadixSort(int[] arr) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		int smallestNegative = Integer.MAX_VALUE;	// loop through the array first to look for the number with the most digits
		for(int i = 0; i < arr.length; i++) {
			if(negativeValue(arr[i]) < smallestNegative){	// look for the smallest negative because |Integer.MIN_VALUE| > |Integer.MAX_VALUE|
				smallestNegative = negativeValue(arr[i]);
			}
		}

		int numOfIterations = numOfDigits(smallestNegative);	// number with the most digits determines the number of iterations needed
		//System.out.println("num of iterations: " + numOfIterations);

		int base = 1;
		for(int i = 0; i < numOfIterations; i++) {	// outer loop: move from the least significant digit to the most significant digit

			List[] buckets = new ArrayList[19];	// initialize the new buckets first			
			for(int n = 0; n < 19; n++) {
				buckets[n] = new ArrayList<Integer>();
			}

			for(int j = 0; j < arr.length; j++) {	// inner loop 1: iterate through all the numbers to put them in the right bucket
				int bucketNo = (arr[j] / base) % 10;	// isolate the digit
				buckets[bucketNo + 9].add(arr[j]);	// right shift the bucket no.: bucket -9 will become bucket 0 etc
				//System.out.println(arr[j] + " added to bucket " + (bucketNo + 9));
			}

			int pointer = 0;
			for(int j = 0; j < 19; j++) {	// inner loop 2: iterate through the buckets to copy numbers back to the array
				if(buckets[j].size() > 0) {
					for(int n = 0; n < buckets[j].size(); n++) {
						arr[pointer] = (int) buckets[j].get(n);
						//System.out.println(buckets[j].get(n) + " added to array " + pointer);
						pointer++;
					}
				}
			}
			base *= 10;
		}
	}

	/**
	 * Private method to compute the absolute of the negation
	 * @param num
	 * @return
	 */
	private static int negativeValue(int num) {
		return num > 0 ? -num : num;
	}

	/**
	 * Private method to compute the number of digits of a give number
	 * @param num
	 * @return
	 */
	private static int numOfDigits(int num) {
		int numDigits = 1;
		while(num / 10 != 0) {
			num = num / 10;
			numDigits++;
		}
		return numDigits;
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

		// testing merge sort
		Integer[] intArr4 = new Integer[8];
		intArr4[0] = 7;
		intArr4[1] = 6;
		intArr4[2] = 5;
		intArr4[3] = 4;
		intArr4[4] = 3;
		intArr4[5] = 2;
		intArr4[6] = 1;
		intArr4[7] = 0;
		System.out.println("Before sorting: " + Arrays.toString(intArr4));
		mergeSort(intArr4, integerComparator);
		System.out.println("After merge sort: " + Arrays.toString(intArr4));
		
		// testing lsd radix sort
		int[] arr = new int[10];
		arr[0] = 258;
		arr[1] = 151;
		arr[2] = 104;
		arr[3] = -138;
		arr[4] = -785;
		arr[5] = -2147483648;
		arr[6] = 543;
		arr[7] = 908;
		arr[8] = -796;
		arr[9] = -800;
		System.out.println("Before sorting: " + Arrays.toString(arr));
		lsdRadixSort(arr);
		System.out.println("After LSD radix sort: " + Arrays.toString(arr));

		// testing sorting single-element arrays
		Integer[] intArr1 = new Integer[1];
		intArr1[0] = 100;
		System.out.println("\nHere is a single element array: " + Arrays.toString(intArr1));
		bubbleSort(intArr1, integerComparator);
		System.out.println("After bubble sort: " + Arrays.toString(intArr1));
		selectionSort(intArr1, integerComparator);
		System.out.println("After selection sort: " + Arrays.toString(intArr1));
		insertionSort(intArr1, integerComparator);
		System.out.println("After insertion sort: " + Arrays.toString(intArr1));
		mergeSort(intArr1, integerComparator);
		System.out.println("After merge sort: " + Arrays.toString(intArr1));
		int[] arr2 = new int[1];
		arr2[0] = 100;
		lsdRadixSort(arr2);
		System.out.println("After lsd radix sort: " + Arrays.toString(arr2));
		
		// testing sorting empty arrays
		Integer[] intArr0 = new Integer[0];
		System.out.println("\nHere is an empty array: " + Arrays.toString(intArr0));
		bubbleSort(intArr0, integerComparator);
		System.out.println("After bubble sort: " + Arrays.toString(intArr0));
		selectionSort(intArr0, integerComparator);
		System.out.println("After selection sort: " + Arrays.toString(intArr0));
		insertionSort(intArr0, integerComparator);
		System.out.println("After insertion sort: " + Arrays.toString(intArr0));
		mergeSort(intArr0, integerComparator);
		System.out.println("After merge sort: " + Arrays.toString(intArr0));
		int[] arr0 = new int[0];
		lsdRadixSort(arr0);
		System.out.println("After lsd radix sort: " + Arrays.toString(arr0));
	}
}