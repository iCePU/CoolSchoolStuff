//Landon Rawson
import java.util.*;
public class Queue<Type> extends List<Type> {
     private Type data;
     public void enqueue(Type data){
          super.First();
          while(super.current.getData().priority>data.priority){
               super.Next();
          }
          super.InsertBefore(data);
     }

    public Type dequeue() {
        if (super.IsEmpty()){
             return null;
        }
        int curr = super.GetPos();
        super.Last();
        data = super.GetValue();
        super.Remove();
        super.SetPos(curr - 1);
        return data;
    }
}
