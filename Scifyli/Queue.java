//Landon Rawson
import java.util.*;
public class Queue<Type> extends List<Type> {
    private Type data;
    public void enqueue(Type data) {
        if(super.IsFull()){
            throw new IndexOutOfBoundsException("Stack is full");
        }
        super.InsertBefore(data);
    }
    public Type dequeue() {
        if (super.IsEmpty())
            throw new NoSuchElementException("No items in Stack");
        int curr = super.GetPos();
        super.Last();
        data = super.GetValue();
        super.Remove();
        super.SetPos(curr - 1);
        return data;
    }
}
