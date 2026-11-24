package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore in avanti. */
public interface ForwardIterator<Data> extends Iterator<Data>{
  // Must extend Iterator.

  /* ************************************************************************ */
  /* Specific member functions of ForwardIterator                             */
  /* ************************************************************************ */

  // Next
    default void Next(){DataNNExt();};

    default void Next(long stp){
        while (stp>0){
            Next();
            stp--;
        }
    };

    default void Next(Natural stp){
        if(stp==null){
            throw new NullPointerException("Natural can't be null");
        };

        Next(stp.ToLong());
    };

  // DataNNext
    Data DataNNExt();

  // ForEachForward
    default boolean ForEachForward(Predicate<Data> fun){
        if(fun!=null){
            while (IsValid()){
                if (fun.Apply(DataNNExt())) return true;
            }
        }
        return false;
    };

}
