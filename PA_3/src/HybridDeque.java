
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Doubly-linked-list implementation of the java.util.Deque interface. This implementation is more
 * space-efficient than Java's LinkedList class for large collections because each node contains a
 * block of elements instead of only one. This reduces the overhead required for next and previous
 * node references. This implementation does not allow null's to be added to the collection. Adding
 * a null will result in a NullPointerException.
 * 
 * @author phanvm
 * @version HW 3
 * 
 *          This work complies with JMU's Honor code.
 */
public class HybridDeque<E> extends AbstractDeque<E> {

  /*
   * IMPLEMENTATION NOTES ----------------------------------
   *
   * The list of blocks is never empty, so leftBlock and rightBlock are never equal to null. The
   * list is not circular.
   *
   * A deque's first element is at leftBlock.elements[leftIndex]
   * 
   * and its last element is at rightBlock.elements[rightIndex].
   * 
   * The indices, leftIndex and rightIndex are always in the range:
   * 
   * 0 <= index < BLOCK_SIZE
   *
   * And their exact relationship is:
   * 
   * (leftIndex + size - 1) % BLOCK_SIZE == rightIndex
   *
   * Whenever leftBlock == rightBlock, then:
   * 
   * leftIndex + size - 1 == rightIndex
   *
   * However, when leftBlock != rightBlock, the leftIndex and rightIndex become indices into
   * distinct blocks and either may be larger than the other.
   *
   * Empty deques have:
   * 
   * size == 0
   * 
   * leftBlock == rightBlock
   * 
   * leftIndex == CENTER + 1
   * 
   * rightIndex == CENTER
   * 
   * Checking for size == 0 is the intended way to see whether the Deque is empty.
   * 
   * 
   * (Comments above are a lightly modified version of comments in Python's deque implementation:
   * https://github.com/python/cpython/blob/v3.11.2/Modules/_collectionsmodule.c
   * https://docs.python.org/3.11/license.html)
   * 
   */

  private static int BLOCK_SIZE = 8;
  private static int CENTER = (BLOCK_SIZE - 1) / 2;

  private Cursor leftCursor;
  private Cursor rightCursor;
  private int size;


  /**
   * DO NOT MODIFY THIS METHOD. This will be used in grading/testing to modify the default block
   * size..
   *
   * @param blockSize The new block size
   */
  protected static void setBlockSize(int blockSize) {
    HybridDeque.BLOCK_SIZE = blockSize;
    HybridDeque.CENTER = (blockSize - 1) / 2;
  }


  /**
   * Doubly linked list node (or block) containing an array with space for multiple elements.
   */
  private class Block {
    private E[] elements;
    private Block next;
    private Block prev;

    /**
     * Block Constructor.
     *
     * @param prev Reference to previous block, or null if this is the first
     * @param next Reference to next block, or null if this is the last
     */
    @SuppressWarnings("unchecked")
    public Block(Block prev, Block next) {
      this.elements = (E[]) (new Object[BLOCK_SIZE]);
      this.next = next;
      this.prev = prev;
    }

  }

  /**
   * Many of the complications of implementing this Deque class are related to the fact that there
   * are two pieces of information that need to be maintained to track a position in the deque: a
   * block reference and the index within that block. This class combines those two pieces of
   * information and provides the logic for moving forward and backward through the deque structure.
   * 
   * 
   * 
   * 
   * <P>NOTE: The provided cursor class is *immutable*: once a Cursor object is created it cannot be
   * modified. Incrementing forward or backward involves creating new Cursor objects at the required
   * location. Immutable objects can be cumbersome to work with, but they prevent coding errors
   * caused by accidentally aliasing mutable objects.
   */
  private class Cursor {
    private final Block block;
    private final int index;

    public Cursor(HybridDeque<E>.Block block, int index) {
      this.block = block;
      this.index = index;
    }

    /**
     * Increment the cursor, crossing a block boundary if necessary.
     *
     * @return A new cursor at the next position, or null if there are no more valid positions.
     */
    private Cursor next() {

      if (index == BLOCK_SIZE - 1) { // We need to cross a block boundary
        if (block.next == null) {
          return null;
        } else {
          return new Cursor(block.next, 0);
        }
      } else { // Just move one spot forward in the current block
        return new Cursor(block, index + 1);
      }
    }

    /**
     * Decrement the cursor, crossing a block boundary if necessary.
     *
     * @return A new cursor at the previous position, or null if there is no previous position.
     */
    private Cursor prev() {
      if (index == 0) { // We need to cross a block boundary
        if (block.prev == null) {
          return null;
        } else {
          return new Cursor(block.prev, BLOCK_SIZE - 1);
        }
      } else { // Just move one spot back in the current block.
        return new Cursor(block, index - 1);
      }
    }

    /**
     * Return the element stored at this cursor.
     */
    public E get() {
      return block.elements[index];
    }

    /**
     * Set the element at this cursor.
     */
    public void set(E item) {
      block.elements[index] = item;
    }
  }

  /**
   * Constructor for HybridDeque.
   */
  public HybridDeque() {
    Block block = new Block(null, null);
    this.leftCursor = new Cursor(block, CENTER + 1);
    this.rightCursor = new Cursor(block, CENTER);
    this.size = 0;
  }

  /**
   * Clears the block and resets the HybridDeque to its original state.
   */
  public void clear() {
    Block block = new Block(null, null);
    this.leftCursor = new Cursor(block, CENTER + 1);
    this.rightCursor = new Cursor(block, CENTER);
    this.size = 0;
  }

  @Override
  public boolean offerFirst(E e) {
    if (e == null) {
      throw new NullPointerException();
    }

    // Checks when block is full
    if (leftCursor.prev() == null) {
      Block block = new Block(null, leftCursor.block);
      leftCursor.block.prev = block;
      leftCursor = new Cursor(new Block(null, leftCursor.block), BLOCK_SIZE - 1);
    } else { // Space in the block
      leftCursor = leftCursor.prev();
    }

    leftCursor.set(e);
    size++;
    return true;
  }

  @Override
  public boolean offerLast(E e) {
    if (e == null) {
      throw new NullPointerException();
    }

    // FIX THIS
    if (rightCursor.next() == null) {
      Block block = new Block(rightCursor.block, null);
      leftCursor.block.prev = block;
      rightCursor = new Cursor(new Block(rightCursor.block, null), 0);
    } else {
      rightCursor = rightCursor.next();
    }

    rightCursor.set(e);
    size++;
    return true;
  }

  @Override
  public E pollFirst() {
    if (this.size == 0) {
      return null;
    }

    E first = leftCursor.get();
    size--;

    leftCursor = leftCursor.next();

    return first;
  }

  @Override
  public E pollLast() {
    if (this.size == 0) {
      return null;
    }

    E first = rightCursor.get();
    size--;

    rightCursor = rightCursor.prev();

    return first;
  }

  @Override
  public E peekFirst() {
    if (this.size == 0) {
      return null;
    }

    return leftCursor.get();
  }

  @Override
  public E peekLast() {
    if (this.size == 0) {
      return null;
    }

    return rightCursor.get();
  }

  /**
   * Helper method for remove methods in iterator.
   * 
   * @param cursor The cursor.
   */
  public void removalHelper(Cursor cursor) {
    for (int i = cursor.index; i < BLOCK_SIZE - 1; i++) {
      cursor.set(cursor.block.elements[i + 1]);
    }

    cursor.block.elements[BLOCK_SIZE - 1] = null;
    size--;
    cursor = new Cursor(cursor.block, cursor.index - 1);
  }

  @Override
  public Iterator<E> descendingIterator() {
    return new HybridDequeDescendingIterator();
  }

  private class HybridDequeDescendingIterator implements Iterator<E> {
    private Cursor cursor = rightCursor;
    private E lastReturned = null;

    @Override
    public boolean hasNext() {
      return cursor.get() != null;
    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      E element = cursor.get();
      lastReturned = element;
      cursor = cursor.prev();

      return element;
    }

    @Override
    public void remove() {
      if (lastReturned == null) {
        throw new IllegalStateException();
      }

      removalHelper(cursor);
    }
  }

  @Override
  public Iterator<E> iterator() {
    return new HybridDequeIterator();
  }

  private class HybridDequeIterator implements Iterator<E> {
    private Cursor cursor = leftCursor;
    private E lastReturned = null;

    @Override
    public boolean hasNext() {
      return cursor.get() != null;
    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      E element = cursor.get();
      lastReturned = element;
      cursor = cursor.next();

      return element;
    }

    @Override
    public void remove() {
      if (lastReturned == null) {
        throw new IllegalStateException();
      }

      removalHelper(cursor);
    }
  }

  @Override
  public int size() {
    return this.size;
  }


  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof HybridDeque)) {
      return false;
    } else if (((HybridDeque<E>) o).size() != size) {
      return false;
    } else {
      HybridDeque<E> other = (HybridDeque<E>) o;

      Iterator<E> otherIterator = other.iterator();
      Iterator<E> thisIterator = this.iterator();

      boolean equal = true;

      while (thisIterator.hasNext()) {
        while (otherIterator.hasNext()) {
          if (!(thisIterator.next().equals(otherIterator.next()))) {
            equal = false;
          }
        }
      }

      return equal;
    }
  }

  @Override
  public boolean removeFirstOccurrence(Object o) {
    Iterator<E> iterator = iterator();
    return removeHelper(iterator, o);
  }

  // Shift to the left, iterate through block, then shift every element index - 1.
  @Override
  public boolean removeLastOccurrence(Object o) {
    Iterator<E> iterator = descendingIterator();
    return removeHelper(iterator, o);
  }

  private boolean removeHelper(Iterator<E> iterator, Object o) {
    while (iterator.hasNext()) {
      if (iterator.next().equals(o)) {
        iterator.remove();
        return true;
      }
    }

    return false;
  }

}