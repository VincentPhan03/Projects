/**
 * Improved MergeSort methods.
 * 
 * @author phanvm
 * @version PA 2
 * 
 *          This work complies with JMU's honor code.
 */
public class MergeSortImproved {
  private static final int THRESHOLD = 177;


  /**
   * Merge sort the provided array using an improved merge operation.
   */
  public static <T extends Comparable<T>> void mergeSortHalfSpace(T[] items) {
    mergeSortHalfSpace(items, 0, items.length - 1);
  }

  /**
   * Recursive helper method for the merge sort algorithm.
   *
   * @param items The array to sort
   * @param start Index of the left end of the region to sort
   * @param end Index of the right end of the region to sort.
   */
  private static <T extends Comparable<T>> void mergeSortHalfSpace(T[] items, int start, int end) {
    if (start < end) {
      int mid = (start + end) / 2;
      mergeSortHalfSpace(items, start, mid);
      mergeSortHalfSpace(items, mid + 1, end);
      merge(items, start, mid, end);
    }
  }

  /**
   * Merge sort the provided array by using an improved merge operation and switching to insertion
   * sort for small sub-arrays.
   */
  public static <T extends Comparable<T>> void mergeSortAdaptive(T[] items) {
    mergeSortAdaptive(items, 0, items.length - 1);
  }

  private static <T extends Comparable<T>> void mergeSortAdaptive(T[] items, int start, int end) {
    if (end - start < THRESHOLD) {
      BasicSorts.insertionSubsort(items, start, end);
    } else {
      int mid = (start + end) / 2;
      mergeSortHalfSpace(items, start, mid);
      mergeSortHalfSpace(items, mid + 1, end);
      merge(items, start, mid, end);
    }
  }

  /**
   * Merge sort the provided sub-array using our improved merge sort. This is the fallback method
   * used by introspective sort.
   */
  public static <T extends Comparable<T>> void mergeSubsortAdaptive(T[] items, int start, int end) {
    int mid = start + (end - start) / 2;

    mergeSortHalfSpace(items, start, mid);
    mergeSortHalfSpace(items, mid + 1, end);
    merge(items, start, mid, end);
  }

  /**
   * Merges the temp array to the original array.
   * 
   * @param <T> Generic type.
   * @param items The original array
   * @param start The start index.
   * @param mid The      mid index.
   * @param end The end index.
   */
  private static <T extends Comparable<T>> void merge(T[] items, int start, int mid, int end) {
    @SuppressWarnings("unchecked")
    T[] mergedItems = (T[]) new Comparable[mid - start + 1]; // Temp array to copy elements into.

    for (int i = start; i <= mid; i++) {
      mergedItems[i - start] = items[i]; // Copies the first half into mergedItems.
    }

    int tmpIndex = 0;
    int mergeIndex = start;
    int rightIndex = mid + 1;

    while (tmpIndex < mergedItems.length && rightIndex <= end) {
      // If mergeItem's element is less than item's element
      if (mergedItems[tmpIndex].compareTo(items[rightIndex]) <= 0) {
        items[mergeIndex] = mergedItems[tmpIndex];
        mergeIndex++;
        tmpIndex++;
        // If items right partition is less than mergeItem right partition is copied to left
        // partition.
      } else {
        items[mergeIndex] = items[rightIndex];
        rightIndex++;
        mergeIndex++;
      }
    }

    // Copies remaining elements from mergedItems to items.
    while (tmpIndex < mergedItems.length) {
      items[mergeIndex] = mergedItems[tmpIndex];
      tmpIndex++;
      mergeIndex++;
    }
  }
}
