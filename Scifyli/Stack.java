//Landon Rawson
import java.util.*;
public class Stack<Type> extends List<Type> {
     private Type data;
     public Type peek() {
          if(super.IsEmpty()){
               return null;
          }
          data = super.GetValue();
          return data;
     }

     public void push(Type i){
          super.InsertAfter(i);
     }

     public Type pop(){
        if( super.IsEmpty()){
             return null;
        }
        
        data = super.GetValue();
        super.Remove();
        return data;
   }
}
