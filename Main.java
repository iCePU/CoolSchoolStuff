package io;
import java.util.concurrent.ThreadLocalRandom;
import java.nio.file.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;
import java.lang.String;
import java.util.Arrays;
//Made by Lewis Johnson
//this program takes in a file of student names and allows the user to select various
//options that interact with the data
public class Main{

     //main loop for the ui
     public static void Main(String args[])throws IOException{
          School UMeanie;
          UMeanie = new School();

          //using buffer reader as perstandard
          BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
          BufferedReader nestedReader = new BufferedReader(new InputStreamReader(System.in));

          while(1==1){
               //main menu out
               System.out.println("Enter the number corresponding to the option you want:");
               System.out.println("1) Add a student.");
               System.out.println("2) Remove student.");
               System.out.println("3) Display roster.");
               System.out.println("4) Assign detention.");
               String command = reader.readLine();

               //used to parse user input
               for(int x = 0;x < command.length(); x++){
                    //add a student
                    if(command.charAt(x)=='1'){

                         System.out.println("Enter the first name:");
                         String first = nestedReader.readLine();
                         System.out.println("Enter the last name:");
                         String last = nestedReader.readLine();

                         if(UMeanie.addStudent(new Student(first,last))){
                              System.out.println("Name entered.");
                         }else{
                              System.out.println("Your name was not entered.");
                         }
                    }
                    //removing a student
                    if(command.charAt(x)=='2'){

                         System.out.println("Enter the first name:");
                         String first = nestedReader.readLine();
                         System.out.println("Enter the last name:");
                         String last = nestedReader.readLine();

                         if(UMeanie.removeStudent(new Student(first,last))){
                              System.out.println("Student removed");
                         }else{
                              System.out.println("Student Could not be removed from list");
                         }
                    }
                    //Display all students and those who have detention
                    if(command.charAt(x)=='3'){

                         System.out.println("Theses are the students currently in the school:");
                         Student[] tmpStudents = UMeanie.getRoster();

                         for(int var = 0; var < (tmpStudents.length-3);var++){
                              System.out.println("     "+tmpStudents[var].fullName());
                         }

                         System.out.println("These students have detention:");

                         for(int var = (tmpStudents.length-3); var < tmpStudents.length+1;var++){
                              System.out.println("     "+tmpStudents[var].fullName()+" "+tmpStudents[var].reason);
                         }
                    }
                    //randomly assigns detention
                    if(command.charAt(x)=='4'){

                         System.out.println("Reassigning Detention");
                         UMeanie.assignDetention();
                         System.out.println("These students have detention:");
                         Student[] tmpStudents = UMeanie.getRoster();

                         for(int var = tmpStudents.length-3; var < tmpStudents.length;var++){
                              System.out.println("     "+tmpStudents[var].fullName()+" "+tmpStudents[var].reason);
                         }

                    }
               }
          }
     }
}

//school class private variables: students,naughtyList,dataFromFile
class School{

     private Student[] students; //list of all the students
     private Student[] naughtyList; //detention list
     private String dataFromFile; //raw data from input file

     //main constructor
     School(){
          //uses paths to get path, convert into a string, memthods throws an IOException
          //by default thus the obnoxious try catch
          try{

               this.dataFromFile = "";
               this.dataFromFile = new String(Files.readAllBytes(Paths.get("students.txt")));
          }catch(IOException e){
               System.out.println("reading file and this happend:" + e.getMessage());
               String dataFromFile;
               this.dataFromFile = "";
          }finally{

               //iterate through the file until empty
               boolean inFile = true;
               int lastIndex = 0;
               int currentIndex = 0;
               int spaceIndex = 0;
               String newline = System.getProperty("line.separator");
               String tmpSubString;
               Student tmp;
               Student tmpArray[];

               while(inFile == true){
                    currentIndex = this.dataFromFile.indexOf(newline,lastIndex);
                    if (currentIndex == -1){
                         inFile = false;
                         continue;
                    }
                    //rip memalloc and all your glory, we make an array with length +1
                    //copy the elements over to it, set the last element to be the new one
                    //set the original variable to be the temporary Array
                    //this instead of increamenting the mememory allocated to the array by one and defining the new space as a variable
                    //this is done three times in the program
                    tmpSubString = this.dataFromFile.substring(lastIndex,currentIndex);
                    spaceIndex = tmpSubString.indexOf(' ');
                    tmp = new Student(tmpSubString.substring(0,spaceIndex-1),tmpSubString.substring(spaceIndex));
                    this.addStudent(tmp);
                    tmpArray = new Student[this.students.length +1];

               }
          }
     }

     //function that edits the private variable naughtyList and assigns it random elements from students
     public void assignDetention(){
          String[] reasons = {"was too cool for school","tried to learn durring class","turned in homework early","talked to the teacher when spoken to"};
          Student[] copyDB = this.students;

          //checks if the list has been made before its so that a random student wont have a reason when they don't have detention
          if(this.naughtyList[0]!=null){
               for(int x =0;x<this.naughtyList.length;x++){
                    this.naughtyList[x].reason = "";
               }
          }

          //assign detention
          for(int x = 0;x<3;x++){

               int randomIndex = ThreadLocalRandom.current().nextInt(0, copyDB.length);
               this.naughtyList[x] = this.students[randomIndex];
               this.naughtyList[x].reason = reasons[ThreadLocalRandom.current().nextInt(0,4)];
               this.removeStudent(copyDB[randomIndex]);

          }
     }

     //a bin search that uses alphabetical order as a comparison enter two strings returns int
     public int findStudent(String firstName, String lastName){

          //bin search using strings as  comparitor
          int index = 0;
          int iteration = 0;
          int maxIteration = (int)Math.round(Math.log(this.students.length/Math.log(2)));

          while(iteration < maxIteration){

               iteration = iteration + 1;
               //the logic might be backwards if so just swap > for <
               if((firstName+" "+lastName).compareToIgnoreCase(this.students[index].fullName())>0){
                    index = index + (int)Math.round(students.length/(Math.pow(2,iteration)));
               //same here but oposite
               }else if((firstName+" "+lastName).compareToIgnoreCase(this.students[index].fullName())<0){
                    index = index - (int)Math.round(students.length/(Math.pow(2,iteration)));

               }else if((firstName+" "+lastName).compareToIgnoreCase(this.students[index].fullName())==0){
                    return index;

               }
          }
          return -1;
     }

     //uses an insertion sort at the time of adding to dynamically sort the array
     //takes in Student type and returns a boolean
     public boolean addStudent(Student tmpStudent){
          //insertion sort(of)

          //could switch to bin search to order but at that point could use different datastructure
          for(int x = 0;x < this.students.length;x++){
               if((tmpStudent.firstName+tmpStudent.lastName).compareToIgnoreCase(this.students[x].firstName+this.students[x].lastName)>1){
                    continue;
               }else if((tmpStudent.firstName+tmpStudent.lastName).compareToIgnoreCase(this.students[x].firstName+this.students[x].lastName)==0){
                    return true;
               }else
               {
                    //second time used in the program
                    Student[] copyDB = new Student[this.students.length+1];
                    System.arraycopy(this.students,0,copyDB,0,(x-1));
                    copyDB[x] = tmpStudent;
                    System.arraycopy(this.students,x,copyDB,x+1,(this.students.length-x));
                    return true;
               }

          }
          return false;
     }

     //runs a findStudent routine then does array manips to remove
     //takes in Student type and returns bool
     public boolean removeStudent(Student tmpStudent){
          int index = this.findStudent(tmpStudent.firstName,tmpStudent.lastName);
          if(index!=-1){
               //third time used in the program
               Student[] copyDB = new Student[this.students.length-1];
               System.arraycopy(this.students,0,copyDB,0,index);
               copyDB[index] = tmpStudent;
               System.arraycopy(this.students,index+1,copyDB,index,(this.students.length-index-1));
               return true;
          }
          return false;
     }

     //returns a student array, acts like an accessor for both student list and naughtyList

     public Student[] getRoster(){
          Student tmpStudentArray[] = new Student[this.students.length+3];
          for(int x = 0; x < this.students.length;x++){
               System.arraycopy(this.students,0,tmpStudentArray,0,this.students.length);
          }
          for(int x = 0; x<3;x++){
               System.arraycopy(this.naughtyList,0,tmpStudentArray,this.students.length-1,3);
          }
          return tmpStudentArray;
     }
}

//Student class used to hold string data
class Student{

     public String firstName; //makes these more managable with public vars
     public String lastName;
     public String reason;

     //costructor, takes in two strings
     Student(String first,String last ){
          this.firstName = first;
          this.lastName = last;
          this.reason = "";
     }

     //returns string (accessor)
     public String getFirst(){
          if(firstName!= null){
               return this.firstName;
          }else{
               return "nully-nelly";
          }
     }

     //returns a string (accessor)
     public String getLast(){
          if(lastName!= null){
               return this.lastName;
          }else{
               return "nully-nelly";
          }
     }

     //string manips returns a concatonated string
     public String fullName(){
               return this.getFirst() +" "+this.getLast();
     }

     //string manips returns a concatonated string
     public String lastFirst(){
          return this.lastName+this.firstName;
     }
}
