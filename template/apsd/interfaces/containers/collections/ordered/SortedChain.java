package apsd.interfaces.containers.collections.ordered;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.sequences.ordered.SortedRemovableAtSequence;

public interface SortedChain<T extends SortedChain<T, Data>, Data extends Comparable<? super Data>> extends OrderedChain<T, Data>, SortedRemovableAtSequence<T, Data> {
  // Must extend OrderedChain and SortedRemovableAtSequence.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Specific member functions of SortedChain                                 */
  /* ************************************************************************ */

  // Intersection
  default void Intersection(SortedChain<?, Data> chn) {
      if (chn != null) {
          long i = 0L;
          long j = 0L;
          while (i < Size().ToLong() && j < chn.Size().ToLong()) {
              Natural posI = Natural.Of(i);
              int cmp = GetAt(posI).compareTo(chn.GetAt(Natural.Of(j)));
              if (cmp == 0) {
                  i++;
                  j++;
              } else if (cmp < 0) {
                  RemoveAt(posI);
              } else {
                  j++;
              }
          }
          while (i < Size().ToLong()) {
              RemoveLast();
          }
      }
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // Search
  @Override
  default Natural Search(Data dat) {
      return SortedRemovableAtSequence.super.Search(dat);
  }

}
