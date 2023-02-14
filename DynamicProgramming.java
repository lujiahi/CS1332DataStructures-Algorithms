import java.util.List;
import java.util.ArrayList;

public class DynamicProgramming {
	
	/**
	 * Largest Contiguous Sum
	 * Suppose we have an array A of integers of length n.
	 * We'd like to compute the largest contiguous sum of the array, 
	 * which is a block of numbers for which the sum is maximized. 
	 * In the worst case, you can always choose the empty sum, 
	 * which gives a value of 0. Come up with a dynamic programming
	 * algorithm to compute the value of the sum.
	 * @param arr
	 * @return
	 */ 
	public static int lcs(int[] arr) {
		int sumTilNow = 0;
		int lcs = 0;
		for(int i = 0; i < arr.length; i++) {
			sumTilNow += arr[i];
			if(sumTilNow < 0) {	// if sumTilNow becomes negative, we are better off discarding the sum and starting from 0
				sumTilNow = 0;
			}
			if(sumTilNow > lcs) {
				lcs = sumTilNow;
			}
		}
		return lcs;
	}
	
	/**
	 * Longest Increasing Subsequence
	 * Suppose that we have an array A of length n as input 
	 * consisting of integers. A subsequence of the array is
	 * a sequence of some of the array entries in the order 
	 * that they appear. An increasing subsequence is a 
	 * subsequence where the numbers go in (strictly) increasing
	 * order. Come up with a dynamic programming algorithm to 
	 * compute the length of the longest increasing subsequence
	 * of the array.
	 * @param arr
	 * @return
	 */
	public static int lis(int[] arr) {
		if(arr.length == 0) {
			return 0;
		}
		
		List<Integer> lis = new ArrayList<>(arr.length);	// let lis store the largest num in the longest increasing subsequence of length i
		lis.add(0, arr[0]);	// store the first number in lis
		int currentLisIndex = 0;
		
		for(int i = 1; i < arr.length; i++) {	// outer loop: nums in the array
			for(int j = 0; j <= currentLisIndex; j++) {	// inner loop: compare them to the largest nums in the lis one by one
				if(arr[i] > lis.get(j)) {	// if arr[i] is bigger than the largest num
					if(j == currentLisIndex) {	// then we extend the lis and add the num to lis
						currentLisIndex++;
						lis.add(currentLisIndex, arr[i]);
						break;
					}
				} else if(arr[i] <= lis.get(j)) {	// if arr[i] is not bigger than the largest num in lis
					lis.set(j, arr[i]);	// we set the largest num in lis to arr[i]
					break;
				}
			}
		}
		
		return lis.size();
		
	}
	
	public static void main(String[] args) {
		// testing largest contiguous sum
		int[] arrr = {1,2,3,4,5};
		System.out.println(lcs(arrr));	//15
		
		int[] arrr1 = {-1,-2,-3,-4,-5};
		System.out.println(lcs(arrr1));	//0
		
		int[] arrr2 = {1,-2,3,-4,5};
		System.out.println(lcs(arrr2));	//5
		
		int[] arrr3 = {1,-2,0,-4,5};
		System.out.println(lcs(arrr3));	//5
		
		int[] arrr4 = {5};
		System.out.println(lcs(arrr4));	//5
		
		int[] arrr5 = {0,0,0,0};
		System.out.println(lcs(arrr5));	//0
		
		int[] arrr6 = {};
		System.out.println(lcs(arrr6));	//0
		
		// testing longest increasing subsequence
		int[] arr1 = {1,2,3,4,5};
		System.out.println(lis(arr1));	//5
		
		int[] arr2 = {};
		System.out.println(lis(arr2));	//0
		
		int[] arr3 = {5,4,3,2,1};
		System.out.println(lis(arr3));	//1
		
		int[] arr4 = {3,4,1,2,5};
		System.out.println(lis(arr4));	//3
		
		int[] arr5 = {1,2,2,3,4,4,5};
		System.out.println(lis(arr5));	//5
		
		int[] arr6 = {10,22,9,33,21,50,41,60,80};
		System.out.println(lis(arr6));	//6
	}

}
