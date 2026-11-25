package apsd.classes.containers.collections.abstractcollections;

 import apsd.classes.containers.collections.abstractcollections.bases.WSetBase;
 import apsd.classes.containers.collections.concretecollections.VList;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.Chain;

/** Object: Wrapper set implementation via chain. */
public class WSet<Data> extends WSetBase<WSet<Data>, Data, Chain<?, Data>> {
  // Must extend WSetBase.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // WSet()
  // WSet(TraversableContainer<?, Data> con)
  // WSet(Chain<?, Data> chn, TraversableContainer<?, Data> con)

    public WSet() { super(); }

    public WSet(TraversableContainer<?, Data> con) { super(con); }

    public WSet(Chain<?, Data> chn, TraversableContainer<?, Data> con) { super(chn, con); }

  /* ************************************************************************ */
  /* Specific member functions of WSet                                        */
  /* ************************************************************************ */

  // ChainAlloc
  @Override
  protected void ChainAlloc() { chn = new VList<>(); }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Empty
  @Override
  public WSet<Data> Empty() { return new WSet<>(); }

}
