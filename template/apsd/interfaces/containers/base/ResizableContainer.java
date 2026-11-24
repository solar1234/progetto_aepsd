package apsd.interfaces.containers.base;

// import apsd.classes.utilities.Natural;

import apsd.classes.utilities.Natural;

/** Interface: ReallocableContainer che è espandibile e riducibile. */
public interface ResizableContainer<T extends ResizableContainer<T>> extends ReallocableContainer<T>{
  // T must have a recursive bound.
  // Must extend ReallocableContainer.

  /* ************************************************************************ */
  /* Specific member functions of ResizableContainer                          */
  /* ************************************************************************ */

  double THRESHOLD_FACTOR = 2.0; // Must be strictly greater than 1.

  // Expand
  void Expand(Natural num);

  default void Expand() {
    Expand(Natural.ONE);
  }

  // Reduce
  void Reduce(Natural num);

  default void Reduce() {
    Reduce(Natural.ONE);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
  @Override
  Natural Size();

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // Grow
  @Override
  default void Grow(Natural dim) {
    if (dim == null) {
      throw new NullPointerException("Natural cannot be null!");
    }
    if (Capacity().ToLong() == Long.MAX_VALUE) {
      throw new ArithmeticException("Overflow: capacity cannot exceed Long.MAX_VALUE!");
    }
    long neededSize = Size().ToLong() + dim.ToLong();
    if (neededSize >= Capacity().ToLong()) {
      ReallocableContainer.super.Grow(dim);
    }
  }

  // Shrink
  @Override
  default void Shrink() {
    long minThreshold = (long) (THRESHOLD_FACTOR * SHRINK_FACTOR * Size().ToLong());
    if (minThreshold <= Capacity().ToLong()) {
      ReallocableContainer.super.Shrink();
    }
  }
}
