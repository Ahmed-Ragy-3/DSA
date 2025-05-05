package Sortings;

import java.util.Arrays;
import java.util.Comparator;

/**
 * CountingSort is an efficient, non-comparison-based sorting algorithm
 * that works by counting the occurrences of elements within a known range.
 * It is especially effective when sorting numbers within a small range.
 *
 * <p>
 * <b>Time Complexity:</b>
 * </p>
 * - Best, Average, and Worst Case: O(n + k), where n is the number of elements
 * and k is the range of the input values.
 *
 * <p>
 * <b>Space Complexity:</b>
 * </p>
 * - O(k) where k is the range of the input values.
 *
 * <p>
 * This implementation supports generic types that extend Number and Comparable.
 * </p>
 *
 * @param <T> The type of elements to be sorted, which must be a Number and
 *            extend Comparable.
 */
public class CountingSort<T extends Number & Comparable<? super T>> {

    private CountingSort() {}

    /**
     * Sorts the given array using Counting Sort.
     * If the range of values exceeds {@code Integer.MAX_VALUE}, it falls back to
     * using Arrays.sort().
     *
     * @param arr The array to be sorted.
     */
    public static <T extends Number & Comparable<? super T>> void sort(T[] arr) {
        if (arr.length == 0)
            return;

        int minValue = min(arr);
        int maxValue = max(arr);
        long range = (long) maxValue - minValue + 1;

        // Check if the range exceeds Integer.MAX_VALUE (practical limitation)
        if (range > Integer.MAX_VALUE) {
            Arrays.sort(arr);
            return;
        }

        // Create the counting array
        int[] count = new int[(int) range];

        // Count occurrences
        for (T num : arr) {
            count[num.intValue() - minValue]++;
        }

        // Reconstruct the sorted array
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                arr[index++] = toT(i + minValue, arr[0]);
            }
        }
    }

    /**
     * Throws an exception since Counting Sort does not support custom comparators.
     *
     * @param arr        The array to be sorted.
     * @param comparator The comparator defining the sorting order.
     * @throws UnsupportedOperationException Always thrown.
     */
    public static <T> void sort(T[] arr, Comparator<? super T> comparator) {
        throw new UnsupportedOperationException("Counting Sort does not support custom comparators.");
    }

    /**
     * Finds the minimum value in the array.
     *
     * @param arr The input array.
     * @return The minimum integer value in the array.
     */
    private static <T extends Number> int min(T[] arr) {
        int minVal = arr[0].intValue();
        for (T num : arr) {
            minVal = Math.min(minVal, num.intValue());
        }
        return minVal;
    }

    /**
     * Finds the maximum value in the array.
     *
     * @param arr The input array.
     * @return The maximum integer value in the array.
     */
    private static <T extends Number> int max(T[] arr) {
        int maxVal = arr[0].intValue();
        for (T num : arr) {
            maxVal = Math.max(maxVal, num.intValue());
        }
        return maxVal;
    }

    /**
     * Converts an integer to the generic type T, assuming T extends Number.
     *
     * @param value The integer value to convert.
     * @param prototype A prototype of type T to use for conversion.
     * @return The converted value as type T.
     */
    @SuppressWarnings("unchecked")
    private static <T extends Number> T toT(int value, T prototype) {
        // This is a simplified placeholder for conversion logic, as generic types can't directly
        // be instantiated in this way. A specific subclass or logic may be needed for actual conversion.
        return (T) Integer.valueOf(value);
    }
}
