package apsd.interfaces.containers.collections;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.ClearableContainer;
 import apsd.interfaces.containers.base.InsertableContainer;
 import apsd.interfaces.containers.base.IterableContainer;
 import apsd.interfaces.containers.base.RemovableContainer;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.traits.Predicate;

public interface Collection<T extends Collection<T, Data>, Data> extends ClearableContainer<T>, InsertableContainer<T, Data>, RemovableContainer<T, Data>, IterableContainer<T,Data> {
  // Must extend ClearableContainer, InsertableContainer, RemovableContainer, and IterableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Collection                                  */
  /* ************************************************************************ */

    default boolean InsertIfAbsent(Data dat) {
        return (!Exists(dat)) ? Insert(dat) : false;
    }

    default void RemoveOccurrences(Data dat) {
        Filter(elm -> (dat == null) ? elm != null : !elm.equals(dat));
    }

    default boolean Filter(Predicate<Data> fun) {
        if (fun != null) {
            Natural oldsize = Size();
            while (TraverseForward(
                    dat -> {
                        if (!fun.Apply(dat)) {
                            Remove(dat);
                            return true;
                        }
                        return false;
                    }
            )) {}
            return !Size().equals(oldsize);
        }
        return false;
    }

    default void Union(Collection<?, Data> col) {
        AllInserted(col);
    }

    default void Difference(Collection<?, Data> col) {
        AllRemoved(col);
    }

    default void Intersection(Collection<?, Data> col) {
        if (col != null) {
            Filter(dat -> Multiplicity(dat).compareTo(col.Multiplicity(dat)) <= 0);
        }
    }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
  @Override
  default void Clear() {
      Filter(dat -> false);
  }

}
