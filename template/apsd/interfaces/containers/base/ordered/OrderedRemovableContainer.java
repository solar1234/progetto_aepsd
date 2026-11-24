package apsd.interfaces.containers.base.ordered;

 import apsd.interfaces.containers.base.RemovableContainer;

/** Interface: OrderedContainer & RemovableContainer. */
public interface OrderedRemovableContainer<T extends OrderedRemovableContainer<T, Data>, Data extends Comparable<? super Data>> extends OrderedContainer<T, Data>, RemovableContainer<T, Data> {
  // Must extend OrderedContainer and RemovableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Specific member functions of OrderedRemovableContainer                   */
  /* ************************************************************************ */

  // RemoveMin
  default void RemoveMin() {
   Remove(Min());
  }

 // MinNRemove
 default Data MinNRemove() {
  Data min = Min();
  Remove(min);
  return min;
 }

 // RemoveMax
 default void RemoveMax() {
  Remove(Max());
 }

 // MaxNRemove
 default Data MaxNRemove() {
  Data max = Max();
  Remove(max);
  return max;
 }

 // RemovePredecessor
 default void RemovePredecessor(Data dat) {
  Remove(Predecessor(dat));
 }

 // PredecessorNRemove
 default Data PredecessorNRemove(Data dat) {
  Data prd = Predecessor(dat);
  Remove(prd);
  return prd;
 }

 // RemoveSuccessor
 default void RemoveSuccessor(Data dat) {
  Remove(Successor(dat));
 }

 // SuccessorNRemove
 default Data SuccessorNRemove(Data dat) {
  Data suc = Successor(dat);
  Remove(suc);
  return suc;
 }


}
