package com.spyatthehatch.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Bill Everton
 *
 */
public class Valve {
   private String name;
   
   private int flow;
   
   private List<Valve> tunnels;
   
   public Valve(final String name, final int flow){
      this.name = name;
      this.flow = flow;
      this.tunnels = new ArrayList<Valve>();
   }
   
   /**
    * Add a Valve to the tunnels.
    * 
    * @param v Valve to add.
    */
   public void addValve(final Valve v){
      this.tunnels.add(v);
   }

   /**
    * @return the name
    */
   public String getName() {
      return this.name;
   }

   /**
    * @return the flow
    */
   public int getFlow() {
      return this.flow;
   }
   
   @Override
   public String toString(){
      final StringBuilder sb = new StringBuilder();
      sb.append("Valve name:" + this.name)
      .append(", flow:" + this.flow)
      .append(", tunnels:" + this.tunnels.size());
      
      return sb.toString();
   }
}