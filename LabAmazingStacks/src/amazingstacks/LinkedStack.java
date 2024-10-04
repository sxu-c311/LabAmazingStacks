//A stack using an underlying implementation of a linked list.
//Created by James Vanderhyde, 4 October 2024

package amazingstacks;

import java.util.EmptyStackException;

/**
 * A stack of elements. The underlying implementation is a linked list.
 * @author James Vanderhyde
 * @param <E> The underlying type of the elements in the stack
 */
public class LinkedStack<E>
{
    //Invariant of the CycleStack class:
    //1. The elements of the stack are stored in a linked list.
    //   The top of the stack is at the head.
    //   When head is null, the stack is empty.
    private Node<E> head;
    
    /**
     * Initializes a new empty stack.
     */
    public LinkedStack()
    {
        this.head = null;
    }
    
    /**
     * Pushes a new item onto this stack.
     * @param item the item to push onto the stack
     */
    public void push(E item)
    {
        this.head = new Node<>(item, this.head);
    }
    
    /**
     * Gets the top item, removing it from this stack.
     * This stack must not be empty.
     * @return The top item of this stack.
     * @throws EmptyStackException if the stack is empty.
     */
    public E pop()
    {
        if(this.isEmpty())
            throw new EmptyStackException();
        E item = this.head.getData();
        this.head = this.head.getNext();
        return item;
    }
    
    /**
     * Gets the top item, leaving the stack intact.
     * This stack must not be empty.
     * @return The top item of this stack.
     * @throws EmptyStackException if the stack is empty.
     */
    public E peek()
    {
        if(this.isEmpty())
            throw new EmptyStackException();
        return this.head.getData();
    }
    
    /**
     * Determines whether this stack is empty.
     * @return true if the stack is empty; false if it is not.
     */
    public boolean isEmpty()
    {
        return this.head == null;
    }
}
