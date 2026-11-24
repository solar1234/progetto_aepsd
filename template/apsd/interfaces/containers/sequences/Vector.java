package apsd.interfaces.containers.sequences;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.ReallocableContainer;


public interface Vector<T extends Vector<T, Data>, Data> extends ReallocableContainer<T>, MutableSequence<T, Data> {
  // Must extend ReallocableContainer and MutableSequence.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

    // ShiftLeft
    default void ShiftLeft(Natural start, Natural end, Natural number) {
        if (start == null || end == null || number == null) {
            throw new NullPointerException("Arguments cannot be null!");
        }
        if (IsEmpty()) {
            return;
        }
        long st = ExcIfOutOfBound(start);
        long en = ExcIfOutOfBound(end);
        if (st > en) {
            throw new IndexOutOfBoundsException("Start index " + st + " cannot exceed end index " + en + "!");
        }
        long num = number.ToLong();
        if (num == 0) {
            return;
        }
        long rangeLen = en - st + 1;
        long shift = Math.min(num, rangeLen);
        for (long i = st; i <= en - shift; i++) {
            SetAt(GetAt(Natural.Of(i + shift)), Natural.Of(i));
        }
        for (long i = en - shift + 1; i <= en; i++) {
            SetAt(null, Natural.Of(i));
        }
    }

    default void ShiftLeft(Natural pos, Natural num) {
        if (pos == null || num == null) {
            throw new NullPointerException("Arguments cannot be null!");
        }
        if (IsEmpty()) {
            return;
        }
        ShiftLeft(pos, Size().Decrement(), num);
    }

    default void ShiftLeft(Natural pos) {
        ShiftLeft(pos, Natural.ONE);
    }

    // ShiftFirstLeft
    default void ShiftFirstLeft() {
        ShiftLeft(Natural.ZERO);
    }

    // ShiftLastLeft
    default void ShiftLastLeft() {
        if (!IsEmpty()) {
            ShiftLeft(Size().Decrement());
        }
    }

    // ShiftRight
    default void ShiftRight(Natural start, Natural end, Natural number) {
        if (start == null || end == null || number == null) {
            throw new NullPointerException("Arguments cannot be null!");
        }
        if (IsEmpty()) {
            return;
        }
        long st = ExcIfOutOfBound(start);
        long en = ExcIfOutOfBound(end);
        if (st > en) {
            throw new IndexOutOfBoundsException("Start index " + st + " cannot exceed end index " + en + "!");
        }
        long num = number.ToLong();
        if (num == 0) {
            return;
        }
        long rangeLen = en - st + 1;
        long shift = Math.min(num, rangeLen);
        for (long i = en; i >= st + shift; i--) {
            SetAt(GetAt(Natural.Of(i - shift)), Natural.Of(i));
        }
        for (long i = st; i < st + shift; i++) {
            SetAt(null, Natural.Of(i));
        }
    }

    default void ShiftRight(Natural pos, Natural num) {
        if (pos == null || num == null) {
            throw new NullPointerException("Arguments cannot be null!");
        }
        if (IsEmpty()) {
            return;
        }
        ShiftRight(pos, Size().Decrement(), num);
    }

    default void ShiftRight(Natural pos) {
        ShiftRight(pos, Natural.ONE);
    }

    // ShiftFirstRight
    default void ShiftFirstRight() {
        ShiftRight(Natural.ZERO);
    }

    // ShiftLastRight
    default void ShiftLastRight() {
        if (!IsEmpty()) {
            ShiftRight(Size().Decrement());
        }
    }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
    @Override
    default Natural Size() {
        return Capacity();
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // SubSequence
  @Override
  default T SubSequence(Natural from, Natural to) {
      T sub = Empty();
      if (from == null || to == null || !IsInBound(from) || !IsInBound(to) || from.compareTo(to) > 0) {
          return sub;
      }
      long start = from.ToLong();
      long len = to.ToLong() - start + 1;
      sub.Realloc(Natural.Of(len));
      for (long i = 0; i < len; i++) {
          sub.SetAt(GetAt(Natural.Of(start + i)), Natural.Of(i));
      }
      return sub;
  }

}
