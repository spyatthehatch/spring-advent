package com.spyatthehatch.objects;

public class MapNode {
   private MapNode left;
   private MapNode right;
   private String name;
   private int visited;
   
   
   public MapNode (final String name) {
      this.name = name;
      this.visited = 0;
   }

   public MapNode getLeft() {
      this.visited++;
      return left;
   }


   public void setLeft(MapNode left) {
      this.visited++;
      this.left = left;
   }

   public int getVisited() {
      return this.visited;
   }

   public MapNode getRight() {
      return right;
   }


   public void setRight(MapNode right) {
      this.right = right;
   }


   public String getName() {
      return name;
   }
   
   @Override
   public String toString() {
      return "MapNode{name=\'" + this.name + "\', left=\'" + this.left.name + "\', right=\'" + this.right.name + "\'}";
   }
}
