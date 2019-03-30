import java.util.*;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.file.*;
import java.lang.String;


public class WordCount{
     public static void main(String[] args){
          String fileName = "test.txt";
          String dataFromFile = "";

          //file io
          try{
               dataFromFile = new String(Files.readAllBytes(Paths.get(fileName)));
          }catch(IOException e){
               System.out.println("reading file and this happend:" + e.getMessage());
               dataFromFile = "";
          }finally{
               String[] allDaWords = dataFromFile.split(" ");
               int totalWords = allDaWords.length;
               ArrayList<ArrayList<String>> histogramz = new ArrayList <ArrayList<String>>();
               String tmp = allDaWords[0];

               //itterates through list and edits a 2d array so that the size of the inner  array correlates to the amount of times a word is used in a paragraph
               for(int element =0;element<allDaWords.length;element++ ){
                    ArrayList<String> tmpValue = new ArrayList<String>();
                    tmpValue.add(allDaWords[element]);
                    boolean flag = true;
                    //checks through the existing words
                    for(int index = 0; index < histogramz.size();index++){
                         if(allDaWords[element].compareToIgnoreCase(histogramz.get(index).get(0))==0){
                              histogramz.get(index).add(new String("one"));
                              flag = false;
                              continue;
                         }
                    }
                    //if none of the words where used then adds a new value to the arraylist
                    if(flag){
                         histogramz.add(tmpValue);
                    }
               }

               //alphebetical sort (using lambda cause its easier)
               histogramz.sort((b,a)->b.get(0).compareToIgnoreCase(a.get(0)));
               String alphebetical ="";
               int  counter =0;
               for(int element = 0; element < histogramz.size();element++ ){
                    counter = counter +1;
                    alphebetical = alphebetical + histogramz.get(element).get(0)+" ";
               }

               //sorting by most used takes the size as the comparison
               histogramz.sort((b,a)->Integer.compare(a.size(),b.size()));
               String swaggin ="";
               for(int element = 0; element < histogramz.size();element++ ){
                    swaggin = swaggin + histogramz.get(element).get(0)+" "+Integer.toString(histogramz.get(element).size())+" ";
               }

               double creativity = (double)counter/totalWords;
               System.out.println(swaggin);
               System.out.println(creativity);

               //saves the output to a file crys if it doesnt have write permissions
               try{
                    PrintWriter out = new PrintWriter(new FileWriter("OutFile.txt"));
                    out.println(swaggin+" "+Double.toString(creativity));
                    out.close();
               }catch(IOException e){
                    System.out.println("no permissions :(");
               }
          }
     }
}
