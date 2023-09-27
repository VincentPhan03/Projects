
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for HybridDeque.
 * 
 * @author phanvm
 * @version PA 3
 * 
 *          This work complies with JMU's honor code.
 */
class HybridDequeTest<E> {
  HybridDeque<String> deque;

  @BeforeEach
  void setUp() throws Exception {
    deque = new HybridDeque<String>();
  }

  /**
   * Test size when HybridDeque is empty.
   */
  @Test
  void testSizeWithEmptyDeque() {
    assertEquals(deque.size(), 0);
  }

  /**
   * Test size when HybridDeque is offerFirsted is called.
   */
  @Test
  void testSizeAfterOfferFirst() {
    deque.offerFirst("A");
    assertEquals(deque.size(), 1);

    deque.offerFirst("B");
    assertEquals(deque.size(), 2);
  }

  /**
   * Test size when HybridDeque is offerLast is called.
   */
  @Test
  void testSizeAfterOfferLast() {
    deque.offerLast("B");
    assertEquals(deque.size(), 1);

    deque.offerLast("A");
    assertEquals(deque.size(), 2);
  }

  /**
   * Test size when HybridDeque after poll.
   */
  @Test
  void testSizeAfterPoll() {
    deque.offerFirst("A");
    deque.offerLast("B");
    deque.pollFirst();

    assertEquals(deque.size(), 1);

    deque.pollLast();
    assertEquals(deque.size(), 0);
  }

  /**
   * Test size HybridDeque after cleared.
   */
  @Test
  void testSizeAfterClear() {
    deque.offerFirst("A");
    deque.offerLast("B");
    deque.clear();

    assertEquals(deque.size(), 0);
  }

  /**
   * Test peek.
   */
  @Test
  void testPeek() {
    deque.offerFirst("A");
    deque.offerLast("B");

    assertEquals(deque.peekFirst(), "A");
    assertEquals(deque.peekLast(), "B");

    deque.offerFirst("E");
    assertEquals(deque.peekFirst(), "E");

    deque.offerLast("D");
    assertEquals(deque.peekLast(), "D");

    deque.clear();
    assertNull(deque.peekFirst());
    assertNull(deque.peekLast());
  }

  /**
   * Test pollFirst.
   */
  @Test
  void testPollFirst() {
    deque.offerFirst("A");
    deque.offerFirst("B");

    assertEquals(deque.pollFirst(), "B");
    assertEquals(deque.peekFirst(), "A");
    assertEquals(deque.pollFirst(), "A");

    assertNull(deque.pollFirst());
  }

  /**
   * Test pollLast.
   */
  @Test
  void testPollLast() {
    deque.offerFirst("A");
    deque.offerFirst("B");

    assertEquals(deque.pollLast(), "A");
    assertEquals(deque.peekFirst(), "B");
    assertEquals(deque.peekLast(), "B");
    assertEquals(deque.pollLast(), "B");

    assertNull(deque.pollLast());
  }


  /**
   * Test offerFirst.
   */
  @Test
  void testOfferFirst() {
    assertTrue(deque.offerFirst("A"));
    assertTrue(deque.offerFirst("B"));
    assertTrue(deque.offerFirst("C"));
    assertTrue(deque.offerFirst("D"));
    assertTrue(deque.offerFirst("E"));
    assertTrue(deque.offerFirst("F"));
    assertTrue(deque.offerFirst("G"));
    assertTrue(deque.offerFirst("H"));

    assertTrue(deque.offerFirst("A"));
    
    assertEquals(deque.peekFirst(), "A");
    assertEquals(deque.peekLast(), "A");
    

    
    try {
      deque.offerFirst(null);
    } catch (NullPointerException e) {
      e.getMessage();
    }
  }

  /**
   * Test offerLast.
   */
  @Test
  void testOfferLast() {
    assertTrue(deque.offerLast("A"));
    assertTrue(deque.offerLast("B"));
    assertTrue(deque.offerLast("C"));
    assertTrue(deque.offerLast("D"));
    assertTrue(deque.offerLast("E"));
    assertTrue(deque.offerLast("F"));
    assertTrue(deque.offerLast("G"));
    assertTrue(deque.offerLast("H"));

    assertTrue(deque.offerLast("A"));
    assertEquals(deque.peekFirst(), "A");
    assertEquals(deque.peekLast(), "A");    
  
    try {
      deque.offerLast(null);
    } catch (NullPointerException e) {
      e.getMessage();
    }
  }

  /**
   * Test equals.
   */
  @Test
  void testEquals() {
    HybridDeque<String> x = new HybridDeque<String>();
    HybridDeque<String> y = new HybridDeque<String>();

    assertTrue(x.equals(y));

    x.offerFirst("A");
    x.offerFirst("B");

    y.offerFirst("A");
    y.offerFirst("B");

    assertTrue(x.equals(y));

    x.clear();
    y.clear();

    assertTrue(x.equals(y));

    x.offerFirst("A");
    assertFalse(x.equals(y));

    assertFalse(x.equals("S"));

    y.offerFirst("B");
    assertFalse(x.equals(y));
  }


  /**
   * Test iterator.
   */
  @Test
  void testIterator() {
    deque.offerLast("A");
    deque.offerLast("B");
    deque.offerLast("C");

    Iterator<String> iterator = deque.iterator();

    assertEquals(iterator.next(), "A");
    assertEquals(iterator.next(), "B");
    assertEquals(iterator.next(), "C");
    
    try {
      iterator.next();
    } catch (NoSuchElementException e) {
      e.getMessage();
    }
  }

  /**
   * Test descendingIterator.
   */
  @Test
  void testDescendingIterator() {
    deque.offerLast("A");
    deque.offerLast("B");
    deque.offerLast("C");
    deque.offerLast("D");

    Iterator<String> iterator = deque.descendingIterator();

    assertEquals(iterator.next(), "D");
    assertEquals(iterator.next(), "C");
    assertEquals(iterator.next(), "B");
    assertEquals(iterator.next(), "A");
    
    try {
      iterator.next();
    } catch (NoSuchElementException e) {
      e.getMessage();
    }
  }

  /**
   * Test removeFirstOccurance.
   */
  @Test
  void testRemoveFirstOccurance() {
    deque.offerLast("A");
    deque.offerLast("B");
    deque.offerLast("C");
    deque.offerLast("D");

    assertTrue(deque.removeFirstOccurrence("A"));
    assertEquals(deque.size(), 3);
    
    assertFalse(deque.removeFirstOccurrence("R"));
  }

  /**
   * Test removeLastOccurance.
   */
  @Test
  void testRemoveLastOccurance() {
    deque.offerLast("A");
    deque.offerLast("B");
    deque.offerLast("C");
    deque.offerLast("D");

    assertTrue(deque.removeLastOccurrence("A"));
    assertEquals(deque.size(), 3);
    
    assertFalse(deque.removeLastOccurrence("R"));
  }
  
  /**
   * Test remove for iterator.
   */
  @Test
  void testRemoveIterator() {
    deque.offerLast("A");
    deque.offerLast("B");
    deque.offerLast("C");
    deque.offerLast("D");
    
    Iterator<String> iterator = deque.iterator(); 

    try {
      iterator.remove();
    } catch (IllegalStateException e) {
      e.getMessage();
    }
    
    iterator.next();
    iterator.remove();
    
    assertEquals(iterator.next(), "D");
    assertEquals(iterator.next(), "C");
  }
  
  /**
   * Test remove for iterator.
   */
  @Test
  void testRemoveDescendingIterator() {
    deque.offerLast("A");
    deque.offerLast("B");
    deque.offerLast("C");
    deque.offerLast("D");
    
    Iterator<String> iterator = deque.descendingIterator(); 
    
    
    try {
      iterator.remove();
    } catch (IllegalStateException e) {
      e.getMessage();
    }
    
    iterator.next();
    iterator.remove();
    
    assertEquals(iterator.next(), "D");
    assertEquals(iterator.next(), "B");
    assertEquals(iterator.next(), "A");
  }
}
