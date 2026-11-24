package apsd.interfaces.containers.deqs;

 import apsd.interfaces.containers.base.ClearableContainer;
 import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<T extends Stack<T, Data>, Data> extends ClearableContainer<T>, InsertableContainer<T,Data>{
  // Must extend ClearableContainer and InsertableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Stack                                       */
  /* ************************************************************************ */

  // Top
    Data Top();

    // Pop
    void Pop();

    // TopNPop
    default Data TopNPop() {
        Data top = Top();
        Pop();
        return top;
    }

    // SwapTop
    default void SwapTop(Data dat) {
        Pop();
        Push(dat);
    }

    // TopNSwap
    default Data TopNSwap(Data dat) {
        Data top = Top();
        SwapTop(dat);
        return top;
    }

    // Push
    void Push(Data dat);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
  @Override
  default void Clear() {
      while (!IsEmpty()) {
          Pop();
      }
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // Insert
  @Override
  default boolean Insert(Data dat) {
      Push(dat);
      return true;
  }

}
