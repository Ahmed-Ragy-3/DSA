package Sortings;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * RadixSort is a non-comparison-based, stable sorting algorithm that sorts
 * integers by processing digits individually. It works by distributing numbers
 * into buckets according to each digit, from least to most significant.
 * <p>
 * This implementation handles both positive and negative integers using
 * separate buckets to maintain correct order.
 * <p>
 * Time Complexity:
 * - Best, Average, and Worst Case: O(nk), where n is the number of elements and
 *   k is the number of digits in the largest number.
 * <p>
 * Space Complexity:
 * - O(n + k), for the buckets and temporary arrays used in each pass.
 * <p>
 * Note: RadixSort does not support custom Comparators due to its non-comparison nature.
 *
 * @param <T> The type of elements to be sorted, must extend Number and implement Comparable.
 */
public class RadixSort<T extends Number & Comparable<? super T>> {

    private static final int BASE = 10; // Base-10 system
    private final Queue<T>[] positiveBuckets;
    private final Queue<T>[] negativeBuckets;

    /**
     * Constructs a RadixSort instance with initialized positive and negative buckets.
     */
    @SuppressWarnings("unchecked")
    public RadixSort() {
        positiveBuckets = (Queue<T>[]) new Queue[BASE];
        negativeBuckets = (Queue<T>[]) new Queue[BASE];
        for (int i = 0; i < BASE; i++) {
            positiveBuckets[i] = new LinkedList<>();
            negativeBuckets[i] = new LinkedList<>();
        }
    }

    /**
     * Sorts the given array using RadixSort with natural ordering.
     *
     * @param arr The array to be sorted.
     */
    public static <T extends Number & Comparable<? super T>> void sort(T[] arr) {
        if (arr.length == 0)
            return;

        long maxValue = 0;
        for (T num : arr) {
            maxValue = Math.max(maxValue, Math.abs(num.longValue()));
        }

        RadixSort<T> sorter = new RadixSort<>();
        for (long place = 1; maxValue / place > 0; place *= BASE) {
            sorter.distribute(arr, place);
            sorter.collect(arr);
        }
    }

    /**
     * Throws UnsupportedOperationException because RadixSort doesn't support custom comparators.
     *
     * @param arr        The array to be sorted.
     * @param comparator The comparator to define element order.
     */
    public static <T extends Number & Comparable<? super T>> void sort(T[] arr, Comparator<? super T> comparator) {
        throw new UnsupportedOperationException("RadixSort does not support custom comparators.");
    }

    /**
     * Distributes elements from the array into buckets based on the digit at the specified place.
     *
     * @param arr   The array of elements to distribute.
     * @param place The current digit place (1s, 10s, 100s, etc.).
     */
    private void distribute(T[] arr, long place) {
        for (T num : arr) {
            long digit = Math.abs(num.longValue() / place) % BASE;
            if (num.longValue() < 0) {
                negativeBuckets[(int) digit].add(num);
            } else {
                positiveBuckets[(int) digit].add(num);
            }
        }
    }

    /**
     * Collects elements from buckets and places them back into the array.
     * Negative buckets are collected in reverse order, followed by positive buckets in ascending order.
     *
     * @param arr The array to be updated with sorted elements.
     */
    private void collect(T[] arr) {
        int index = 0;

        // Collect negative numbers (in reverse)
        for (int i = BASE - 1; i >= 0; i--) {
            while (!negativeBuckets[i].isEmpty()) {
                arr[index++] = negativeBuckets[i].poll();
            }
        }

        // Collect positive numbers
        for (int i = 0; i < BASE; i++) {
            while (!positiveBuckets[i].isEmpty()) {
                arr[index++] = positiveBuckets[i].poll();
            }
        }
    }
}
