import java.util.Iterator;
import java.util.*;

public class MyLinkedListImproved <T extends Comparable<T>> implements Iterable<T>{
  public Node start,end;
  public int size;

  private class Node{
    Node next,prev;
    T data;

    public Node(T value){
      data = value;
    }
    public Node(Node n, Node p, T val){
      next = n;
      prev = p;
      data = val;
    }

    public Node getNext(){
      return next;
    }
    public void setNext(Node n){
      next = n;
    }
    public Node getPrev(){
      return prev;
    }
    public void setPrev(Node p){
      prev = p;
    }
    public T getValue(){
      return data;
    }
    public void setValue(T d){
      data = d;
    }
    public String toString(){
      return data +"";
    }
  }
  public MyLinkedListImproved(){
   start = null;
   end = null;
   size = 0;
     }


  //returns node at given index
  private Node getNode(int index){
    Node nodeN = start;
    for(int c = 0;c <= index;c++){
      if(c == index){
        return nodeN;
      }
      nodeN = nodeN.getNext();
    }
    return nodeN;
  }


  public int size(){
    return size;
  }

  public void clear(){
    start = null;
    end = null;
    size = 0;
  }

  //adds at end of the list
  public boolean add(T newData){
    if(size == 0){
      start = new Node(newData);
      end = start;
    }
    else {
      Node newNode = new Node(newData);
      end.setNext(newNode);
      newNode.setPrev(end);
      end = newNode;
    }
    size++;
    return true;
  }

  //adds value at index
  public void add(int index, T value){
    if(index > size() || index < 0){
      throw new IndexOutOfBoundsException();
    }
    if(index == size()){
      add(value);
      return ;
    }
    if(index == 0){
      Node nodeN = new Node(null, null, value);
      start.setPrev(nodeN);
      nodeN.setNext(start);
      start = nodeN;
      size++;
      return ;
    }
    Node nodeN = new Node(null, null, value);
    Node temp = start;
    for(int c = 0; c < index -1;c++){
      temp = temp.getNext();
    }
    Node te = temp.getNext();
    temp.setNext(nodeN);
    nodeN.setPrev(temp);
    te.setPrev(nodeN);
    nodeN.setNext(te);
    size++;
  }

  public int indexOf(T value){
    int c = 0;
    Node index = start;
    while(index != null){
      if(index.getValue().equals(value)){
        return c;
      }
      c++;
      index = index.getNext();
    }
    return -1;
  }

  public String toString(){
    String str = "[";
    Node index = start;
    while(index != null){
      str += index;
      if(index.getNext() != null){
        str +=  ", ";
      }
      index = index.getNext();
    }
    str +=  "]";
    return str;
  }

  //returns value of index
  public T get(int index){
    if(index >= size() || index < 0){
      throw new IndexOutOfBoundsException();
    }
    Node value = start;
    for(int c = 0;c < index;c++){
      value = value.getNext();
    }
    return value.getValue();
  }

  //channges value at indext into new value
  public T set(int index, T newValue){
    if(index >= size || index < 0){
      throw new IndexOutOfBoundsException();
    }
    Node value = start;
    for(int c = 0; c <= index; c++){
      value = value.getNext();
    }
    T ans = value.getValue();
    value.setValue(newValue);
    return ans;
  }

  //removes start instance of value
  public boolean remove(T value){
    Node index = start;
    while(index.getValue() != value){
      index = index.getNext();
    }
    if(index == null)return false;

    if(index.getPrev() == null){
      index.getNext().setPrev(null);
      start = index.getNext();
      size--;
      return true;
    }
    if(index.getNext() == null){
      index.getPrev().setNext(null);
      size--;
      return true;
    }
    Node back = index.getPrev();
    Node after = index.getNext();
    back.setNext(after);
    after.setPrev(back);
    size--;
    return true;
  }


//removes value at index
public T remove(int index){
  if(index >= size() || index < 0){
    throw new IndexOutOfBoundsException();
  }
  Node Nindex = start;
  for(int c = 0;c < index;c++){
    Nindex.getNext();
  }
  if (Nindex.getPrev() == null){
    Nindex.getNext().setPrev(null);
    start = Nindex.getNext();
    size--;
    return Nindex.getValue();
  }
  if (Nindex.getNext() == null){
    Nindex.getPrev().setNext(null);
    end = Nindex.getPrev();
    size--;
    return Nindex.getValue();
  }
  Node m = Nindex.getNext();
  Node k = Nindex.getPrev();
  Nindex.setPrev(k);
  k.setNext(m);
  size--;
  return Nindex.getValue();
}


public int max(){
  if(size() == 0){
    return -1;
  }
  T tempMax = start.getValue();
  for(T index:this){
    if(tempMax.compareTo(index) < 0){
      tempMax = index;
    }
  }
  return indexOf(tempMax);
}

public int min(){
  if(size() == 0){
    return -1;
  }
  T tempMin = start.getValue();
  for(T index:this){
    if(tempMin.compareTo(index) > 0){
      tempMin = index;
    }
  }
  return indexOf(tempMin);
}

//take out all of other list and move to linkedlist, attach to end
//other is now empty

public void extend(MyLinkedListImproved<T> other){
  end.setNext(other.start);
  other.start.setPrev(end);
  end = other.end;
  other.clear();
}


public class MyLLIterator implements Iterator<T>{
  private Node next;

  public MyLLIterator(Node start){
    next = start;
  }
  public void remove(){
    throw new UnsupportedOperationException();
  }

  public boolean hasNext(){
    return next != null;
  }

  public T next(){
    if (hasNext()){
      T index = next.getValue();
      next = next.getNext();
      return index;
    }
    throw new NoSuchElementException();
  }
}

public Iterator<T> iterator(){
  return new MyLLIterator(start);
}


public static void main(String[]args){
  MyLinkedListImproved<String> n = new MyLinkedListImproved<>();
  n.add("fish");
  System.out.println(n);
  MyLinkedListImproved<Integer> m = new MyLinkedListImproved<>();
  for (int x = 1; x < 10; x++){
    m.add(10 - x);
  }
  MyLinkedListImproved<Integer> r = new MyLinkedListImproved<>();
  for (int x = 1; x < 10; x++){
    r.add(x);
  }
  m.extend(r);
  System.out.println(m.toString());
  System.out.println(r.toString());
  System.out.println(m.min());
  System.out.println(m.max());
}

}
