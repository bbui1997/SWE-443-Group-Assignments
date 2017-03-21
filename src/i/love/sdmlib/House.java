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
   
package i.love.sdmlib;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import i.love.sdmlib.MancalaGame;
import i.love.sdmlib.util.HouseSet;
   /**
    * 
    * @see <a href='../../../../mancala/src/main/java/Model.java'>Model.java</a>
 */
   public  class House implements SendableEntity
{

   
   //==========================================================================
   public void takeOppositePebbles(  )
   {
      
   }

   
   //==========================================================================
   public void redistributeCounterClockwise(  )
   {
      
   }

   
   //==========================================================================
   public void redistributeCounterClockwiseRecurse( int x, Object p1 )
   {
      
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setGame(null);
      setHouse(null);
      setHouseAcross(null);
      setHousePrev(null);
      setHouseNext(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_PEBBLES = "pebbles";
   
   private int pebbles;

   public int getPebbles()
   {
      return this.pebbles;
   }
   
   public void setPebbles(int value)
   {
      if (this.pebbles != value) {
      
         int oldValue = this.pebbles;
         this.pebbles = value;
         this.firePropertyChange(PROPERTY_PEBBLES, oldValue, value);
      }
   }
   
   public House withPebbles(int value)
   {
      setPebbles(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getPebbles());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_ISSTORE = "isStore";
   
   private boolean isStore;

   public boolean isIsStore()
   {
      return this.isStore;
   }
   
   public void setIsStore(boolean value)
   {
      if (this.isStore != value) {
      
         boolean oldValue = this.isStore;
         this.isStore = value;
         this.firePropertyChange(PROPERTY_ISSTORE, oldValue, value);
      }
   }
   
   public House withIsStore(boolean value)
   {
      setIsStore(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * House ----------------------------------- MancalaGame
    *              p1Houses                   game
    * </pre>
    */
   
   public static final String PROPERTY_GAME = "game";

   private MancalaGame game = null;

   public MancalaGame getGame()
   {
      return this.game;
   }

   public boolean setGame(MancalaGame value)
   {
      boolean changed = false;
      
      if (this.game != value)
      {
         MancalaGame oldValue = this.game;
         
         if (this.game != null)
         {
            this.game = null;
            oldValue.withoutP1Houses(this);
         }
         
         this.game = value;
         
         if (value != null)
         {
            value.withP1Houses(this);
         }
         
         firePropertyChange(PROPERTY_GAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public House withGame(MancalaGame value)
   {
      setGame(value);
      return this;
   } 

   public MancalaGame createGame()
   {
      MancalaGame value = new MancalaGame();
      withGame(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * House ----------------------------------- House
    *              houseAcross                   house
    * </pre>
    */
   
   public static final String PROPERTY_HOUSE = "house";

   private House house = null;

   public House getHouse()
   {
      return this.house;
   }
   public HouseSet getHouseTransitive()
   {
      HouseSet result = new HouseSet().with(this);
      return result.getHouseTransitive();
   }


   public boolean setHouse(House value)
   {
      boolean changed = false;
      
      if (this.house != value)
      {
         House oldValue = this.house;
         
         if (this.house != null)
         {
            this.house = null;
            oldValue.setHouseAcross(null);
         }
         
         this.house = value;
         
         if (value != null)
         {
            value.withHouseAcross(this);
         }
         
         firePropertyChange(PROPERTY_HOUSE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public House withHouse(House value)
   {
      setHouse(value);
      return this;
   } 

   public House createHouse()
   {
      House value = new House();
      withHouse(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * House ----------------------------------- House
    *              house                   houseAcross
    * </pre>
    */
   
   public static final String PROPERTY_HOUSEACROSS = "houseAcross";

   private House houseAcross = null;

   public House getHouseAcross()
   {
      return this.houseAcross;
   }
   public HouseSet getHouseAcrossTransitive()
   {
      HouseSet result = new HouseSet().with(this);
      return result.getHouseAcrossTransitive();
   }


   public boolean setHouseAcross(House value)
   {
      boolean changed = false;
      
      if (this.houseAcross != value)
      {
         House oldValue = this.houseAcross;
         
         if (this.houseAcross != null)
         {
            this.houseAcross = null;
            oldValue.setHouse(null);
         }
         
         this.houseAcross = value;
         
         if (value != null)
         {
            value.withHouse(this);
         }
         
         firePropertyChange(PROPERTY_HOUSEACROSS, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public House withHouseAcross(House value)
   {
      setHouseAcross(value);
      return this;
   } 

   public House createHouseAcross()
   {
      House value = new House();
      withHouseAcross(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * House ----------------------------------- House
    *              houseNext                   housePrev
    * </pre>
    */
   
   public static final String PROPERTY_HOUSEPREV = "housePrev";

   private House housePrev = null;

   public House getHousePrev()
   {
      return this.housePrev;
   }
   public HouseSet getHousePrevTransitive()
   {
      HouseSet result = new HouseSet().with(this);
      return result.getHousePrevTransitive();
   }


   public boolean setHousePrev(House value)
   {
      boolean changed = false;
      
      if (this.housePrev != value)
      {
         House oldValue = this.housePrev;
         
         if (this.housePrev != null)
         {
            this.housePrev = null;
            oldValue.setHouseNext(null);
         }
         
         this.housePrev = value;
         
         if (value != null)
         {
            value.withHouseNext(this);
         }
         
         firePropertyChange(PROPERTY_HOUSEPREV, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public House withHousePrev(House value)
   {
      setHousePrev(value);
      return this;
   } 

   public House createHousePrev()
   {
      House value = new House();
      withHousePrev(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * House ----------------------------------- House
    *              housePrev                   houseNext
    * </pre>
    */
   
   public static final String PROPERTY_HOUSENEXT = "houseNext";

   private House houseNext = null;

   public House getHouseNext()
   {
      return this.houseNext;
   }
   public HouseSet getHouseNextTransitive()
   {
      HouseSet result = new HouseSet().with(this);
      return result.getHouseNextTransitive();
   }


   public boolean setHouseNext(House value)
   {
      boolean changed = false;
      
      if (this.houseNext != value)
      {
         House oldValue = this.houseNext;
         
         if (this.houseNext != null)
         {
            this.houseNext = null;
            oldValue.setHousePrev(null);
         }
         
         this.houseNext = value;
         
         if (value != null)
         {
            value.withHousePrev(this);
         }
         
         firePropertyChange(PROPERTY_HOUSENEXT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public House withHouseNext(House value)
   {
      setHouseNext(value);
      return this;
   } 

   public House createHouseNext()
   {
      House value = new House();
      withHouseNext(value);
      return value;
   } 
}
