import java.lang.UnsupportedOperationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * ArrayListMultiset class.
 * 
 * @author phanvm and Nathan Sprauge
 * @version PA 1
 * 
 *          This work complies with JMU's honor code. I used Professor Sprauge's Implementation of
 *          the Multiset class, as well as the source code from the default implementation of
 *          java.util.AbstractCollection
 */
public class ArrayListMultiset<E> extends AbstractMultiset<E> {
  private ArrayList<E> elements;

  public ArrayListMultiset() {
    this.elements = new ArrayList<E>();
  }

  /**
   * Add an item.
   * 
   * @param item The item to add
   * @return Always returns true.
   */
  public boolean add(E item) {
    return elements.add(item);
  }

  /**
   * Add some number of occurances for an item.
   * 
   * @param item The item too add.
   * @param occurances The amount of occurances.
   * @return The count of the item before the operation.
   */
  public int add(E item, int occurances) {
    int num = getCount(item);
    for (int i = 0; i < occurances; i++) {
      elements.add(item);
    }
    return num;
  }

  /**
   * Remove all elements.
   */
  public void clear() {
    elements.clear();
  }

  /**
   * Return true if the provided item is contained in the Multiset.
   * 
   * @param item The item to check
   * @return true if the item is in the Multiset, false otherwise
   */
  public boolean contains(Object item) {
    return elements.contains(item);
  }

  /**
   * Return a count of the number of occurrences of the provided item in the Multiset.
   * 
   * @param item The item to count
   * @return The number of occurrences
   */
  public int getCount(Object item) {
    int total = 0;

    for (E element : elements) {
      if (element.equals(item)) {
        total++;
      }
    }

    return total;
  }

  /**
   * Return true if the provided object is equal to this Multiset. Two Multisets are considered to
   * be equal if they contain the same elements with the same counts.
   * 
   * @param other The object to check for equality
   * @return true if the object is equal to this Multiset
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object other) {

    if (!(other instanceof Multiset) || ((Multiset<E>) other).size() != size()) {
      return false;
    }

    for (E element : elements) {
      if (this.getCount(element) != ((Multiset<E>) other).getCount(element)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Return an iterator for this Multiset. Repeated elements will be returned the appropriate number
   * of times by the iterator.
   * 
   * @return The iterator
   */
  public Iterator<E> iterator() {
    return elements.iterator();
  }

  /**
   * Reduce the count of the provided element by one. Remove the element if the count reaches 0.
   * 
   * @param item The element to remove
   * @return true if the element was contained in the Multiset
   */
  public boolean remove(Object item) {
    return elements.remove(item);
  }

  /**
   * Return the total number of elements stored in the Multiset, taking into account the number of
   * occurrences of each element.
   * 
   * @return The total number of elements
   */
  public int size() {
    return elements.size();
  }

  /**
   * Return a String representation of this Multiset. A string representation of each element will
   * be included along with the count for that element. For example, the Multiset [a, a, b] could be
   * represented by the String "[a x 2, b x 1]". The order of the elements is not specified.
   * 
   * @return A string representation of the Multiset
   */
  @Override
  public String toString() {
    HashSet<E> added = new HashSet<>();
    String result = "[";

    for (E element : elements) {

      if (!added.contains(element)) {

        if (added.size() > 0) {
          result += ", ";
        }

        result += element.toString() + " x " + this.getCount(element);
      }
      added.add(element);
    }
    return result + "]";
  }


  @Override
  public boolean isEmpty() {
    return elements.isEmpty();
  }


  @Override
  public Object[] toArray() {
    return elements.toArray();
  }


  @Override
  public <T> T[] toArray(T[] a) {
    return elements.toArray(a);
  }

  @Override
  public <T> T[] toArray(IntFunction<T[]> generator) {
    // TODO Auto-generated method stub
    return elements.toArray(generator);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return elements.containsAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeIf(Predicate<? super E> filter) {
    // TODO Auto-generated method stub
    return elements.removeIf(filter);
  }

  @Override
  public Spliterator<E> spliterator() {
    // TODO Auto-generated method stub
    return elements.spliterator();
  }

  @Override
  public Stream<E> stream() {
    // TODO Auto-generated method stub
    return elements.stream();
  }

  @Override
  public Stream<E> parallelStream() {
    // TODO Auto-generated method stub
    return elements.parallelStream();
  }
}
