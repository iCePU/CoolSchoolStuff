import java.lang.String;
import java.util.Scanner;

//infix to postfix program
//this takes in a string from the user and outputs the postfix expression
public class InfixToPostfix{
     public static void main(String args[]){

          //makes a new stack to hold opperators
          Stack opperandStack = new Stack();

          //takes in inpurt from the user
          Scanner scanzo = new Scanner(System.in);
          String preinfixExpression = scanzo.next();
          String postfixExpression = new String();

          //gets rid of nonsense
          preinfixExpression.replaceAll("\\s+","");

          //adds this to the end to trrigger the last opperators to be appen to the expression
          preinfixExpression = preinfixExpression + ")";

          //allows us to iterate through the infix expression
          char[] infixExpression = preinfixExpression.toCharArray();
          String tmp = new String();
          opperandStack.push("(");

          //infix to postfix conversion
          for(int element = 0; element<infixExpression.length;element++){
               tmp = String.valueOf(infixExpression[element]);

               //function that gives pemdas number and tells us if its an opperator or a variable
               int p = priority(tmp);

               // if its a variable
               if(p==0){
                    postfixExpression = postfixExpression + tmp;
                    continue;
               }

               //if its a left parenthiesis <- idk how to spell
               if(tmp.equals("(")){
                    opperandStack.push(tmp);
                    continue;
               }

               //if its an opperator
               if(p>0){
                    while(p>=priority(opperandStack.peak())){
                         if(priority(opperandStack.peak())==-1){
                              break;
                         }

                         postfixExpression = postfixExpression + opperandStack.pop();
                    }

                    opperandStack.push(tmp);
               }

               //if right parenthiesis
               if(tmp.equals(")")){
                    while(!opperandStack.peak().equals("(")){
                         System.out.println("its been a while");
                         postfixExpression = postfixExpression + opperandStack.pop();
                    }

                    //gets rid of the end parenthiesis
                    String throwingAwayMyLifeOnThisStupidAlgorithm = opperandStack.pop();
               }
          }

          //outputs the postfix
          System.out.println(postfixExpression);
     }

     //function to classify a single char string
     private static int priority(String var){
          if(var.equals("^")){
               return 3;
          }

          if(var.equals("*")){
               return 2;
          }

          if(var.equals("/")){
               return 2;
          }

          if(var.equals("+")){
               return 1;
          }

          if(var.equals("-")){
               return 1;
          }

          if(var.equals(")")){
               return -1;
          }

          if(var.equals("(")){
               return -1;
          }

          return 0;

     }
}

//base type for the stack class
class Node{
	private String data;
	private Node link;

	public Node(){
		this.data = null;
		this.link = null;
	}

	public String getData(){
		return this.data;
	}

	public void setData(String data){
		this.data = data;
	}

	public Node getLink(){
		return this.link;
	}

	public void setLink(Node link){
		this.link = link;
	}
}

//stackk, standard stack for all your stack needs
class Stack{
     private Node head;

     public Stack(){
          this.head = null;
     }

     public String peak(){
          if(this.head ==null){
               return " ";
          }

          return this.head.getData();
     }

     public String pop(){
          //if theres nothing in the list
          if(this.head == null){
               return " ";
          }

          //if theres one element in the list
          String tmp = this.head.getData();
          if(this.head.getLink()==null){
               this.head = null;
               return tmp;
          }

          //normal opperation
          this.head = this.head.getLink();
          return tmp;
     }

     public void push(String val){
          Node tmp = new Node();
          tmp.setData(val);

          //if there is no head
          if(head==null){
               this.head = tmp;

          }else{
               //standard opperations: proceed
               Node other = this.head;
               tmp.setLink(other);
               this.head = tmp;
          }
     }
}
