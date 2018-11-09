import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Scifyli{
     public static void main(String[] args){
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
               String in = reader.next();
               int i = Integer.parseInt(in);
               if(i==1){
                    System.out.println("enter the name of the title");
                    String in = reader.next();
                    ArrayList<Book> out = findByT(IOArray,in);
                    for(int element =0;element <out.size();element ++){
                         System.out.println(out.get(element));
                    }
               }

               if(i==2){
                    System.out.println("enter the name of the author");
                    String in = reader.next();
                    ArrayList<Book> out = findByA(IOArray,in);
                    for(int element =0;element <out.size();element ++){
                         System.out.println(out.get(element));
                    }
               }

               if(i==3){
                    ArrayList<Book> out = sortMeByT(IOArray,in);
                    for(int element =0;element <out.size();element ++){
                         System.out.println(out.get(element));
                    }
               }

               if(i==4){
                    ArrayList<Book> out = sortMeByA(IOArray,in);
                    for(int element =0;element <out.size();element ++){
                         System.out.println(out.get(element));
                    }
               }

               if(i==5){

               }

               if(i==6){

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

     //this is for returning an authors works
     public ArrayList<Book> findByA(ArrayList<Book> listings, String aurthor){
          ArrayList<Book> found= new ArrayList<Book>();
          for(int element = 0;element<listings.size();element ++){
               if(listings.get(element).author.compareToIgnoreCase(aurthor)){
                    found.add(listings.get(element));
               }
          }
          return found;
     }

     public ArrayList<Book> findByT(ArrayList<Book> listings, String title){
          ArrayList<Book> found= new ArrayList<Book>();
          for(int element = 0;element<listings.size();element ++){
               if(listings.get(element).author.compareToIgnoreCase(title)){
                    found.add(listings.get(element));
               }
          }
          return found;
     }

     //sorting function for author
     public ArrayList<Book> sortMeByA(ArrayList<Book> helpMe){
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
     public ArrayList<Book> sortMeByT(ArrayList<Book> helpMe){
          Collections.sort(helpMe, new Comparator<Book>(){
               public int compare(Book book1, Book book2){
                    return book1.title.compareToIgnoreCase(book2.title);
               }
          });
          return helpMe;
     }

     public ArrayList<Book> readFromFile()throws Exception{
          ArrayList<Book> snarf = new ArrayList<Book>();
          File inputList =  new File("test.txt");
          BufferedReader inputBuffer = new BufferedReader(new FileReader(inputList));
          String tmp;
          while((tmp=inputBuffer.readLine())!=null){
               String[] arrayString = tmp.split(",");
               Book glob = new Book(Integer.parseInt(arrayString[2]),Integer.parseInt(arrayString[3]),arrayString[0],Integer.parseInt(arrayString[1]));
               snarf.add(glob);
          }
          return snarf;
     }

     public void writeToFile(ArrayList<book> snarf)throws Exception{

     }
}

//a struct to hold data together
public class Book implements Comparable<Book>{
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

     String toString(){
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
