package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.spyatthehatch.objects.Node;
import com.spyatthehatch.util.AlphabetUtils;

/**
 * Solution for Advent of Code 2022, Day 12.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D12 extends AbstractSolution {
   private int width;
   
   private int height;

   
   public Solution_2022D12(){
      super();
      this.width = this.rawList.get(0).length();
      this.height = this.rawList.size();
   }
   
   public String solvePartOne(){
      Node[][] map = new Node[width][height];
      Node start = null;
      Node end = null;
      
      for(int y=0; y < this.height; y++){
         final String s = this.rawList.get(y); 
         
         for(int x=0; x < this.width; x++){
            char c = s.charAt(x);
            if(c == 'S'){
               start = new Node(x, y, AlphabetUtils.getAlphaIndex('a'));
               map[x][y] = start;
            } else if (c == 'E'){
               end = new Node(x, y, AlphabetUtils.getAlphaIndex('z'));
               map[x][y] = end;
            } else {
               map[x][y] = new Node(x, y, AlphabetUtils.getAlphaIndex(c));
            }
         }
      }

      Node current = start;
      Queue<Node> nodeQ = new LinkedList<Node>();
      Set<Node> visited = new HashSet<Node>();
      visited.add(start);
      current.setSteps(0);
      
      while(current != end){
         int x = current.getX();
         int y = current.getY();
         int alt = current.getHeight();
                  
         if(x - 1 >= 0){
            Node n = map[x - 1][y];
            if(alt + 1 >= n.getHeight() && !visited.contains(n)){
               n.setSteps(current.getSteps() + 1);
               nodeQ.add(n);
            }
         }
         
         if(x + 1 < this.width){
            Node n = map[x + 1][y];
            if(alt + 1 >= n.getHeight() && !visited.contains(n)){
               n.setSteps(current.getSteps() + 1);
               nodeQ.add(n);
            }
         }
         
         if(y - 1 >= 0){
            Node n = map[x][y - 1];
            if(alt + 1 >= n.getHeight() && !visited.contains(n)){
               n.setSteps(current.getSteps() + 1);
               nodeQ.add(map[x][y - 1]);
            }
         }
         
         if(y + 1 < this.height){
            Node n = map[x][y + 1];
            if(alt + 1 >= n.getHeight() && !visited.contains(n)){
               n.setSteps(current.getSteps() + 1);
               nodeQ.add(n);
            }
         }

         visited.add(current);
         
         Node n = nodeQ.remove();
         while(visited.contains(n)){
            n = nodeQ.remove();
         }
         current = n;
      }
      LOGGER.info("Steps:" + current.getSteps());
      this.partOneAnswer = String.valueOf(current.getSteps());
      return this.partOneAnswer;
   }     
   
   public String solvePartTwo() {
      Node[][] map = new Node[width][height];
      Node end = null;
      List<Node>lows = new ArrayList<Node>();
      
      for(int y=0; y < this.height; y++){
         final String s = this.rawList.get(y); 
         
         for(int x=0; x < this.width; x++){
            char c = s.charAt(x);
            if(c == 'S'){
               map[x][y] = new Node(x, y, AlphabetUtils.getAlphaIndex(c));
            } else if (c == 'E'){
               end = new Node(x, y, AlphabetUtils.getAlphaIndex('z'));
               map[x][y] = end;
            } else {
               map[x][y] = new Node(x, y, AlphabetUtils.getAlphaIndex(c));
            }
            
            if(c == 'a'){
               lows.add(map[x][y]);
            }
         }
      }
      
      int lowest = Integer.MAX_VALUE;
      
      for(Node start : lows){         
         Node current = start;
         Queue<Node> nodeQ = new LinkedList<Node>();
         Set<Node> visited = new HashSet<Node>();
         visited.add(start);
         current.setSteps(0);
         boolean valid = true;
         
         while(current != end){
            int x = current.getX();
            int y = current.getY();
            int alt = current.getHeight();
            
            if(x - 1 >= 0){
               Node n = map[x - 1][y];
               if(alt + 1 >= n.getHeight() && !visited.contains(n)){
                  n.setSteps(current.getSteps() + 1);
                  nodeQ.add(n);
               }
            }
            
            if(x + 1 < this.width){
               Node n = map[x + 1][y];
               if(alt + 1 >= n.getHeight() && !visited.contains(n)){
                  n.setSteps(current.getSteps() + 1);
                  nodeQ.add(n);
               }
            }
            
            if(y - 1 >= 0){
               Node n = map[x][y - 1];
               if(alt + 1 >= n.getHeight() && !visited.contains(n)){
                  n.setSteps(current.getSteps() + 1);
                  nodeQ.add(map[x][y - 1]);
               }
            }
            
            if(y + 1 < this.height){
               Node n = map[x][y + 1];
               if(alt + 1 >= n.getHeight() && !visited.contains(n)){
                  n.setSteps(current.getSteps() + 1);
                  nodeQ.add(n);
               }
            }
   
            visited.add(current);
            
            if(nodeQ.isEmpty()){
               valid = false;
               break;
            }
            
            Node n = nodeQ.remove();
            while(visited.contains(n) && !nodeQ.isEmpty()){
               n = nodeQ.remove();
            }
            
            current = n;
            if(current == null){
               valid = false;
               break;
            }
         }
      
         if(current.getSteps() < lowest && valid){
            lowest = current.getSteps();
         }
      }
      
      this.partTwoAnswer = String.valueOf(lowest);
      return this.partTwoAnswer;
   }
}