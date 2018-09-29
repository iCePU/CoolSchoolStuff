import java.nio.file.*;
import java.io.IOException;
import java.lang.string;
import java.io.InputStreamReader;

public class planesBooking{
     List<Plane> arrayOfPlanes = new ArrayList<Plane>();

     public static void planesBooking(String args[]){
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
                         for(int element =0;element <planesBooking.arrayOfPlanes.size();element++){
                               if(planesBooking.arrayOfPlanes[element].getPlaneNumber() == pNumber && planesBooking.arrayOfPlanes[element].isSeatBooked(rowNum,columnNum)){
                                    if(!planesBooking.arrayOfPlanes[element].hasMeal() && tmpPerson.FoodPreferance=="none"){
                                         tmpPerson.FoodPreferance = "snack";
                                    }
                                    planesBooking.arrayOfPlanes[element].Bookings[rowNum][columnNum] = tmpPerson;
                               }
                         }
                    }

                    //list planes
                    if(command.charAt(x)=='2'){
                         for(int element = 0; element <planesBooking.arrayOfPlanes.size(); element++){
                              String pNumber = Integer.toString(planesBooking.arrayOfPlanes.getNumOnPlane());
                              String dayNum = Integer.toString(planesBooking.arrayOfPlanes.getDayOfTravel());
                              String amountPeople = Integer.toString(planesBooking.arrayOfPlanes.numPassengers());
                              String isFull = "";
                              if(planesBooking.arrayOfPlanes){
                                   isFull = " this plane is full";
                              }
                              System.out.println(pNumber + " " + dayNum + " " + amountPeople + " " + isFull);
                         }
                    }

                    if(command.charAt(x)=='3'){
                         System.out.println("What plane do you want to see?");
                         int pNumber = Integer.valueOf(reader.readLine().trim());
                         for(int element = 0;element < planesBooking.arrayOfPlanes.size();element++){
                              if(planesBooking.arrayOfPlanes[element].getNumOnPlane()==pNumber){
                                   System.out.println(planesBooking.arrayOfPlanes[element].manifest());
                              }
                         }

                    }

                    if(command.charAt(x)=='4'){
                         int[] tmp;
                         System.out.println("What plane do you want to see?");
                         int pNumber = Integer.valueOf(reader.readLine().trim());
                         for(int element = 0;element < planesBooking.arrayOfPlanes.size();element++){
                              if(planesBooking.arrayOfPlanes[element].getNumOnPlane()==pNumber){
                                   tmp = planesBooking.arrayOfPlanes[element].numOfFoodPreferences();
                                   System.out.println("Chicken "+Integer.toString(tmp[0])+ " pasta "+Integer.toString(tmp[0]) + " Special "+Integer.toString(tmp[0]));
                              }
                         }
                    }

                    if(command.charAt(x)=='5'){
                         System.out.println("What plane do you want to see?");
                         int pNumber = Integer.valueOf(reader.readLine().trim());
                         for(int element = 0;element < planesBooking.arrayOfPlanes.size();element++){
                              if(planesBooking.arrayOfPlanes[element].getNumOnPlane()==pNumber){
                                   String name = reader.readline().strip();
                                   int[] tmpList = planesBooking.arrayOfPlanes[element].findPassenger(name);
                                   System.out.println("Row :" + Integer.valueOf(tmpList[0].trim()) + " Column :" + Integer.valueOf(tmpList[1].trim()));
                              }
                         }
                    }

                    if(command.charAt(x)=='6'){
                         System.exit();
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
               this.dataFromFile = "";
          }finally{

               //iterate through the file until empty
               boolean inFile = true;
               int lastIndex = 0;
               int currentIndex = 0;
               String newline = System.getProperty("line.separator");
               currentIndex = this.dataFromFile.indexOf(newline,lastIndex);

               while(inFile == true){
                    currentIndex = this.dataFromFile.indexOf(newline,lastIndex);
                    if (currentIndex == -1){
                         inFile = false;
                         continue;
                    }

                    tmpSubString = this.dataFromFile.substring(lastIndex,currentIndex);
                    List<String> tmpData = Arrays.asList(tmpSubString.split(","));
                    if(tmpData[3]=="no"){
                         boolean hasMeal = false;
                    }else{
                         boolean hasMeal = true;
                    }
                    //put in try catch
                    try{
                    Plane tmpPlane = new Plane(Integer.valueOf(tmpData[0]),tmpData[1],Integer.valueOf(tmpData[2]),hasMeal,Integer.valueOf(tmpData[4]),Integer.valueOf(tmpData[5]));
                    arrayOfPlanes.add(Plane);
                    }
               }
          }
     }

     public void readPassengerFile(String fileName){
          try{
               String dataFromFile = "";
               dataFromFile = new String(Files.readAllBytes(Paths.get(fileName)));
          }catch(IOException e){
               System.out.println("reading file and this happend:" + e.getMessage());
               String dataFromFile;
               this.dataFromFile = "";
          }finally{

               //iterate through the file until empty
               boolean inFile = true;
               int lastIndex = 0;
               int currentIndex = 0;
               String newline = System.getProperty("line.separator");
               currentIndex = this.dataFromFile.indexOf(newline,lastIndex);

               while(inFile == true){
                    currentIndex = this.dataFromFile.indexOf(newline,lastIndex);
                    if (currentIndex == -1){
                         inFile = false;
                         continue;
                    }

                    tmpSubString = this.dataFromFile.substring(lastIndex,currentIndex);
                    List<String> tmpData = Arrays.asList(tmpSubString.split(","));
                    int planeNumber = Integer.valueOf(tmpData[0]);
                    int row = Integer.valueOf(tmpData[3]);
                    int column = Integer.valueOf(tmpData[4]);
                    Passenger tmpPerson = new Passenger(tmpData[1],tmpData[2]);

                    for(int element =0;element<planesBooking.arrayOfPlanes.size();element++){
                         if(planesBooking.arrayOfPlanes[element].PlaneNumber ==planeNumber){
                              if(!planesBooking.arrayOfPlanes[element].isSeatBooked(row,column)){
                                   if(!planesBooking.arrayOfPlanes[element].hasMeal() && tmpPerson.FoodPreferance=="none"){
                                        tmpPerson.FoodPreferance = "snack";
                                   }
                                   planesBooking.arrayOfPlanes[element].Bookings[row][column]=tmpPerson;
                                   break;
                              }else if(planesBooking.arrayOfPlanes[element].setSeats(tmpPerson)<0){
                                   System.out.println("We tried booking you to flight number" + tmpData[0]);
                                   //use the data you've already itterated through
                                   for(int x =element; x <planesBooking.arrayOfPlanes.size();x++){
                                   }
                                   if(planesBooking.arrayOfPlanes[x].getDestination == planesBooking.arrayOfPlanes[element].getDestination() && !planesBooking.arrayOfPlanes[x].isFull()){
                                        List<int> seats = planesBooking.arrayOfPlanes[x].availableSeats();
                                        String strSeats = "";
                                        for(int index =0; index<seats.size();index++){
                                             strSeats = strSeats + Integer.toString(seats[index][0])+Integer.toString(seats[index][0])+newline;
                                        }
                                        System.out.println("Theses seats are available");
                                        System.out.println(strSeats);
                                        System.out.println("enter two number seperated by a comma <0,0> that correspond to the seat that you want");

                                        escape = false;
                                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                                        while(!escape){
                                             try{
                                                  String command = reader.readLine();
                                                  List<String> extremeTmpData = Arrays.asList(command.split(","));
                                                  if(!planesBooking.arrayOfPlanes[x].isSeatBooked(Integer.valueOf(extremeTmpData[0]),Integer.valueOf(extremeTmpData[1]))){
                                                       if(!planesBooking.arrayOfPlanes[element].hasMeal() && tmpPerson.FoodPreferance=="none"){
                                                            tmpPerson.FoodPreferance = "snack";
                                                       }
                                                       planesBooking.arrayOfPlanes[x].Bookings[Integer.valueOf(extremeTmpData[0])][Integer.valueOf(extremeTmpData[1])] = tmpPerson;
                                                       break;
                                                  }else{
                                                       System.out.println("you entered the index of a seat that wasn't available, try again");
                                                  }finally{
                                                       System.out.println("Something happend that shouldn't have.");
                                                  }
                                             }
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
     private Passenger[][] Bookings;

     Plane(int planeNumber,String destination, int dayOfTravel,boolean hasMeal, int rows, int numPerRow){
          this.PlaneNumber = PlaneNumber;
          this.DayOfTravel =dayOfTravel;
          this.HasMeal = hasMeal;
          this.Bookings = new int[rows][numPerRow];
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

     string getDestination(){
          return this.Destination;
     }

     boolean getHasMeal(){
          return this.HasMeal;
     }

     int setSeats(Passenger timmy){
          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    if(this.Bookings[row][column]==null){
                         this.Bookings[rows][column] = timmy;
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
          int[] foods = {0,0,0,0,0} //chicken,pasta,special,snack,none

          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    if(this.Bookings[row][column].FoodPreferance=="chicken"){
                         foods[0]++;
                    }

                    if(this.Bookings[row][column].FoodPreferance=="pasta"){
                         foods[1]++;
                    }

                    if(this.Bookings[row][column].FoodPreferance=="special"){
                         foods[2]++;
                    }

                    if(this.Bookings[row][column].FoodPreferance=="snack"){
                         foods[3]++;
                    }

                    if(this.Bookings[row][column].FoodPreferance=="none"){
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
                    if(this.Bookings[row][column]!=null){
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
                    if(this.Bookings[row][column].name==timmy){
                         rowColumn = {row,column};
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
                    if(this.Bookings[row][column]=!null){
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
     List<Integer> availableSeats(){
          List<Integer> seats = new ArrayList<Integer>();
          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    if(this.Bookings[row][column]==null){
                         seats.add({row,column});
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
          if(name=! null){
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
