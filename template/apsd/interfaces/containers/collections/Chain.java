package apsd.interfaces.containers.collections;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<T extends Chain<T, Data>, Data> extends Collection<T, Data>, RemovableAtSequence<T, Data> {
  // Must extend Collection and RemovableAtSequence.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // Remove
  @Override
  default boolean Remove(Data dat) {
      Natural pos = Search(dat);
      if (pos != null) {
          RemoveAt(pos);
          return true;
      }
      return false;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // Search
  @Override
  default Natural Search(Data dat) {
      return RemovableAtSequence.super.Search(dat);
  }

}
