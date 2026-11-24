package apsd.classes.containers.sequences.abstractbases;

 import apsd.classes.utilities.MutableNatural;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.iterators.MutableBackwardIterator;
 import apsd.interfaces.containers.iterators.MutableForwardIterator;
 import apsd.interfaces.containers.sequences.Vector;

/** Object: Abstract vector base implementation. */
abstract public class VectorBase<T extends VectorBase<T,Data>, Data> implements Vector<T,Data>{
  // Must implement Vector.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

   protected Data[] arr = null;

   protected VectorBase(){
       ArrayAlloc(Natural.ZERO);
   }
  // VectorBase(Natural inisize)
    protected VectorBase(Natural inisize) {
      if (inisize == null) throw new NullPointerException("Natural cannot be null!");
      ArrayAlloc(inisize);
    }
  // VectorBase(TraversableContainer<?, Data> con)
    protected VectorBase(TraversableContainer<?, Data> con) {
      if (con == null) throw new NullPointerException("TraversableContainer cannot be null!");
      ArrayAlloc(con.Size());
      final MutableNatural idx = new MutableNatural();
      con.TraverseForward(dat -> {
          arr[(int) idx.GetLongNIncrement()] = dat;
          return false;
      });
    }


  /* ************************************************************************ */
  /* Specific member functions of VectorBase                                  */
  /* ************************************************************************ */

  // ArrayAlloc
    @SuppressWarnings("unchecked")
    protected void ArrayAlloc(Natural newsize){
        if (newsize == null) throw new NullPointerException("Natural cannot be null!");

        long size = newsize.ToLong();
        if (size >= Integer.MAX_VALUE) {
            throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!");
        }
        arr = (Data[]) new Object[(int) size];
    }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
      @Override
      public void Clear() {
          ArrayAlloc(Natural.ZERO);
      }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // Capacity
      @Override
      public Natural Capacity() {
          return (arr == null) ? Natural.ZERO : Natural.Of(arr.length);
      }
  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // VectorFIterator
  protected class VectorFIterator implements MutableForwardIterator<Data> {

      protected final MutableNatural cur = new MutableNatural();

      @Override
      public boolean IsValid() {
          return (cur.ToLong() < Size().ToLong());
      }

      @Override
      public void Reset() {
          cur.Zero();
      }

      @Override
      public Data GetCurrent() {
          if (!IsValid()) throw new IllegalStateException("Iterator is not valid!");
          return GetAt(cur.ToNatural());
      }

      @Override
      public void SetCurrent(Data dat) {
          if (!IsValid()) throw new IllegalStateException("Iterator is not valid!");
          SetAt(dat, cur.ToNatural());
      }

      @Override
      public void Next() {
          if (!IsValid()) throw new IllegalStateException("Iterator is not valid!");
          cur.Increment();
      }

      @Override
      public Data DataNNext() {
          if (!IsValid()) throw new IllegalStateException("Iterator is not valid!");
          return GetAt(cur.GetNIncrement());
      }

  }

  // FIterator
    @Override
    public MutableForwardIterator<Data> FIterator() {
      return new VectorFIterator();
    }

  // VectorBIterator
  protected class VectorBIterator implements MutableBackwardIterator<Data> {

      protected final MutableNatural cur = new MutableNatural(Size());

      @Override
      public boolean IsValid() {
          return (cur.IsNotZero() && cur.ToLong() <= Size().ToLong());
      }

      @Override
      public void Reset() {
          cur.Assign(Size());
      }

      @Override
      public Data GetCurrent() {
          if (!IsValid()) throw new IllegalStateException("Iterator is not valid!");
          return GetAt(Natural.Of(cur.ToLong() - 1));
      }

      @Override
      public void SetCurrent(Data dat) {
          if (!IsValid()) throw new IllegalStateException("Iterator is not valid!");
          SetAt(dat, Natural.Of(cur.ToLong() - 1));
      }

      @Override
      public void Prev() {
          if (!IsValid()) throw new IllegalStateException("Iterator is not valid!");
          cur.Decrement();
      }

      @Override
      public Data DataNPrev() {
          if (!IsValid()) throw new IllegalStateException("Iterator is not valid!");
          return GetAt(Natural.Of(cur.GetLongNDecrement() - 1));
      }

  }

  // BIterator
  @Override
  public MutableBackwardIterator<Data> BIterator() {
      return new VectorBIterator();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // GetAt
  @Override
  abstract public Data GetAt(Natural pos);

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // SetAt
  @Override
  abstract public void SetAt(Data dat, Natural pos);

}
