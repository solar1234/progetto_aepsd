package apsd.classes.containers.collections.concretecollections;

 import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
 import apsd.classes.containers.collections.concretecollections.bases.LLNode;
 import apsd.classes.utilities.Box;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.List;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.containers.iterators.MutableBackwardIterator;
 import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Concrete list implementation on linked-list. */
public class LLList<Data> extends LLChainBase<LLList<Data>, Data> implements List<LLList<Data>, Data> {
  // Must extend LLChainBase.
  // Must implement List.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // LLList()
  // LLList(TraversableContainer<?, Data> con)

    public LLList() {}

    public LLList(TraversableContainer<?, Data> con) { super(con); }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Empty

    @Override
    public LLList<Data> Empty() { return new LLList<>(); }

  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */

  // FIterator
  // BIterator

    @Override
    public MutableForwardIterator<Data> FIterator() { return new ListFIterator(); }

    @Override
    public MutableBackwardIterator<Data> BIterator() { return new ListBIterator(); }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // SetAt

    @Override
    public void SetAt(Data dat, Natural pos) {
        if (dat == null) return;
        List.super.SetAt(dat, pos);
    }

    // SetFirst
    @Override
    public void SetFirst(Data dat) {
        if (dat == null) return;
        if (headref.IsNull()) throw new IndexOutOfBoundsException("First element does not exist!");
        headref.Get().Set(dat);
    }

    // SetLast
    @Override
    public void SetLast(Data dat) {
        if (dat == null) return;
        if (tailref.IsNull()) throw new IndexOutOfBoundsException("Last element does not exist!");
        tailref.Get().Set(dat);
    }


  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  // InsertAt
  // InsertFirst
  // InsertLast

    @Override
    public void InsertAt(Data dat, Natural pos) {
        if (dat == null) return;
        if (pos == null) throw new NullPointerException("Natural number cannot be null!");
        long idx = pos.ToLong();
        if (idx > Size().ToLong()) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size() + "!");
        if (idx == Size().ToLong()) {
            InsertLast(dat);
        } else {
            ForwardIterator<Box<LLNode<Data>>> itr = FRefIterator();
            itr.Next(idx);
            Box<LLNode<Data>> cur = itr.GetCurrent();
            cur.Set(new LLNode<>(dat, cur.Get()));
            size.Increment();
        }
    }

    @Override
    public void InsertFirst(Data dat) {
        if (dat == null) return;
        LLNode<Data> newnode = new LLNode<>(dat, headref.Get());
        headref.Set(newnode);
        if (tailref.IsNull()) { tailref.Set(newnode); }
        size.Increment();
    }

    @Override
    public void InsertLast(Data dat) {
        if (dat == null) return;
        LLNode<Data> newnode = new LLNode<>(dat, null);
        if (tailref.IsNull()) {
            headref.Set(newnode);
        } else {
            tailref.Get().SetNext(newnode);
        }
        tailref.Set(newnode);
        size.Increment();
    }

}
