//---------------------------------------------------------------------------
// LinkedQueue.java            by Dale/Joyce/Weems                  Chapter 4
//
// Implements QueueInterface using a linked list.
//---------------------------------------------------------------------------


public class LinkedQueue<T> implements QueueInterface<T>
{
  protected LLNode<T> front;     // reference to the front of this queue
  protected LLNode<T> rear;      // reference to the rear of this queue
  protected int numElements = 0; // number of elements in this queue

  public LinkedQueue()
  {
    front = null;
    rear = null;
  }

  public void enqueue(T element)
  // Adds element to the rear of this queue.
  {
    LLNode<T> newNode = new LLNode<T>(element);
    if (rear == null)
      front = newNode;
    else
      rear.setLink(newNode);
    rear = newNode;
    numElements++;
  }

  public T dequeue()
  // Throws QueueUnderflowException if this queue is empty;
  // otherwise, removes front element from this queue and returns it.
  {
    if (isEmpty())
      throw new QueueUnderflowException("Dequeue attempted on empty queue.");
    else
    {
      T element;
      element = front.getInfo();
      front = front.getLink();
      if (front == null)
        rear = null;
      numElements--;
      return element;
    }
  }

  //find item in queue and remove it
  public T cancel(PrintJob item) {
    if (isEmpty()) {
      //throw new QueueUnderflowException("Cancel attempted on empty queue.");
      //System.out.println("Cancel attempted on empty queue.");
      return null;
    }
    else
    {
      LLNode<T> prev = null;
      LLNode<T> curr = front;
      T element;
      element = curr.getInfo();

      while(curr != null && !curr.getInfo().equals(item)) {
        prev = curr;
        curr = curr.getLink();
      }

      //if curr == null then item not in queue and null is returned
      if (curr == null) {
        return null;
      }
      else if (curr == front)  //checks if at front and if queue is empty
        element = dequeue();
      else if (curr == rear) {  //checks if at rear
        prev = null;
      }
      else { 
        prev.setLink(curr.getLink());
      }
      numElements--;
      return element;
    }
  }

  public boolean isEmpty()
  // Returns true if this queue is empty; otherwise, returns false.
  {
    return (front == null);
  }

  public boolean isFull()
  // Returns false - a linked queue is never full.
  {
    return false;
  }

  public int size()
  // Returns the number of elements in this queue.
  {
    return numElements;
  }

}

