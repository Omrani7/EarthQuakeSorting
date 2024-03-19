
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        /*ArrayList<QuakeEntry> deepQuakes = sortByLargestDepth(list);
        for (QuakeEntry qe: deepQuakes) { 
            System.out.println(qe);}
        */
        //ArrayList<QuakeEntry> list2 = sortByLargestDepth(list);
        //for (QuakeEntry qe: list2) { 
            // System.out.println(qe);}
              sortByMagnitudeWithBubbleSortWithCheck(list);
             // sortByMagnitudeWithCheck(list);
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    public int  getLargestDepth(ArrayList<QuakeEntry> qe, int from){
       int index = from ;
       for(int i = from+1; i < qe.size();i++){
       if(qe.get(i).getDepth()>qe.get(index).getDepth()){
       index = i;}
       
      }
      return index ;
       
       }
       public ArrayList<QuakeEntry> sortByLargestDepth(ArrayList<QuakeEntry> in ){
      ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
       QuakeEntry maxQuake = null;
       int index = 0;
    
      /*QuakeEntry max = largest(in);
      in.remove(max);
    out.add(max);*/
    for(int i =0;i<50;i++){
         index = getLargestDepth(in,i);
         maxQuake = in.get(index);
    out.add(maxQuake);
    in.remove(maxQuake);
    }
    
    
       
    
      return out ; }
      public QuakeEntry largest(ArrayList<QuakeEntry> in){
       QuakeEntry max = in.get(0);
       for(int i =1; i< in.size();i++){
       if(in.get(i).getDepth()>max.getDepth()){max = in.get(i);}
       }
       return max ;}
       public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
       for(int i =0; i < quakeData.size()-1 -numSorted;i++){
        if (quakeData.get(i).getMagnitude()> quakeData.get(i+1).getMagnitude()){
        QuakeEntry tmp = quakeData.get(i);             
        quakeData.set(i,quakeData.get(i+1));
        quakeData.set(i+1,tmp);
        }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size() - 1; i++) {
            System.out.println("the list  before the "+i+"th pass");
            in.forEach(QuakeEntry -> System.out.println(QuakeEntry));
            System.out.println("after");
            onePassBubbleSort(in, i);
            
            in.forEach(QuakeEntry -> System.out.println(QuakeEntry));
            
        }
    }
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> in){
    for(int i = 0; i<in.size()-1;i++){
    if(in.get(i).getMagnitude()> in.get(i+1).getMagnitude() ){
    return false;}
    }
    return true;
    }
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in ){
     /*for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
            
            if (checkInSortedOrder(in)) {
                System.out.println("Number of passes = " + (i + 1));
                break;
            }
       */ 
       int count = 0;
    for (int i = 0; i < in.size() - 1; i++) {
           if(!checkInSortedOrder(in)){
            count++;
               onePassBubbleSort(in, i);}
            
           
            
        }
        //in.forEach(QuakeEntry -> System.out.println(QuakeEntry));
        System.out.println(count);
    
    }
     public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
     
        int i = 0;
        for (i=0; i< in.size(); i++)
        {
            if(checkInSortedOrder(in))
            {
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        System.out.println("Passes required to Sort: " + i);
    
    /* for (int i = 0; i < in.size(); i++) {
            if (!checkInSortedOrder(in)) {
               count++;
                
            
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry temp = in.get(i);
            in.set(i, in.get(minIdx));
            in.set(minIdx, temp);
        }
            
        }
        *///in.forEach(QuakeEntry -> System.out.println(QuakeEntry));
        
        }
        
    }
