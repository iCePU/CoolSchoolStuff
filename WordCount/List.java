//ok this file has some bugs that havent been crushed
public class List<Type>{
    public static final int MAX_SIZE = 50; //why limit yourself
    private Node<Type> head;
    private Node<Type> tail;
    private Node<Type> curr;
    private int num_items;

    // constructor
    // remember that an empty list has a "size" of -1 and its "position" is 1
    public List(){
         //null everything
         this.head = null;
         this.tail = null;
         this.curr = null;
         this.num_items = 0;
    }

    // copy constructor
    // clones the list l and sets the last element as the current
    public List(List<Type> l){
        Node<Type> n = l.head;
        this.head = this.tail = this.curr = null;
        this.num_items = 0;

        while (n != null){
            this.InsertAfter(n.getData());
            n = n.getLink();

        }
    }

    // navigates to the beginning of the list
    public void First(){
         this.curr = this.head;
    }

    // navigates to the end of the list
    // the end of the list is at the last valid item in the list
    public void Last(){
         this.curr = this.tail;
    }

    // navigates to the specified element (0-index)
    // this should not be possible for an empty list
    // this should not be possible for invalid positions
    public void SetPos(int pos){
         if(this.IsEmpty()||(pos<0 || pos>this.num_items)){
              ;//this basically takes the not of the condition
         }else{
             this.First();
             for(int element = 0; element<pos; element ++){
                  this.Next();
             }
        }
    }

    // navigates to the previous element
    // this should not be possible for an empty list
    // there should be no wrap-around
    public void Prev(){
        if(this.curr == null){
             this.First();
        }else{
             Node<Type> newNode = this.curr;
             this.First();
             //go to the first of the list and iterate till youre right before the last current
             while(this.curr != null){
                  if(this.curr.getLink() == newNode){
                       break;
                  }

                  this.Next();
             }
        }

    }


    // navigates to the next element
    // this should not be possible for an empty list
    // there should be no wrap-around
    public void Next(){
         if(this.curr == null){
              return;
         }
         //check if a null pointer gets you
         if(this.curr.getLink() != null){
             this.curr = this.curr.getLink();
        }
    }

    // returns the location of the current element (or -1)
    public int GetPos(){
         if(this.IsEmpty()){
              return -1;
         }
         //iterate through till you get to the node you where on
         Node<Type> comp = this.curr;
         int counter = 0;
         this.First();
         while(!(this.curr == comp)){
              counter = counter + 1;
              this.Next();
              if(counter >MAX_SIZE){
                   System.out.println("something happened");
                   break;
              }
         }
         return counter;
    }

    // returns the value of the current element (or null)
    public Type GetValue(){
         //null check
         if(this.curr!=null){
             return this.curr.getData();
         }
         return null;
    }

    // returns the size of the list
    // size does not imply capacity
    public int GetSize(){
        return this.num_items;
        //assumes that num_items is correct the other algorithm I was using was failing on me
    }

    // inserts an item before the current element
    // the new element becomes the current
    // this should not be possible for a full list
    //some of this code was adapted from Brennan Forrest git repository
    public void InsertBefore(Type data){
         if(this.num_items < MAX_SIZE){
              Node<Type> newNode = new Node<>();
              newNode.setData(data);

              //checks to see if the head exists(like if adding to a new list)
              if(this.head ==null){
                   this.head = newNode;
                   this.First();
                   this.num_items = 1;

               //if adding to a list thats 1 big
              }else if(this.tail == null){
                   System.out.println(data.toString());
                   Node<Type> tmpHead = this.curr;
                   tmpHead.setLink(null);
                   this.tail = tmpHead;
                   newNode.setLink(this.tail);
                   this.head = newNode;
                   this.First();
                   this.num_items = this.num_items + 1;

               //adding to the begining of a list
              }else if (this.curr == this.head){
                   newNode.setLink(this.head);
                   this.head = newNode;
                   this.First();
                   this.num_items = this.num_items + 1;

               //if in the middle of a list
              }else{
                   this.Prev();
                   this.InsertAfter(data);
                   this.num_items = this.num_items + 1;

              }
         }
    }

    // inserts an item after the current element
    // the new element becomes the current
    // this should not be possible for a full list
    public void InsertAfter(Type data){
         if(this.num_items < MAX_SIZE){
              Node<Type> newNode = new Node<>();
              newNode.setData(data);

              //if the list is fresh
              if(this.head == null){
                   this.head = newNode;
                   this.First();

               //insert at a list that only has a head
              }else if(this.tail == null){
                   this.tail=newNode;
                   this.head.setLink(this.tail);
                   this.Last();
               //insert at end
              }else if(this.curr == this.tail){
                   this.tail.setLink(newNode);
                   newNode.setLink(null);
                   this.tail = newNode;
                   this.Last();

               //insert in middle of list
              }else{
                   Node<Type> current = this.curr;
                   this.Next();
                   Node<Type> nextNode = this.curr;
                   current.setLink(newNode);
                   newNode.setLink(nextNode);
                   this.curr = newNode;

              }

              this.num_items = this.num_items + 1;
         }
    }

    // removes the current element
    // this should not be possible for an empty list
    public void Remove(){
         //check existance
         if(this.IsEmpty()){
              return;
         }

         //exists
         if(this.curr == null){
              return;
         }

         //if at begining
         if(this.head == this.curr){
              Node<Type> newNode = this.curr.getLink();
              //at the preultimum of a list
              if(newNode != null && newNode == this.tail){
                   System.out.println("remove "+ this.curr.getData().toString());
                   this.head = this.tail;
                   this.tail = null;
                   this.head.setLink(null);
                   this.num_items = this.num_items - 1;
                   this.First();

               //if the list is 1 big
              }else if(newNode == null){
                   this.head = null;
                   this.curr = null;
                   this.tail = null;
                   this.num_items = 0;

               //if removing the head
              }else{
                   this.head = this.head.getLink();
                   this.num_items = this.num_items - 1;
                   this.First();

              }
          //if at the end of a list
         }else if(this.tail== this.curr){
              this.Prev();
              //if the list is one big
              if(this.curr != null && this.curr == this.head){

                   this.head = null;
                   this.curr = null;
                   this.tail = null;
                   this.num_items = 0;
                   //remove the tail
              }else{

                   this.tail = this.curr;
                   this.tail.setLink(null);
                   this.num_items = this.num_items - 1;
                   this.Last();
              }

          // in the middle of the list
         }else{
             Node<Type> nextNode = this.curr.getLink();
             this.Prev();
             this.curr.setLink(nextNode);
             this.num_items = this.num_items - 1;
             this.Next();
        }
    }

    // replaces the value of the current element with the specified value
    // this should not be possible for an empty list
    public void Replace(Type data){
         if(this.curr!=null){
              //null check
             this.curr.setData(data);
        }
    }

    // returns if the list is empty
    public boolean IsEmpty(){
         if(this.head == null && this.tail == null && this.curr == null){
              return true;
         }
         return false;
    }

    // returns if the list is full
    public boolean IsFull(){
         if(this.GetSize()>= this.MAX_SIZE){
              return true;
         }
         return false;
    }

    // returns if two lists are equal (by value)
    public boolean Equals(List<Type> l){
         //assumes num_items is correct
         if(this.num_items != l.num_items){
              return false;
         }
         //check each list element by element for equivalence
         for(int x = 0; x < this.num_items;x++){
              if(this.curr!=l.curr){
                   return false;
              }
              this.Next();
              l.Next();
         }
         return true;
   }

    // returns the concatenation of two lists
    // l should not be modified
    // l should be concatenated to the end of *this
    // the returned list should not exceed MAX_SIZE elements
    // the last element of the new list is the current
    // take the tail of one and point it to the head of a copy of the other
    public List<Type> Add(List<Type> l){
         if(this==null){
              return l;
         }
         List<Type> bothLists = new List<>(this);
         if(this.tail== null){
              return l;
         }
         bothLists.tail.setLink(l.head);
         bothLists.tail = l.tail;
         bothLists.num_items = this.num_items + l.num_items;
         bothLists.Last();
         return this;
    }

    // returns a string representation of the entire list (e.g., 1 2 3 4 5)
    // the string "NULL" should be returned for an empty list
    public String toString(){
         //sanity check
         if(this.num_items <1){
              return "null";
         }
         this.First();
         String output= "";
         //goes through element by element and type casts it to a string
         while(!(this.curr == this.tail)&&this.curr!=null){
              //System.out.println("in to String");
              try{
                   output = output +" "+ this.curr.getData().toString();
                   this.Next();
              }catch(NullPointerException e){
                   break;
              }
         }
         output = output + " "+ this.tail.getData().toString();
         return output;
    }
}
