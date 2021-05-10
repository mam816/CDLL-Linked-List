import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See executeRitual() method 
	public CDLL_JosephusList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	
	public CDLL_JosephusList( String infileName ) throws Exception
	{
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );	
		while ( infile.ready() )
		{	@SuppressWarnings("unchecked") 
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail( data ); 
		}
		infile.close();
	}
	

	
	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################
   
   public void insertAtFront(T data)
	{
		 CDLL_Node<T> newNode = new CDLL_Node<T>( data,null,null);
		if (head==null)
		{
			newNode.next = newNode;
			newNode.prev = newNode;
			head = newNode;
			return;
		}
		 
       newNode.next = head;
       head.prev.next = newNode;
		 newNode.prev = head.prev;
		 head.prev = newNode;
		 head = newNode;
      }
	
	// TACK ON NEW NODE AT END OF LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
	  insertAtFront(data);
	  head = head.next;
   }

	
	public int size()
	{	
		int count = 0;
		if(head == null)
		{
		return 0;
		}
		CDLL_Node<T> curr = head;
		do
         {
				 ++count;
				 curr = curr.next;
         }
			while(curr != head);
				return count;
	}
	
	// RETURN REF TO THE FIRST NODE CONTAINING  KEY. ELSE RETURN NULL
	public CDLL_Node<T> search( T key )
	{	
		 CDLL_Node<T> curr = head;
		if(head == null)
		{
			return null;
		}
		do
		    {
		         if(curr.data.equals(key))
				 {
					 return curr;
				 }
				 curr = curr.next;
			}
			 while(curr != head);
		return null;
	}
	
	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString()
	{
		String toString = "";
		CDLL_Node<T> curr = head;
		if(head == null)
		{
			return toString;
		}
		do
		   { 
		       toString += curr.data + "<=>";
				 curr = curr.next;
		   }
			while(curr != head);
		     return toString.substring(0, toString.length() - 3);
		
	}
	
	void removeNode( CDLL_Node<T> deadNode )
	{
     deadNode.prev.next = deadNode.next;
     deadNode.next.prev = deadNode.prev;
	}
	
	public void executeRitual( T first2Bdeleted, int skipCount )
	{
		if (size() <= 1 ) return;
		CDLL_Node<T> curr = search( first2Bdeleted );
		if ( curr==null ) return;
		
		// OK THERE ARE AT LEAST 2 NODES AND CURR IS SITING ON first2Bdeleted
		do
		{
			CDLL_Node<T> deadNode = curr;
			T deadName = deadNode.data;
         int count = 0;
			
			System.out.println("stopping on "+ curr.data+" to delete "+ curr.data);
			
			// BEFORE DOING ACTUAL DELETE DO THESE TWO THINGS 

			// 1: you gotta move that curr off of the deadNode. 
			//    if skipCount poitive do curr=curr.next  esle do  curr=curr.prev
           if(skipCount > 0)
             { 
              curr = curr.next;
             }
           else
             {
              curr = curr.prev;
             }
			// 2: check to see if HEAD is pointing to the deadnode. 
			//    If so make head=curr 
			    if(head.data.equals(deadNode.data))
               {
                head = curr;
               }
             removeNode(deadNode);
			// NOW DELETE THE DEADNODE

			System.out.println("deleted. list now:   " + toString() );
			
			// if the list size has reached 1 return YOU ARE DONE.  RETURN RIGHT HERE
         if(size() == 1)
           {
             return;
           }
	      else
           {
             if(skipCount < 0)
               {
			        System.out.println("resuming at " + curr.data +"," + " skipping "+ curr.data + " + " + ((skipCount*-1)-1) +" nodes COUNTER_CLOCKWISE after");
			      }
             if(skipCount > 0)
               {
                 System.out.println("resuming at " + curr.data +"," + " skipping "+ curr.data + " + " + (skipCount-1) +" nodes CLOCKWISE after");
               }
			//write loop that advances curr pointer skipCount times (be sure of CLOCKWISE or COUNTER)
         if(skipCount < 0)
           {
            while(count != skipCount)
                 {
                  curr = curr.prev;
                  count--;
                 }
           }
         if(skipCount > 0)
           {
            while(count != skipCount)
                 {
                  curr = curr.next;
                  count++;
                 }
           }
         }
			// OPTIONAL HERE FOR DEBUGGING TO MAKE IT STOP AT BOTTOM OF LOOP
			// Scanner kbd = new Scanner( System.in ); String junk = kbd.nextLine();   
			
		}
		while (size() > 1 );  // ACTUALLY COULD BE WHILE (TRUE) SINCE WE RETURN AS SOON AS SIZE READES 1

	}
	
 class CDLL_Node<T>
{
  T data;
  CDLL_Node<T> prev, next; // EACH CDLL_Node PTS TO ITS PREV  & NEXT

  CDLL_Node()
  {
    this( null, null, null );  // 3 FIELDS TO INIT
  }

  CDLL_Node(T data)
  {
    this( data, null, null);
  }

  CDLL_Node(T data, CDLL_Node<T> prev, CDLL_Node<T> next)
  {
    this.data = data;
	 this.prev = prev;
    this.next = next;
  }
 
 public String toString()
  {
	  return ""+this.data;
  } 
	 
} //EOF
	
} // END CDLL_LIST CLASS