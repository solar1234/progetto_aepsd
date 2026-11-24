package apsd.interfaces.containers.sequences.ordered;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.ordered.OrderedIterableContainer;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.containers.sequences.Sequence;

/** Interface: Sequence & OrderedIterableContainer. */
public interface OrderedSequence<T extends OrderedSequence<T, Data>, Data extends Comparable<? super Data>> extends Sequence<T, Data>, OrderedIterableContainer<T, Data> {
  // Must extend Sequence and OrderedIterableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Specific member functions of OrderedSequence                             */
  /* ************************************************************************ */

  // SearchPredecessor
    default Natural SearchPredecessor(Data dat) {
        if (dat == null) {
            return null;
        }
        ForwardIterator<Data> itr = FIterator();
        Data pred = null;
        Natural predPos = null;
        for (long idx = 0L; itr.IsValid(); idx++) {
            Data cur = itr.DataNNext();
            if (cur != null && cur.compareTo(dat) < 0 && (pred == null || cur.compareTo(pred) >= 0)) {
                pred = cur;
                predPos = Natural.Of(idx);
            }
        }
        return predPos;
    }

    // SearchSuccessor
    default Natural SearchSuccessor(Data dat) {
        if (dat == null) {
            return null;
        }
        ForwardIterator<Data> itr = FIterator();
        Data succ = null;
        Natural succPos = null;
        for (long idx = 0L; itr.IsValid(); idx++) {
            Data cur = itr.DataNNext();
            if (cur != null && cur.compareTo(dat) > 0 && (succ == null || cur.compareTo(succ) < 0)) {
                succ = cur;
                succPos = Natural.Of(idx);
            }
        }
        return succPos;
    }


}
