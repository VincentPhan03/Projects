import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Abstract class for Multiset.
 * 
 * @author phanvm
 * @version PA 1
 *
 *          This work complies with JMU honor code.
 */
public abstract class AbstractMultiset<E> extends AbstractCollection<E> implements Multiset<E> {
  private ArrayList<E> elements;

  public AbstractMultiset() {
    this.elements = new ArrayList<E>();
  }

  @Override
  public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
  
  @Override
  public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }


  @Override
  public int hashCode() {
    return 1;
  }

}
