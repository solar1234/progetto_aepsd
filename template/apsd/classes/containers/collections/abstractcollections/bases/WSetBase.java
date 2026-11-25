package apsd.classes.containers.collections.abstractcollections.bases;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.Chain;
 import apsd.interfaces.containers.collections.Collection;
 import apsd.interfaces.containers.collections.Set;
 import apsd.interfaces.containers.iterators.BackwardIterator;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.traits.Predicate;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WSetBase<T extends WSetBase<T, Data, Chn>, Data, Chn extends Chain<?, Data>> implements Set<T, Data> {

  // Must implement Set.
  // T must have a recursive bound.
  // Chn must extend Chain.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

   protected Chn chn;

    // WSetBase()
    public WSetBase() { ChainAlloc(); }

    // WSetBase(TraversableContainer<?, Data> con)
    public WSetBase(TraversableContainer<?, Data> con) {
        ChainAlloc();
        if (con != null) {
            con.TraverseForward(dat -> { chn.InsertIfAbsent(dat); return false; });
        }
    }

    // WSetBase(Chn chn, TraversableContainer<?, Data> con)
    public WSetBase(Chn chn, TraversableContainer<?, Data> con) {
        this.chn = chn;
        chn.Clear();
        if (con != null) {
            con.TraverseForward(dat -> { chn.InsertIfAbsent(dat); return false; });
        }
    }
  /* ************************************************************************ */
  /* Specific member functions of WSetBase                                    */
  /* ************************************************************************ */

  // ChainAlloc
  abstract protected void ChainAlloc();

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
  @Override
  public Natural Size() { return chn.Size(); }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
  @Override
  public void Clear() { chn.Clear(); }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // Insert
  @Override
  public boolean Insert(Data dat) { return chn.InsertIfAbsent(dat); }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // Remove
  @Override
  public boolean Remove(Data dat) { return chn.Remove(dat); }


  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // FIterator
    @Override
    public ForwardIterator<Data> FIterator() { return chn.FIterator(); }

  // BIterator
    @Override
    public BackwardIterator<Data> BIterator() { return chn.BIterator(); }


  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

    // Filter
    @Override
    public boolean Filter(Predicate<Data> fun) { return chn.Filter(fun); }

    // Intersection
    @Override
    public void Intersection(Collection<?, Data> col) { chn.Intersection(col); }


}
