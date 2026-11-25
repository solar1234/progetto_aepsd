package apsd.classes.containers.sequences;

 import apsd.classes.containers.sequences.abstractbases.CircularVectorBase;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete (static) circular vector implementation. */
public class CircularVector<Data> extends CircularVectorBase<CircularVector<Data>, Data> {
    // Must extend CircularVectorBase.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

    // CircularVector()
    public CircularVector() {
        super();
    }

    // CircularVector(Natural inisize)
    public CircularVector(Natural inisize) {
        super(inisize);
    }

    // CircularVector(TraversableContainer<?, Data> con)
    public CircularVector(TraversableContainer<?, Data> con) {
        super(con);
    }
  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

    // Empty
    @Override
    public CircularVector<Data> Empty() {
        return new CircularVector<>();
    }

}
