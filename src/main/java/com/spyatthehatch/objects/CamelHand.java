package com.spyatthehatch.objects;

import java.util.Objects;

/**
 * 
 */
public class CamelHand implements Comparable<CamelHand> {
   private String cards;
   private int bid;
   private enum Type {HIGH_CARD, PAIR, TWO_PAIR, THREE_KIND, FULL_HOUSE, FOUR_KIND, FIVE_KIND};
   private Type type;
   private String values;
   
   static final String STANDARD_HIERARCHY = "23456789TJQKA";
   static final String JOKER_HIERARCHY = "J23456789TQKA";
   
   public CamelHand(String cards, int bid) {
      this.cards = cards;
      this.bid = bid;
      

   }

   public String getCards() {
      return this.cards;
   }

   public void setCards(final String cards) {
      this.cards = cards;
   }

   public int getBid() {
      return this.bid;
   }

   public void setBid(final int bid) {
      this.bid = bid;
   }
      
   public Type getType() {
      return type;
   }

   public void setType() {  
      this.values = STANDARD_HIERARCHY;
      final int size = this.values.length();
      
      int occurences[] = new int[size];
      for(int i=0; i < this.cards.length(); i++) {
         occurences[this.values.indexOf(String.valueOf(this.cards.charAt(i)))]++;
      }
      
      String repeats = "";
      for(int i=0; i < size; i++) {
         if(occurences[i] != 0) {
            repeats += occurences[i];
         }
      }
            
      if(repeats.contains("5")) {
         this.type = Type.FIVE_KIND;
      }
      
      else if(repeats.contains("4")) {
         this.type = Type.FOUR_KIND;
      }
      
      else if(repeats.contains("3") && repeats.contains("2")) {
         this.type = Type.FULL_HOUSE;
      }
      
      else if(repeats.contains("3")) {
         this.type = Type.THREE_KIND;
      }
      
      else if(repeats.equals("11111")) {
         this.type = Type.HIGH_CARD;
      }
      
      else if(repeats.contains("11")) {
         this.type = Type.PAIR;
      }
      
      else {
         this.type = Type.TWO_PAIR;
      }
   }

   public void setTypeWithJokers() {
      this.values = JOKER_HIERARCHY;
      final int size = this.values.length();
      
      int occurences[] = new int[size];
      for(int i=0; i < this.cards.length(); i++) {
         occurences[this.values.indexOf(String.valueOf(this.cards.charAt(i)))]++;
      }
      
      final int jokers = occurences[0];
      
      String repeats = "";
      int most = 0;
      int pairs = 0;
      for(int i=1; i < size; i++) {
         if(occurences[i] != 0) {
            int count = occurences[i];
            if(count > most) {
               most = count;
            }
            
            if(count == 2) {
               pairs++;
            }
            repeats += count;
         }
      }
      
      if(jokers == 0) {
         if(repeats.contains("5")) {
            this.type = Type.FIVE_KIND;
         }
         
         else if(repeats.contains("4")) {
            this.type = Type.FOUR_KIND;
         }
         
         else if(repeats.contains("3") && repeats.contains("2")) {
            this.type = Type.FULL_HOUSE;
         }
         
         else if(repeats.contains("3")) {
            this.type = Type.THREE_KIND;
         }
         
         else if(repeats.equals("11111")) {
            this.type = Type.HIGH_CARD;
         }
         
         else if(repeats.contains("11")) {
            this.type = Type.PAIR;
         }
         
         else {
            this.type = Type.TWO_PAIR;
         }
      } else {
         int kinds = most + jokers;
         
         switch(kinds) {
            case 2:
               this.type = Type.PAIR;
               break;
            case 3:
               if (pairs < 2) {
                  this.type = Type.THREE_KIND;
               } else {
                  this.type = Type.FULL_HOUSE;
               }
               break;
            case 4:
               this.type = Type.FOUR_KIND;
               break;
            case 5:
               this.type = Type.FIVE_KIND;
               break;
         }
      }
   }
   
   @Override
   public String toString() {
      return "CamelHand{cards=\'" + this.cards + "\', bid=" + this.bid + ", type=" + this.type + "}";
   }
   
   @Override
   public int hashCode() {
      return Objects.hash(this.cards, this.bid);
   }
   
   @Override
   public boolean equals(final Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      final CamelHand ch = (CamelHand) o;
      return Objects.equals(this.cards, ch.cards) && Objects.equals(this.bid, ch.bid);
   }
   
   public int compareTo(final CamelHand ch) {
      int compare = Integer.compare(ch.type.ordinal(), this.type.ordinal());
      
      if(compare != 0) {
         return compare;
      } else {
         for(int i=0; i < this.cards.length(); i++){
            compare = Integer.compare(this.values.indexOf(ch.cards.charAt(i)), this.values.indexOf(this.cards.charAt(i)));
            
            if(compare != 0) {
               return compare;
            }
         }
         
         return 0;
      }
   }
}
