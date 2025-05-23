package Sortings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Implements the Merge Sort algorithm, a divide-and-conquer sorting method
 * that recursively splits an array into smaller subarrays, sorts them, and
 * merges them back.
 *
 * @param <T> The type of elements to be sorted, which must extend Comparable.
 */
public class MergeSort {

    private MergeSort() {}

    /**
     * Sorts the given array using Merge Sort with natural ordering.
     *
     * @param arr The array to be sorted.
     */
    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        sort(arr, Comparator.naturalOrder());
    }

    /**
     * Sorts the given array using Merge Sort with a custom comparator.
     *
     * @param arr        The array to be sorted.
     * @param comparator Comparator defining the sorting order.
     */
    public static <T> void sort(T[] arr, Comparator<? super T> comparator) {
        divide(arr, 0, arr.length - 1, comparator);
    }

    /**
     * Recursively divides the array into smaller subarrays until each subarray
     * contains one element,
     * then merges them back in sorted order.
     *
     * @param arr        The array to be sorted.
     * @param l          The leftmost index of the current subarray.
     * @param r          The rightmost index of the current subarray.
     * @param comparator Comparator defining the sorting order.
     */
    private static <T> void divide(T[] arr, int l, int r, Comparator<? super T> comparator) {
        if (l < r) {
            int m = (l + r) / 2;
            divide(arr, l, m, comparator);
            divide(arr, m + 1, r, comparator);
            merge(arr, l, m, r, comparator);
        }
    }

    /**
     * Merges two sorted subarrays into a single sorted subarray using natural
     * ordering.
     *
     * @param arr        The original array containing the subarrays.
     * @param l          The starting index of the left subarray.
     * @param m          The middle index separating the subarrays.
     * @param r          The ending index of the right subarray.
     * @param comparator Comparator defining the sorting order.
     */
    private static <T> void merge(T[] arr, int l, int m, int r, Comparator<? super T> comparator) {
        int i = l;
        int j = m + 1;
        List<T> list = new ArrayList<>();

        while (i <= m && j <= r) {
            if (comparator.compare(arr[i], arr[j]) <= 0) {
                list.add(arr[i++]);
            } else {
                list.add(arr[j++]);
            }
        }
        while (i <= m) {
            list.add(arr[i++]);
        }
        while (j <= r) {
            list.add(arr[j++]);
        }

        for (int k = l; k <= r; k++) {
            arr[k] = list.get(k - l);
        }
    }
}
