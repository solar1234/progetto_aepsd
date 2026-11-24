package apsd.interfaces.containers.sequences;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.RemovableContainer;

/** Interface: Sequence con supporto alla rimozione di un dato tramite posizione. */
public interface RemovableAtSequence<T extends RemovableAtSequence<T, Data>, Data> extends Sequence<T, Data>, RemovableContainer<T, Data> {
  // Must extend Sequence and RemovableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of RemovableAtSequence                         */
  /* ************************************************************************ */

    // RemoveAt
    default void RemoveAt(Natural pos) {
        AtNRemove(pos);
    }

    // AtNRemove
    Data AtNRemove(Natural pos);

    // RemoveFirst
    default void RemoveFirst() {
        FirstNRemove();
    }

    // FirstNRemove
    default Data FirstNRemove() {
        return AtNRemove(Natural.ZERO);
    }

    // RemoveLast
    default void RemoveLast() {
        LastNRemove();
    }

    // LastNRemove
    default Data LastNRemove() {
        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("Sequence is empty!");
        }
        return AtNRemove(Size().Decrement());
    }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // Remove
    @Override
    default boolean Remove(Data dat) {
        Natural pos = Search(dat);
        if (pos == null) {
            return false;
        }
        RemoveAt(pos);
        return true;
    }

}
