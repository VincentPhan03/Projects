import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Multiset interface. Implementing classes will need their own test class that
 * will override makeMultiset to return a Multiset of the appropriate type.
 *
 * @author Nathan Sprague
 * @version V2, 01/2023
 *
 */
public abstract class MultisetTest {
 
  public abstract <T> Multiset<T> makeMultiset();

  // --------------------------------------------
  // TESTS FOR SIZE
  // --------------------------------------------
  @Test
  public void testEmptySizeZero() {
    Multiset<String> set = makeMultiset();
    assertEquals(set.getClass().getName(), 0, set.size());
  }

  @Test
  public void testSizeDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    set.add("b");
    assertEquals(set.getClass().getName(), 3, set.size());
  }

  @Test
  public void testSizeNoDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("b");
    set.add("c");
    assertEquals(set.getClass().getName(), 3, set.size());
  }

  @Test
  public void testSizeRemoveDuplicate() {
    Multiset<String> set = makeMultiset();

    set.add("a");
    set.add("a");
    set.add("b");
    set.remove("a");
    assertEquals(set.getClass().getName(), 2, set.size());
  }

  @Test
  public void testSizeRemoveNonDuplicate() {
    Multiset<String> set = makeMultiset();

    set.add("a");
    set.add("b");
    set.add("c");
    set.remove("a");
    assertEquals(set.getClass().getName(), 2, set.size());
  }

  // --------------------------------------------
  // TESTS FOR MULTIPLE ADD
  // --------------------------------------------
  @Test
  public void testAddZeroDoesntChangeSize() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a", 0);
    assertEquals(set.getClass().getName(), 1, set.size());
  }

  @Test
  public void testAddMultipleAddsCorrectNumber() {
    Multiset<String> set = makeMultiset();
    set.add("a", 4);
    set.add("b", 3);
    assertEquals(set.getClass().getName(), 4, set.getCount("a"));
    assertEquals(set.getClass().getName(), 3, set.getCount("b"));
    set.add("b", 10);
    assertEquals(set.getClass().getName(), 4, set.getCount("a"));
    assertEquals(set.getClass().getName(), 13, set.getCount("b"));
    assertEquals(set.getClass().getName(), 17, set.size());
  }

  @Test
  public void testAddMultipleCorrectReturnValue() {
    Multiset<String> set = makeMultiset();
    assertEquals(set.getClass().getName(), 0, set.add("a", 4));
    set.add("a");
    assertEquals(set.getClass().getName(), 5, set.add("a", 3));
    assertEquals(set.getClass().getName(), 0, set.add("b", 10));
    assertEquals(set.getClass().getName(), 10, set.add("b", 1));
  }

  // --------------------------------------------
  // TESTS FOR CONTAINS
  // --------------------------------------------

  @Test
  public void testContainsSetEmpty() {
    Multiset<String> set = makeMultiset();

    assertFalse(set.getClass().getName(), set.contains("a"));
  }

  @Test
  public void testContainsTrueOneElement() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    assertTrue(set.getClass().getName(), set.contains("a"));
  }

  @Test
  public void testContainsFalseOneElement() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    assertFalse(set.getClass().getName(), set.contains("tree"));
    assertFalse(set.getClass().getName(), set.contains("house"));
  }

  @Test
  public void testContainsMultipleElementsNoDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("b");
    set.add("c");
    set.add("d");

    assertTrue(set.getClass().getName(), set.contains("a"));
    assertTrue(set.getClass().getName(), set.contains("b"));
    assertTrue(set.getClass().getName(), set.contains("c"));
    assertTrue(set.getClass().getName(), set.contains("d"));
    assertFalse(set.getClass().getName(), set.contains("q"));
  }

  @Test
  public void testContainsMultipleElementsWithDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    set.add("b");
    set.add("b");
    set.add("c");
    set.add("c");

    assertTrue(set.getClass().getName(), set.contains("a"));
    assertTrue(set.getClass().getName(), set.contains("b"));
    assertTrue(set.getClass().getName(), set.contains("c"));
    assertFalse(set.getClass().getName(), set.contains("q"));
  }

  @Test
  public void testContainsAfterRemovalNoDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("b");
    set.add("c");
    set.add("d");
    set.add("e");
    set.remove("a");
    set.remove("c");
    set.remove("e");

    assertFalse(set.getClass().getName(), set.contains("a"));
    assertFalse(set.getClass().getName(), set.contains("c"));
    assertFalse(set.getClass().getName(), set.contains("e"));
    assertTrue(set.getClass().getName(), set.contains("b"));
    assertTrue(set.getClass().getName(), set.contains("d"));
  }

  @Test
  public void testContainsAfterRemovalWithDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    set.add("c");
    set.add("c");
    set.add("e");
    set.add("e");

    set.remove("a");
    set.remove("c");
    set.remove("e");
    assertTrue(set.getClass().getName(), set.contains("a"));
    assertTrue(set.getClass().getName(), set.contains("c"));
    assertTrue(set.getClass().getName(), set.contains("e"));

    set.remove("a");
    set.remove("c");
    set.remove("e");
    assertFalse(set.getClass().getName(), set.contains("a"));
    assertFalse(set.getClass().getName(), set.contains("c"));
    assertFalse(set.getClass().getName(), set.contains("e"));

  }

  @SuppressWarnings("unlikely-arg-type")
  @Test
  public void testContainsObjectArgument() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    Integer integer = Integer.valueOf(3);
    assertFalse(set.getClass().getName(), set.contains(integer));
  }

  // --------------------------------------------
  // TESTS FOR COUNT
  // --------------------------------------------
  @Test
  public void testCountSetEmpty() {
    Multiset<String> set = makeMultiset();

    assertEquals(0, set.getCount("A"));
  }

  @Test
  public void testCountZeroNonEmpty() {
    Multiset<String> set = makeMultiset();

    set.add("B");
    assertEquals(0, set.getCount("A"));
  }

  @Test
  public void testCountOneOneElement() {
    Multiset<String> set = makeMultiset();

    set.add("C");
    assertEquals(1, set.getCount("C"));
  }

  @Test
  public void testCountMultipleElementsNoDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("b");
    set.add("c");
    set.add("d");

    assertEquals(1, set.getCount("a"));
    assertEquals(1, set.getCount("b"));
    assertEquals(1, set.getCount("c"));
    assertEquals(1, set.getCount("d"));
    assertEquals(0, set.getCount("q"));
  }

  @Test
  public void testCountMultipleElementsWithDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    set.add("b");
    set.add("b");
    set.add("c");
    set.add("c");

    assertEquals(2, set.getCount("a"));
    assertEquals(2, set.getCount("b"));
    assertEquals(2, set.getCount("c"));
    assertEquals(0, set.getCount("q"));
  }

  @Test
  public void testCountAfterRemovalNoDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("b");
    set.add("c");
    set.add("d");
    set.add("e");
    set.remove("a");
    set.remove("c");
    set.remove("e");

    assertEquals(0, set.getCount("a"));
    assertEquals(1, set.getCount("b"));
    assertEquals(0, set.getCount("c"));
    assertEquals(1, set.getCount("d"));
    assertEquals(0, set.getCount("e"));
  }

  @Test
  public void testCountAfterRemovalWithDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    set.add("c");
    set.add("c");
    set.add("e");
    set.add("e");

    set.remove("a");
    set.remove("c");
    set.remove("e");

    assertEquals(1, set.getCount("a"));
    assertEquals(1, set.getCount("c"));
    assertEquals(1, set.getCount("e"));
  }

  @Test
  public void testCountObjectArgument() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    Integer integer = Integer.valueOf(3);
    assertEquals(set.getClass().getName(), 0, set.getCount(integer));

  }

  // --------------------------------------------
  // TESTS FOR EQUALS
  // --------------------------------------------

  @Test
  public void testEqualsNonMultiset() {
    Multiset<String> set = makeMultiset();

    assertFalse(set.equals("HELLO"));
  }

  @Test
  public void testEqualsEmptyMultisetsAfterRemoval() {
    Multiset<String> set1 = makeMultiset();
    Multiset<String> set2 = makeMultiset();
    set1.add("a");
    set1.remove("a");

    assertTrue(set1.equals(set2));
    assertTrue(set2.equals(set1));
  }

  @Test
  public void testEqualsNoRepeats() {
    Multiset<String> set1 = makeMultiset();
    Multiset<String> set2 = makeMultiset();
    Multiset<String> set3 = makeMultiset();

    set1.add("a");
    set1.add("b");
    set1.add("c");
    set1.add("d");

    set2.add("d");
    set2.add("b");
    set2.add("c");
    set2.add("a");

    set3.add("b");
    set3.add("c");
    set3.add("a");

    assertTrue(set1.equals(set2));
    assertTrue(set2.equals(set1));
    assertFalse(set3.equals(set1));
  }

  @Test
  public void testEqualsWithRepeats() {
    Multiset<String> set1 = makeMultiset();
    Multiset<String> set2 = makeMultiset();
    Multiset<String> set3 = makeMultiset();
    set1.add("a");
    set1.add("b");
    set1.add("a");
    set1.add("b");
    set1.add("c");
    set1.add("d");

    set2.add("d");
    set2.add("b");
    set2.add("b");
    set2.add("c");
    set2.add("a");
    set2.add("a");

    set3.add("d");
    set3.add("b");
    set3.add("d");
    set3.add("c");
    set3.add("a");
    set3.add("a");

    assertTrue(set1.equals(set2));
    assertTrue(set2.equals(set1));
    assertFalse(set3.equals(set1));
    assertFalse(set1.equals(set3));
  }

  // --------------------------------------------
  // TESTS FOR ITERATOR
  // --------------------------------------------

  @Test
  public void testIteratorEmptyHasNext() {
    Multiset<String> set1 = makeMultiset();
    Iterator<String> it = set1.iterator();

    assertFalse(it.hasNext());
  }

  @Test
  public void testIteratorEmptyNextException() {
    Multiset<String> set1 = makeMultiset();
    Iterator<String> it = set1.iterator();
    assertThrows(NoSuchElementException.class, () -> it.next());
  }

  @Test
  public void testIteratorNonEmptyNextException() {
    Multiset<String> set1 = makeMultiset();
    set1.add("a");
    Iterator<String> it = set1.iterator();
    it.next();
    assertThrows(NoSuchElementException.class, () -> it.next());
  }

  private void testIteration(ArrayList<String> items) {

    // Create a hashmap with item counts
    HashMap<String, Integer> map = new HashMap<>();
    for (String item : items) {
      if (map.containsKey(item)) {
        map.put(item, map.get(item) + 1);
      } else {
        map.put(item, 1);
      }
    }

    // Add all of the items to the multiset
    Multiset<String> set = makeMultiset();
    for (String item : items) {
      set.add(item);
    }

    // Iterate through the multiset, decrementing counts
    for (String item : set) {
      if (map.containsKey(item)) {
        map.put(item, map.get(item) - 1);
      } else {
        fail();
      }
    }

    // all counts should be 0.
    for (String key : map.keySet()) {
      assertEquals(0, (int) map.get(key));
    }
  }

  @Test
  public void testIteratorNoRepeats() {
    ArrayList<String> items = new ArrayList<>();
    items.add("a");
    items.add("b");
    items.add("c");
    items.add("d");
    items.add("e");
    items.add("f");
    testIteration(items);
  }

  @Test
  public void testIteratorWithRepeatsAndRemovals() {
    ArrayList<String> items = new ArrayList<>();
    items.add("a");
    items.add("b");
    items.add("c");
    items.add("d");
    items.add("e");
    items.add("f");

    items.add("a");
    items.add("b");
    items.add("c");
    items.add("d");
    items.add("e");

    items.add("a");
    items.add("b");

    items.remove("a");

    testIteration(items);
  }

  // --------------------------------------------
  // TESTS FOR ITERATOR REMOVE
  // --------------------------------------------
  @Test
  public void testIteratorRemoveToCount1() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    set.add("b");
    set.add("b");
    set.add("c");
    set.add("c");

    Iterator<String> it = set.iterator();

    while (it.hasNext()) { // remove one "b"
      if (it.next().equals("b")) {
        it.remove();
        break;
      }
    }
    assertEquals(2, set.getCount("a"));
    assertEquals(1, set.getCount("b"));
    assertEquals(2, set.getCount("c"));
    assertEquals(5, set.size());

    it = set.iterator();
    while (it.hasNext()) { // remove one "a"
      if (it.next().equals("a")) {
        it.remove();
        break;
      }
    }
    assertEquals(1, set.getCount("a"));
    assertEquals(1, set.getCount("b"));
    assertEquals(2, set.getCount("c"));
    assertEquals(4, set.size());

    it = set.iterator();
    while (it.hasNext()) { // remove one "c"
      if (it.next().equals("c")) {
        it.remove();
        break;
      }
    }
    assertEquals(1, set.getCount("a"));
    assertEquals(1, set.getCount("b"));
    assertEquals(1, set.getCount("c"));
    assertEquals(3, set.size());
  }

  @Test
  public void testIteratorRemoveToCount0() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    set.add("b");
    set.add("b");
    set.add("c");
    set.add("c");

    Iterator<String> it = set.iterator();

    while (it.hasNext()) { // remove one "b"
      if (it.next().equals("b")) {
        it.remove();
      }
    }
    assertEquals(2, set.getCount("a"));
    assertEquals(0, set.getCount("b"));
    assertEquals(2, set.getCount("c"));
    assertEquals(4, set.size());

    it = set.iterator();
    while (it.hasNext()) { // remove one "a"
      if (it.next().equals("a")) {
        it.remove();
      }
    }
    assertEquals(0, set.getCount("a"));
    assertEquals(0, set.getCount("b"));
    assertEquals(2, set.getCount("c"));
    assertEquals(2, set.size());

    it = set.iterator();
    while (it.hasNext()) { // remove one "c"
      if (it.next().equals("c")) {
        it.remove();
      }
    }
    assertEquals(0, set.getCount("a"));
    assertEquals(0, set.getCount("b"));
    assertEquals(0, set.getCount("c"));
    assertEquals(0, set.size());
  }

  @Test
  public void testIteratorDoubleRemoveThrowsIllegalStateException() {

    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    Iterator<String> it = set.iterator();
    it.next();
    it.remove();
    try {
      it.remove();
    } catch (IllegalStateException e) {
      // Good!
      return;
    }
    fail(); // Shouldn't make it here.
  }

  @Test
  public void testIteratorRemoveBeforeNextThrowsIllegalStateException() {

    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    Iterator<String> it = set.iterator();
    assertThrows(IllegalStateException.class, () -> it.remove());
  }

  // --------------------------------------------
  // TESTS FOR REMOVEALL and RETAINALL
  // --------------------------------------------

  @Test
  public void testRemoveAllUnsupported() {
    Multiset<String> set1 = makeMultiset();
    assertThrows(UnsupportedOperationException.class, () -> set1.removeAll(set1));
  }

  @Test
  public void testReteainAllUnsupported() {
    Multiset<String> set1 = makeMultiset();
    assertThrows(UnsupportedOperationException.class, () -> set1.retainAll(set1));
  }

  // --------------------------------------------
  // TESTS FOR ADDALL
  // --------------------------------------------
  @Test
  public void testAddAll() {
    Multiset<String> set = makeMultiset();
    ArrayList<String> list = new ArrayList<>();
    list.add("a");
    list.add("a");
    list.add("c");
    list.add("e");
    list.add("e");

    set.addAll(list);

    assertEquals(5, set.size());
    assertEquals(2, set.getCount("a"));
    assertEquals(1, set.getCount("c"));
    assertEquals(2, set.getCount("e"));
  }

  // --------------------------------------------
  // TESTS FOR CLEAR
  // --------------------------------------------

  @Test
  public void testClear() {
    Multiset<String> set = makeMultiset();

    set.add("a");
    set.add("a");
    set.add("c");

    set.clear();

    assertEquals(0, set.size());
    assertEquals(0, set.getCount("a"));
    assertEquals(0, set.getCount("c"));
  }

  // --------------------------------------------
  // TESTS FOR HASH CODE
  // --------------------------------------------

  @Test
  public void testHashCodeEmptySets() {
    Multiset<String> set1 = makeMultiset();
    Multiset<String> set2 = makeMultiset();

    assertTrue(set1.hashCode() == set2.hashCode());
  }

  @Test
  public void testHashCodeEqualNonEmptySets() {
    Multiset<String> set1 = makeMultiset();
    Multiset<String> set2 = makeMultiset();

    set1.add("a");
    set1.add("b");

    set2.add("b");
    set2.add("a");

    assertTrue(set1.hashCode() == set2.hashCode());
  }

  // --------------------------------------------
  // TESTS FOR ISEMPTY
  // --------------------------------------------

  @Test
  public void testIsEmptyEmpty() {
    Multiset<String> set = makeMultiset();

    assertTrue(set.isEmpty());
  }

  @Test
  public void testIsEmptyNotEmpty() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    assertFalse(set.isEmpty());
  }

  @Test
  public void testIsEmptyEmptyAfterRemoval() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.remove("a");
    assertTrue(set.isEmpty());
  }

  // --------------------------------------------
  // TESTS FOR REMOVE
  // (Note that remove is implicitly covered by lots of other tests. These
  // are mostly intended to test the return value.)
  // --------------------------------------------

  @Test
  public void testRemoveFromEmptyReturnsFalse() {
    Multiset<String> set = makeMultiset();
    assertFalse(set.remove("A"));
  }

  @Test
  public void testRemoveSingletonReturnsTrue() {
    Multiset<String> set = makeMultiset();
    set.add("A");
    assertTrue(set.remove("A"));
  }

  @Test
  public void testRemoveDuplicateReturnsTrue() {
    Multiset<String> set = makeMultiset();
    set.add("A");
    set.add("A");
    set.remove("A");
    assertTrue(set.remove("A"));
  }

  @SuppressWarnings("unlikely-arg-type")
  @Test
  public void testRemoveObjectArgument() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    Integer integer = Integer.valueOf(3);
    assertFalse(set.getClass().getName(), set.remove(integer));
  }

  // --------------------------------------------
  // TESTS FOR toArray()
  // --------------------------------------------

  @Test
  public void testToObjectArrayEmptySet() {
    Multiset<String> set = makeMultiset();
    Object[] array = set.toArray();
    assertEquals(0, array.length);
  }

  @Test
  public void testToObjectArrayOneElement() {
    Multiset<String> set = makeMultiset();
    set.add("A");
    Object[] array = set.toArray();
    assertEquals(1, array.length);
    assertEquals("A", (String) array[0]);
  }

  @Test
  public void testToObjectArrayRepeatedElements() {
    Multiset<String> set = makeMultiset();
    set.add("A");
    set.add("A");
    Object[] array = set.toArray();
    assertEquals(2, array.length);
    assertEquals("A", (String) array[0]);
    assertEquals("A", (String) array[1]);
  }

  // --------------------------------------------
  // TESTS FOR toArray() (generic)
  // --------------------------------------------

  @Test
  public void testToGenericArrayEmptySet() {
    Multiset<String> set = makeMultiset();
    String[] array = set.toArray(new String[0]);
    assertEquals(0, array.length);
  }

  @Test
  public void testToGenericArrayOneElement() {
    Multiset<String> set = makeMultiset();
    set.add("A");
    String[] array = set.toArray(new String[0]);
    assertEquals(1, array.length);
    assertEquals("A", array[0]);
  }

  @Test
  public void testToGenericArrayRepeatedElements() {
    Multiset<String> set = makeMultiset();
    set.add("A");
    set.add("A");
    String[] array = set.toArray(new String[0]);
    assertEquals(2, array.length);
    assertEquals("A", array[0]);
    assertEquals("A", array[1]);
  }

  // --------------------------------------------
  // TESTS FOR toString()
  // --------------------------------------------

  @Test
  public void testToStringEmpty() {
    Multiset<String> set = makeMultiset();

    assertEquals("[]", set.toString());
  }

  @Test
  public void testToStringSingleElement() {
    Multiset<String> set = makeMultiset();
    set.add("C");
    set.add("C");

    assertEquals("[C x 2]", set.toString());
  }

  @Test
  public void testToStringDuplicateElement() {
    Multiset<String> set = makeMultiset();
    set.add("C");
    set.add("C");

    assertEquals("[C x 2]", set.toString());
  }

  @Test
  public void testToStringMultipleElements() {
    Multiset<String> set = makeMultiset();
    set.add("B");
    set.add("C");
    set.add("C");

    boolean ok = "[C x 2, B x 1]".equals(set.toString()) || "[B x 1, C x 2]".equals(set.toString());
    assertTrue(ok);
  }

  @Test
  public void testToStringAfterRemoval() {
    Multiset<String> set = makeMultiset();
    set.add("B");
    set.add("C");
    set.add("C");
    set.remove("B");
    assertEquals("[C x 2]", set.toString());
  }

  // --------------------------------------------
  // TESTS FOR proper use of generics
  // --------------------------------------------
  @Test
  public void testMethodsWorkWithIntegerElements() {
    Multiset<Integer> set = makeMultiset();
    set.add(1);
    set.add(2);
    set.add(5);
    set.add(5);

    assertEquals(4, set.size());
    assertEquals(1, set.getCount(1));
    assertEquals(1, set.getCount(2));
    assertEquals(2, set.getCount(5));

    set.clear();

    set.add(1);
    assertEquals("[1 x 1]", set.toString());

    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(2);
    set.addAll(list);
    set.contains(4);
    assertFalse(set.equals(list));
    assertFalse(set.isEmpty());
    Integer[] num = set.toArray(new Integer[0]);

  }
}
