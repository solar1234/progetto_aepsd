package apsd.classes.containers.deqs;

 import apsd.classes.containers.collections.concretecollections.VList;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.List;
 import apsd.interfaces.containers.deqs.Stack;

/** Object: Wrapper stack implementation. */
public class WStack<Data> implements Stack<WStack<Data>, Data> {
  // Must implement Stack.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

   protected final List<?, Data> lst;
    // WStack()
    public WStack() { lst = new VList<>(); }

    // WStack(List<?, Data> lst)
    public WStack(List<?, Data> lst) { this.lst = lst; }

    // WStack(TraversableContainer<?, Data> con)
    public WStack(TraversableContainer<?, Data> con) {
        lst = new VList<>();
        if (con != null) {
            con.TraverseForward(dat -> { lst.Insert(dat); return false; });
        }
    }

    // WStack(List<?, Data> lst, TraversableContainer<?, Data> con)
    public WStack(List<?, Data> lst, TraversableContainer<?, Data> con) {
        this.lst = lst;
        if (con != null) {
            con.TraverseForward(dat -> { lst.Insert(dat); return false; });
        }
    }


  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

    // Size
    @Override
    public Natural Size() { return lst.Size(); }

    // Empty
    @Override
    public WStack<Data> Empty() { return new WStack<>(); }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
  @Override
  public void Clear() { lst.Clear(); }

  /* ************************************************************************ */
  /* Override specific member functions from Stack                            */
  /* ************************************************************************ */

    // Top
    @Override
    public Data Top() {
      if (lst.Size().IsZero()) { return null; } else { return lst.GetFirst(); }
    }

    // Pop
    @Override
    public void Pop() {
        if (lst.Size().IsNotZero()) { lst.RemoveFirst(); }
    }

    // TopNPop
    @Override
    public Data TopNPop() {
        if (lst.Size().IsZero()) { return null; } else { return lst.FirstNRemove(); }
    }

    // SwapTop
    @Override
    public void SwapTop(Data dat) {
        if (dat == null) return;
        if (lst.Size().IsNotZero()) { lst.SetFirst(dat); }
    }

    // TopNSwap
    @Override
    public Data TopNSwap(Data dat) {
        if (dat == null) return Top();
        if (lst.Size().IsZero()) { return null; } else { return lst.GetNSetFirst(dat); }
    }

    // Push
    @Override
    public void Push(Data dat) {
        if (dat == null) return;
        lst.InsertFirst(dat);
    }

}
