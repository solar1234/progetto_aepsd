package apsd.classes.containers.collections.concretecollections.bases;

 import apsd.classes.containers.sequences.Vector;
 import apsd.classes.utilities.Box;
 import apsd.classes.utilities.MutableNatural;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.Chain;
 import apsd.interfaces.containers.iterators.BackwardIterator;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.containers.iterators.MutableBackwardIterator;
 import apsd.interfaces.containers.iterators.MutableForwardIterator;
 import apsd.interfaces.traits.Predicate;

/** Object: Abstract chain base implementation on linked-list. */
abstract public class LLChainBase<T extends LLChainBase<T,Data>, Data> implements Chain<T,Data>{
  // Must implement Chain.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

   protected final MutableNatural size = new MutableNatural();
   protected final Box<LLNode<Data>> headref = new Box<>();
   protected final Box<LLNode<Data>> tailref = new Box<>();

  // LLChainBase()
   public LLChainBase() {};

  // LLChainBase(TraversableContainer<?, Data> con)
  public LLChainBase(TraversableContainer<?, Data> con) {
      if (con != null) {
          AllInserted(con);
      }
  }

  /* ************************************************************************ */
  /* Specific member functions of LLChainBase                                 */
  /* ************************************************************************ */


    // ListFRefIterator
    protected class ListFRefIterator implements ForwardIterator<Box<LLNode<Data>>> {

        protected Box<LLNode<Data>> cur = headref;

        public ListFRefIterator() {}

        public ListFRefIterator(Box<LLNode<Data>> cur) { this.cur = cur; }

        @Override
        public boolean IsValid() { return !cur.IsNull(); }

        @Override
        public void Reset() { cur = headref; }

        @Override
        public Box<LLNode<Data>> GetCurrent() {
            if (!IsValid()) throw new IllegalStateException("Iterator terminated!");
            return cur;
        }

        @Override
        public void Next() {
            if (!IsValid()) throw new IllegalStateException("Iterator terminated!");
            cur = cur.Get().GetNext();
        }

        @Override
        public Box<LLNode<Data>> DataNNext() {
            if (!IsValid()) throw new IllegalStateException("Iterator terminated!");
            Box<LLNode<Data>> oldcur = cur;
            cur = cur.Get().GetNext();
            return oldcur;
        }

    }

    // FRefIterator
    protected ForwardIterator<Box<LLNode<Data>>> FRefIterator() {
        return new ListFRefIterator();
    }

    // ListBRefIterator
    protected class ListBRefIterator implements BackwardIterator<Box<LLNode<Data>>> {

        protected long cur = -1L;
        protected Vector<Box<LLNode<Data>>> arr = null;

        public ListBRefIterator() { Reset(); }

        public ListBRefIterator(ListBRefIterator itr) {
            cur = itr.cur;
            arr = (itr.arr == null) ? null : new Vector<>(itr.arr);
        }

        @Override
        public boolean IsValid() { return (cur >= 0 && cur < Size().ToLong()); }

        @Override
        public void Reset() {
            cur = -1L;
            if (Size().IsZero()) {
                arr = null;
            } else {
                arr = new Vector<>(Size());
                long idx = 0;
                for (Box<LLNode<Data>> ref = headref; !ref.IsNull(); ref = ref.Get().GetNext()) {
                    arr.SetAt(ref, Natural.Of(idx++));
                }
                cur = Size().ToLong() - 1;
            }
        }

        @Override
        public Box<LLNode<Data>> GetCurrent() {
            if (!IsValid()) throw new IllegalStateException("Iterator terminated!");
            return arr.GetAt(Natural.Of(cur));
        }

        @Override
        public void Prev() {
            if (!IsValid()) throw new IllegalStateException("Iterator terminated!");
            cur--;
        }

        @Override
        public Box<LLNode<Data>> DataNPrev() {
            if (!IsValid()) throw new IllegalStateException("Iterator terminated!");
            return arr.GetAt(Natural.Of(cur--));
        }

    }

    // BRefIterator
    protected BackwardIterator<Box<LLNode<Data>>> BRefIterator() {
        return new ListBRefIterator();
    }


  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
    @Override
    public Natural Size() { return size.ToNatural(); }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
  @Override
  public void Clear() {
      size.Zero();
      headref.Set(null);
      tailref.Set(null);
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // Remove
  @Override
  public boolean Remove(Data dat) {
      if (dat == null) return false;
      final Box<LLNode<Data>> prd = new Box<>();
      return FRefIterator().ForEachForward(cur -> {
          LLNode<Data> node = cur.Get();
          if (node.Get().equals(dat)) {
              cur.Set(node.GetNext().Get());
              if (tailref.Get() == node) { tailref.Set(prd.Get()); }
              size.Decrement();
              return true;
          }
          prd.Set(node);
          return false;
      });
  }


  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ListFIterator
  protected class ListFIterator implements MutableForwardIterator<Data> {

      protected final ForwardIterator<Box<LLNode<Data>>> itr;

      public ListFIterator() { itr = FRefIterator(); }

      public ListFIterator(ListFIterator itr) { this.itr = itr.itr; }

      @Override
      public boolean IsValid() { return itr.IsValid(); }

      @Override
      public void Reset() { itr.Reset(); }

      @Override
      public Data GetCurrent() { return itr.GetCurrent().Get().Get(); }

      @Override
      public void SetCurrent(Data dat) {
          if (dat == null) return;
          itr.GetCurrent().Get().Set(dat);
      }

      @Override
      public void Next() { itr.Next(); }

      @Override
      public Data DataNNext() { return itr.DataNNext().Get().Get(); }

  }

  // FIterator
  @Override
  public ForwardIterator<Data> FIterator() { return new ListFIterator(); }

  // ListBIterator
  protected class ListBIterator implements MutableBackwardIterator<Data> {

      protected final BackwardIterator<Box<LLNode<Data>>> itr;

      public ListBIterator() { itr = BRefIterator(); }

      public ListBIterator(ListBIterator itr) { this.itr = itr.itr; }

      @Override
      public boolean IsValid() { return itr.IsValid(); }

      @Override
      public void Reset() { itr.Reset(); }

      @Override
      public Data GetCurrent() { return itr.GetCurrent().Get().Get(); }

      @Override
      public void SetCurrent(Data dat) {
          if (dat == null) return;
          itr.GetCurrent().Get().Set(dat);
      }

      @Override
      public void Prev() { itr.Prev(); }

      @Override
      public Data DataNPrev() { return itr.DataNPrev().Get().Get(); }

  }

  // BIterator
  @Override
  public BackwardIterator<Data> BIterator() { return new ListBIterator(); }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // GetFirst

    @Override
    public Data GetFirst() {
        if (headref.IsNull()) throw new IndexOutOfBoundsException("First element does not exist!");
        return headref.Get().Get();
    }

    // GetLast
    @Override
    public Data GetLast() {
        if (tailref.IsNull()) throw new IndexOutOfBoundsException("Last element does not exist!");
        return tailref.Get().Get();
    }

    // SubSequence
    @Override
    public T SubSequence(Natural from, Natural to) {
        if (from == null || to == null) { throw new NullPointerException("Natural cannot be null!"); }
        long f = from.ToLong(), t = to.ToLong();
        T chn = Empty();
        if (f > t || t >= size.ToLong()) return chn;
        final Box<Long> idx = new Box<>(0L);
        TraverseBackward(dat -> {
            long curIdx = size.ToLong() - 1 - idx.Get();
            if (curIdx < f) {
                return true;
            } else if (curIdx <= t) {
                chn.Insert(dat);
            }
            idx.Set(idx.Get() + 1);
            return false;
        });
        return chn;
    }


  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // AtNRemove
  @Override
  public Data AtNRemove(Natural pos) {
      long idx = ExcIfOutOfBound(pos);
      if (idx == 0) return FirstNRemove();
      if (idx == size.ToLong() - 1) return LastNRemove();
      ForwardIterator<Box<LLNode<Data>>> itr = FRefIterator();
      itr.Next(idx - 1);
      LLNode<Data> prd = itr.GetCurrent().Get();
      Box<LLNode<Data>> cur = prd.GetNext();
      LLNode<Data> node = cur.Get();
      cur.Set(node.GetNext().Get());
      size.Decrement();
      return node.Get();
  }

  // FirstNRemove
  @Override
  public Data FirstNRemove() {
      if (headref.IsNull()) throw new IndexOutOfBoundsException("First element does not exist!");
      LLNode<Data> node = headref.Get();
      headref.Set(node.GetNext().Get());
      if (tailref.Get() == node) { tailref.Set(null); }
      size.Decrement();
      return node.Get();
  }

  // LastNRemove
  @Override
  public Data LastNRemove() {
      if (tailref.IsNull()) throw new IndexOutOfBoundsException("Last element does not exist!");
      if (size.ToLong() == 1) return FirstNRemove();
      BackwardIterator<Box<LLNode<Data>>> itr = BRefIterator();
      itr.Prev();
      LLNode<Data> prd = itr.GetCurrent().Get();
      Data dat = tailref.Get().Get();
      prd.SetNext(null);
      tailref.Set(prd);
      size.Decrement();
      return dat;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  // Filter
  @Override
  public boolean Filter(Predicate<Data> fun) {
      MutableNatural oldsize = new MutableNatural(size);
      if (fun != null) {
          ForwardIterator<Box<LLNode<Data>>> itr = FRefIterator();
          LLNode<Data> prd = null;
          while (itr.IsValid()) {
              Box<LLNode<Data>> cur = itr.GetCurrent();
              LLNode<Data> node = cur.Get();
              if (fun.Apply(node.Get())) {
                  prd = node;
                  itr.Next();
              } else {
                  cur.Set(node.GetNext().Get());
                  if (tailref.Get() == node) { tailref.Set(prd); }
                  size.Decrement();
              }
          }
      }
      return !size.equals(oldsize);
  }

}

