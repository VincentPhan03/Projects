

/**
 * Mutable 2-tuple type.
 * 
 * @author Nathan Sprague and Michael S. Kirkpatrick
 * @version V4, 8/2021
 */
public class Pair<T, U> {

  private T first;
  private U second;

  /**
   * Create an Pair with the provided objects.
   * 
   * @param first The first object.
   * @param second The second object.
   */
  public Pair(T first, U second) {
    this.first = first;
    this.second = second;
  }

  public T getFirst() {
    return first;
  }

  public void setFirst(T first) {
    this.first = first;
  }

  public U getSecond() {
    return second;
  }

  public void setSecond(U second) {
    this.second = second;
  }

  @Override
  public String toString() {
    return "<" + first.toString() + ", " + second.toString() + ">";
  }

}
