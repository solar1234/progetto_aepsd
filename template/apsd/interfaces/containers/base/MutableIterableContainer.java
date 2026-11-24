package apsd.interfaces.containers.base;

// import apsd.interfaces.containers.iterators.MutableBackwardIterator;
// import apsd.interfaces.containers.iterators.MutableForwardIterator;

import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Interface: IterableContainer con supporto all'iterazione mutabile. */
public interface MutableIterableContainer<T extends MutableIterableContainer<T, Data>, Data> extends IterableContainer<T, Data>{
  // Must extend IterableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Override specific member functions of IterableContainer                  */
  /* ************************************************************************ */

  // FIterator
    @Override
    MutableForwardIterator<Data> FIterator();
  // BIterator
    @Override
    MutableBackwardIterator<Data> BIterator();

}
