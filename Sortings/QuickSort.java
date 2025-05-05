package Sortings;

import java.util.Comparator;
import java.util.Random;

/**
 * QuickSort is a highly efficient, comparison-based, divide-and-conquer sorting
 * algorithm. It works by selecting a pivot element from the array and partitioning
 * the other elements into two sub-arrays: elements smaller than the pivot and
 * elements greater than the pivot. The sub-arrays are then sorted recursively.
 * 
 * This implementation uses a randomized pivot selection to improve performance
 * on nearly sorted or identical elements, helping to avoid the worst-case scenario.
 * 
 * Time Complexity:
 * - Best Case: O(n log n)
 * - Average Case: O(n log n)
 * - Worst Case: O(n²)
 * 
 * Space Complexity:
 * - O(log n) due to recursive calls.
 */
public class QuickSort {

    private static final Random random = new Random();

    private QuickSort() {}

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Sorts the given array using QuickSort with natural ordering.
     *
     * @param arr The array to be sorted.
     */
    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        sort(arr, Comparator.naturalOrder());
    }

    /**
     * Sorts the given array using QuickSort with a custom comparator.
     *
     * @param arr        The array to be sorted.
     * @param comparator The comparator to determine element order.
     */
    public static <T> void sort(T[] arr, Comparator<? super T> comparator) {
        recSort(arr, 0, arr.length - 1, comparator);
    }

    /**
     * Recursively sorts the subarray between left and right indices.
     */
    private static <T> void recSort(T[] arr, int left, int right, Comparator<? super T> comparator) {
        if (left < right) {
            // Partition the array and get the pivot index
            int index = partition(arr, left, right, comparator);

            // Recursively sort the two partitions
            recSort(arr, left, index - 1, comparator);
            recSort(arr, index + 1, right, comparator);
        }
    }

    /**
     * Partitions the array around a pivot chosen at random.
     */
    private static <T> int partition(T[] arr, int left, int right, Comparator<? super T> comparator) {
        int randomIndex = random.nextInt(right - left + 1) + left;
        swap(arr, randomIndex, right);
        T pivot = arr[right];
        int index = left;

        for (int i = left; i < right; i++) {
            if (comparator.compare(arr[i], pivot) < 0) {
                swap(arr, i, index++);
            }
        }

        swap(arr, index, right);
        return index;
    }
}
