import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CounterMultiset class.
 * 
 * @author phanvm
 * @version PA 1
 * 
 *          This work complies with JMU's Honor Code.
 */
public class CounterMultiset<E> extends AbstractMultiset<E> {
  private ArrayList<Pair<E, Integer>> elements;

  public CounterMultiset() {
    this.elements = new ArrayList<Pair<E, Integer>>();
  }

  /**
   * Helper method for CounterMultiset, gets the index of the specified element.
   * 
   * @param item The item to get the index for.
   * @return The index of the item.
   */
  private int getIndex(Object item) {
    return elements.indexOf(item);
  }

  /**
   * Helper method for CounterMultiset, gets the first value of a pair.
   * 
   * @param item The item to get the first value for.
   * @return The first value of item.
   */
  private E getFirst(Object item) {
    return elements.get(getIndex(item)).getFirst();
  }

  /**
   * Helper method for CounterMultiset, updates count of a item.
   * 
   * @param item The item to update the value for.
   * @param count The amount to update the value for.
   */
  private void updateCount(Object item, Integer count) {
    elements.get(getIndex(item)).setSecond(getCount(item) + count);
  }


  @Override
  public boolean add(E item) {
    if (elements.contains(item)) {
      updateCount(item, 1);
    }

    elements.add(new Pair<E, Integer>(item, 1));
    return true;
  }

  @Override
  public int add(E item, int occurances) {
    int num = getCount(item);

    if (elements.contains(item)) {
      updateCount(item, occurances);
      return getCount(item);
    }

    elements.add(new Pair<E, Integer>(item, occurances));
    return num;
  }

  @Override
  public void clear() {
    elements.removeAll(elements);
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean contains(Object item) {
    boolean isIn = false;

    for (Pair<E, Integer> pair : elements) {
      if (pair.getFirst().equals((E) item)) {
        isIn = true;
      }
    }

    return isIn;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof CounterMultiset) || ((CounterMultiset<E>) other).size() != size()) {
      return false;
    }

    for (Pair<E, Integer> pair1 : elements) {
      if (!(getFirst(other).equals(pair1.getFirst())) && !(getCount(other) == pair1.getSecond())) {
        return false;
      }
    }

    return true;
  }

  @Override
  public int getCount(Object item) {
    if (elements.contains(item)) {
      return elements.get(getIndex(item)).getSecond();
    }

    return 0;
  }

  @Override
  public int size() {
    int size = 0;

    for (Pair<E, Integer> pair : elements) {
      size += pair.getSecond();
    }

    return size;
  }

  @Override
  public Iterator<E> iterator() {
    return new CounterMultiSetIterator();
  }

  @Override
  public boolean remove(Object item) {
    return remove(item, 1) > 0;
  }


  /**
   * Removes an object with a certain amount occurances.
   * 
   * @param item The item to remove.
   * @param count The amount to remove.
   * @return The previous amount of count.
   */
  public int remove(Object item, int count) {
    if (count < 0) {
      throw new IllegalArgumentException();
    }

    int index = elements.indexOf(item);

    if (index == -1) {
      return 0;
    }

    int prevCount = getCount(item);

    if (prevCount > count) {
      elements.get(getIndex(item)).setSecond(prevCount - count);
    } else {
      elements.remove(getIndex(item));
    }

    return prevCount;
  }

  @Override
  public String toString() {
    HashSet<E> added = new HashSet<>();
    String result = "[";

    for (Pair<E, Integer> pair : elements) {

      if (!added.contains(pair)) {

        if (added.size() > 0) {
          result += ", ";
        }

        result += pair.getFirst() + " x " + pair.getSecond();
      }
      added.add(pair.getFirst());
    }
    return result + "]";
  }

  private class CounterMultiSetIterator implements Iterator<E> {
    int next = 0;
    int counter = 0;
    boolean canRemove = false;

    @Override
    public boolean hasNext() {
      return next != elements.size();
    }

    @Override
    public E next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      if (counter < getCount(elements.get(next))) {
        counter++;
        canRemove = true;
        return getFirst(elements.get(next));
      }

      canRemove = true;
      next++;
      counter = 0;

      return getFirst(elements.get(next));
    }

    @Override
    public void remove() throws IllegalStateException {
      if (!canRemove) {
        throw new IllegalStateException();
      }

      elements.remove(elements.get(next));
    }
  }
}
