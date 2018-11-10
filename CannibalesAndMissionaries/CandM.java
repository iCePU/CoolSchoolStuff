import java.util.*;
import java.lang.String;

//main part of the program
public class CandM{
     public static void main(String args[]){
          State start = new State();
          boolean solution = start.solve();
          System.out.println(start.toString());
     }
}

//Data Structure design (its effectively a tree but its cool we dont know that yet)
class State{
     public int numMR;
     public int numCR;
     public int numML;
     public int numCL;
     public Boolean side;
     public ArrayList<State> substates;
     public ArrayList<State> prevStates;
     public int numOfSS;

     //since we're only dealing with one case it'll default to what the assignment says
     State(){
          this.numMR = 0;
          this.numML = 3;
          this.numCR = 0;
          this.numCL = 3;
          this.side = false;
          this.substates = new ArrayList<State>();
          this.prevStates = new ArrayList<State>();
          this.numOfSS = 0;
     }

     //finds legal moves and appends them to the current state
     void branch(){
          //right side
          if(this.side){
               //case when one missionary goes from right to left
               if((this.numMR-1>=this.numCR||this.numMR-1==0)&&this.numMR-1>=0){
                    State newState = new State();
                    newState.numML = this.numML+1;
                    newState.numMR = this.numMR-1;
                    newState.numCL = this.numCL;
                    newState.numCR = this.numCR;
                    newState.side = !this.side;
                    newState.prevStates = this.prevStates;
                    newState.prevStates.add(this);
                    this.substates.add(newState);
                    this.numOfSS ++;
               }

               //case when one cannibal goes from right to left
               if((this.numML>=this.numCL+1||this.numML==0)&&this.numCR-1>=0){
                    State newState = new State();
                    newState.numCL = this.numCL+1;
                    newState.numCR = this.numCR-1;
                    newState.numML = this.numML;
                    newState.numMR = this.numMR;
                    newState.side = !this.side;
                    newState.prevStates = this.prevStates;
                    newState.prevStates.add(this);
                    this.substates.add(newState);
                    this.numOfSS ++;
               }
               //case when two missionarys go from right to left
               if((this.numMR-2>=this.numCR||this.numMR-2==0)&&this.numMR-2>=0){
                    State newState = new State();
                    newState.numML = this.numML+2;
                    newState.numMR = this.numMR-2;
                    newState.numCL = this.numCL;
                    newState.numCR = this.numCR;
                    newState.side = !this.side;
                    newState.prevStates = this.prevStates;
                    newState.prevStates.add(this);
                    this.substates.add(newState);
                    this.numOfSS ++;
               }

               //case when two cannibals go from right to left
               if((this.numML>=this.numCL+2||this.numML==0)&&this.numCR-2>=0){
                    State newState = new State();
                    newState.numCL = this.numCL+2;
                    newState.numCR = this.numCR-2;
                    newState.numML = this.numML;
                    newState.numMR = this.numMR;
                    newState.side = !this.side;
                    newState.prevStates = this.prevStates;
                    newState.prevStates.add(this);
                    this.substates.add(newState);
                    this.numOfSS ++;
               }

               //case when one missionary and one cannibal go from right to left
               if(this.numMR-1>=0&&this.numCR-1>=0){
                    State newState = new State();
                    newState.numCL = this.numCL+1;
                    newState.numCR = this.numCR-1;
                    newState.numML = this.numML+1;
                    newState.numMR = this.numMR-1;
                    newState.side = !this.side;
                    newState.prevStates = this.prevStates;
                    newState.prevStates.add(this);
                    this.substates.add(newState);
                    this.numOfSS ++;
               }
          }else{
               //left side
               //when one missionary goes from left to right
               if((this.numML-1>=this.numCL||this.numML-1==0)&&this.numML-1>=0){
                    State newState = new State();
                    newState.numMR = this.numMR+1;
                    newState.numML = this.numML-1;
                    newState.numCL = this.numCL;
                    newState.numCR = this.numCR;
                    newState.side = !this.side;
                    newState.prevStates = this.prevStates;
                    newState.prevStates.add(this);
                    this.substates.add(newState);
                    this.numOfSS ++;
               }

               //when one cannibal goes from left to right
               if((this.numMR>=this.numCR+1||this.numMR==0)&&this.numCL-1>=0){
                    State newState = new State();
                    newState.numML = this.numML;
                    newState.numMR = this.numMR;
                    newState.numCL = this.numCL-1;
                    newState.numCR = this.numCR+1;
                    newState.side = !this.side;
                    newState.prevStates = this.prevStates;
                    newState.prevStates.add(this);
                    this.substates.add(newState);
                    this.numOfSS ++;
               }

               //when two missionarys go from left to right
               if((this.numML-2>=this.numCL||this.numML-2 ==0)&&this.numML-2>=0){
                    State newState = new State();
                    newState.numMR = this.numMR+2;
                    newState.numML = this.numML-2;
                    newState.numCR = this.numCR;
                    newState.numCL = this.numCL;
                    newState.side = !this.side;
                    newState.prevStates = this.prevStates;
                    newState.prevStates.add(this);
                    this.substates.add(newState);
                    this.numOfSS ++;
               }

               //case when two cannibals go from left to right
               if((this.numMR>=this.numCR+2||this.numMR==0)&&this.numCL-2>=0){
                    State newState = new State();
                    newState.numCR = this.numCR+2;
                    newState.numCL = this.numCL-2;
                    newState.numMR = this.numMR;
                    newState.numML = this.numML;
                    newState.side = !this.side;
                    newState.prevStates = this.prevStates;
                    newState.prevStates.add(this);
                    this.substates.add(newState);
                    this.numOfSS ++;
               }

               //case when one missionary and one cannibal go from left to right
               if(this.numML-1>=0&&this.numCL-1>=0){
                    State newState = new State();
                    newState.numCR = this.numCR+1;
                    newState.numCL = this.numCL-1;
                    newState.numMR = this.numMR+1;
                    newState.numML = this.numML-1;
                    newState.side = !this.side;
                    newState.prevStates = this.prevStates;
                    newState.prevStates.add(this);
                    this.substates.add(newState);
                    this.numOfSS ++;
               }
          }

     }

     //could reduce to one line but I like to be able to understand whats happening
     public String toString(){
          String out = new String();
          String m = "M";
          String c = "C";

          for(int x = 0;x<this.numMR;x++){
               out = out+m;
          }

          for(int x = 0;x<this.numCR;x++){
               out = out+c;
          }

          out = out + "/_/_/";

          for(int x = 0;x<this.numML;x++){
               out = out+m;
          }

          for(int x = 0;x<this.numCL;x++){
               out = out+c;
          }

          return out;
     }

     public boolean isEqual(State comparez){
          if(this.numML == comparez.numML&&this.numCL == comparez.numCL&&this.numMR == comparez.numMR&&this.numCR == comparez.numCR&&this.side==comparez.side){
               return true;
          }

          return false;
     }

     public boolean solve(){
          //does all the legal branches that the node can do
          this.branch();

          for(int x = 0; x<this.numOfSS;x++){
               boolean flag = false;
               State tmpState = this.substates.get(x);

               //checking if tmpState is equal to a previous state
               //this adds to the complexity slightly but allows us to prune n-cycle recursion loops
               for(int y =0;y<tmpState.prevStates.size();y++){
                    if(tmpState.isEqual(tmpState.prevStates.get(y))){
                         flag = true;
                         break;
                         //basically skips a leaf if the leaf is the same as a prior node
                    }
               }

               if(flag){
                    //part of skipping cyclical leafs
                    continue;
               }

               //checks to see if its the final state
               if(tmpState.numML == 0&&tmpState.numMR==3&&tmpState.numCL==0&&tmpState.numCR ==3){
                    System.out.println(tmpState.toString());
                    return true;

               //if not the final state and new continue the recursion
               }else{
                    boolean tmpBool =tmpState.solve();
                    if(tmpBool){
                         System.out.println(tmpState.toString());
                         return true;
                    }
               }
          }

          return false;
     }
}
