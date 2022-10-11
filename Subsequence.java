//Increasing Subsequences in Arrays
//Suppose that we have an array  of length  as input consisting of  distinct integers. 
//A subsequence of the array is a sequence of some of the array entries in the order that they appear.
//The problem we want to solve here is to count the number of increasing subsequences there are in the array.

import java.util.ArrayList;
import java.util.List;

public class Subsequence {
	public static List increasingSubsequence (List<Integer> arr) {
		if (arr.size() == 0) {	// if the list is empty, return the empty list (assume that an empty list is an increasingSubsequence)
			return arr;
		} else if (arr.size() == 1) {	// if the list has one number, return a list of two elements: an empty list and the number itself
			return List.of(new ArrayList(), arr);
		} else {
			int last = arr.get(arr.size() - 1);	// get the last number of the current list
			List newList = new ArrayList();
			List increasingSubsequence = increasingSubsequence(arr.subList(0, arr.size() - 1));	// recursively call this method while reducing the size of the list
			newList.addAll(increasingSubsequence);	// first copy all previous subsequences to the new list
			for (int i = 0; i < increasingSubsequence.size(); i++) {	// iterate through the previous subsequences
				if (((List) increasingSubsequence.get(i)).size() == 0) {	// if the current element is an empty list, add a new list with the current number
					newList.add(List.of(last));
				} else if (last > (int) ((List) increasingSubsequence.get(i)).get(((List) increasingSubsequence.get(i)).size() - 1)) {	// compare current number with the last item
																																		// in the current subsequence. If greater, a new 
																																		// increasingSubsequence is found.
					List newSubList = new ArrayList<>();
					newSubList.addAll((List) increasingSubsequence.get(i));
					newSubList.add(last);
					newList.add(newSubList);
				}
			}
			return newList;
		}
	}

	public static void main(String[] args) {
		List<Integer> arr = List.of(1,7,3,5,2,8,10,24,-1,-5,4,6);
		System.out.println(increasingSubsequence(arr));
		List<Integer> arr2 = List.of();
		System.out.println(increasingSubsequence(arr2));
		List<Integer> arr3 = List.of(0,1,2);
		System.out.println(increasingSubsequence(arr3));
	}
}