import java.nio.file.*;
import java.io.IOException;
import java.lang.string;
import java.io.InputStreamReader;

public class planesBooking{

}
class Plane{

     private int PlaneNumber;
     private int DayOfTravel;
     private int NumOnPlane;
     private int MaxSeats;
     private String Destination;
     private boolean HasMeal;
     private Passenger[][] Bookings;

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
