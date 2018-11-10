import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Scifyli{
     public static void main(String[] args)throws Exception{
          ArrayList<Book> IOArray = readFromFile();
          Scanner reader = new Scanner(System.in);
          System.out.println("Welcome to the library at the end of the universe! how may this system help you?");
          while(1==1){
               System.out.println("1) search by title");
               System.out.println("2) search by author");
               System.out.println("3) view all books title");
               System.out.println("4) view all books author");
               System.out.println("5) end of the day?");
               System.out.println("6) theres a fire!!!AHAHAHAHHASDDFHASDFHASDFHASDFHASDFHASDFa");
               System.out.println("7) toggle the checked in value of a book");
               String in = reader.next();
               int i = Integer.parseInt(in);
               if(i==1){
                    System.out.println("enter the title");
                    in = reader.next();
                    in += reader.nextLine();
                    ArrayList<Book> out = findByT(IOArray,in);
                    for(int element =0;element <out.size();element ++){
                         System.out.println(out.get(element));
                    }
               }

               if(i==2){
                    System.out.println("enter the name of the author");
                    in = reader.next();
                    ArrayList<Book> out = findByA(IOArray,in);
                    for(int element =0;element <out.size();element ++){
                         System.out.println(out.get(element));
                    }
               }

               if(i==3){
                    ArrayList<Book> out = sortMeByT(IOArray);
                    for(int element =0;element <out.size();element ++){
                         System.out.println(out.get(element));
                    }
               }

               if(i==4){
                    ArrayList<Book> out = sortMeByA(IOArray);
                    for(int element =0;element <out.size();element ++){
                         System.out.println(out.get(element));
                    }
               }

               if(i==5){
                    Stack<Book> endPile = new Stack<Book>();
                    for(int element = 0;element<IOArray.size();element ++){
                         if(IOArray.get(element).checkedOut == 0){
                              endPile.push(IOArray.get(element));
                         }
                    }
                    while(endPile.peek() != null){
                         Book aBook = endPile.pop();
                         for(int element = 0;element<IOArray.size();element ++){
                              if(IOArray.get(element).title.compareToIgnoreCase(aBook.title)==1){
                                   Book tmp = IOArray.get(element);
                                   if(tmp.checkedOut ==0){
                                        tmp.checkedOut = 1;
                                   }else{
                                        tmp.checkedOut= 0;
                                   }
                                   IOArray.set(element,tmp);
                              }
                         }
                    }
               }

               if(i==6){
                    PriorityQueue<Book> tmpQueue = new PriorityQueue<Book>();
                    for(int element = 0;element<IOArray.size();element ++){
                         tmpQueue.add(IOArray.get(element));
                    }
                    Iterator itr = tmpQueue.iterator();
                    while(itr.hasNext()){
                         System.out.println(itr.next());
                    }
               }

               if(i==7){
                    System.out.println("enter the title");
                    String tmpString = reader.next();
                    for(int element = 0;element<IOArray.size();element ++){
                         if(IOArray.get(element).title.compareToIgnoreCase(tmpString)==0){
                              Book tmp = IOArray.get(element);
                              if(tmp.checkedOut ==0){
                                   tmp.checkedOut = 1;
                              }else{
                                   tmp.checkedOut= 0;
                              }
                              IOArray.set(element,tmp);

                         }
                    }
               }
               //return list
               //tell if the book is out or not
               //search order list by author or title
               //edit chechin var
               //save to file
               //return proccess, given a list insert into list
               //covert to priority list
          }
     }
     //this is for returning an authors works
     public static ArrayList<Book> findByA(ArrayList<Book> listings, String author){
          ArrayList<Book> found= new ArrayList<Book>();
          for(int element = 0;element<listings.size();element ++){
               if(listings.get(element).author.compareToIgnoreCase(author)==0){
                    found.add(listings.get(element));
               }
          }
          return found;
     }

     public static ArrayList<Book> findByT(ArrayList<Book> listings, String title){
          ArrayList<Book> found= new ArrayList<Book>();
          for(int element = 0;element<listings.size();element ++){
               if(listings.get(element).title.compareToIgnoreCase(title)==0){
                    found.add(listings.get(element));
               }
          }
          return found;
     }

     //sorting function for author
     public static ArrayList<Book> sortMeByA(ArrayList<Book> helpMe){
          //lol cause lambda expressions don't exist apparently
          //using an anon inner class we can compare the authors of two books
          //and sort based on that
          Collections.sort(helpMe, new Comparator<Book>(){
               public int compare(Book book1, Book book2){
                    return book1.author.compareToIgnoreCase(book2.author);
               }
          });
          return helpMe;
     }

     //same code as above but for titles
     public static ArrayList<Book> sortMeByT(ArrayList<Book> helpMe){
          Collections.sort(helpMe, new Comparator<Book>(){
               public int compare(Book book1, Book book2){
                    return book1.title.compareToIgnoreCase(book2.title);
               }
          });
          return helpMe;
     }

     public static ArrayList<Book> readFromFile()throws Exception{
          ArrayList<Book> snarf = new ArrayList<Book>();
          File inputList =  new File("test.txt");
          BufferedReader inputBuffer = new BufferedReader(new FileReader(inputList));
          String tmp;
          while((tmp=inputBuffer.readLine())!=null){
               String[] arrayString = tmp.split(",");
               Book glob = new Book(Integer.parseInt(arrayString[2].replaceAll("\\s","")),Integer.parseInt(arrayString[3].replaceAll("\\s","")),arrayString[0],arrayString[1]);
               snarf.add(glob);
          }
          return snarf;
     }

     public void writeToFile(ArrayList<Book> snarf)throws Exception{

     }
}

//a struct to hold data together
class Book implements Comparable<Book>{
     public int priority   = 0;
     public int checkedOut = 0;
     public String title   = "";
     public String author = "";

     Book(int pri, int check,String title, String author){
          this.priority = pri;
          this.checkedOut = check;
          this.title = title;
          this.author = author;
     }

     public String toString(){
          String tmp ="";
          tmp = this.title+" "+this.author;
          if(this.checkedOut==0){
               tmp = tmp + " " + "checked out";
          }else{
               tmp = tmp + " " +"is not checked out";
          }
          return tmp;
     }
     @Override
     public int compareTo(Book other) {
          if(this.priority>other.priority){
               return 1;
          }else if(this.priority==other.priority){
               return 0;
          }else{
               return -1;
          }
     }
}
