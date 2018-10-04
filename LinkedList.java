//Linked list class
class LinkedList
{
    ListNode head;    //Head of list
 
    //Inserts a new node at the front of the list
    public void push(int new_data)
    {
        //Allocate new node and putting data
        ListNode new_node = new ListNode(new_data);
 
        //Make next of new node as head
        new_node.next = head;
 
        //Move the head to point to new Node
        head = new_node;
    }
}