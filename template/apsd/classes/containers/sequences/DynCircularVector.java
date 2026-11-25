package apsd.classes.containers.sequences;

 import apsd.classes.containers.sequences.abstractbases.DynCircularVectorBase;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete dynamic circular vector implementation. */
public class DynCircularVector<Data> extends DynCircularVectorBase<DynCircularVector<Data>, Data> {
  // Must extend DynCircularVectorBase.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

    // DynCircularVector()
    public DynCircularVector() {
        super();
    }

    // DynCircularVector(Natural inisize)
    public DynCircularVector(Natural inisize) {
        super(inisize);
    }

    // DynCircularVector(TraversableContainer<?, Data> con)
    public DynCircularVector(TraversableContainer<?, Data> con) {
        super(con);
    }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

    // Empty
    @Override
    public DynCircularVector<Data> Empty() {
        return new DynCircularVector<>();
    }
}
