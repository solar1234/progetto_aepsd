package apsd.classes.containers.sequences;

 import apsd.classes.containers.sequences.abstractbases.LinearVectorBase;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete (static linear) vector implementation. */
public class Vector<Data> extends LinearVectorBase<Vector<Data>, Data> {
  // Must extend LinearVectorBase.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

    // Vector()
    public Vector() {
        super();
    }

    // Vector(Natural inisize)
    public Vector(Natural inisize) {
        super(inisize);
    }

    // Vector(TraversableContainer<?, Data> con)
    public Vector(TraversableContainer<?, Data> con) {
        super(con);
    }


  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

    // Empty
      @Override
      public Vector<Data> Empty() {
          return new Vector<>();
      }

}
