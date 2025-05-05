package Sortings;

import java.util.Comparator;

/**
 * HeapSort is an in-place, comparison-based sorting algorithm that builds a heap
 * from the input array and then repeatedly extracts the largest (or smallest)
 * element, ensuring a sorted order.
 *
 * <p>
 * <b>Time Complexity:</b>
 * </p>
 * - Best Case: O(n log n) when the heap is already balanced.
 * - Average Case: O(n log n) due to heapification and extraction.
 * - Worst Case: O(n log n) when the array is sorted in reverse order.
 *
 * <p>
 * <b>Space Complexity:</b>
 * </p>
 * - O(1) since sorting is performed in-place without additional memory usage.
 *
 * <p>
 * This implementation supports generic types by extending Comparable<? super
 * T> and using a custom comparator.
 * </p>
 *
 * @param <T> The type of elements to be sorted, which must extend Comparable.
 */
public class HeapSort {

    private HeapSort() {}

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Sorts the given array using Heap Sort with natural ordering.
     *
     * @param arr The array to be sorted.
     */
    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        sort(arr, Comparator.naturalOrder());
    }

    /**
     * Sorts the given array using Heap Sort with a custom comparator.
     *
     * @param arr        The array to be sorted.
     * @param comparator The comparator defining the sorting order.
     */
    public static <T> void sort(T[] arr, Comparator<? super T> comparator) {
        int n = arr.length;

        // Build the heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, comparator);
        }

        // Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i); // Move the root (max or min) to the end
            heapify(arr, i, 0, comparator); // Restore heap property
        }
    }

    /**
     * Maintains the heap property by ensuring the subtree rooted at index `i` is a
     * heap.
     *
     * @param arr        The array representing the heap.
     * @param n          The size of the heap.
     * @param i          The index of the root of the subtree.
     * @param comparator The comparator defining the sorting order.
     */
    private static <T> void heapify(T[] arr, int n, int i, Comparator<? super T> comparator) {
        int largest = i; // Assume root as largest
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && comparator.compare(arr[left], arr[largest]) > 0) {
            largest = left;
        }

        if (right < n && comparator.compare(arr[right], arr[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest, comparator);
        }
    }
}
