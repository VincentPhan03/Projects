/**
 * JUnit tests for the CounterMultiset class. The tests themselves are defined in MultisetTest. This
 * class just overrides makeMultiset to return a CounterMultiset.
 *
 * @author Nathan Sprague
 * @version V2, 01/2023
 *
 */
public class ArrayListMultisetTest extends MultisetTest {
 
  public <T> Multiset<T> makeMultiset() {
    return new ArrayListMultiset<T>();
  }

}
