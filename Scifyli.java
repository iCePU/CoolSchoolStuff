import java.io.*;
import java.util.*;

public class Scifyli{

     public static void main(String[] args){

          IOArray = readFromFile();
          ArrayList<Book> IOArray = new ArrayList<Book>();
          //get  data from the user
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
               Book glob = new Book()
               snarf.add()
          }
     }
}
s
//a struct to hold data together
class Book{
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
          return tmp;
     }
}
