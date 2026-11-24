package apsd.interfaces.containers.sequences;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<T extends DynVector<T, Data>, Data> extends ResizableContainer<T>, InsertableAtSequence<T,Data>, RemovableAtSequence<T, Data>, Vector<T, Data> {
  // Must extend ResizableContainer, InsertableAtSequence, RemovableAtSequence, and Vector.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  // InsertAt
  @Override
  default void InsertAt(Data dat, Natural pos) {
      if (pos == null) {
          throw new NullPointerException("Natural cannot be null!");
      }
      long idx = pos.ToLong();
      long size = Size().ToLong();
      if (idx > size) {
          throw new IndexOutOfBoundsException("Index " + idx + " is out of bounds for insertion in size " + size + "!");
      }
      Expand();
      if (idx < size) {
          ShiftRight(pos);
      }
      SetAt(dat, pos);
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // AtNRemove
  @Override
  default Data AtNRemove(Natural pos) {
      Data old = GetAt(pos);
      ShiftLeft(pos);
      Reduce();
      return old;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
  @Override
  Natural Size();

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // SubSequence
  @Override
  default T SubSequence(Natural from, Natural to) {
      return InsertableAtSequence.super.SubSequence(from, to);
  }

}
