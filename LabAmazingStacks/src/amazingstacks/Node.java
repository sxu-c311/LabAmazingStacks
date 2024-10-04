// File: Node.java from the package edu.colorado.nodes
// Complete documentation is available from the IntNode link in:
//   http://www.cs.colorado.edu/~main/docs
// Modified by James Vanderhyde, 27 September 2012
//   Changed package and rearranged methods.
//   Modified listPosition so it is 0-indexed.
// Modified by James Vanderhyde, 15 October 2012
//   Made this a generic node
// Modified by James Vanderhyde, 23 September 2024
//   Changed "link" to "next" to match ZyBooks
//   Renamed static methods to avoid confusion with list ADT

package amazingstacks;

/**
 * An Node provides a node for a linked list with data in each node.
 * 
 * Note: Lists of nodes can be made of any length, limited only by the amount of
 * free memory in the heap. But beyond Integer.MAX_VALUE (2,147,483,647), the
 * answer from listLength is incorrect because of arithmetic overflow.
 *
 * @param <E> the type of elements in the nodes of the linked list
 * 
 * @see <A HREF="../../../../edu/colorado/nodes/IntNode.java"> Java Source Code
 * for this class
 * (www.cs.colorado.edu/~main/edu/colorado/nodes/IntNode.java)</A>
 * 
 * @author Michael Main <A HREF="mailto:main@colorado.edu"> (main@colorado.edu)
 * </A>
 * 
 * @version March 6, 2002
 * 
 */
public class Node<E>
{
    // Invariant of the Node<E> class:
    //   1. The node's integer data is in the instance variable data.
    //   2. For the final node of a list, the next part is null.
    //      Otherwise, the next part is a reference to the
    //      next node of the list.
    private E data;
    private Node<E> next;

    /**
     * Initialize a node with a specified initial data and link to the next
     * node. Note that the initialNext may be the null reference, which
     * indicates that the new node has nothing after it.
     *
     * @param initialData the initial data of this new node
     * @param initialNext a reference to the node after this new node--this
     * reference may be null to indicate that there is no node after this new
     * node.
     */
    public Node(E initialData, Node<E> initialNext)
    {
        data = initialData;
        next = initialNext;
    }

    /**
     * Accessor method to get the data from this node.
     *
     * @return the data from this node
     */
    public E getData()
    {
        return data;
    }

    /**
     * Accessor method to get a reference to the next node after this node.
     *
     * @return a reference to the node after this node (or the null reference if
     * there is nothing after this node)
     */
    public Node<E> getNext()
    {
        return next;
    }

    /**
     * Modification method to set the data in this node.
     *
     * @param newData the new data to place in this node
     */
    public void setData(E newData)
    {
        data = newData;
    }

    /**
     * Modification method to set the link to the next node after this node. 
     * Any other node (that used to be in this next) is no longer
     * connected to this node.
     *
     * @param newNext a reference to the node that should appear after this node
     * in the linked list (or the null reference if there is no node after this
     * node)
     */
    public void setNext(Node<E> newNext)
    {
        next = newNext;
    }

    /**
     * Modification method to add a new node after this node.
     * A new node is created and placed after this node.
     * The data for the new node is item. Any other nodes that used to be after
     * this node are now after the new node.
     *
     * @param item the data to place in the new node
     * @throws OutOfMemoryError Indicates that there is insufficient memory
     * for a new IntNode. 
     */
    public void addNodeAfter(E item)
    {
        next = new Node<>(item, next);
    }

    /**
     * Modification method to remove the node after this node.
     * If there were further nodes after that one, they are still present
     * on the list. This node must not be the tail node of the list.
     * @throws NullPointerException Indicates that this was the tail node of
     * the list, so there is nothing after it to remove.
     */
    public void removeNodeAfter()
    {
        next = next.next;
    }

    /**
     * Computes the number of nodes in a linked list. 
     * Note: A wrong answer occurs for lists longer than Int.MAX_VALUE.
     *
     * @param <E> the type of elements in the nodes
     * @param head the head reference for a linked list (which may be an empty
     * list with a null head)
     * @return the number of nodes in the list with the given head
     */
    public static<E> int linkedLength(Node<E> head)
    {
        Node<E> cursor;
        int answer;

        answer = 0;
        for (cursor = head; cursor != null; cursor = cursor.next)
            answer++;

        return answer;
    }

    /**
     * Searches for a particular piece of data in a linked list.
     *
     * @param <E> the type of elements in the nodes
     * @param head the head reference for a linked list (which may be an empty
     * list in which case the head is null)
     * @param target a piece of data to search for
     * @return The return value is a reference to the first node that contains
     * the specified target. If there is no such node, the null reference is
     * returned.     
     */
    public static<E> Node<E> linkedSearch(Node<E> head, E target)
    {
        Node<E> cursor;

        for (cursor = head; cursor != null; cursor = cursor.next)
            if (target == cursor.data ||
                (target != null && target.equals(cursor.data)))
                return cursor;

        return null;
    }

    /**
     * Finds a node at a specified position in a linked list.
     *
     * @param <E> the type of elements in the nodes
     * @param head the head reference for a linked list (which may be an empty
     * list in which case the head is null)
     * @param position a node number. Must be >= 0.
     * @return a reference to the node at the specified
     * position in the list. (The head node is position 0, the next node is
     * position 1, and so on.) If there is no such position (because the list is
     * too short), then the null reference is returned.
     * @throws IllegalArgumentException Indicates that position is 
     * negative.    
     */
    public static<E> Node<E> linkedPosition(Node<E> head, int position)
    {
        Node<E> cursor;
        int i;

        if (position < 0)
            throw new IllegalArgumentException("position is negative");

        cursor = head;
        for (i = 0; (i < position) && (cursor != null); i++)
            cursor = cursor.next;

        return cursor;
    }

    /**
     * Copies a list. 
     * The method makes a copy of the linked list starting at source.
     *
     * @param <E> the type of elements in the nodes
     * @param source the head of a linked list that will be copied (which may be
     * an empty list in where source is null)
     * @return the head reference for the copy.
     * @throws OutOfMemoryError Indicates that there is insufficient memory
     * for the new list.   
     */
    public static<E> Node<E> linkedCopy(Node<E> source)
    {
        Node<E> copyHead;
        Node<E> copyTail;

        // Handle the special case of the empty list.
        if (source == null)
            return null;

        // Make the first node for the newly created list.
        copyHead = new Node<>(source.data, null);
        copyTail = copyHead;

        // Make the rest of the nodes for the newly created list.
        while (source.next != null)
        {
            source = source.next;
            copyTail.addNodeAfter(source.data);
            copyTail = copyTail.next;
        }

        // Return the head reference for the new list.
        return copyHead;
    }

    /**
     * Copies a list, returning both a head and tail reference for the copy.
     * The method makes a copy of the linked list starting at source.
     *
     * @param <E> the type of elements in the nodes
     * @param source the head of a linked list that will be copied (which may be
     * an empty list in where source is null)
     * @return an array where the [0] element is a head reference
     * for the copy and the [1] element is a tail reference for the copy.
     * @throws OutOfMemoryError Indicates that there is insufficient memory
     * for the new list.   
     */
    public static<E> Object[] linkedCopyWithTail(Node<E> source)
    {
        Node<E> copyHead;
        Node<E> copyTail;
        Object[] answer = new Object[2];

        // Handle the special case of the empty list.   
        if (source == null)
            return answer; // The answer has two null references .

        // Make the first node for the newly created list.
        copyHead = new Node<>(source.data, null);
        copyTail = copyHead;

        // Make the rest of the nodes for the newly created list.
        while (source.next != null)
        {
            source = source.next;
            copyTail.addNodeAfter(source.data);
            copyTail = copyTail.next;
        }

        // Return the head and tail references.
        answer[0] = copyHead;
        answer[1] = copyTail;
        return answer;
    }

    /**
     * Copies part of a list, providing a head and tail reference for the new
     * copy. 
     * The method makes a copy of apart of a linked list, from the
     * specified start node to the specified end node.
     * The start and end must be non-null references 
     * to nodes on the same
     * linked list, with the start node at or before the end node.
     *
     * @param <E> the type of elements in the nodes
     * @param start reference to a node of a linked list
     * @param end references to a node of the same linked list
     * @return an array where the [0] component is a head reference for the 
     * copy and the [1] component is a tail reference for the copy.
     * @throws IllegalArgumentException Indicates that start and end are not
     * references to nodes on the same list.
     * @throws NullPointerException Indicates that start is null.
     * @throws OutOfMemoryError Indicates that there is insufficient memory
     * for the new list.    
     */
    public static<E> Object[] linkedPart(Node<E> start, Node<E> end)
    {
        Node<E> copyHead;
        Node<E> copyTail;
        Node<E> cursor;
        Object[] answer = new Object[2];

        // Make the first node for the newly created list. Notice that this will
        // cause a NullPointerException if start is null.
        copyHead = new Node<>(start.data, null);
        copyTail = copyHead;
        cursor = start;

        // Make the rest of the nodes for the newly created list.
        while (cursor != end)
        {
            cursor = cursor.next;
            if (cursor == null)
                throw new IllegalArgumentException("end node was not found on the list");
            copyTail.addNodeAfter(cursor.data);
            copyTail = copyTail.next;
        }

        // Return the head and tail references
        answer[0] = copyHead;
        answer[1] = copyTail;
        return answer;
    }

}
