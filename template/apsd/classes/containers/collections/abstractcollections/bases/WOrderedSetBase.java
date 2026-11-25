package apsd.classes.containers.collections.abstractcollections.bases;

 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.ordered.OrderedSet;
 import apsd.interfaces.containers.collections.ordered.SortedChain;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WOrderedSetBase<T extends WOrderedSetBase<T, Data, Chn>, Data extends Comparable<? super Data>, Chn extends SortedChain<?, Data>> extends WSetBase<T, Data, Chn> implements OrderedSet<T, Data> {
  // Must extend WSetBase.
  // Must implement OrderedSet.
  // T must have a recursive bound.
  // Data must extend Comparable.
  // Chn must extend SortedChain.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // WOrderedSetBase()
  // WOrderedSetBase(TraversableContainer<?, Data> con)
  // WOrderedSetBase(Chn chn, TraversableContainer<?, Data> con)

    public WOrderedSetBase() { super(); }

    public WOrderedSetBase(TraversableContainer<?, Data> con) { super(con); }

    public WOrderedSetBase(Chn chn, TraversableContainer<?, Data> con) { super(chn, con); }

  /* ************************************************************************ */
  /* Specific member functions of WOrderedSetBase                             */
  /* ************************************************************************ */

  // IsEqual
  public boolean IsEqual(WOrderedSetBase<?, Data, ?> con) {
      if (con == null) return false;
      return chn.IsEqual(con);
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

    // Min
    @Override
    public Data Min() { return chn.Min(); }

    // RemoveMin
    @Override
    public void RemoveMin() { chn.RemoveMin(); }

    // MinNRemove
    @Override
    public Data MinNRemove() { return chn.MinNRemove(); }

    // Max
    @Override
    public Data Max() { return chn.Max(); }

    // RemoveMax
    @Override
    public void RemoveMax() { chn.RemoveMax(); }

    // MaxNRemove
    @Override
    public Data MaxNRemove() { return chn.MaxNRemove(); }

    // Predecessor
    @Override
    public Data Predecessor(Data dat) { return chn.Predecessor(dat); }

    // RemovePredecessor
    @Override
    public void RemovePredecessor(Data dat) { chn.RemovePredecessor(dat); }

    // PredecessorNRemove
    @Override
    public Data PredecessorNRemove(Data dat) { return chn.PredecessorNRemove(dat); }

    // Successor
    @Override
    public Data Successor(Data dat) { return chn.Successor(dat); }

    // RemoveSuccessor
    @Override
    public void RemoveSuccessor(Data dat) { chn.RemoveSuccessor(dat); }

    // SuccessorNRemove
    @Override
    public Data SuccessorNRemove(Data dat) { return chn.SuccessorNRemove(dat); }

}
