package apsd.classes.containers.sequences.abstractbases;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic linear vector base implementation. */
abstract public class DynLinearVectorBase<T extends DynLinearVectorBase<T, Data>, Data> extends LinearVectorBase<T, Data> implements DynVector<T, Data> {
  // Must extend LinearVectorBase.
  // Must implement DynVector.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

   protected long size = 0L;

    // DynLinearVectorBase()
    protected DynLinearVectorBase() {
        super();
    }

    // DynLinearVectorBase(Natural inicapacity)
    protected DynLinearVectorBase(Natural inicapacity) {
        super(inicapacity);
    }

    // DynLinearVectorBase(TraversableContainer<?, Data> con)
    protected DynLinearVectorBase(TraversableContainer<?, Data> con) {
        super(con);
        size = con.Size().ToLong();
    }


  /* ************************************************************************ */
  /* Specific member functions of DynLinearVectorBase                         */
  /* ************************************************************************ */

  // ArrayAlloc
    @Override
    protected void ArrayAlloc(Natural newsize) {
        super.ArrayAlloc(newsize);
        size = 0L;
    }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
    @Override
    public Natural Size() {
        return Natural.Of(size);
    }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
    @Override
    public void Clear() {
        super.Clear();
    }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // Realloc
    @Override
    public void Realloc(Natural newsize) {
        long oldSize = size;
        super.Realloc(newsize);
        size = Math.min(oldSize, newsize.ToLong());
    }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // Expand
    @Override
    public void Expand(Natural num) {
        if (num == null) throw new NullPointerException("Natural cannot be null!");
        long n = num.ToLong();
        if (n == 0) return;
        if (size + n >= Capacity().ToLong()) {
            Grow(num);
        }
        size += n;
    }

  // Reduce
    @Override
    public void Reduce(Natural num) {
        if (num == null) throw new NullPointerException("Natural cannot be null!");
        long n = num.ToLong();
        if (n > size) {
            throw new IllegalArgumentException("Cannot reduce size by " + n + " when size is " + size + "!");
        }
        if (n == 0) return;
        long newSize = size - n;
        for (long i = newSize; i < size; i++) {
            arr[(int) i] = null;
        }
        size = newSize;
        if (THRESHOLD_FACTOR * SHRINK_FACTOR * size <= Capacity().ToLong()) {
            Shrink();
        }
    }

}
