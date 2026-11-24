package apsd.interfaces.containers.base;

/** Interface: Container con supporto alla rimozione di un dato. */
public interface RemovableContainer<T extends RemovableContainer<T, Data>, Data> extends Container<T>{
  // Must extend Container.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of RemovableContainer                          */
  /* ************************************************************************ */

    // Remove
    boolean Remove(Data dat);

    // AllRemoved
    default boolean AllRemoved(TraversableContainer<?, Data> con) {
        if (con == null) return false;

        return con.FoldForward((dat, acc) -> {
            boolean removed = Remove(dat);
            return acc && removed;
        }, true);
    }

    // SomeRemoved
    default boolean SomeRemoved(TraversableContainer<?, Data> con) {
        if (con == null) return false;

        return con.FoldForward((dat, acc) -> {
            boolean removed = Remove(dat);
            return acc || removed;
        }, false);
    }

}
