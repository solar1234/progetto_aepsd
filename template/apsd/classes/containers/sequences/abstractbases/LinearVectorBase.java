package apsd.classes.containers.sequences.abstractbases;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) linear vector base implementation. */
abstract public class LinearVectorBase<T extends LinearVectorBase<T, Data>, Data> extends VectorBase<T, Data> {
    // Must extend VectorBase.
    // T must have a recursive bound.

    /* ************************************************************************ */
    /* Fields and constructors                                                  */
    /* ************************************************************************ */

    // LinearVectorBase()
    protected LinearVectorBase() {
        super();
    }

    // LinearVectorBase(Natural inisize)
    protected LinearVectorBase(Natural inisize) {
        super(inisize);
    }

    // LinearVectorBase(TraversableContainer<?, Data> con)
    protected LinearVectorBase(TraversableContainer<?, Data> con) {
        super(con);
    }


    /* ************************************************************************ */
    /* Override specific member functions from ReallocableContainer             */
    /* ************************************************************************ */

    // Realloc
    @Override
    public void Realloc(Natural newsize) {
        if (newsize == null) throw new NullPointerException("Natural cannot be null!");
        Data[] oldArr = arr;
        ArrayAlloc(newsize);
        if (oldArr != null) {
            long limit = Math.min(oldArr.length, arr.length);
            for (int i = 0; i < limit; i++) {
                arr[i] = oldArr[i];
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
        return arr[(int) idx];
    }

    /* ************************************************************************ */
    /* Override specific member functions from MutableSequence                  */
    /* ************************************************************************ */

    // SetAt
    @Override
    public void SetAt(Data dat, Natural pos) {
        long idx = ExcIfOutOfBound(pos);
        arr[(int) idx] = dat;

    }
}
