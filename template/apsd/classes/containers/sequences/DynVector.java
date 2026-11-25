package apsd.classes.containers.sequences;

 import apsd.classes.containers.sequences.abstractbases.DynLinearVectorBase;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete dynamic (linear) vector implementation. */
public class DynVector<Data> extends DynLinearVectorBase<DynVector<Data>, Data> {
  // Must extend DynLinearVectorBase.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

    // DynVector()
    public DynVector() {
        super();
    }

    // DynVector(Natural inisize)
    public DynVector(Natural inisize) {
        super(inisize);
    }

    // DynVector(TraversableContainer<?, Data> con)
    public DynVector(TraversableContainer<?, Data> con) {
        super(con);
    }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

    // Empty
    @Override
    public DynVector<Data> Empty() {
        return new DynVector<>();
    }

}
