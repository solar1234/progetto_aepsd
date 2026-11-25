package apsd.classes.containers.collections.concretecollections;

 import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
 import apsd.classes.containers.collections.concretecollections.bases.LLNode;
 import apsd.classes.utilities.Box;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.ordered.SortedChain;
 import apsd.interfaces.containers.iterators.ForwardIterator;

/** Object: Concrete sorted chain implementation on linked-list. */
public class LLSortedChain<Data extends Comparable<? super Data>> extends LLChainBase<LLSortedChain<Data>, Data> implements SortedChain<LLSortedChain<Data>, Data> {
  // Must extend LLChainBase.
  // Must implement SortedChain.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // LLSortedChain()
  // LLSortedChain(LLSortedChain<Data> chn)
  // LLSortedChain(TraversableContainer<?, Data> con)

    public LLSortedChain() {}

    public LLSortedChain(LLSortedChain<Data> chn) { super(chn); }

    public LLSortedChain(TraversableContainer<?, Data> con) { super(con); }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Empty

  @Override
  public LLSortedChain<Data> Empty() { return new LLSortedChain<>(); }

  /* ************************************************************************ */
  /* Specific member functions of LLSortedChain                               */
  /* ************************************************************************ */

  // PredFind
  protected LLNode<Data> PredFind(Data dat) {
      if (dat == null) return null;
      ForwardIterator<Box<LLNode<Data>>> itr = FRefIterator();
      long len = size.ToLong();
      LLNode<Data> prd = null;
      while (len > 0) {
          long newlen = len / 2;
          ForwardIterator<Box<LLNode<Data>>> nxt = new ListFRefIterator(itr.GetCurrent());
          nxt.Next(newlen);
          LLNode<Data> prb = nxt.GetCurrent().Get();
          if (prb.Get().compareTo(dat) < 0) {
              prd = prb;
              nxt.Next();
              itr = nxt;
              len = len - newlen - 1;
          } else {
              len = newlen;
          }
      }
      return prd;
  }

  // PredPredFind
  protected LLNode<Data> PredPredFind(Data dat) {
      if (dat == null) return null;
      ForwardIterator<Box<LLNode<Data>>> itr = FRefIterator();
      long len = size.ToLong();
      LLNode<Data> prdprd = null;
      LLNode<Data> prd = null;
      while (len > 0) {
          long newlen = len / 2;
          ForwardIterator<Box<LLNode<Data>>> nxt = new ListFRefIterator(itr.GetCurrent());
          nxt.Next(newlen);
          LLNode<Data> prb = nxt.GetCurrent().Get();
          if (prb.Get().compareTo(dat) < 0) {
              if (newlen > 0) {
                  ForwardIterator<Box<LLNode<Data>>> prevItr = new ListFRefIterator(itr.GetCurrent());
                  prevItr.Next(newlen - 1);
                  prdprd = prevItr.GetCurrent().Get();
              } else {
                  prdprd = prd;
              }
              prd = prb;
              nxt.Next();
              itr = nxt;
              len = len - newlen - 1;
          } else {
              len = newlen;
          }
      }
      return prdprd;
  }

  // PredSuccFind
  protected LLNode<Data> PredSuccFind(Data dat) {
      if (dat == null) return null;
      ForwardIterator<Box<LLNode<Data>>> itr = FRefIterator();
      long len = size.ToLong();
      LLNode<Data> prdsuc = null;
      while (len > 0) {
          long newlen = len / 2;
          ForwardIterator<Box<LLNode<Data>>> nxt = new ListFRefIterator(itr.GetCurrent());
          nxt.Next(newlen);
          LLNode<Data> prb = nxt.GetCurrent().Get();
          if (prb.Get().compareTo(dat) <= 0) {
              prdsuc = prb;
              nxt.Next();
              itr = nxt;
              len = len - newlen - 1;
          } else {
              len = newlen;
          }
      }
      return prdsuc;
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // Insert
    @Override
    public boolean Insert(Data dat) {
        if (dat == null) return false;
        LLNode<Data> prd = PredFind(dat);
        Box<LLNode<Data>> cur = (prd == null) ? headref : prd.GetNext();
        LLNode<Data> node = cur.Get();
        LLNode<Data> newnode = new LLNode<>(dat, node);
        cur.Set(newnode);
        if (tailref.Get() == prd) { tailref.Set(newnode); }
        size.Increment();
        return true;
    }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // Remove
  @Override
  public boolean Remove(Data dat) {
      if (dat == null) return false;
      LLNode<Data> prd = PredFind(dat);
      Box<LLNode<Data>> cur = (prd == null) ? headref : prd.GetNext();
      LLNode<Data> node = cur.Get();
      if (node != null && node.Get().compareTo(dat) == 0) {
          cur.Set(node.GetNext().Get());
          if (tailref.Get() == node) { tailref.Set(prd); }
          size.Decrement();
          return true;
      }
      return false;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // Search
  @Override
  public Natural Search(Data dat) {
      if (dat == null) return null;
      Box<LLNode<Data>> cur = headref;
      long len = size.ToLong();
      long pos = 0;
      while (len > 0) {
          long newlen = len / 2;
          Box<LLNode<Data>> nxt = cur;
          for (long idx = 0; idx < newlen; idx++, nxt = nxt.Get().GetNext()) {}
          Data elm = nxt.Get().Get();
          int cmp = elm.compareTo(dat);
          if (cmp == 0) {
              return Natural.Of(pos + newlen);
          } else if (cmp < 0) {
              cur = nxt.Get().GetNext();
              pos += newlen + 1;
              len = len - newlen - 1;
          } else {
              len = newlen;
          }
      }
      return null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from SortedSequence                   */
  /* ************************************************************************ */

  // SearchPredecessor
  @Override
  public Natural SearchPredecessor(Data dat) {
      if (dat == null) return null;
      Box<LLNode<Data>> cur = headref;
      long len = size.ToLong();
      long pos = 0;
      Long res = null;
      while (len > 0) {
          long newlen = len / 2;
          Box<LLNode<Data>> nxt = cur;
          for (long idx = 0; idx < newlen; idx++, nxt = nxt.Get().GetNext()) {}
          Data elm = nxt.Get().Get();
          if (elm.compareTo(dat) < 0) {
              res = pos + newlen;
              cur = nxt.Get().GetNext();
              pos += newlen + 1;
              len = len - newlen - 1;
          } else {
              len = newlen;
          }
      }
      return (res == null) ? null : Natural.Of(res);
  }

    // SearchSuccessor
    @Override
    public Natural SearchSuccessor(Data dat) {
        if (dat == null) return null;
        Box<LLNode<Data>> cur = headref;
        long len = size.ToLong();
        long pos = 0;
        Long res = null;
        while (len > 0) {
            long newlen = len / 2;
            Box<LLNode<Data>> nxt = cur;
            for (long idx = 0; idx < newlen; idx++, nxt = nxt.Get().GetNext()) {}
            Data elm = nxt.Get().Get();
            if (elm.compareTo(dat) > 0) {
                res = pos + newlen;
                len = newlen;
            } else {
                cur = nxt.Get().GetNext();
                pos += newlen + 1;
                len = len - newlen - 1;
            }
        }
        return (res == null) ? null : Natural.Of(res);
    }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  // Predecessor

    @Override
    public Data Predecessor(Data dat) {
        LLNode<Data> prd = PredFind(dat);
        return (prd == null) ? null : prd.Get();
    }

    // RemovePredecessor
    @Override
    public void RemovePredecessor(Data dat) {
        PredecessorNRemove(dat);
    }

    // PredecessorNRemove
    @Override
    public Data PredecessorNRemove(Data dat) {
        if (dat == null) return null;
        LLNode<Data> prd = PredPredFind(dat);
        Box<LLNode<Data>> cur = (prd == null) ? headref : prd.GetNext();
        LLNode<Data> node = cur.Get();
        if (node == null) return null;
        Data elm = node.Get();
        if (elm.compareTo(dat) >= 0) return null;
        cur.Set(node.GetNext().Get());
        if (tailref.Get() == node) { tailref.Set(prd); }
        size.Decrement();
        return elm;
    }

    // Successor
    @Override
    public Data Successor(Data dat) {
        if (dat == null) return null;
        LLNode<Data> prd = PredSuccFind(dat);
        LLNode<Data> node = ((prd == null) ? headref : prd.GetNext()).Get();
        return (node == null) ? null : node.Get();
    }

    // RemoveSuccessor
    @Override
    public void RemoveSuccessor(Data dat) {
        SuccessorNRemove(dat);
    }

    // SuccessorNRemove
    @Override
    public Data SuccessorNRemove(Data dat) {
        if (dat == null) return null;
        LLNode<Data> prd = PredSuccFind(dat);
        Box<LLNode<Data>> cur = (prd == null) ? headref : prd.GetNext();
        LLNode<Data> node = cur.Get();
        if (node == null) return null;
        Data elm = node.Get();
        cur.Set(node.GetNext().Get());
        if (tailref.Get() == node) { tailref.Set(prd); }
        size.Decrement();
        return elm;
    }


  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

  // InsertIfAbsent
  @Override
  public boolean InsertIfAbsent(Data dat) {
      if (dat == null) return false;
      LLNode<Data> prd = PredFind(dat);
      Box<LLNode<Data>> cur = (prd == null) ? headref : prd.GetNext();
      LLNode<Data> node = cur.Get();
      if (node != null && node.Get().compareTo(dat) == 0) return false;
      LLNode<Data> newnode = new LLNode<>(dat, node);
      cur.Set(newnode);
      if (tailref.Get() == prd) { tailref.Set(newnode); }
      size.Increment();
      return true;
  }

    // RemoveOccurrences
    @Override
    public void RemoveOccurrences(Data dat) {
        if (dat == null) return;
        LLNode<Data> prd = PredFind(dat);
        Box<LLNode<Data>> cur = (prd == null) ? headref : prd.GetNext();
        LLNode<Data> node = cur.Get();
        while (node != null && node.Get().compareTo(dat) == 0) {
            cur.Set(node.GetNext().Get());
            if (tailref.Get() == node) { tailref.Set(prd); }
            size.Decrement();
            node = cur.Get();
        }
    }





}
