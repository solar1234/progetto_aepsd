package apsd.interfaces.containers.sequences;

 import apsd.classes.utilities.Box;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.IterableContainer;
 import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: IterableContainer con supporto alla lettura e ricerca tramite posizione. */
public interface Sequence<T extends Sequence<T,Data>, Data> extends IterableContainer<T, Data>{
  // Must extend IterableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Sequence                                    */
  /* ************************************************************************ */

    // GetAt
    default Data GetAt(Natural pos) {
      long idx = ExcIfOutOfBound(pos);
      ForwardIterator<Data> itr = FIterator();
      itr.Next(idx);
      return itr.GetCurrent();
    }

    // GetFirst
    default Data GetFirst() {
        return GetAt(Natural.ZERO);
    }

    // GetLast
    default Data GetLast() {
        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("Sequence is empty!");
        }
        return GetAt(Size().Decrement());
    }

    // Search
    default Natural Search(Data dat) {
        if (dat == null) {
            return null;
        }
        Box<Natural> idx = new Box<>(Natural.ZERO);
        boolean found = TraverseForward(elm -> {
            if (dat.equals(elm)) {
                return true;
            }
            idx.Set(idx.Get().Increment());
            return false;
        });
        return found ? idx.Get() : null;
    }

    // IsInBound
    default boolean IsInBound(Natural pos) {
        if (pos == null) {
            return false;
        }
        return pos.ToLong() < Size().ToLong();
    }

    // ExcIfOutOfBound
    default long ExcIfOutOfBound(Natural pos) {
        if (pos == null) {
            throw new NullPointerException("Natural can't be null!");
        }
        long idx = pos.ToLong();
        if (idx >= Size().ToLong()) {
            throw new IndexOutOfBoundsException("Index " + idx + " is out of bounds for size " + Size().ToLong() + "!");
        }
        return idx;
    }

    // IsEqual
    default boolean IsEqual(Sequence<?, Data> seq) {
        if (seq == null || Size().ToLong() != seq.Size().ToLong()) {
            return false;
        }
        ForwardIterator<Data> itr1 = FIterator();
        ForwardIterator<? extends Data> itr2 = seq.FIterator();
        while (itr1.IsValid() && itr2.IsValid()) {
            Data d1 = itr1.GetCurrent();
            Data d2 = itr2.GetCurrent();
            if ((d1 == null) ? (d2 != null) : !d1.equals(d2)) {
                return false;
            }
            itr1.Next();
            itr2.Next();
        }
        return true;
    }

    // SubSequence
    T SubSequence(Natural from, Natural to);


}
