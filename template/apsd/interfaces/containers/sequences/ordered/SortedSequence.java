package apsd.interfaces.containers.sequences.ordered;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.ordered.SortedIterableContainer;

/** Interface: OrderedSequence & SortedIterableContainer. */
public interface SortedSequence<T extends SortedSequence<T, Data>, Data extends Comparable<? super Data>> extends OrderedSequence<T, Data>, SortedIterableContainer<T, Data>{
  // Must extend OrderedSequence and SortedIterableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

  // Exists
    @Override
    default boolean Exists(Data dat) {
        return Search(dat) != null;
    }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedContainer                 */
  /* ************************************************************************ */

  // Min
    @Override
    default Data Min() {
        return IsEmpty() ? null : GetFirst();
    }

    // Max
    @Override
    default Data Max() {
        return IsEmpty() ? null : GetLast();
    }

    // Predecessor
    @Override
    default Data Predecessor(Data dat) {
        Natural pos = SearchPredecessor(dat);
        return (pos == null) ? null : GetAt(pos);
    }

    // Successor
    @Override
    default Data Successor(Data dat) {
        Natural pos = SearchSuccessor(dat);
        return (pos == null) ? null : GetAt(pos);
    }


  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // Search
    @Override
    default Natural Search(Data dat) {
        if (dat == null || IsEmpty()) {
            return null;
        }
        long left = 0L;
        long right = Size().ToLong() - 1L;
        Natural pos = null;
        while (left <= right) {
            long mid = left + (right - left) / 2L;
            Natural midNat = Natural.Of(mid);
            int cmp = GetAt(midNat).compareTo(dat);
            if (cmp == 0) {
                pos = midNat;
                right = mid - 1L;
            } else if (cmp < 0) {
                left = mid + 1L;
            } else {
                right = mid - 1L;
            }
        }
        return pos;
    }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSequence                  */
  /* ************************************************************************ */

  // SearchPredecessor
    @Override
    default Natural SearchPredecessor(Data dat) {
        if (dat == null || IsEmpty()) {
            return null;
        }
        long left = 0L;
        long right = Size().ToLong() - 1L;
        Natural pos = null;
        while (left <= right) {
            long mid = left + (right - left) / 2L;
            Natural midNat = Natural.Of(mid);
            if (GetAt(midNat).compareTo(dat) < 0) {
                pos = midNat;
                left = mid + 1L;
            } else {
                right = mid - 1L;
            }
        }
        return pos;
    }

    // SearchSuccessor
    @Override
    default Natural SearchSuccessor(Data dat) {
        if (dat == null || IsEmpty()) {
            return null;
        }
        long left = 0L;
        long right = Size().ToLong() - 1L;
        Natural pos = null;
        while (left <= right) {
            long mid = left + (right - left) / 2L;
            Natural midNat = Natural.Of(mid);
            if (GetAt(midNat).compareTo(dat) > 0) {
                pos = midNat;
                right = mid - 1L;
            } else {
                left = mid + 1L;
            }
        }
        return pos;
    }


}
