package apsd.classes.containers.collections.concretecollections;

 import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.List;
 import apsd.interfaces.containers.iterators.MutableBackwardIterator;
 import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Concrete list implementation on (dynamic circular) vector. */
public class VList<Data> extends VChainBase<VList<Data>, Data> implements List<VList<Data>, Data> {
  // Must extend VChainBase.
  // Must implement List.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // VList()
  public VList() { super(); }

  // VList(TraversableContainer<?, Data> con)
  public VList(TraversableContainer<?, Data> con) { super(con); }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Empty
    @Override
    public VList<Data> Empty() { return new VList<>(); }

  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */

  // FIterator
  // BIterator

    @Override
    public MutableForwardIterator<Data> FIterator() { return vec.FIterator(); }

    @Override
    public MutableBackwardIterator<Data> BIterator() { return vec.BIterator(); }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // SetAt

    @Override
    public void SetAt(Data dat, Natural pos) {
        if (dat == null) return;
        vec.SetAt(dat, pos);
    }


  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  // InsertAt

    @Override
    public void InsertAt(Data dat, Natural pos) {
        if (dat == null) return;
        vec.InsertAt(dat, pos);
    }
}
