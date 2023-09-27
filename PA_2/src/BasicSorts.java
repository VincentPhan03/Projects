/**
 * Basic n^2 sorting algorithms.
 * 
 * @author phanvm and CS 240 Professors
 * @version PA 2
 * 
 *          This work complies with JMU's honor code
 */
public class BasicSorts {

  /**
   * Sort the given array using insertion sort.
   *
   * @param items The array to sort.
   */
  public static <T extends Comparable<T>> void insertionSort(T[] items) {
    for (int i = 1; i < items.length; i++) {
      T itemToShift = items[i];

      // Displace any smaller items one cell to the right.
      int j = i;
      while (j > 0 && itemToShift.compareTo(items[j - 1]) < 0) {
        items[j] = items[j - 1];
        j--;
      }

      items[j] = itemToShift;
    }
  }

  /**
   * Sort only the given window of the array.
   *
   * @param items The array to partially sort.
   * @param start The inclusive starting index of the window.
   * @param end The inclusive ending index of the window.
   */
  public static <T extends Comparable<T>> void insertionSubsort(T[] items, int start, int end) {
    for (int i = start + 1; i <= end; i++) {
      T itemToShift = items[i];

      // Displace any smaller items one cell to the right.
      int j = i - 1;
      while (j >= start && itemToShift.compareTo(items[j]) < 0) {
        items[j + 1] = items[j];
        j--;
      }

      items[j + 1] = itemToShift;
    }
  }


  /**
   * Selection sort the given array.
   *
   * @param items The array to sort.
   */
  public static <T extends Comparable<T>> void selectionSort(T[] items) {
    for (int i = 0; i < items.length - 1; i++) {
      int indexSmallest = i;

      for (int j = i + 1; j < items.length; j++) {
        if (items[j].compareTo(items[indexSmallest]) < 0) {
          indexSmallest = j;
        }
      }

      T temp = items[i];
      items[i] = items[indexSmallest];
      items[indexSmallest] = temp;
    }
  }

}
