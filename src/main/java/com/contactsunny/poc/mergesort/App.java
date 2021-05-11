package com.contactsunny.poc.mergesort;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        int[] array = {3, 2, 1, 2, 6};
        System.out.println("Given array: " + Arrays.toString(array));
        array = mergeSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }

    /**
     * Wrapper method to start the merge sort process.
     *
     * @param array The array to be sorted, or the unsorted input array.
     *
     * @return The sorted array
     */
    private static int[] mergeSort(int[] array) {
        // Creating a new array to hold the sorted array.
        int[] result = new int[array.length];
        // Calling the merge sort method to actually sort the array
        mergeSort(array, 0, array.length - 1, result);
        // Returning the sorted array
        return result;
    }

    /**
     * Method which actually performs the sorting.
     *
     * @param array The input unsorted array
     * @param left The left index of the sub-array
     * @param right The right index of the sub-array
     *
     * @param result The sorted array.
     */
    private static void mergeSort(int[] array, int left, int right, int[] result) {
        // Checking if we're crossing the boundary
        if (left < right) {
            // Getting the middle index when the split will happen.
            int middle = (left + right) / 2;
            // Calling the method recursively to sort the left sub-array.
            mergeSort(array, left, middle, result);
            // Calling the method recursively to sort the right sub-array.
            mergeSort(array, middle + 1, right, result);

            // Calling the merge method to merge the two sorted arrays.
            merge(array, left, middle, right, result);
        }
    }

    /**
     * Method to merge two sorted arrays.
     *
     * @param array The array with two sorted sub-arrays.
     * @param left The left index
     * @param middle The middle index which separates the two arrays
     * @param right The right index
     *
     * @param result The merged array
     */
    private static void merge(int[] array, int left, int middle, int right, int[] result) {
        // Creating new indices which we'll need in the merge algorithm
        int index1 = left;
        int index2 = middle + 1;
        // This is the index for the resultant array which will
        // have the merge of the two sorted arrays.
        int resultIndex = left;

        // Looping through both the sub-arrays with two pointers.
        // Also making sure that none of them cross the boundary.
        while ((index1 <= middle) && (index2 <= right)) {
            // Checking if the element in the first array is less or equal to that in the second.
            // If so, we'll copy that to the result array and increment the index of the first array.
            if (array[index1] <= array[index2]) {
                result[resultIndex] = array[index1];
                index1++;
            } else {
                // If not, we'll copy the element from the second array into the result array
                // and increment the index of the second array.
                result[resultIndex] = array[index2];
                index2++;
            }
            // Increment the index of the result array.
            resultIndex++;
        }

        // We'll loop through the first array in case there were some elements left out in the
        // first loop. If so, we'll just copy them to the result array. This will still
        // keep the result array sorted.
        while (index1 <= middle) {
            result[resultIndex] = array[index1];
            index1++;
            resultIndex++;
        }

        // We'll loop through the second array in case there were some elements left out in the
        // first loop. If so, we'll just copy them to the result array. This will still
        // keep the result array sorted.
        while (index2 <= right && resultIndex < array.length) {
            result[resultIndex] = array[index2];
            index2++;
            resultIndex++;
        }

        // We'll finally copy all the elements we sorted in this method call
        // from the result array back to the given array.
        while (left <= right) {
            array[left] = result[left];
            left++;
        }
    }
}
