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

                    if(command.charAt(x)=='1'){

                    }

                    if(command.charAt(x)=='2'){

                    }

                    if(command.charAt(x)=='3'){

                    }

                    if(command.charAt(x)=='4'){

                    }

                    if(command.charAt(x)=='5'){

                    }

                    if(command.charAt(x)=='6'){

                    }
               }
          }
     }
     public String[] readPlaneFile(String fileName){
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
                    Plane tmpPlane = new Plane(Integer.valueOf(tmpData[0]),tmpData[1],Integer.valueOf(tmpData[2]),hasMeal,Integer.valueOf(tmpData[4]),Integer.valueOf(tmpData[5]));
                    arrayOfPlanes.add(Plane);
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

     String[] manifest(){
          String[] listOPeople;
          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    for(int x = 0;x < listOPeople.length;x++){
                         if((this.Bookings[row][column].name).compareToIgnoreCase(listOPeople[x])>1){
                              continue;
                         }
                         else{
                              //second time used in the program
                              String[] copyDB = new String[listOPeople.length+1];
                              System.arraycopy(listOPeople,0,copyDB,0,(x-1));
                              copyDB[x] = this.Bookings[row][column].name + Integer.toString(row)+" "+Integer.toString(column);
                              System.arraycopy(listOPeople,x,copyDB,x+1,(listOPeople.length-x));
                         }
                    }
               }
          }
          return listOPeople;
     }

     int[] findPassenger(Passenger timmy){
          int[] rowColumn ={0,0};
          for(int row =0; row<this.Bookings.length;row++){
               for(int column =0; column<this.Bookings[0].length;column++){
                    if(this.Bookings[row][column]==timmy){
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
