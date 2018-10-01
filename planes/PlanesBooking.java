package io;
import java.nio.file.*;
import java.io.IOException;
import java.lang.String;
import java.io.InputStreamReader;
import java.util.*;
import java.io.BufferedReader;


public class PlanesBooking{
     public static List<Plane> arrayOfPlanes = new ArrayList<Plane>();

     public static void main(String args[])throws IOException{
          BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
          while(1==1){
               System.out.println("What would you like to do?");
               System.out.println("1) Add Passenger");
               System.out.println("2) List Planes");
               System.out.println("3) List Passengers");
               System.out.println("4) Food Count");
               System.out.println("5) Find Passenger");
               System.out.println("6) End Program");

               String command = reader.readLine();

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

                         Passenger tmpPerson = new Passenger(name,foodPref);
                         for(int element =0;element <PlanesBooking.arrayOfPlanes.size();element++){
                               if(PlanesBooking.arrayOfPlanes.get(element).getPlaneNumber() == pNumber && PlanesBooking.arrayOfPlanes.get(element).isSeatBooked(rowNum,columnNum)){
                                    if(!PlanesBooking.arrayOfPlanes.get(element).getHasMeal() && tmpPerson.getFoodPreferance()=="none"){
                                         tmpPerson.setFoodPreferance("snack");
                                    }
                                    PlanesBooking.arrayOfPlanes.get(element).Bookings[rowNum][columnNum] = tmpPerson;
                               }
                         }
                    }

                    //list planes
                    if(command.charAt(x)=='2'){
                         for(int element = 0; element <PlanesBooking.arrayOfPlanes.size(); element++){
                              String pNumber = Integer.toString(PlanesBooking.arrayOfPlanes.get(element).getNumOnPlane());
                              String dayNum = Integer.toString(PlanesBooking.arrayOfPlanes.get(element).getDayOfTravel());
                              String amountPeople = Integer.toString(PlanesBooking.arrayOfPlanes.get(element).numPassengers());
                              String isFull = "";
                              if(!(PlanesBooking.arrayOfPlanes.get(element)==null)){
                                   isFull = " this plane is full";
                              }
                              System.out.println(pNumber + " " + dayNum + " " + amountPeople + " " + isFull);
                         }
                    }

                    if(command.charAt(x)=='3'){
                         System.out.println("What plane do you want to see?");
                         int pNumber = Integer.valueOf(reader.readLine().trim());
                         for(int element = 0;element < PlanesBooking.arrayOfPlanes.size();element++){
                              if(PlanesBooking.arrayOfPlanes.get(element).getNumOnPlane()==pNumber){
                                   System.out.println(PlanesBooking.arrayOfPlanes.get(element).manifest());
                              }
                         }

                    }

                    if(command.charAt(x)=='4'){
                         int[] tmp;
                         System.out.println("What plane do you want to see?");
                         int pNumber = Integer.valueOf(reader.readLine().trim());
                         for(int element = 0;element < PlanesBooking.arrayOfPlanes.size();element++){
                              if(PlanesBooking.arrayOfPlanes.get(element).getNumOnPlane()==pNumber){
                                   tmp = PlanesBooking.arrayOfPlanes.get(element).numOfFoodPreferences();
                                   System.out.println("Chicken "+Integer.toString(tmp[0])+ " Pasta "+Integer.toString(tmp[0]) + " Special "+Integer.toString(tmp[0]));
                              }
                         }
                    }

                    if(command.charAt(x)=='5'){
                         System.out.println("What plane do you want to see?");
                         int pNumber = Integer.valueOf(reader.readLine().trim());
                         for(int element = 0;element < PlanesBooking.arrayOfPlanes.size();element++){
                              if(PlanesBooking.arrayOfPlanes.get(element).getNumOnPlane()==pNumber){
                                   String name = reader.readLine();
                                   name = name.trim();
                                   int[] tmpList = PlanesBooking.arrayOfPlanes.get(element).findPassenger(name);
                                   System.out.println("Row :" + Integer.valueOf(tmpList[0]) + " Column :" + Integer.valueOf(tmpList[1]));
                              }
                         }
                    }

                    if(command.charAt(x)=='6'){
                         System.exit(0);
                    }
               }
          }
     }
     public void readPlaneFile(String fileName){
          try{
               String dataFromFile = "";
               dataFromFile = new String(Files.readAllBytes(Paths.get(fileName)));
          }catch(IOException e){
               System.out.println("reading file and this happend:" + e.getMessage());
               String dataFromFile;
               dataFromFile = "";
          }finally{

               //iterate through the file until empty
               String dataFromFile;
               dataFromFile = "";
               boolean inFile = true;
               int lastIndex = 0;
               int currentIndex = 0;
               String newline = System.getProperty("line.separator");
               currentIndex = dataFromFile.indexOf(newline,lastIndex);

               while(inFile == true){
                    currentIndex = dataFromFile.indexOf(newline,lastIndex);
                    if (currentIndex == -1){
                         inFile = false;
                         continue;
                    }
                    boolean hasMeal;
                    String tmpSubString = dataFromFile.substring(lastIndex,currentIndex);
                    List<String> tmpData = Arrays.asList(tmpSubString.split(","));
                    if(tmpData.get(3)=="no"){
                         hasMeal = false;
                    }else{
                         hasMeal = true;
                    }
                    //put in try catch
                    try{
                    Plane tmpPlane = new Plane(Integer.valueOf(tmpData.get(0)),tmpData.get(1),Integer.valueOf(tmpData.get(2)),hasMeal,Integer.valueOf(tmpData.get(4)),Integer.valueOf(tmpData.get(5)));
                    arrayOfPlanes.add(tmpPlane);
                    }finally{
		    	System.out.println("error in processing a plane's data");
		    }
               }
          }
     }

     public void readPassengerFile(String fileName)throws IOException{
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
               String newline = System.getProperty("line.separator");
               currentIndex = dataFromFile.indexOf(newline,lastIndex);

               //in file
               while(inFile == true){
                    currentIndex = dataFromFile.indexOf(newline,lastIndex);
                    if (currentIndex == -1){
                         inFile = false;
                         continue;
                    }

                    String tmpSubString = dataFromFile.substring(lastIndex,currentIndex);
                    List<String> tmpData = Arrays.asList(tmpSubString.split(","));
                    int planeNumber = Integer.valueOf(tmpData.get(0));
                    int row = Integer.valueOf(tmpData.get(3));
                    int column = Integer.valueOf(tmpData.get(4));
                    Passenger tmpPerson = new Passenger(tmpData.get(1),tmpData.get(2));
                    //matches passenger to plane
                    for(int element =0;element<PlanesBooking.arrayOfPlanes.size();element++){
                         if(PlanesBooking.arrayOfPlanes.get(element).getPlaneNumber() == planeNumber){
                              if(!PlanesBooking.arrayOfPlanes.get(element).isSeatBooked(row,column)){
                                   if(!PlanesBooking.arrayOfPlanes.get(element).getHasMeal() && tmpPerson.getFoodPreferance()=="none"){
                                        tmpPerson.setFoodPreferance("snack");
                                   }
                                   PlanesBooking.arrayOfPlanes.get(element).Bookings[row][column]=tmpPerson;
                                   //this may not actually set the elements properties to tmp prerson but rather set the local variables properties to tmp person which would be exactly as dumb as I expect java to be
                                   break;
                              }else if(PlanesBooking.arrayOfPlanes.get(element).setSeats(tmpPerson)<0){
                                   System.out.println("We tried booking you to flight number" + tmpData.get(0));
                                   //use the data you've already itterated through
                                   for(int x = element; x <PlanesBooking.arrayOfPlanes.size();x++){
                                        //shows all planes going to the same destination, seats and asks for user input
                                        if(PlanesBooking.arrayOfPlanes.get(x).getDestination() == PlanesBooking.arrayOfPlanes.get(element).getDestination() && !PlanesBooking.arrayOfPlanes.get(x).isFull()){
                                             List<int[]> seats = new ArrayList<int[]>();
                                             seats = PlanesBooking.arrayOfPlanes.get(x).availableSeats();
                                             String strSeats = "";
                                             for(int index =0; index<seats.size();index++){
                                                  strSeats = strSeats + Integer.toString(seats.get(index)[0])+Integer.toString(seats.get(index)[1])+newline;
                                             }
                                             System.out.println("Theses seats are available");
                                             System.out.println(strSeats);
                                             System.out.println("enter two number seperated by a comma <0,0> that correspond to the seat that you want");

                                             boolean escape = false;
                                             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                                             while(!escape){
                                                  try{
                                                       String command = reader.readLine();
                                                       List<String> extremeTmpData = Arrays.asList(command.split(","));
                                                       if(!(PlanesBooking.arrayOfPlanes.get(x).isSeatBooked(Integer.valueOf(extremeTmpData.get(0)),Integer.valueOf(extremeTmpData.get(1))))){
                                                            if(!PlanesBooking.arrayOfPlanes.get(element).getHasMeal() && tmpPerson.getFoodPreferance()=="none"){
                                                                 tmpPerson.setFoodPreferance("snack");
                                                            }
                                                            PlanesBooking.arrayOfPlanes.get(x).Bookings[Integer.valueOf(extremeTmpData.get(0))][Integer.valueOf(extremeTmpData.get(1))] = tmpPerson;
                                                            escape =false;
                                                       }else{
                                                            System.out.println("you entered the index of a seat that wasn't available, try again");
                                                       }
                                                  }finally{
                                                            System.out.println("Something happend that shouldn't have.");
                                                  }
                                             }
                                             x = PlanesBooking.arrayOfPlanes.size();
                                        }
                                   }
                              }
                         }
                    }
               }
          }
     }
}

class Plane{

     private int PlaneNumber;
     private int DayOfTravel;
     private int NumOnPlane;
     private int MaxSeats;
     private String Destination;
     private boolean HasMeal;
     public Passenger[][] Bookings;

     Plane(int planeNumber,String destination, int dayOfTravel,boolean hasMeal, int rows, int numPerRow){
          this.PlaneNumber = PlaneNumber;
          this.DayOfTravel =dayOfTravel;
          this.HasMeal = hasMeal;
          this.Destination = destination;
     }

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
          return this.MaxSeats;
     }

     String getDestination(){
          return this.Destination;
     }

     boolean getHasMeal(){
          return this.HasMeal;
     }

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

     int[] numOfFoodPreferences(){
          int[] foods = {0,0,0,0,0}; //chicken,pasta,special,snack,none

          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    if(this.Bookings[row][column].getFoodPreferance()=="chicken"){
                         foods[0]++;
                    }

                    if(this.Bookings[row][column].getFoodPreferance()=="pasta"){
                         foods[1]++;
                    }

                    if(this.Bookings[row][column].getFoodPreferance()=="special"){
                         foods[2]++;
                    }

                    if(this.Bookings[row][column].getFoodPreferance()=="snack"){
                         foods[3]++;
                    }

                    if(this.Bookings[row][column].getFoodPreferance()=="none"){
                         foods[4]++;
                    }
               }
          }
          return foods;
     }

     String manifest(){
          String tmp = "";
          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    if(!(this.Bookings[row][column]==null)){
                         tmp = tmp + this.Bookings[row][column] + System.getProperty("line.separator");
                    }
               }
          }
          return tmp;
     }

     int[] findPassenger(String timmy){
          int[] rowColumn ={0,0};
          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    if(this.Bookings[row][column].getName()==timmy){
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

     boolean isSeatBooked(int row, int column){
          if(this.Bookings[row][column]==null){
               return false;
          }
          return true;
     }
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

class Passenger{

     private String name;
     private String FoodPreferance;

     Passenger(String name, String FoodPreferance){
          this.setName(name);
          this.setFoodPreferance(FoodPreferance);

     }

     void setName(String name){
          if(!(name== null)){
               this.name = name;
          }
          else{
               this.name="empty";
          }
     }

     void setFoodPreferance(String FoodPreferance){
          if(FoodPreferance == "chicken" || FoodPreferance == "pasta"||FoodPreferance=="special"||FoodPreferance=="snack"){
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
