package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore all'indietro. */
public interface BackwardIterator<Data> extends Iterator<Data>{
  // Must extend Iterator.

  /* ************************************************************************ */
  /* Specific member functions of BackwardIterator                            */
  /* ************************************************************************ */

  // Prev
  default void Prev() { DataNPrev(); }

    default void Prev(long stp) {
        while (stp > 0) {
            Prev();
            stp--;
        }
    }

    default void Prev(Natural stp) {
        if (stp == null) { throw new NullPointerException("Natural cannot be null!"); }
        Prev(stp.ToLong());
    }
  // DataNPrev
    Data DataNPrev();

  // ForEachBackward
    default boolean ForEachBackward(Predicate<Data> fun) {
      if (fun != null) {
          while (IsValid()) {
              if (fun.Apply(DataNPrev())) { return true; }
          }
      }
      return false;
    }

}
