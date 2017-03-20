/*
   Copyright (c) 2017 Spade
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package i.love.sdmlib.util;

import de.uniks.networkparser.list.SimpleSet;
import i.love.sdmlib.House;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.ObjectSet;
import i.love.sdmlib.util.MancalaGameSet;
import i.love.sdmlib.MancalaGame;
import i.love.sdmlib.util.HouseSet;

public class HouseSet extends SimpleSet<House>
{
	protected Class<?> getTypClass() {
		return House.class;
	}

   public HouseSet()
   {
      // empty
   }

   public HouseSet(House... objects)
   {
      for (House obj : objects)
      {
         this.add(obj);
      }
   }

   public HouseSet(Collection<House> objects)
   {
      this.addAll(objects);
   }

   public static final HouseSet EMPTY_SET = new HouseSet().withFlag(HouseSet.READONLY);


   public HousePO createHousePO()
   {
      return new HousePO(this.toArray(new House[this.size()]));
   }


   public String getEntryType()
   {
      return "i.love.sdmlib.House";
   }


   @Override
   public HouseSet getNewList(boolean keyValue)
   {
      return new HouseSet();
   }


   public HouseSet filter(Condition<House> condition) {
      HouseSet filterList = new HouseSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public HouseSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<House>)value);
      }
      else if (value != null)
      {
         this.add((House) value);
      }
      
      return this;
   }
   
   public HouseSet without(House value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public HouseSet takeOppositePebbles()
   {
      return HouseSet.EMPTY_SET;
   }

   
   //==========================================================================
   
   public HouseSet redistributeCounterClockwise()
   {
      return HouseSet.EMPTY_SET;
   }

   
   //==========================================================================
   
   public HouseSet redistributeCounterClockwiseRecurse(int x, Object p0)
   {
      return HouseSet.EMPTY_SET;
   }


   /**
    * Loop through the current set of House objects and collect a list of the pebbles attribute values. 
    * 
    * @return List of int objects reachable via pebbles attribute
    */
   public NumberList getPebbles()
   {
      NumberList result = new NumberList();
      
      for (House obj : this)
      {
         result.add(obj.getPebbles());
      }
      
      return result;
   }


   /**
    * Loop through the current set of House objects and collect those House objects where the pebbles attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of House objects that match the parameter
    */
   public HouseSet filterPebbles(int value)
   {
      HouseSet result = new HouseSet();
      
      for (House obj : this)
      {
         if (value == obj.getPebbles())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of House objects and collect those House objects where the pebbles attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of House objects that match the parameter
    */
   public HouseSet filterPebbles(int lower, int upper)
   {
      HouseSet result = new HouseSet();
      
      for (House obj : this)
      {
         if (lower <= obj.getPebbles() && obj.getPebbles() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of House objects and assign value to the pebbles attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of House objects now with new attribute values.
    */
   public HouseSet withPebbles(int value)
   {
      for (House obj : this)
      {
         obj.setPebbles(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of House objects and collect a list of the isStore attribute values. 
    * 
    * @return List of boolean objects reachable via isStore attribute
    */
   public BooleanList getIsStore()
   {
      BooleanList result = new BooleanList();
      
      for (House obj : this)
      {
         result.add(obj.isIsStore());
      }
      
      return result;
   }


   /**
    * Loop through the current set of House objects and collect those House objects where the isStore attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of House objects that match the parameter
    */
   public HouseSet filterIsStore(boolean value)
   {
      HouseSet result = new HouseSet();
      
      for (House obj : this)
      {
         if (value == obj.isIsStore())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of House objects and assign value to the isStore attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of House objects now with new attribute values.
    */
   public HouseSet withIsStore(boolean value)
   {
      for (House obj : this)
      {
         obj.setIsStore(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of House objects and collect a set of the MancalaGame objects reached via game. 
    * 
    * @return Set of MancalaGame objects reachable via game
    */
   public MancalaGameSet getGame()
   {
      MancalaGameSet result = new MancalaGameSet();
      
      for (House obj : this)
      {
         result.with(obj.getGame());
      }
      
      return result;
   }

   /**
    * Loop through the current set of House objects and collect all contained objects with reference game pointing to the object passed as parameter. 
    * 
    * @param value The object required as game neighbor of the collected results. 
    * 
    * @return Set of MancalaGame objects referring to value via game
    */
   public HouseSet filterGame(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      HouseSet answer = new HouseSet();
      
      for (House obj : this)
      {
         if (neighbors.contains(obj.getGame()) || (neighbors.isEmpty() && obj.getGame() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the House object passed as parameter to the Game attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Game attributes.
    */
   public HouseSet withGame(MancalaGame value)
   {
      for (House obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of House objects and collect a set of the House objects reached via house. 
    * 
    * @return Set of House objects reachable via house
    */
   public HouseSet getHouse()
   {
      HouseSet result = new HouseSet();
      
      for (House obj : this)
      {
         result.with(obj.getHouse());
      }
      
      return result;
   }

   /**
    * Loop through the current set of House objects and collect all contained objects with reference house pointing to the object passed as parameter. 
    * 
    * @param value The object required as house neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via house
    */
   public HouseSet filterHouse(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      HouseSet answer = new HouseSet();
      
      for (House obj : this)
      {
         if (neighbors.contains(obj.getHouse()) || (neighbors.isEmpty() && obj.getHouse() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow house reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of House objects reachable via house transitively (including the start set)
    */
   public HouseSet getHouseTransitive()
   {
      HouseSet todo = new HouseSet().with(this);
      
      HouseSet result = new HouseSet();
      
      while ( ! todo.isEmpty())
      {
         House current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getHouse()))
            {
               todo.with(current.getHouse());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the House object passed as parameter to the House attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their House attributes.
    */
   public HouseSet withHouse(House value)
   {
      for (House obj : this)
      {
         obj.withHouse(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of House objects and collect a set of the House objects reached via houseAcross. 
    * 
    * @return Set of House objects reachable via houseAcross
    */
   public HouseSet getHouseAcross()
   {
      HouseSet result = new HouseSet();
      
      for (House obj : this)
      {
         result.with(obj.getHouseAcross());
      }
      
      return result;
   }

   /**
    * Loop through the current set of House objects and collect all contained objects with reference houseAcross pointing to the object passed as parameter. 
    * 
    * @param value The object required as houseAcross neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via houseAcross
    */
   public HouseSet filterHouseAcross(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      HouseSet answer = new HouseSet();
      
      for (House obj : this)
      {
         if (neighbors.contains(obj.getHouseAcross()) || (neighbors.isEmpty() && obj.getHouseAcross() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow houseAcross reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of House objects reachable via houseAcross transitively (including the start set)
    */
   public HouseSet getHouseAcrossTransitive()
   {
      HouseSet todo = new HouseSet().with(this);
      
      HouseSet result = new HouseSet();
      
      while ( ! todo.isEmpty())
      {
         House current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getHouseAcross()))
            {
               todo.with(current.getHouseAcross());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the House object passed as parameter to the HouseAcross attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their HouseAcross attributes.
    */
   public HouseSet withHouseAcross(House value)
   {
      for (House obj : this)
      {
         obj.withHouseAcross(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of House objects and collect a set of the House objects reached via housePrev. 
    * 
    * @return Set of House objects reachable via housePrev
    */
   public HouseSet getHousePrev()
   {
      HouseSet result = new HouseSet();
      
      for (House obj : this)
      {
         result.with(obj.getHousePrev());
      }
      
      return result;
   }

   /**
    * Loop through the current set of House objects and collect all contained objects with reference housePrev pointing to the object passed as parameter. 
    * 
    * @param value The object required as housePrev neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via housePrev
    */
   public HouseSet filterHousePrev(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      HouseSet answer = new HouseSet();
      
      for (House obj : this)
      {
         if (neighbors.contains(obj.getHousePrev()) || (neighbors.isEmpty() && obj.getHousePrev() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow housePrev reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of House objects reachable via housePrev transitively (including the start set)
    */
   public HouseSet getHousePrevTransitive()
   {
      HouseSet todo = new HouseSet().with(this);
      
      HouseSet result = new HouseSet();
      
      while ( ! todo.isEmpty())
      {
         House current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getHousePrev()))
            {
               todo.with(current.getHousePrev());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the House object passed as parameter to the HousePrev attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their HousePrev attributes.
    */
   public HouseSet withHousePrev(House value)
   {
      for (House obj : this)
      {
         obj.withHousePrev(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of House objects and collect a set of the House objects reached via houseNext. 
    * 
    * @return Set of House objects reachable via houseNext
    */
   public HouseSet getHouseNext()
   {
      HouseSet result = new HouseSet();
      
      for (House obj : this)
      {
         result.with(obj.getHouseNext());
      }
      
      return result;
   }

   /**
    * Loop through the current set of House objects and collect all contained objects with reference houseNext pointing to the object passed as parameter. 
    * 
    * @param value The object required as houseNext neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via houseNext
    */
   public HouseSet filterHouseNext(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      HouseSet answer = new HouseSet();
      
      for (House obj : this)
      {
         if (neighbors.contains(obj.getHouseNext()) || (neighbors.isEmpty() && obj.getHouseNext() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow houseNext reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of House objects reachable via houseNext transitively (including the start set)
    */
   public HouseSet getHouseNextTransitive()
   {
      HouseSet todo = new HouseSet().with(this);
      
      HouseSet result = new HouseSet();
      
      while ( ! todo.isEmpty())
      {
         House current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getHouseNext()))
            {
               todo.with(current.getHouseNext());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the House object passed as parameter to the HouseNext attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their HouseNext attributes.
    */
   public HouseSet withHouseNext(House value)
   {
      for (House obj : this)
      {
         obj.withHouseNext(value);
      }
      
      return this;
   }

}
