package com.spyatthehatch.solutions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Solution for Advent of Code 2024, Day 14.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D14 extends AbstractSolution {
   public final int WIDTH = 101;
   public final int HEIGHT = 103;
	/**
	 * Constructor.
	 */
   public Solution_2024D14(){
      super();
   }
   
   /**
    * 
    */
   public String solvePartOne(){
      int total = 0;
      final int UPDATES = 100;
      List<Robot>robots = new ArrayList<Robot>();

      
      for(String s : rawList) {
         String[] line = s.split(" ");
         String[] position = line[0].split(",");
         String[] velocity = line[1].split(",");
         int posX = Integer.valueOf(position[0].substring(2,position[0].length()));
         int posY = Integer.valueOf(position[1]);
         int velX = Integer.valueOf(velocity[0].substring(2,velocity[0].length()));
         int velY = Integer.valueOf(velocity[1]);
         
         Robot robot = new Robot(posX, posY, velX, velY);
         robots.add(robot);
      }
      
      for(int i=0; i<UPDATES; i++) {
         for(Robot robot : robots) {
            robot.updatePosition();
         }
      }
      
      int quadOne = 0;
      int quadTwo = 0;
      int quadThree = 0;
      int quadFour = 0;
      
      for(Robot robot : robots) {            
         switch(robot.getQuadrant()) {
            case 1:
               quadOne++;
               break;
            case 2:
               quadTwo++;
               break;
            case 3:
               quadThree++;
               break;
            case 4:
               quadFour++;
            default:
         }
                  
         total = quadOne * quadTwo * quadThree * quadFour;
      }
      
      this.partOneAnswer = String.valueOf(total);
      return this.partOneAnswer;
   }
 
   /**
    * 
    */
   public String solvePartTwo() {
      List<Robot>robots = new ArrayList<Robot>();
      
      for(String s : rawList) {
         String[] line = s.split(" ");
         String[] position = line[0].split(",");
         String[] velocity = line[1].split(",");
         int posX = Integer.valueOf(position[0].substring(2,position[0].length()));
         int posY = Integer.valueOf(position[1]);
         int velX = Integer.valueOf(velocity[0].substring(2,velocity[0].length()));
         int velY = Integer.valueOf(velocity[1]);
         
         Robot robot = new Robot(posX, posY, velX, velY);
         robots.add(robot);
      }

      final PixelPainter pp = new PixelPainter(robots);
      final JFrame frame = new JFrame("Advent Day 14");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(pp);
      frame.setSize(10 * WIDTH, 10 * HEIGHT);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
           
      int count = 1;
      int threshold = 200;
      int sleep = 200;
      
      while(true) {
         int[][] map = initMap();

         for(Robot robot : robots){
            robot.updatePosition();
            map[robot.getX()][robot.getY()]++;
         }
          
         pp.repaint();

         if(countLonely(map) < threshold) {
            LOGGER.trace("Found threshold at Update:" + count); 
            break;
         } else {
            try {
               
               sleep = adjustSleep(count, sleep);
               
               Thread.sleep(sleep);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
         
         count++;
      }
      
      this.partTwoAnswer = String.valueOf(count);
      return this.partTwoAnswer;
   }
   
   public int adjustSleep(int count, int sleep) {      
      if (count < 6300) {
         sleep--;
      } else {
         sleep++;
      }
      
      if(sleep < 2) {
         sleep = 2;
      }
      
      if (sleep > 200) {
         sleep = 200;
      }
      
      return sleep;
   }
   
   public int countLonely(int[][] map) {
      int count = 0;
      
      for(int y=0; y<HEIGHT; y++) {
         for(int x=0; x<WIDTH; x++) {
            if(map[x][y] > 0 && !hasNeighbor(map, x, y)) {
               count++;
            }
         }
      }
      
      return count;
   }
   
   public boolean hasNeighbor(int[][] map, int x, int y) {
      if(x-1 >= 0 ) {
         if(map[x-1][y] > 0) {
            return true;
         }
      }
      
      if(x+1 < WIDTH) {
         if(map[x+1][y] > 0) {
            return true;
         }
      }
      
      if(y-1 >= 0) {
         if(map[x][y-1] > 0) {
            return true;
         }
      }
      
      if(y+1 < HEIGHT) {
         if(map[x][y+1] > 0) {
            return true;
         }
      }
      
      return false;
   }
   
   public int[][] initMap(){
      int[][] map = new int[WIDTH][HEIGHT];
      
      for(int y=0; y<HEIGHT; y++) {
         for(int x=0; x<WIDTH; x++) {
            map[x][y] = 0;
         }
      }
      
      return map;
   }
   
   public class Robot {
      public int x;
      public int y;
      public int velX;
      public int velY;
      
      public Robot (int x, int y, int velX, int velY) {
         this.x = x;
         this.y = y;
         this.velX = velX;
         this.velY = velY;
      }
      
      public String toString() {
         return "x:" + this.x + ", y:" + this.y + ", velX:" + velX + ", vehY:" + this.velY;
      }
      
      public void updatePosition() {
         this.x += this.velX;
         this.y += this.velY;
         
         if(this.x >= WIDTH) {
            this.x -= WIDTH;
         }
         
         if(this.x < 0) {
            this.x += WIDTH;
         }
         
         if(this.y >= HEIGHT) {
            this.y -= HEIGHT;
         }
         
         if(this.y < 0) {
            this.y += HEIGHT;
         }
      }
      
      public int getQuadrant() {
         if(this.x < (WIDTH-1)/2 && this.y < (HEIGHT-1)/2) {
            return 1;
         }
         
         if(this.x > (WIDTH-1)/2 && this.y < (HEIGHT-1)/2) {
            return 2;
         }
         
         if(this.x < (WIDTH-1)/2 && this.y > (HEIGHT-1)/2) {
            return 3;
         }
         
         if(this.x > (WIDTH-1)/2 && this.y > (HEIGHT-1)/2) {
            return 4;
         }
         
         return 0;
      }
      
      public int getX() {
         return this.x;
      }
      
      public int getY() {
         return this.y;
      }
   }
   
   public class PixelPainter extends JPanel {   
      /**
       * Serial version.
       */
      private static final long serialVersionUID = 1692597651156745181L;

      /**
       * List of pixels.
       */
      private List<Robot> pixels;
      
      /**
       * Constructor.
       * 
       * @param list List of pixels to manage.
       */
      public PixelPainter(final List<Robot> list){
          this.pixels = list;
      }
      
      /**
       * Paint the pixels.
       */
      public void paintComponent(final Graphics g){
         super.paintComponent(g);
         this.setBackground(Color.BLACK);
          
         final Graphics2D g2d = (Graphics2D) g;
         g2d.setColor(Color.red);
          
         for(final Robot robot : this.pixels){
            final int x = robot.getX() * 10;
            final int y = robot.getY() * 10;
            g2d.drawRect(x, y, 3, 3);
         }
      }
  }
}