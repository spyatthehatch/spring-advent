package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spyatthehatch.objects.MapNode;
import com.spyatthehatch.util.MathUtils;

/**
 * Solution for Advent of Code 2023, Day 8.
 * 
 * @author Bill Everton
 * @version Advent 2023
 */
public class Solution_2023D8 extends AbstractSolution {
   private Map<String, MapNode> nodes;
   
   private List<MapNode> nodeList;
   
   private String instructions;
   
	/**
	 * Constructor.
	 */
   public Solution_2023D8(){
      super();
      
      this.instructions = rawList.get(0);
      this.nodes = new HashMap<String, MapNode>();
      this.nodeList = new ArrayList<MapNode>();
      
      for(int i=2; i < rawList.size(); i++) {
         final String[] nodeString = rawList.get(i).split("=");
         final String name = nodeString[0].trim();
         final MapNode node = new MapNode(name);
         this.nodes.put(name, node);
      }
      
      for(int i=2; i < rawList.size(); i++) {
         final String[] nodeString = rawList.get(i).split("=");
         String nextNodesString = nodeString[1];
         nextNodesString = nextNodesString.replace('(', ' ');
         nextNodesString = nextNodesString.replace(')', ' ');
         final String[] nextNodes = nextNodesString.split(",");
         final String left = nextNodes[0].trim();
         final String right = nextNodes[1].trim();
         final MapNode thisNode = this.nodes.get(nodeString[0].trim());
         
         thisNode.setLeft(this.nodes.get(left));
         thisNode.setRight(this.nodes.get(right));
         this.nodeList.add(thisNode);
      }
   }

   /**
    * 
    */
   public String solvePartOne(){
      int steps = 0;

      LOGGER.info("Instruction size:" + this.instructions.length());
      
      MapNode node = this.nodes.get("AAA");
      
      int index = 0;
      while(!node.getName().equals("ZZZ")) {
         if(this.instructions.charAt(index) == 'L') {
            node = this.nodes.get(node.getLeft().getName());
         } else {
            node = this.nodes.get(node.getRight().getName());
         }
         
         steps++;
         index++;
         
         if(index >= this.instructions.length()) {
            index = 0;
         }
      }
      
      this.partOneAnswer = String.valueOf(steps);
      return this.partOneAnswer;
   }
   
   /**
    * 
    */
   public String solvePartTwo() {
      List<MapNode>nextNodes = new ArrayList<MapNode>();
      
      
      for(MapNode mn : this.nodeList) {
         if(mn.getName().charAt(2) == 'A') {
            nextNodes.add(mn);
         }
      }
      
      int[] steps = new int[nextNodes.size()];
      
      for(int i=0; i<nextNodes.size(); i++) {
         int index = 0;
         int count = 0;
         MapNode node = nextNodes.get(i);
         
         while(node.getName().charAt(2) != 'Z') {
            if(this.instructions.charAt(index) == 'L') {
               node = this.nodes.get(node.getLeft().getName());
            } else {
               node = this.nodes.get(node.getRight().getName());
            }
            
            count++;
            index++;
            
            if(index >= this.instructions.length()) {
               index = 0;
            }
         }
         
         steps[i] = count;
      }
      
      this.partTwoAnswer = String.valueOf(MathUtils.leastCommonMultiple(steps));
      return this.partTwoAnswer;
   }
}