package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;


/** Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione. */
public interface MutableSequence<T extends MutableSequence<T, Data>, Data> extends Sequence<T, Data>, MutableIterableContainer<T, Data> {
  // Must extend Sequence and MutableIterableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of MutableSequence                             */
  /* ************************************************************************ */

    // SetAt
    default void SetAt(Data dat, Natural pos) {
      long idx = ExcIfOutOfBound(pos);
      MutableForwardIterator<Data> itr = FIterator();
      itr.Next(idx);
      itr.SetCurrent(dat);
  }

    // GetNSetAt
    default Data GetNSetAt(Data dat, Natural pos) {
        Data old = GetAt(pos);
        SetAt(dat, pos);
        return old;
    }

    // SetFirst
    default void SetFirst(Data dat) {
        SetAt(dat, Natural.ZERO);
    }

    // GetNSetFirst
    default Data GetNSetFirst(Data dat) {
        return GetNSetAt(dat, Natural.ZERO);
    }

    // SetLast
    default void SetLast(Data dat) {
        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("Sequence is empty!");
        }
        SetAt(dat, Size().Decrement());
    }

    // GetNSetLast
    default Data GetNSetLast(Data dat) {
        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("Sequence is empty!");
        }
        return GetNSetAt(dat, Size().Decrement());
    }

    // Swap
    default void Swap(Natural pos1, Natural pos2) {
        Data dat1 = GetAt(pos1);
        Data dat2 = GetNSetAt(dat1, pos2);
        SetAt(dat2, pos1);
    }


}
