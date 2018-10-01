//package io;
import java.nio.file.*;
import java.io.IOException;
import java.lang.String;
import java.io.InputStreamReader;
import java.util.*;
import java.io.BufferedReader;

//main class used to initiate the other two planes and passengers
public class PlanesBooking{

     //main data structure of the program contains an arrayList of the Planes class
     public static List<Plane> arrayOfPlanes = new ArrayList<Plane>();

     //main class Has File IO exceptions but are handled java just wants a hand to hold <3
     public static void main(String args[])throws IOException{

          //reads the Input files
          PlanesBooking.readPlaneFile("planes.txt");
          PlanesBooking.readPassengerFile("bookings.txt");

          //takes in data from the user
          BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
          while(1==1){
               //main menu
               System.out.println("What would you like to do?");
               System.out.println("1) Add Passenger");
               System.out.println("2) List Planes");
               System.out.println("3) List Passengers");
               System.out.println("4) Food Count");
               System.out.println("5) Find Passenger");
               System.out.println("6) End Program");

               // read data from user
               String command = reader.readLine();
               System.out.println(command);

               for(int x = 0;x < command.length(); x++){

                    //add passenger
                    if(command.charAt(x)=='1'){
                         System.out.println("enter the number of the plane");
                         int pNumber = Integer.valueOf(reader.readLine().trim()); //will through an error

                         System.out.println("enter the name the first name of the passenger");
                         String name = reader.readLine().trim();

                         System.out.println("enter the food preference");
                         String foodPref = reader.readLine().trim();

                         System.out.println("enter the row of the seat");
                         int rowNum = Integer.valueOf(reader.readLine().trim()); //will through an error

                         System.out.println("enter the column of the seat");
                         int columnNum = Integer.valueOf(reader.readLine().trim()); //will through an error

                         //breaking it down the first part took in the passenger data and the second part stores the data
                         Passenger tmpPerson = new Passenger(name,foodPref);
                         for(int element =0;element <PlanesBooking.arrayOfPlanes.size();element++){
                               if(PlanesBooking.arrayOfPlanes.get(element).getPlaneNumber() == pNumber && PlanesBooking.arrayOfPlanes.get(element).isSeatBooked(rowNum,columnNum)){
                                    if(!PlanesBooking.arrayOfPlanes.get(element).getHasMeal() && tmpPerson.getFoodPreferance()=="none"){
                                         tmpPerson.setFoodPreferance("snack");
                                    }
                                    //ok so the setter for seats has an auto booking method which doesn't take in int arguments, however the input is checked in the line above so its cool; trust
                                    PlanesBooking.arrayOfPlanes.get(element).Bookings[rowNum][columnNum] = tmpPerson;
                               }
                         }
                    }

                    //list planes
                    if(command.charAt(x)=='2'){
                         for(int element = 0; element <PlanesBooking.arrayOfPlanes.size(); element++){
                              String pNumber = Integer.toString(PlanesBooking.arrayOfPlanes.get(element).getPlaneNumber());
                              String dayNum = Integer.toString(PlanesBooking.arrayOfPlanes.get(element).getDayOfTravel());
                              String isFull = "";

                              //TODO Theres a function called get number on the plane that does absolutely nothing except make my life hard, get rid of it
                              if((PlanesBooking.arrayOfPlanes.get(element).getMaxSeats()-PlanesBooking.arrayOfPlanes.get(element).numPassengers())==0){
                                   isFull = " this plane is full";
                              }
                              //output to user
                              System.out.println(pNumber + " " + dayNum + " " + isFull);
                         }
                    }

                    //list the passengers
                    if(command.charAt(x)=='3'){
                         System.out.println("What plane do you want to see?");
                         int pNumber = Integer.valueOf(reader.readLine().trim());
                         System.out.println(Integer.toString(pNumber));

                         for(int element = 0;element < PlanesBooking.arrayOfPlanes.size();element++){
                              if(PlanesBooking.arrayOfPlanes.get(element).getPlaneNumber()==pNumber){
                                   System.out.println(PlanesBooking.arrayOfPlanes.get(element).manifest());
                                   //manifest gives a string that has newlines in it, ^_^
                              }
                         }

                    }

                    //shows a histogram of whos eating who
                    if(command.charAt(x)=='4'){
                         int[] tmp;
                         System.out.println("What plane do you want to see?");
                         int pNumber = Integer.valueOf(reader.readLine().trim());

                         //iterate through arrayOfPlanes
                         for(int element = 0;element < PlanesBooking.arrayOfPlanes.size();element++){
                              if(PlanesBooking.arrayOfPlanes.get(element).getPlaneNumber()==pNumber){
                                   tmp = PlanesBooking.arrayOfPlanes.get(element).numOfFoodPreferences();
                                   System.out.println("Chicken "+Integer.toString(tmp[0])+ " Pasta "+Integer.toString(tmp[1]) + " Special "+Integer.toString(tmp[2]));
                              }
                         }
                    }

                    //Finds passenger on a speciffic plane TODO expand function to every database
                    if(command.charAt(x)=='5'){
                         //take in user data
                         System.out.println("What plane do you want to see?");
                         int pNumber = Integer.valueOf(reader.readLine().trim());

                         //iterate through array of planes
                         for(int element = 0;element < PlanesBooking.arrayOfPlanes.size();element++){
                              if(PlanesBooking.arrayOfPlanes.get(element).getPlaneNumber()==pNumber){
                                   //take in more user data
                                   System.out.println("enter a name:");
                                   String name = reader.readLine();
                                   name = name.trim();

                                   int[] tmpList = PlanesBooking.arrayOfPlanes.get(element).findPassenger(name);
                                   System.out.println("Row :" + Integer.valueOf(tmpList[0]) + " Column :" + Integer.valueOf(tmpList[1]));
                              }
                         }
                    }

                    //quit the program
                    if(command.charAt(x)=='6'){
                         System.out.println(" ");
                         System.exit(0);
                    }
               }
          }
     }

     //First crazy file IO, I blame java for the obscene length of this function
     public static void readPlaneFile(String fileName){
          //lets take this slow; try catch jjust incase the file is mia
          String dataFromFile = "";
          try{
               dataFromFile = new String(Files.readAllBytes(Paths.get(fileName)));
          }catch(IOException e){
               System.out.println("reading file and this happend:" + e.getMessage());
               dataFromFile = "";
          }finally{

               //iterate through the file until empty

               boolean inFile = true;
               int lastIndex = 0;
               int currentIndex = 0;
               String newline = System.getProperty("line.separator");;
               currentIndex = dataFromFile.indexOf(newline,lastIndex);
               lastIndex = currentIndex;
               //ok classic algorithm, parses the file by leap frogging newline characters
               while(inFile == true){
                    currentIndex = dataFromFile.indexOf(newline,lastIndex+1);

                    //checks to see if you're at the end of the file
                    if (currentIndex == -1){
                         inFile = false;
                         break;
                    }
                    //have to init hear because scope and java
                    boolean hasMeal;

                    try{
                         String tmpSubString = dataFromFile.substring(lastIndex,currentIndex);
                         List<String> tmpData = Arrays.asList(tmpSubString.split(","));
                         //tmpSubString is the meat of the file, it has all the data in it

                         //setting the bools cause java hast type casting :(
                         if(tmpData.get(3).trim()=="no"){
                              hasMeal = false;

                         }else{
                              hasMeal = true;
                         }

                         //probably could have spaced out the init but no amount of spacing will make this garbage look beautiful
                         Plane tmpPlane = new Plane(Integer.valueOf(tmpData.get(0).trim()),tmpData.get(1),Integer.valueOf(tmpData.get(2).trim()),hasMeal,Integer.valueOf(tmpData.get(4).trim()),Integer.valueOf(tmpData.get(5).trim()));
                         //I have a lot of problems with the way java handles try catch finally statments; it'll run through the block, execute the instruction THEN handle
                         //the error, that would be fine if the code in the block reverted or had any kind of containment
                         //anyway it works and im sad about it
                         PlanesBooking.arrayOfPlanes.add(tmpPlane);

                    }catch(ArrayIndexOutOfBoundsException e){
                         inFile=false;
                         continue;
                         //happens when the data is malformed
                    }finally{
                         //used in debugging, a lot
                    }

                    lastIndex = currentIndex;     //leaping           weeeeeeeeeee
               }
          }
     }

     //This is a travesty, but its my travesty
     //reads and writes the passnger file/data
     public static void readPassengerFile(String fileName)throws IOException{
          String dataFromFile = "";

          //again makes sure the file is a thing
          try{
               dataFromFile = new String(Files.readAllBytes(Paths.get(fileName)));
          }catch(IOException e){
               System.out.println("reading file and this happend:" + e.getMessage());
               dataFromFile = "";
          }finally{

               //iterate through the file until empty
               boolean inFile = true;
               int lastIndex = 0;
               int currentIndex = 0;
               String newline = "\n";
               currentIndex = dataFromFile.indexOf(newline,lastIndex);
               lastIndex = currentIndex;

               //in file(same leap frog algorithm)
               while(inFile == true){
                    currentIndex = dataFromFile.indexOf(newline,lastIndex+1);

                    //sees if the file is over
                    if (currentIndex == -1){
                         inFile = false;
                         break;
                    }

                    //initializations I thought you know what would be great? spacing out all the passenger initialization
                    String tmpSubString = dataFromFile.substring(lastIndex,currentIndex);
                    List<String> tmpData = Arrays.asList(tmpSubString.split(","));
                    int planeNumber = Integer.valueOf(tmpData.get(0).trim());
                    int row = Integer.valueOf(tmpData.get(3).trim());
                    int column = Integer.valueOf(tmpData.get(4).trim());

                    //inits passenger
                    Passenger tmpPerson = new Passenger(tmpData.get(1),tmpData.get(2));

                    //matches passenger to plane
                    for(int element =0;element<PlanesBooking.arrayOfPlanes.size();element++){
                         //System.out.println(Integer.toString(element));
                         if(PlanesBooking.arrayOfPlanes.get(element).getPlaneNumber() == planeNumber){
                              if(!(PlanesBooking.arrayOfPlanes.get(element).isSeatBooked(row,column))){
                                   if(!PlanesBooking.arrayOfPlanes.get(element).getHasMeal() && tmpPerson.getFoodPreferance()=="none"){
                                        tmpPerson.setFoodPreferance("snack");
                                   }

                                   //litterally the worst io handling but makes the garbage input file tollerable
                                   if(row >= PlanesBooking.arrayOfPlanes.get(element).Bookings.length){
                                        break;
                                   }
                                   if(column >= PlanesBooking.arrayOfPlanes.get(element).Bookings[0].length){
                                        break;
                                   }

                                   //making Bookings Public was an amazing idea I think it was supposed to be private but I didn't like that
                                   PlanesBooking.arrayOfPlanes.get(element).Bookings[row][column]=tmpPerson;
                                   //this may not actually set the elements properties to tmp prerson but rather set the local variables properties to tmp person which would be exactly as dumb as I expect java to be
                                   break;

                              }else if(PlanesBooking.arrayOfPlanes.get(element).setSeats(tmpPerson)<0){
                                   System.out.println("We tried booking you to flight number" + tmpData.get(0));

                                   //use the data you've already iterated through
                                   for(int x = element; x <PlanesBooking.arrayOfPlanes.size();x++){
                                        //shows all planes going to the same destination, seats and asks for user input

                                        if(PlanesBooking.arrayOfPlanes.get(x).getDestination() == PlanesBooking.arrayOfPlanes.get(element).getDestination() && !PlanesBooking.arrayOfPlanes.get(x).isFull()){
                                             List<int[]> seats = new ArrayList<int[]>();
                                             seats = PlanesBooking.arrayOfPlanes.get(x).availableSeats();
                                             String strSeats = "";

                                             //get the available seats
                                             for(int index =0; index<seats.size();index++){
                                                  strSeats = strSeats + Integer.toString(seats.get(index)[0])+Integer.toString(seats.get(index)[1])+newline;
                                             }

                                             System.out.println("Theses seats are available");
                                             System.out.println(strSeats);
                                             System.out.println("enter two number seperated by a comma <0,0> that correspond to the seat that you want");

                                             boolean escape = false;
                                             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                                             //super robust IO handling
                                             while(!escape){
                                                  try{
                                                       String command = reader.readLine();
                                                       List<String> extremeTmpData = Arrays.asList(command.split(","));

                                                       //take in the data and parse it
                                                       if(!(PlanesBooking.arrayOfPlanes.get(x).isSeatBooked(Integer.valueOf(extremeTmpData.get(0)),Integer.valueOf(extremeTmpData.get(1))))){

                                                            //sets the data makes the preference snack when nescisary
                                                            if(!PlanesBooking.arrayOfPlanes.get(element).getHasMeal() && tmpPerson.getFoodPreferance()=="none"){
                                                                 tmpPerson.setFoodPreferance("snack");
                                                            }

                                                            //set the data
                                                            PlanesBooking.arrayOfPlanes.get(x).Bookings[Integer.valueOf(extremeTmpData.get(0))][Integer.valueOf(extremeTmpData.get(1))] = tmpPerson;
                                                            escape = true;

                                                       }else{
                                                            //incase stupid happeneds
                                                            System.out.println("you entered the index of a seat that wasn't available, try again");
                                                       }

                                                  //incase the world ends
                                                  }finally{
                                                            System.out.println("Something happend that shouldn't have.");
                                                  }
                                             }
                                             //sneaky way of not dealing with java's continue bs
                                             x = PlanesBooking.arrayOfPlanes.size();
                                        }
                                   }
                              }
                         }
                    }
                    lastIndex = currentIndex; //leap
               }
          }
     }
}

//Planes class for all your plane needs, store data on planes and has functions to handle that data
class Plane{

     private int PlaneNumber;
     private int DayOfTravel;
     private int NumOnPlane;
     private int MaxSeats;
     private String Destination;
     private boolean HasMeal;
     public Passenger[][] Bookings;

     //constructor
     Plane(int planeNumber,String destination, int dayOfTravel,boolean hasMeal, int rows, int numPerRow){
          this.PlaneNumber = planeNumber;
          this.DayOfTravel =dayOfTravel;
          this.HasMeal = hasMeal;
          this.Destination = destination;
          this.Bookings = new Passenger[rows][numPerRow];
     }

//setter/getter block
     int getPlaneNumber(){
          return this.PlaneNumber;
     }

     int getDayOfTravel(){
          return this.DayOfTravel;
     }

     int getNumOnPlane(){
          return this.NumOnPlane;
     }

     int getMaxSeats(){
          return this.Bookings.length * this.Bookings[0].length;
     }

     String getDestination(){
          return this.Destination;
     }

     boolean getHasMeal(){
          return this.HasMeal;
     }

//auto assigns the passenger
     int setSeats(Passenger timmy){
          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    if(this.Bookings[row][column]==null){
                         this.Bookings[row][column] = timmy;
                         return 1;
                    }
               }
          }
          return -1;
     }

     void setHasMeal(boolean booly){
          this.HasMeal = booly;
     }

//FUCKING .equals() != == spent litterally hours on this bug at like 3 in the morning
//gives a histogram of what people eat
     int[] numOfFoodPreferences(){
          int[] foods = {0,0,0,0,0}; //chicken,pasta,special,snack,none

          //iterates through the bookings
          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    if(this.Bookings[row][column]==null){
                         continue;
                         //checking existance
                    }

                    //catagorizes the inputs
                    if(this.Bookings[row][column].getFoodPreferance().equals("chicken")){
                         foods[0]=foods[0] + 1;
                         System.out.println(Integer.toString(column));
                    }

                    if(this.Bookings[row][column].getFoodPreferance().equals("pasta")){
                         foods[1]=foods[1] + 1;
                    }

                    if(this.Bookings[row][column].getFoodPreferance().equals("special")){
                         foods[2]=foods[2] + 1;
                    }

                    if(this.Bookings[row][column].getFoodPreferance().equals("snack")){
                         foods[3]=foods[3] + 1;
                    }

                    if(this.Bookings[row][column].getFoodPreferance().equals("none")){
                         foods[4]=foods[4] + 1;
                    }
               }
          }
          return foods;
     }

//returns a string of names seperated by newline characters
     String manifest(){
          String tmp = "";
          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    //existance checking
                    if(!(this.Bookings[row][column]==null)){
                         tmp = tmp + this.Bookings[row][column].getName() + System.getProperty("line.separator");
                    }
               }
          }
          return tmp;
     }

//finds a passenger in a plane
     int[] findPassenger(String timmy){
          int[] rowColumn ={0,0};
          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    if(this.Bookings[row][column] == null){
                         continue;
                    }
                    if(this.Bookings[row][column].getName().equals(timmy)){
                         rowColumn = new int[] {row,column};
                    }
               }
          }
          return rowColumn;
     }

     //does not count number of snakes on the plane
     int numPassengers(){
          int num=0;
          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    if(!(this.Bookings[row][column]==null)){
                         num++;
                    }
               }
          }
          return num;
     }

//sees if the array Bookings has any null elements
     boolean isFull(){
          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    if(this.Bookings[row][column]==null){
                         return false;
                    }
               }
          }
          return true;
     }

//checks the existance/occupancy of a seat
     boolean isSeatBooked(int row, int column){
          //System.out.println(Integer.toString(row)+" "+Integer.toString(column)+ Integer.toString(this.PlaneNumber));
          if(row>=this.Bookings.length){
               return false;

          }else if(column>=this.Bookings[0].length){
               return false;
          }

          if(this.Bookings[row][column]==null){
               return false;
          }
          return true;
     }

//shows the seats not taken
     List<int[]> availableSeats(){
          List<int[]> seats = new ArrayList<int[]>();

          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    if(this.Bookings[row][column]==null){
                         int[] tmp = {row,column};
                         seats.add(tmp);
                    }
               }
          }
          return seats;
     }
}

//Passenger class holds name and food preference
//my roommate pointed out that I mispelled preference the day i was done with this so its staying
class Passenger{

     private String name;
     private String FoodPreferance;

     //init with no default
     Passenger(String name, String FoodPreferance){
          this.setName(name);
          this.setFoodPreferance(FoodPreferance);
     }

     //special name setter
     void setName(String name){
          if(!(name== null)){
               this.name = name;
          }
          else{
               this.name="empty";
          }
     }

//again java sucks
     void setFoodPreferance(String FoodPreferance){

          FoodPreferance = FoodPreferance.trim();
          this.FoodPreferance = FoodPreferance;

          if(FoodPreferance.equals("chicken")){
               this.FoodPreferance = FoodPreferance;

          }else if(FoodPreferance.equals("pasta")){
               this.FoodPreferance = FoodPreferance;

          }else if(FoodPreferance.equals("special")){
               this.FoodPreferance = FoodPreferance;

          }else if(FoodPreferance.equals("snack")){
               this.FoodPreferance = FoodPreferance;

          }else{
               this.FoodPreferance = "none";
          }
     }

     String getName(){
          return this.name;
     }

     String getFoodPreferance(){
          return this.FoodPreferance;
     }
}
//wow
