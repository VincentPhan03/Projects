import java.util.List;

/**
 * This class was use to test for thresholds for insertion vs merge.
 * 
 * @author phanvm
 * @version PA 2
 *
 *          This work complies with JMU's honor code.
 */
public class ProfileDriver {
  
  /**
   * Main method.
   * 
   * @param args Unused
   */
  public static void main(String[] args) {
    // Create a SortProfiler object.
    // See the JavaDoc for an explanation of the parameters.

    SortProfiler sp = new SortProfiler(List.of(MergeSort::mergeSort, BasicSorts::insertionSort),
        List.of("merge", "insertion"), 100, 10, 500, 10000, Generators::generateRandom);

    sp.run(System.out);
    // $ java SortProfiler -s 100 -i 1 -m 500 -t 25000 -w merge,insertion
  }
}
