package Sortings;

import java.util.Comparator;
import java.util.Random;

// import sort.SortUtil;

/**
 * QuickSort is a highly efficient, comparison-based, divide-and-conquer sorting
 * algorithm.
 * It works by selecting a pivot element from the array and partitioning the
 * other elements
 * into two sub-arrays: elements smaller than the pivot and elements greater
 * than the pivot.
 * The sub-arrays are then sorted recursively.
 * <p>
 * This implementation uses a randomized pivot selection to improve performance
 * on nearly
 * sorted or identical elements, which helps avoid the worst-case scenario.
 * <p>
 * Time Complexity:
 * - Best Case: O(n log n) when the pivot divides the array evenly.
 * - Average Case: O(n log n) for a random distribution of elements.
 * - Worst Case: O(n²) when the smallest or largest element is always selected
 * as the pivot
 * (without randomization).
 * <p>
 * Space Complexity:
 * - O(log n) due to the recursive stack calls.
 * <p>
 * This implementation supports generic types by extending Comparable<T>.
 *
 * @param <T> The type of elements to be sorted, must implement Comparable<T>.
 */
public class QuickSort {

    private final Random random = new Random();

    private QuickSort() {
    }

    /**
     * Sorts the given array using QuickSort with natural ordering.
     *
     * @param arr The array to be sorted.
     */
    @Override
    public static void sort(T[] arr) {
        recSort(arr, 0, arr.length - 1, Comparator.naturalOrder());

    }

    /**
     * Sorts the given array using QuickSort and a custom comparator.
     *
     * @param arr        The array to be sorted.
     * @param comparator The comparator to determine element order.
     */
    @Override
    public static void sort(T[] arr, Comparator<? super T> comparator) {
        recSort(arr, 0, arr.length - 1, comparator);
    }

    /**
     * Recursive function to perform QuickSort on a portion of the array.
     *
     * @param arr        Array to sort.
     * @param left       Left index of the current partition range.
     * @param right      Right index of the current partition range.
     * @param comparator Custom comparator to define sorting order.
     */
    private void recSort(T[] arr, int left, int right, Comparator<? super T> comparator) {
        if (left < right) {

            // Partition the array and get the pivot index
            int index = partition(arr, left, right, comparator);

            // Recursively sort the two partitions
            sort(arr, left, index - 1, comparator);
            sort(arr, index + 1, right, comparator);
        }
    }

    /**
     * Partitions the array using a randomly selected pivot and the provided
     * comparator.
     *
     * @param arr        Array to partition.
     * @param left       Left index of the partition range.
     * @param right      Right index of the partition range.
     * @param comparator Comparator to use for element comparison.
     * @return The final index of the pivot.
     */
    private int partition(T[] arr, int left, int right, Comparator<? super T> comparator) {
        int randomIndex = random.nextInt(right - left + 1) + left;
        SortUtil.swap(arr, randomIndex, right);
        T pivot = arr[right];
        int index = left;

        for (int i = left; i < right; i++) {
            if (comparator.compare(arr[i], pivot) < 0) {
                SortUtil.swap(arr, i, index);
                index++;
            }
        }

        SortUtil.swap(arr, index, right);
        return index;
    }
}
