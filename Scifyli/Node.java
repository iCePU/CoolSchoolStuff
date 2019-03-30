/* ***************************************************
 * Landon Rawson
 * 10-4-18
 * Node.java
 *
 * A node class
 *************************************************** */
// the Node class
class Node<Type>{
    private Type data;
    private Node<Type> link;

    // constructor
    public Node(){
        this.data = null;
        this.link = null;
    }

    // accessor and mutator for the data component
    public Type getData(){
        return this.data;
    }

    public void setData(Type data){
        this.data = data;
    }

    // accessor and mutator for the link component
    public Node<Type> getLink(){
        return this.link;
    }

    public void setLink(Node<Type> link){
        this.link = link;
    }
}
