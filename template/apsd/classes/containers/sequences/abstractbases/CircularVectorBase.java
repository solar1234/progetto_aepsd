package apsd.classes.containers.sequences.abstractbases;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) circular vector base implementation. */
abstract public class CircularVectorBase<T extends CircularVectorBase<T, Data>, Data> extends VectorBase<T,Data>{
  // Must extend VectorBase.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

   protected long start = 0L;

  // CircularVectorBase()
    protected CircularVectorBase() {
        super();
    }

    // CircularVectorBase(Natural inisize)
    protected CircularVectorBase(Natural inisize) {
        super(inisize);
    }

    // CircularVectorBase(TraversableContainer<?, Data> con)
    protected CircularVectorBase(TraversableContainer<?, Data> con) {
        super(con);
    }


  /* ************************************************************************ */
  /* Specific member functions of CircularVectorBase                          */
  /* ************************************************************************ */

  // ArrayAlloc
    @Override
    protected void ArrayAlloc(Natural newsize) {
        super.ArrayAlloc(newsize);
        start = 0L;
    }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // Realloc
    @Override
    public void Realloc(Natural newsize) {
        if (newsize == null) throw new NullPointerException("Natural cannot be null!");
        Data[] oldArr = arr;
        long oldStart = start;
        long oldSize = Size().ToLong();
        ArrayAlloc(newsize);
        if (oldArr != null && oldArr.length > 0 && arr.length > 0) {
            long limit = Math.min(oldSize, arr.length);
            for (long i = 0; i < limit; i++) {
                arr[(int) i] = oldArr[(int) ((oldStart + i) % oldArr.length)];
            }
        }
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // GetAt

    @Override
    public Data GetAt(Natural pos) {
        long idx = ExcIfOutOfBound(pos);
        return arr[(int) ((start + idx) % arr.length)];
    }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // SetAt

    @Override
    public void SetAt(Data dat, Natural pos) {
        long idx = ExcIfOutOfBound(pos);
        arr[(int) ((start + idx) % arr.length)] = dat;
    }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  // ShiftLeft
    @Override
    public void ShiftLeft(Natural pos, Natural num) {
        if (pos == null || num == null) throw new NullPointerException("Natural cannot be null!");
        long len = Size().ToLong();
        if (len == 0) {
            if (!pos.IsZero()) throw new IndexOutOfBoundsException("Index out of bounds: " + pos + " for size 0!");
            return;
        }
        long idx = ExcIfOutOfBound(pos);
        long shift = num.ToLong();
        if (shift == 0) return;

        long cap = arr.length;
        long effectiveShift = Math.min(shift, len - idx);
        long leftCount = idx;
        long rightCount = (len - idx) - effectiveShift;

        if (leftCount >= rightCount) {
            for (long i = idx; i < len - effectiveShift; i++) {
                arr[(int) ((start + i) % cap)] = arr[(int) ((start + i + effectiveShift) % cap)];
            }
            for (long i = len - effectiveShift; i < len; i++) {
                arr[(int) ((start + i) % cap)] = null;
            }
        } else {
            for (long i = idx - 1; i >= 0; i--) {
                arr[(int) ((start + i + effectiveShift) % cap)] = arr[(int) ((start + i) % cap)];
            }
            for (long i = 0; i < effectiveShift; i++) {
                arr[(int) ((start + i) % cap)] = null;
            }
            start = (start + effectiveShift) % cap;
            for (long i = len - effectiveShift; i < len; i++) {
                arr[(int) ((start + i) % cap)] = null;
            }
        }
    }

    // ShiftRight
    @Override
    public void ShiftRight(Natural pos, Natural num) {
        if (pos == null || num == null) throw new NullPointerException("Natural cannot be null!");
        long len = Size().ToLong();
        if (len == 0) {
            if (!pos.IsZero()) throw new IndexOutOfBoundsException("Index out of bounds: " + pos + " for size 0!");
            return;
        }
        long idx = ExcIfOutOfBound(pos);
        long shift = num.ToLong();
        if (shift == 0) return;

        long cap = arr.length;
        long effectiveShift = Math.min(shift, len - idx);
        long leftCount = idx;
        long rightCount = (len - idx) - effectiveShift;

        if (leftCount >= rightCount) {
            for (long i = len - 1; i >= idx + effectiveShift; i--) {
                arr[(int) ((start + i) % cap)] = arr[(int) ((start + i - effectiveShift) % cap)];
            }
            for (long i = idx; i < idx + effectiveShift; i++) {
                arr[(int) ((start + i) % cap)] = null;
            }
        } else {
            long newStart = (start - effectiveShift + cap) % cap;
            for (long i = 0; i < idx; i++) {
                arr[(int) ((newStart + i) % cap)] = arr[(int) ((start + i) % cap)];
            }
            for (long i = idx; i < idx + effectiveShift; i++) {
                arr[(int) ((newStart + i) % cap)] = null;
            }
            if (cap > len) {
                for (long i = len - effectiveShift; i < len; i++) {
                    arr[(int) ((start + i) % cap)] = null;
                }
            }
            start = newStart;
        }
    }

}
