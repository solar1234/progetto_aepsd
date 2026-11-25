package apsd.classes.containers.collections.concretecollections;

 import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.ordered.SortedChain;

/** Object: Concrete set implementation via (dynamic circular) vector. */
public class VSortedChain<Data extends Comparable<? super Data>> extends VChainBase<VSortedChain<Data>, Data> implements SortedChain<VSortedChain<Data>, Data> {
  // Must extend VChainBase.
  // Must implement SortedChain.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // VSortedChain()
  // VSortedChain(TraversableContainer<?, Data> con)

 public VSortedChain() { super(); }

 public VSortedChain(TraversableContainer<?, Data> con) { super(con); }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Empty
  @Override
  public VSortedChain<Data> Empty() { return new VSortedChain<>(); }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // Insert
 @Override
 public boolean Insert(Data dat) {
  if (dat == null) return false;
  Natural pos = SearchSuccessor(dat);
  if (pos == null) {
   vec.InsertLast(dat);
  } else {
   vec.InsertAt(dat, pos);
  }
  return true;
 }

  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

  // InsertIfAbsent
  @Override
  public boolean InsertIfAbsent(Data dat) {
   if (dat == null || Search(dat) != null) return false;
   return Insert(dat);
  }

 // RemoveOccurrences
 @Override
 public void RemoveOccurrences(Data dat) {
  if (dat == null) return;
  Natural pos = Search(dat);
  if (pos == null) return;
  long idx = pos.ToLong();
  while (idx > 0 && vec.GetAt(Natural.Of(idx - 1)).compareTo(dat) == 0) {
   idx--;
  }
  while (idx < vec.Size().ToLong() && vec.GetAt(Natural.Of(idx)).compareTo(dat) == 0) {
   vec.AtNRemove(Natural.Of(idx));
  }
 }

}
