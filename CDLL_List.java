import java.io.*;
import java.util.*;

public class CDLL_List<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	
	public CDLL_List()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public CDLL_List( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------



	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################

	// inserts new elem at front of list - pushing old elements back one place

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
		 
		// WRITE THE LINES OF CODE TO DO THE INSERT
	}
	public void insertAtTail(T data)
	{
      
		insertAtFront(data);
		head = head.next;
		
		// CALL INSERT AT FRONT HERE 
		// THEN ADJUST THE HEAD POINTER 
	}
	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
	    CDLL_Node<T> newNode = head.prev;
         if(head == null)
		 {
            return false;
		 }
       if(size() == 1)
         {
           head = null;
           return true;
         }
          newNode.prev.next = head.prev;
          head.prev = newNode.prev;
		    return true; 
			
		 // YOUR CODE HERE. TRY TO DO CODE RE-USE SIMILAR TO FRONT
	}

	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
	  CDLL_Node<T> newNode = head;
     if(size() == 1)
       {
         head = null;
         return true;
       }
    // newNode.prev.next = newNode.next;
     newNode.next.prev = newNode.prev.next;
     newNode.prev.next = newNode.next;
	  head = newNode.next; 
     return true;
		
		 // YOUR CODE HERE.  // TRY TO DO CODE RE-USE SIMILAR TO FRONT
	}

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
		       toString += curr.data + "  ";
				 curr = curr.next;
		   }
			while(curr != head);
		     return toString;
		  // NO <=> DELIMITERS REQUIRED ANYWHERE IN OUTPUT

		 // JUST A SPACE BETEEN ELEMENTS LIKE IN LAB3
	}

	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
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
				
		// YOUR CODE HERE
	}

	public boolean empty()
	{
		return (size() == 0);  // YOUR CODE HERE
	}

	public boolean contains( T key )
	{
		return (search(key) != null);  // YOUR CODE HERE
	}

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
		
		  // YOUR CODE HERE
	}

	@SuppressWarnings("unchecked")  //CAST TO COMPARABLE THROWS WARNING
	public void insertInOrder(T  data)
	{
		if(size() == 0 )//|| result < 0)
        {
         insertAtFront(data);
         return;
        }
        
     CDLL_Node <T> curr = this.head;
      Comparable cData = (Comparable)data; 
      int result = cData.compareTo(curr.data);
      if(result > 0)
         {
             while(result > 1 && curr.next != head)
                 {
                  result = cData.compareTo(curr.next.data);
                  if(result < 0 && result > -2)
                    {
                     curr.next = new CDLL_Node<T>(data,curr.prev, curr.next);
                     return;
                    }
                  if(result == -2)
                    {
                      curr.next = new CDLL_Node<T>(data, curr, curr.next);
                      curr.next.next.prev = curr.next;
                      return;
                    }
                  curr = curr.next;
                  result = cData.compareTo(curr.data);
                 }
                 if(curr.next == null)
                   {
                    curr.next = new CDLL_Node<T>(data,null,null);
                    return;
                   }
                 if(result == 1)
                   {
                    result = cData.compareTo(curr.next.data);
                    while(result >= 1)
                         {
                          curr = curr.next;
                          result = cData.compareTo(curr.next.data);
                          if(result == 1)
                            {
                             curr.next.next = new CDLL_Node<T>(data, curr.next, curr.next.next);
                             curr.prev = curr.next.next;
                             return;
                            }
                         }
                      if(result < 1)
                        {
                          curr.next = new CDLL_Node<T>(data,curr,curr.next);
                          curr.next.next.prev = curr.next;
                          return;
                        }
                   }
                 curr.next = new CDLL_Node<T>(data, curr, head);
                 head.prev = curr.next;
                 return;
                   }
      if(result < 0)
         {
           insertAtFront(data);
           return;
         }
         } 
      
		// YOUR CODE HERE

	public boolean remove(T key)
	{
		
	  CDLL_Node<T> curr = head;
      if(size() == 0)
        {
         return false;
        }
      if(size() == 1 && curr.data.equals(key))
        {
          curr = null;
          return true;
        }
      if(size() > 1 && curr.data.equals(key))
        {
         removeAtFront();
         return true;
        }  
     // while(curr != null && curr.next != null)
		 do
             {
              if(curr.next.data.equals(key))
                {
                curr.next = curr.next.next;
                return true;
                } 
              curr = curr.next;
	          }
			  while(curr != head);
          return false;
   }
	  //  REPLACE WITH YOUR CODE 
	


	public CDLL_List<T> union( CDLL_List<T> other )
	{
		CDLL_List<T> union = new CDLL_List<T>();

     CDLL_Node<T> curr = other.head;
     CDLL_Node<T> curr2 = this.head;
     CDLL_Node<T> curr3 = union.head;
     //while(curr != null) 
		 do
		     {
             union.insertAtTail(curr.data);
             curr = curr.next;
  			  }
		while(curr != other.head);
		
     //while(curr2 != null)
		 do
          {
            if(union.search(curr2.data) == null)
              {
               union.insertInOrder(curr2.data);
              }
            curr2 = curr2.next;
  		    }		
		while(curr2 != this.head);
		return union;
	}
		// YOUR CODE HERE

	
	
	public CDLL_List<T> inter( CDLL_List<T> other )
	{
		CDLL_List<T> inter = new CDLL_List<T>();

      CDLL_Node<T> curr = other.head;
      CDLL_Node<T> curr2 = this.head;
      CDLL_Node<T> curr3 = inter.head;
      //while(curr != null && curr.next != null)
		  do
          {
            if(this.contains(curr.data))
              {
              inter.insertAtTail(curr.data);             
              }
            curr = curr.next;
          }	
          while(curr != other.head);		  
		return inter;
	}
		// YOUR CODE HERE

		
	
	public CDLL_List<T> diff( CDLL_List<T> other )
	{
		CDLL_List<T> diff = new CDLL_List<T>();

        CDLL_Node<T> curr = other.head;
        CDLL_Node<T> curr2 = this.head;	
     // while(curr2 != null && curr.data != null)
		 do
           {
            if(other.search(curr2.data) == null && curr2.data != null)
              {
               diff.insertAtTail(curr2.data);
               
              }
              curr2 = curr2.next;
           }  
		   while(curr2 != this.head);
		return diff;
	}
		// YOUR CODE HERE

		
	
	public CDLL_List<T> xor( CDLL_List<T> other )
	{
		return this.union(other).diff(this.inter(other));  // REPLACE WITH YOUR CODE 
	}

} //END CDLL_LIST CLASS

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
    this. prev = prev;
    this.next = next;
  }
 
  public String toString()
  {
	  return ""+ this.data;
  } 
	 
} //EOF

// A D D   C D L L  N O D E   C L A S S  D O W N   H E R E 
// R E M O V E  A L L  P U B L I C  &  P R I V A T E (except toString)
// R E M O V E  S E T T E R S  &  G E T T E R S 
// M A K E  T O  S T R I N G  P U B L I C