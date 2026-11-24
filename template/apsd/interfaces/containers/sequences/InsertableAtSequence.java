package apsd.interfaces.containers.sequences;

 import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.InsertableContainer;

/** Interface: Sequence con supporto all'inserimento di un dato tramite posizione. */
public interface InsertableAtSequence<T extends InsertableAtSequence<T, Data>, Data> extends Sequence<T, Data>, InsertableContainer<T, Data> {
  // Must extend Sequence and InsertableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of InsertableAtSequence                        */
  /* ************************************************************************ */

    //  InsertAt
    void InsertAt(Data dat, Natural pos);

    // InsertFirst
    default void InsertFirst(Data dat) {
        InsertAt(dat, Natural.ZERO);
    }

    // InsertLast
    default void InsertLast(Data dat) {
        InsertAt(dat, Size());
    }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // Insert
    @Override
    default boolean Insert(Data dat) {
        InsertLast(dat);
        return true;
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // SubSequence
  @Override
  default T SubSequence(Natural from, Natural to) {
      if (from == null || to == null || !IsInBound(from) || !IsInBound(to) || from.compareTo(to) > 0) {
          return Empty();
      }
      T sub = Empty();
      for (Natural idx = from; idx.compareTo(to) <= 0; idx = idx.Increment()) {
          sub.InsertLast(GetAt(idx));
      }
      return sub;
  }

}
