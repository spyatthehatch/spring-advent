package com.spyatthehatch.objects;

public class Sensor {
   private Point location;
   private Point beacon;
   private int distance;
   
   public Sensor (Point p){
      this.location = p;
   }
   
   public void setBeacon(Point p){
      this.beacon = p;
      this.distance = this.location.getManhattanDistance(this.beacon);
   }
   
   public int getDistance(){
      return this.distance;
   }
   
   public Point getLocation(){
      return this.location;
   }
   
   public String toString(){
      final StringBuilder sb = new StringBuilder();
      sb.append("Sensor Location " + this.location.toString())
      .append(", Beacon " + this.beacon.toString())
      .append(", Distance:" + this.distance);
      
      return sb.toString();
   }
   
   public Point getBeacon(){
      return this.beacon;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.beacon == null) ? 0 : this.beacon.hashCode());
      result = prime * result + ((this.location == null) ? 0 : this.location.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Sensor other = (Sensor) obj;
      if (this.beacon == null) {
         if (other.beacon != null)
            return false;
      } else if (!this.beacon.equals(other.beacon))
         return false;
      if (this.location == null) {
         if (other.location != null)
            return false;
      } else if (!this.location.equals(other.location))
         return false;
      return true;
   }
   
   
}