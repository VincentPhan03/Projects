import java.util.function.Consumer;

/**
 * Driver class designed to illustrate a scenario where the CounterMultiset has a significant
 * advantage over the ArrayListMultiset.
 *
 * @author Nathan Sprague and ???
 * @version ??
 *
 */
public class WorkloadDriver {

  /**
   * The main method creates an ArrayListMultiset and a CounterMultiset and passes them to a method
   * that will perform the same sequence of observations on each and time the results.
   * 
   * @param args Not used
   */
  public static void main(String[] args) {
    ArrayListMultiset<Integer> arrayListSet = new ArrayListMultiset<>();
    System.out.println("Timing CounterMultiset with counterFriendlyOperations (fast!):");
    performTiming((set) -> counterFriendlyOperations(set), arrayListSet);
 
    CounterMultiset<Integer> counterSet = new CounterMultiset<>();
    performTiming((set) -> counterFriendlyOperations(set), counterSet);
    System.out.println("Timing ArrayListMultiset with counterFriendlyOperations (slow!)");
    
    
    


    arrayListSet = new ArrayListMultiset<>();
    counterSet = new CounterMultiset<>();

    System.out.println("Timing ArrayListMultiset with arrayListFriendlyOperations (fast!)");
    performTiming((set) -> arrayListFriendlyOperations(set), arrayListSet);

    System.out.println("Timing CounterMultiset with arrayListFriendlyOperations (slow!):");
    performTiming((set) -> arrayListFriendlyOperations(set), counterSet);
  }

  /**
   * Run the provided function and print timing information.
   *
   * @param consumer The function to time. Expects a Multiset.
   * @param set An empty Multiset object (either ArrayListMultiset or CounterMultiset)
   */
  public static void performTiming(Consumer<Multiset<Integer>> consumer, Multiset<Integer> set) {
    long start = System.currentTimeMillis();
    consumer.accept(set);
    double elapsed = (System.currentTimeMillis() - start) / 1000.0;
    System.out.println("Done. " + elapsed + " (s)\n");
  }

  /**
   * Perform a set of operations that will take longer when executed with an ArrayListMultiset than
   * with a CounterMultiset.
   * 
   * 
   * 
   * <P>For full credit the CounterMultiset should be at least 10 times faster and the entire method
   * should finish within 10 seconds for both collections.
   *
   * @param set An empty Multiset to use for testing
   */
  public static void counterFriendlyOperations(Multiset<Integer> set) {

    // YOUR CODE HERE!

  }

  /**
   * Perform a set of operations that will take longer when executed with a CounterMultiset than
   * with a ArrayListMultiset.
   * 
   *
   * 
   * <P>For full credit the ArrayListMultiset should be at least 10% faster and the entire method
   * should finish within 10 seconds for both collections.
   *
   * @param set An empty Multiset to use for testing
   */
  public static void arrayListFriendlyOperations(Multiset<Integer> set) {

    // YOUR CODE HERE!

  }


}
