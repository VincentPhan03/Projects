import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Lab MinHeap.
 * 
 * @author phanvm
 * @version 4/14/2023
 * @param <E>
 * 
 *        This work complies with JMU's honor code.
 */
public class MinHeap<E extends Comparable<E>> {
  public final E[] items;
  private int size = 0;

  @SuppressWarnings("unchecked")
  public MinHeap(int capacity) {
    this.items = (E[]) new Comparable[capacity];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public E peek() {
    if (size == 0) {
      throw new NoSuchElementException();
    }

    return items[0];
  }

  public void enqueue(E item) {
    if (size == items.length) {
      throw new IllegalStateException();
    }

    items[size] = item;
    percolateUp(size);
    size++;
  }

  /**
   * Helper method for enqueue.
   * 
   * @param nodeIndex Index of node.
   */
  private void percolateUp(int nodeIndex) {
    while (nodeIndex > 0) {
      int parentIndex = (nodeIndex - 1) / 2;

      if (items[nodeIndex].compareTo(items[parentIndex]) >= 0) {
        return;
      } else {
        E temp = items[nodeIndex];
        items[nodeIndex] = items[parentIndex];
        items[parentIndex] = temp;

        nodeIndex = parentIndex;
      }
    }
  }

  public E dequeue() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    
    E min = items[0];
    items[0] = items[size - 1];
    items[size - 1] = null;
    size--;
    percolateDown(0);
    return min;
  }

  /**
   * Private helper method for dequeu
   * 
   * @param nodeIndex
   */
  private void percolateDown(int nodeIndex) {
    while (true) {
      int leftChildIndex = 2 * nodeIndex + 1;
      int rightChildIndex = 2 * nodeIndex + 2;
      int minIndex = nodeIndex;
      
      if (leftChildIndex < size && items[leftChildIndex].compareTo(items[minIndex]) < 0) {
        minIndex = leftChildIndex;
      }
      
      if (rightChildIndex < size && items[rightChildIndex].compareTo(items[minIndex]) < 0) {
        minIndex = rightChildIndex;
      }

      if (minIndex == nodeIndex) {
        break;
      }

      E temp = items[nodeIndex];
      items[nodeIndex] = items[minIndex];
      items[minIndex] = temp;

      nodeIndex = minIndex;
    }
  }

  public static void main(String[] args) {
    MinHeap<Integer> heap = new MinHeap<Integer>(3);

    heap.enqueue(2);
    heap.enqueue(4);
    heap.enqueue(1);
    System.out.println(heap.peek());
    
    heap.dequeue();
    
    System.out.println(heap.peek());
    
  }
}
