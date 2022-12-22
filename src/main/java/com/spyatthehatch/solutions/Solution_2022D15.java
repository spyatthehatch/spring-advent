package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.objects.Point;
import com.spyatthehatch.objects.Sensor;

/**
 * Solution for Advent of Code 2022, Day 15.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D15 extends AbstractSolution {
   private List<Sensor> sensors = null;
   
   public Solution_2022D15(){
      super();
   }
   
   public String solvePartOne(){
      this.sensors = new ArrayList<Sensor>();
      
      for(String s : this.rawList){
         s = s.replace('=', ' ');
         s = s.replace(',', ' ');
         s = s.replace(':', ' ');
         String[] words = s.split(" ");
         int sensorX = Integer.valueOf(words[3]);
         int sensorY = Integer.valueOf(words[6]);
         int beaconX = Integer.valueOf(words[13]);
         int beaconY = Integer.valueOf(words[16]);
         Sensor sensor = new Sensor(new Point(sensorX, sensorY));
         sensor.setBeacon(new Point(beaconX, beaconY));
         sensors.add(sensor);         
      }
      

      final int minX = -10000000;
      final int maxX = 10000000;
      int detectable = 0;
      for(int x = minX; x <= maxX; x++){
         if(isDetectable(x, 2000000)){
            detectable++;
         }
      }
      
      this.partOneAnswer = String.valueOf(detectable);
      return this.partOneAnswer;
   }   
   
   public String solvePartTwo() {
      long frequency = 0;
      boolean found = false;
      int sensorNum = 1;
      
      for(Sensor sensor : this.sensors){
         LOGGER.info("Checking sensor #" + sensorNum + " at " + sensor.getLocation().toString());
         LOGGER.info("Distance:" + sensor.getDistance());
         
         List<Point> points = new ArrayList<Point>();
         
         int start = sensor.getLocation().getX() - sensor.getDistance();
         int stop = sensor.getLocation().getX() + sensor.getDistance();
         int sensorX = sensor.getLocation().getX();
         int sensorY = sensor.getLocation().getY();
         
         LOGGER.info("Start:" + start);
         LOGGER.info("Stop:" + stop);
         
         for(int x = start; x <= stop; x++){
            int vertical = sensor.getDistance() - Math.abs(sensorX - x);
            
            int y = sensorY + vertical + 1;
            if(inScope(x, y)){
               points.add(new Point(x, y));
            }
            
            y = sensorY - vertical - 1;
            if(inScope(x, y)){
               points.add(new Point(x, y));
            }
            
            if(x == start){
               LOGGER.info("Vertical:" + vertical);
               LOGGER.info("x:" + x + ", y:" + (y + vertical + 1));
               LOGGER.info("x:" + x + ", y:" + (y - vertical - 1));
            }
         }
         
         if(inScope(start - 1, sensorY)){
            points.add(new Point(start - 1, sensorY));
         }
         
         if(inScope(stop + 1, sensorY)){
            points.add(new Point(stop + 1, sensorY));
         }
         
         LOGGER.info("Checking " + points.size() + " points for detection.");
         
         int checked = 1;
         for(Point p : points){
            if(!isDetectable(p.getX(), p.getY())){
               LOGGER.info("Found:" + p.toString() + ", after checking " + checked + " points.");
               frequency = (4000000 * (long)p.getX()) + p.getY();
               LOGGER.info("Frequency:" + frequency);
               found = true;
               break;
            }
            checked++;
         }
         
         if(found){
            break;
         }
         
         sensorNum++;
      }
      
      this.partTwoAnswer = String.valueOf(frequency);
      return this.partTwoAnswer;
   }
   
   public boolean inScope(int x, int y){
      if(x < 0 || x > 4000000){
         return false;
      }
      
      if(y < 0 || y > 4000000){
         return false;
      }
      
      return true;
   }
   
   public boolean isDetectable(final int x, final int y){
      final Point p = new Point(x, y);
      
      for(Sensor sensor : this.sensors){
         int distanceToSensor = p.getManhattanDistance(sensor.getLocation());
                     
         if(distanceToSensor <= sensor.getDistance() && !p.equals(sensor.getBeacon())){
            return true;
         }
      }
      
      return false;
   }
}