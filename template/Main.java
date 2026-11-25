import apsd.classes.containers.collections.abstractcollections.WOrderedSet;
import apsd.classes.containers.collections.abstractcollections.WSet;
import apsd.classes.containers.collections.concretecollections.LLList;
import apsd.classes.containers.collections.concretecollections.LLSortedChain;
import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.containers.deqs.WQueue;
import apsd.classes.containers.deqs.WStack;
import apsd.classes.containers.sequences.CircularVector;
import apsd.classes.containers.sequences.Vector;
import apsd.classes.utilities.Natural;

public class Main {

  static public void main(String[] args) {

    System.out.println("APSD Libraries 2026!");

    // Test vettori
    Vector<Integer> vec = new Vector<>(Natural.Of(4));
    vec.SetAt(10, Natural.Of(0));
    vec.SetAt(20, Natural.Of(1));
    vec.SetAt(30, Natural.Of(2));
    vec.SetAt(40, Natural.Of(3));
    System.out.println("Vector size: " + vec.Size() + ", first: " + vec.GetFirst() + ", last: " + vec.GetLast());

    try {
      vec.GetAt(Natural.Of(10));
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Eccezione out of bounds ok");
    }

    CircularVector<Integer> cvec = new CircularVector<>(Natural.Of(3));
    cvec.SetAt(5, Natural.Of(0));
    cvec.SetAt(15, Natural.Of(1));
    cvec.SetAt(25, Natural.Of(2));
    System.out.println("CircularVector elem 1: " + cvec.GetAt(Natural.Of(1)));
    cvec.Clear();
    System.out.println("CircularVector dopo clear: " + cvec.IsEmpty());

    // Test liste
    VList<String> vlist = new VList<>();
    vlist.InsertLast("uno");
    vlist.InsertLast("tre");
    vlist.InsertAt("due", Natural.Of(1));
    vlist.InsertFirst("zero");
    System.out.println("VList size: " + vlist.Size() + ", pos 2: " + vlist.GetAt(Natural.Of(2)));
    vlist.Remove("due");
    System.out.println("VList exists 'due': " + vlist.Exists("due"));

    LLList<Integer> llist = new LLList<>();
    llist.InsertFirst(2);
    llist.InsertFirst(1);
    llist.InsertLast(3);
    llist.SetAt(99, Natural.Of(1));
    System.out.println("LLList: " + llist.GetFirst() + " " + llist.GetAt(Natural.Of(1)) + " " + llist.GetLast());
    llist.RemoveFirst();
    System.out.println("LLList nuovo primo: " + llist.GetFirst());

    // Test stack e queue
    WStack<Integer> stk = new WStack<>(new LLList<>());
    stk.Insert(10);
    stk.Insert(20);
    stk.Insert(30);
    System.out.println("Stack top: " + stk.Top() + ", size: " + stk.Size());
    stk.Pop();
    System.out.println("Stack top dopo pop: " + stk.Top());

    WQueue<String> que = new WQueue<>(new LLList<>());
    que.Insert("primo");
    que.Insert("secondo");
    que.Insert("terzo");
    System.out.println("Queue head: " + que.Head() + ", size: " + que.Size());
    que.Dequeue();
    System.out.println("Queue head dopo dequeue: " + que.Head());

    // Test insiemi e sorted chain
    WSet<Integer> s1 = new WSet<>(new LLList<>());
    s1.Insert(1);
    s1.Insert(2);
    boolean dup = s1.Insert(2);
    System.out.println("Inserimento duplicato in WSet: " + dup + ", size: " + s1.Size());

    WSet<Integer> s2 = new WSet<>();
    s2.Insert(2);
    s2.Insert(3);
    s1.Union(s2);
    System.out.println("WSet dopo union size: " + s1.Size() + ", contiene 3: " + s1.Exists(3));
    s1.Difference(s2);
    System.out.println("WSet dopo difference size: " + s1.Size() + ", contiene 2: " + s1.Exists(2));

    LLSortedChain<Integer> chn = new LLSortedChain<>();
    chn.Insert(30);
    chn.Insert(10);
    chn.Insert(20);
    chn.Insert(20);
    System.out.println("SortedChain search 20: " + chn.Search(20) + ", pred(25): " + chn.Predecessor(25));
    chn.RemoveOccurrences(20);
    System.out.println("SortedChain size dopo RemoveOccurrences(20): " + chn.Size());

    WOrderedSet<Integer> oset = new WOrderedSet<>();
    oset.Insert(40);
    oset.Insert(10);
    oset.Insert(25);
    System.out.println("WOrderedSet pred(25): " + oset.Predecessor(25) + ", succ(25): " + oset.Successor(25));
    oset.Remove(10);
    System.out.println("WOrderedSet size: " + oset.Size() + ", exists(10): " + oset.Exists(10));

  }

}