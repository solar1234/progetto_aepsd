package apsd.classes.containers.deqs;

 import apsd.classes.containers.collections.concretecollections.VList;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.List;
 import apsd.interfaces.containers.deqs.Queue;

/** Object: Wrapper lstue implementation. */
public class WQueue<Data> implements Queue<WQueue<Data>, Data> {
  // Must implement Queue.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

   protected final List<?, Data> lst;

    // WQueue()
    public WQueue() { lst = new VList<>(); }

    // WQueue(List<?, Data> lst)
    public WQueue(List<?, Data> lst) { this.lst = lst; }

    // WQueue(TraversableContainer<?, Data> con)
    public WQueue(TraversableContainer<?, Data> con) {
        lst = new VList<>();
        if (con != null) {
            con.TraverseForward(dat -> { lst.InsertLast(dat); return false; });
        }
    }

    // WQueue(List<?, Data> lst, TraversableContainer<?, Data> con)
    public WQueue(List<?, Data> lst, TraversableContainer<?, Data> con) {
        this.lst = lst;
        if (con != null) {
            con.TraverseForward(dat -> { lst.InsertLast(dat); return false; });
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
    public WQueue<Data> Empty() { return new WQueue<>(); }


  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
  @Override
  public void Clear() { lst.Clear(); }

  /* ************************************************************************ */
  /* Override specific member functions from Queue                            */
  /* ************************************************************************ */

  // Head
  @Override
  public Data Head() {
      if (lst.Size().IsZero()) { return null; } else { return lst.GetFirst(); }
  }

  // Dequeue
  @Override
  public void Dequeue() {
      if (lst.Size().IsNotZero()) { lst.RemoveFirst(); }
  }

  // HeadNDequeue
  @Override
  public Data HeadNDequeue() {
      if (lst.Size().IsZero()) { return null; } else { return lst.FirstNRemove(); }
  }

  // Enqueue
  @Override
  public void Enqueue(Data dat) {
      if (dat == null) return;
      lst.InsertLast(dat);
  }

}
