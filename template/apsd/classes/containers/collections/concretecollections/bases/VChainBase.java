package apsd.classes.containers.collections.concretecollections.bases;

 import apsd.classes.containers.sequences.DynCircularVector;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.Chain;
 import apsd.interfaces.containers.iterators.BackwardIterator;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.containers.iterators.MutableForwardIterator;
 import apsd.interfaces.containers.sequences.DynVector;
 import apsd.interfaces.traits.Predicate;

/** Object: Abstract list base implementation on (dynamic circular) vector. */
abstract public class VChainBase<T extends VChainBase<T,Data>, Data> implements Chain<T,Data>{
  // Must implement Chain.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

   protected DynVector<?, Data> vec = new DynCircularVector<>();

  // VChainBase()
  // VChainBase(TraversableContainer<?, Data> con)

    public VChainBase() {}

    public VChainBase(TraversableContainer<?, Data> con) {
        if (con != null) {
            con.TraverseForward(dat -> { Insert(dat); return false; });
        }
    }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
    @Override
    public Natural Size() { return vec.Size(); }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
    @Override
    public void Clear() { vec.Clear(); }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // FIterator
  // BIterator

    @Override
    public ForwardIterator<Data> FIterator() { return vec.FIterator(); }

    @Override
    public BackwardIterator<Data> BIterator() { return vec.BIterator(); }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // GetAt
  // SubSequence

    @Override
    public Data GetAt(Natural pos) { return vec.GetAt(pos); }

    @Override
    public T SubSequence(Natural from, Natural to) {
        if (from == null || to == null) { throw new NullPointerException("Natural cannot be null!"); }
        long f = from.ToLong(), t = to.ToLong();
        T chn = Empty();
        if (f > t || t >= vec.Size().ToLong()) return chn;
        for (long idx = t; idx >= f; idx--) {
            chn.Insert(vec.GetAt(Natural.Of(idx)));
        }
        return chn;
    }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // AtNRemove
    @Override
    public Data AtNRemove(Natural pos) { return vec.AtNRemove(pos); }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  // Filter
    @Override
    public boolean Filter(Predicate<Data> fun) {
        if (fun == null) return false;
        Natural oldsize = vec.Size();
        long idx = 0;
        while (idx < vec.Size().ToLong()) {
            if (fun.Apply(vec.GetAt(Natural.Of(idx)))) {
                idx++;
            } else {
                vec.AtNRemove(Natural.Of(idx));
            }
        }
        return !vec.Size().equals(oldsize);
    }

}
