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
import i.love.sdmlib.MancalaGame;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import i.love.sdmlib.util.PlayerSet;
import i.love.sdmlib.Player;
import i.love.sdmlib.util.HouseSet;
import i.love.sdmlib.House;

public class MancalaGameSet extends SimpleSet<MancalaGame>
{
	protected Class<?> getTypClass() {
		return MancalaGame.class;
	}

   public MancalaGameSet()
   {
      // empty
   }

   public MancalaGameSet(MancalaGame... objects)
   {
      for (MancalaGame obj : objects)
      {
         this.add(obj);
      }
   }

   public MancalaGameSet(Collection<MancalaGame> objects)
   {
      this.addAll(objects);
   }

   public static final MancalaGameSet EMPTY_SET = new MancalaGameSet().withFlag(MancalaGameSet.READONLY);


   public MancalaGamePO createMancalaGamePO()
   {
      return new MancalaGamePO(this.toArray(new MancalaGame[this.size()]));
   }


   public String getEntryType()
   {
      return "i.love.sdmlib.MancalaGame";
   }


   @Override
   public MancalaGameSet getNewList(boolean keyValue)
   {
      return new MancalaGameSet();
   }


   public MancalaGameSet filter(Condition<MancalaGame> condition) {
      MancalaGameSet filterList = new MancalaGameSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public MancalaGameSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MancalaGame>)value);
      }
      else if (value != null)
      {
         this.add((MancalaGame) value);
      }
      
      return this;
   }
   
   public MancalaGameSet without(MancalaGame value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public MancalaGameSet makeBoard(int x)
   {
      return MancalaGameSet.EMPTY_SET;
   }

   /**
    * Loop through the current set of MancalaGame objects and collect a set of the Player objects reached via players. 
    * 
    * @return Set of Player objects reachable via players
    */
   public PlayerSet getPlayers()
   {
      PlayerSet result = new PlayerSet();
      
      for (MancalaGame obj : this)
      {
         result.with(obj.getPlayers());
      }
      
      return result;
   }

   /**
    * Loop through the current set of MancalaGame objects and collect all contained objects with reference players pointing to the object passed as parameter. 
    * 
    * @param value The object required as players neighbor of the collected results. 
    * 
    * @return Set of Player objects referring to value via players
    */
   public MancalaGameSet filterPlayers(Object value)
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
      
      MancalaGameSet answer = new MancalaGameSet();
      
      for (MancalaGame obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPlayers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the MancalaGame object passed as parameter to the Players attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Players attributes.
    */
   public MancalaGameSet withPlayers(Player value)
   {
      for (MancalaGame obj : this)
      {
         obj.withPlayers(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the MancalaGame object passed as parameter from the Players attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public MancalaGameSet withoutPlayers(Player value)
   {
      for (MancalaGame obj : this)
      {
         obj.withoutPlayers(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of MancalaGame objects and collect a set of the House objects reached via p1Houses. 
    * 
    * @return Set of House objects reachable via p1Houses
    */
   public HouseSet getP1Houses()
   {
      HouseSet result = new HouseSet();
      
      for (MancalaGame obj : this)
      {
         result.with(obj.getP1Houses());
      }
      
      return result;
   }

   /**
    * Loop through the current set of MancalaGame objects and collect all contained objects with reference p1Houses pointing to the object passed as parameter. 
    * 
    * @param value The object required as p1Houses neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via p1Houses
    */
   public MancalaGameSet filterP1Houses(Object value)
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
      
      MancalaGameSet answer = new MancalaGameSet();
      
      for (MancalaGame obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getP1Houses()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the MancalaGame object passed as parameter to the P1Houses attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their P1Houses attributes.
    */
   public MancalaGameSet withP1Houses(House value)
   {
      for (MancalaGame obj : this)
      {
         obj.withP1Houses(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the MancalaGame object passed as parameter from the P1Houses attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public MancalaGameSet withoutP1Houses(House value)
   {
      for (MancalaGame obj : this)
      {
         obj.withoutP1Houses(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of MancalaGame objects and collect a set of the House objects reached via p2Houses. 
    * 
    * @return Set of House objects reachable via p2Houses
    */
   public HouseSet getP2Houses()
   {
      HouseSet result = new HouseSet();
      
      for (MancalaGame obj : this)
      {
         result.with(obj.getP2Houses());
      }
      
      return result;
   }

   /**
    * Loop through the current set of MancalaGame objects and collect all contained objects with reference p2Houses pointing to the object passed as parameter. 
    * 
    * @param value The object required as p2Houses neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via p2Houses
    */
   public MancalaGameSet filterP2Houses(Object value)
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
      
      MancalaGameSet answer = new MancalaGameSet();
      
      for (MancalaGame obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getP2Houses()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the MancalaGame object passed as parameter to the P2Houses attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their P2Houses attributes.
    */
   public MancalaGameSet withP2Houses(House value)
   {
      for (MancalaGame obj : this)
      {
         obj.withP2Houses(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the MancalaGame object passed as parameter from the P2Houses attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public MancalaGameSet withoutP2Houses(House value)
   {
      for (MancalaGame obj : this)
      {
         obj.withoutP2Houses(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of MancalaGame objects and collect a set of the House objects reached via p1Store. 
    * 
    * @return Set of House objects reachable via p1Store
    */
   public HouseSet getP1Store()
   {
      HouseSet result = new HouseSet();
      
      for (MancalaGame obj : this)
      {
         result.with(obj.getP1Store());
      }
      
      return result;
   }

   /**
    * Loop through the current set of MancalaGame objects and collect all contained objects with reference p1Store pointing to the object passed as parameter. 
    * 
    * @param value The object required as p1Store neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via p1Store
    */
   public MancalaGameSet filterP1Store(Object value)
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
      
      MancalaGameSet answer = new MancalaGameSet();
      
      for (MancalaGame obj : this)
      {
         if (neighbors.contains(obj.getP1Store()) || (neighbors.isEmpty() && obj.getP1Store() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the MancalaGame object passed as parameter to the P1Store attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their P1Store attributes.
    */
   public MancalaGameSet withP1Store(House value)
   {
      for (MancalaGame obj : this)
      {
         obj.withP1Store(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of MancalaGame objects and collect a set of the House objects reached via p2Store. 
    * 
    * @return Set of House objects reachable via p2Store
    */
   public HouseSet getP2Store()
   {
      HouseSet result = new HouseSet();
      
      for (MancalaGame obj : this)
      {
         result.with(obj.getP2Store());
      }
      
      return result;
   }

   /**
    * Loop through the current set of MancalaGame objects and collect all contained objects with reference p2Store pointing to the object passed as parameter. 
    * 
    * @param value The object required as p2Store neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via p2Store
    */
   public MancalaGameSet filterP2Store(Object value)
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
      
      MancalaGameSet answer = new MancalaGameSet();
      
      for (MancalaGame obj : this)
      {
         if (neighbors.contains(obj.getP2Store()) || (neighbors.isEmpty() && obj.getP2Store() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the MancalaGame object passed as parameter to the P2Store attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their P2Store attributes.
    */
   public MancalaGameSet withP2Store(House value)
   {
      for (MancalaGame obj : this)
      {
         obj.withP2Store(value);
      }
      
      return this;
   }

}
