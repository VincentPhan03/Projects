import java.util.Collection;
import java.util.Iterator;

/**
 * Multiset interface.
 * 
 * @author phanvm
 * @version PA 1
 * 
 * @param <E> Generic type
 */
interface Multiset<E> extends Collection<E> {
  /**
   * Add an item.
   * 
   * @param item The item to add
   * 
   * @return Always returns true.
   */
  boolean add(E item);

  /**
   * Add some number of occurrences for an item.
   * 
   * @param item The item to add
   * @param occurances The occurrence of the item.
   * @return The count of the item before the operation.
   */
  int add(E item, int occurances);

  /**
   * Remove all elements.
   */
  void clear();

  /**
   * Return true if the provided item is contained in the Multiset.
   * 
   * @param item The item to check
   * @return true if the item is in the Multiset, false otherwise
   *
   */
  boolean contains(Object item);



  /**
   * Return a count of the number of occurrences of the provided item in the Multiset.
   * 
   * @param item The item to count
   * @return The number of occurrences
   */
  int getCount(Object item);


  /**
   * Return true if the provided object is equal to this Multiset. Two Multisets are considered to
   * be equal if they contain the same elements with the same counts.
   * 
   * @param other The object to check for equality
   * @return true if the object is equal to this Multiset
   */
  @Override
  boolean equals(Object other);

  /**
   * Return an iterator for this Multiset. Repeated elements will be returned the appropriate number
   * of times by the iterator.
   * 
   * @return The iterator
   */
  Iterator<E> iterator();

  /**
   * Reduce the count of the provided element by one. Remove the element if the count reaches 0.
   * 
   * @param item The element to remove
   * @return true if the element was contained in the Multiset
   */
  boolean remove(Object item);

  /**
   * Return the total number of elements stored in the Multiset, taking into account the number of
   * occurrences of each element.
   * 
   * @return The total number of elements
   * 
   */
  int size();

  /**
   * Return a String representation of this Multiset. A string representation of each element will
   * be included along with the count for that element. For example, the Multiset [a, a, b] could be
   * represented by the String "[a x 2, b x 1]". The order of the elements is not specified.
   * 
   * @return A string representation of the Multiset
   */
  @Override
  String toString();
}
