package apsd.interfaces.containers.base;

 import apsd.classes.utilities.Box;
 import apsd.classes.utilities.MutableNatural;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.traits.Accumulator;
 import apsd.interfaces.traits.Predicate;

/** Interface: MembershipContainer con supporto all'attraversamento. */
public interface TraversableContainer<T extends TraversableContainer<T, Data>, Data> extends MembershipContainer<T, Data>{
  // Must extend MembershipContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of TraversableContainer                        */
  /* ************************************************************************ */

  // TraverseForward
    boolean TraverseForward(Predicate<Data> fun);
  // TraverseBackward
    boolean TraverseBackward(Predicate<Data> fun);
  // FoldForward
    default <Acc> Acc FoldForward(Accumulator<Data, Acc> fun, Acc ini) {
      final Box<Acc> acc = new Box<>(ini);
      if (fun != null) {
          TraverseForward(dat -> {
              acc.Set(fun.Apply(dat, acc.Get()));
              return false;
          });
      }
      return acc.Get();
    }
  // FoldBackward
    default <Acc> Acc FoldBackward(Accumulator<Data, Acc> fun, Acc ini) {
      final Box<Acc> acc = new Box<>(ini);
      if (fun != null) {
          TraverseBackward(dat -> {
              acc.Set(fun.Apply(dat, acc.Get()));
              return false;
          });
      }
      return acc.Get();
    }
  // Multiplicity
    default Natural Multiplicity(Data dat){
        return FoldForward(
                (cur, acc) -> ((cur == dat) || (cur != null && dat != null && cur.equals(dat))) ? acc.Increment() : acc,
                Natural.ZERO
        );
    };
  // IsEqual
    default boolean IsEqual(TraversableContainer<?, Data> con){
        return (this == con) || ((con != null) && (Size().compareTo(con.Size()) == 0) && !TraverseForward(dat -> Multiplicity(dat).compareTo(con.Multiplicity(dat)) != 0));
    };

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
    @Override
    default Natural Size(){
        final MutableNatural num = new MutableNatural();
        TraverseForward(dat -> { num.Increment(); return false; });
        return num.ToNatural();
    };

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

  // Exists
    @Override
    default boolean Exists(Data dat){
        return TraverseForward(cur -> (cur == dat) || (cur != null && dat != null && cur.equals(dat)));
    };

}
