/**
 * Merge sort class.
 */
public class MergeSort {
  /**
   * Merge sort the provided array.
   */
  public static <T extends Comparable<T>> void mergeSort(T[] items) {
    mergeSort(items, 0, items.length - 1);
  }     

  /**
   * Recursive helper method for the merge sort algorithm.
   *
   * @param items The array to sort
   * @param start Index of the left end of the region to sort
   * @param end Index of the right end of the region to sort.
   */
  private static <T extends Comparable<T>> void mergeSort(T[] items, int start, int end) {
    if (start < end) {
      int mid = (start + end) / 2;
      mergeSort(items, start, mid);
      mergeSort(items, mid + 1, end);
      merge(items, start, mid, end);
    }
  }

  /**
   * Merge two sorted sub-arrays.
   */
  private static <T extends Comparable<T>> void merge(T[] items, int start, int mid, int end) {
    int mergedSize = end - start + 1;

    @SuppressWarnings("unchecked")
    T[] mergedItems = (T[]) new Comparable[mergedSize]; // temp array to merge into
    int tmpIndex = 0;
    int mergeIndex = start;
    int rightIndex = mid + 1;

    // Add smallest element from left or right partition to merged items
    while (mergeIndex <= mid && rightIndex <= end) {
      if (items[mergeIndex].compareTo(items[rightIndex]) <= 0) {
        mergedItems[tmpIndex] = items[mergeIndex];
        mergeIndex++;
      } else {
        mergedItems[tmpIndex] = items[rightIndex];
        rightIndex++;
      }
      tmpIndex++;
    }

    // If left partition is not empty, add remaining elements to merged numbers
    while (mergeIndex <= mid) {
      mergedItems[tmpIndex] = items[mergeIndex];
      mergeIndex++;
      tmpIndex++;
    }

    // If right partition is not empty, add remaining elements to merged numbers
    while (rightIndex <= end) {
      mergedItems[tmpIndex] = items[rightIndex];
      rightIndex++;
      tmpIndex++;
    }

    // Copy merged numbers back to numbers
    for (tmpIndex = 0; tmpIndex < mergedSize; tmpIndex++) {
      items[start + tmpIndex] = mergedItems[tmpIndex];
    }
  }
}
