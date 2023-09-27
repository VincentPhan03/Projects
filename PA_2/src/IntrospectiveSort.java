/**
 * IntrospectiveSort class.
 * 
 * @author phanvm
 * @version PA 2
 * 
 *          This work complies with JMU's honor code.
 */
public class IntrospectiveSort {

  /**
   * Sort the provided items using introspective sort.
   */
  public static <T extends Comparable<T>> void introspectiveSort(T[] items) {
    int maxDepth = (int) (Math.log(items.length)) * 2;
    introspectiveSort(items, 0, items.length - 1, maxDepth);
  }

  /**
   * Recursive helped for introspective sort.
   * 
   * @param <T> Generic type.
   * @param items The items to sort.
   * @param maxDepth The maximum recursion depth.
   */
  private static <T extends Comparable<T>> void introspectiveSort(T[] items, int start, int end,
      int maxDepth) {
    if ((end - start) + 1 < 2) {
      return;
    } else if (maxDepth == 0) {
      MergeSortImproved.mergeSubsortAdaptive(items, start, end);
    } else {
      int partition = partition(items, start, end);

      introspectiveSort(items, start, partition, maxDepth - 1);
      introspectiveSort(items, partition + 1, end, maxDepth - 1);
    }
  }

  /**   
   * Partition the indicated region of the array. The pivot item will be placed in its final sorted
   * position, with all smaller elements moved to the left and all larger elements moved to the
   * right.
   *
   * @return The final index of the pivot item.
   */
  protected static <T extends Comparable<T>> int partition(T[] items, int left, int right) {
    int pivotIndex = left + (right - left) / 2;
    T pivotItem = items[pivotIndex];

    // Advance from both ends until window collapses.
    boolean isDone = false;
    while (!isDone) {
      // Skip rightward past items < pivot.
      while (items[left].compareTo(pivotItem) < 0) {
        left++;
      }

      // Skip leftward past items > pivot.
      while (items[right].compareTo(pivotItem) > 0) {
        right--;
      }

      if (left >= right) {
        isDone = true;

      } else {
        T temp = items[left];
        items[left] = items[right];
        items[right] = temp;

        left++;
        right--;
      }
    }

    return right;
  }
}
