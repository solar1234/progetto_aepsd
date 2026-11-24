package apsd.interfaces.containers.base.ordered;

// import apsd.interfaces.containers.base.MembershipContainer;

import apsd.interfaces.containers.base.MembershipContainer;

/** Interface: MembershipContainer con dati ordinati. */
public interface OrderedContainer<T extends OrderedContainer<T, Data>, Data extends Comparable<? super Data>> extends MembershipContainer<T, Data> {
  // Must extend MembershipContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Specific member functions of OrderedContainer                            */
  /* ************************************************************************ */

  // Min
    Data Min();
  // Max
    Data Max();
  // Predecessor
    Data Predecessor(Data dat);
  // Successor
    Data Successor(Data dat);

}
