package apsd.interfaces.containers.base;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<T extends InsertableContainer<T, Data>, Data> extends Container<T>{
  // Must extend Container.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of InsertableContainer                         */
  /* ************************************************************************ */

  // Insert
    boolean Insert(Data dat);

  // AllInserted
    default boolean AllInserted(TraversableContainer<?, Data> con){
        if(con==null) return false;

        return con.FoldForward((dat, acc)->{
            boolean inserted = Insert(dat);
            return acc && inserted;
        }, true);
    };

  // SomeInserted
    default boolean SomeInserted(TraversableContainer<?, Data> con){
        if (con == null) return false;

        return con.FoldForward((dat, acc) -> {
            boolean inserted = Insert(dat);
            return acc || inserted;
        }, false);
    };
}
