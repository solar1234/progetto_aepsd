package apsd.interfaces.containers.base.ordered;

 import apsd.interfaces.containers.base.TraversableContainer;

/** Interface: OrderedContainer & TraversableContainer. */
public interface OrderedTraversableContainer<T extends OrderedTraversableContainer<T, Data>, Data extends Comparable<? super Data>> extends OrderedContainer<T, Data>, TraversableContainer<T, Data> {
  // Must extend OrderedContainer and TraversableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Specific member functions of OrderedTraversableContainer                 */
  /* ************************************************************************ */

  // Min
  @Override
  default Data Min() {
   return FoldForward((elm, acc) -> {
    if (elm != null && (acc == null || elm.compareTo(acc) < 0)) {
     return elm;
    }
    return acc;
   }, null);
  }

  // Max
  @Override
  default Data Max() {
   return FoldForward((elm, acc) -> {
    if (elm != null && (acc == null || elm.compareTo(acc) > 0)) {
     return elm;
    }
    return acc;
   }, null);
  }

  // Predecessor
  @Override
  default Data Predecessor(Data dat) {
   if (dat == null) return null;

   return FoldForward((elm, acc) -> {
    if (elm != null && elm.compareTo(dat) < 0) {
     if (acc == null || elm.compareTo(acc) > 0) {
      return elm;
     }
    }
    return acc;
   }, null);
  }

  // Successor
  @Override
  default Data Successor(Data dat) {
   if (dat == null) return null;

   return FoldForward((elm, acc) -> {
    if (elm != null && elm.compareTo(dat) > 0) {
     if (acc == null || elm.compareTo(acc) < 0) {
      return elm;
     }
    }
    return acc;
   }, null);
  }

}
