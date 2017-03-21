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
import i.love.sdmlib.util.PlayerSet;
import i.love.sdmlib.Player;
import i.love.sdmlib.util.HouseSet;
import i.love.sdmlib.House;
   /**
    * 
    * @see <a href='../../../../mancala/src/main/java/Model.java'>Model.java</a>
 */
   public  class MancalaGame implements SendableEntity
{

   
   //==========================================================================
   public void makeBoard( int x )
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
      withoutPlayers(this.getPlayers().toArray(new Player[this.getPlayers().size()]));
      withoutP1Houses(this.getP1Houses().toArray(new House[this.getP1Houses().size()]));
      withoutP2Houses(this.getP2Houses().toArray(new House[this.getP2Houses().size()]));
      setP1Store(null);
      setP2Store(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * MancalaGame ----------------------------------- Player
    *              game                   players
    * </pre>
    */
   
   public static final String PROPERTY_PLAYERS = "players";

   private PlayerSet players = null;
   
   public PlayerSet getPlayers()
   {
      if (this.players == null)
      {
         return PlayerSet.EMPTY_SET;
      }
   
      return this.players;
   }

   public MancalaGame withPlayers(Player... value)
   {
      if(value==null){
         return this;
      }
      for (Player item : value)
      {
         if (item != null)
         {
            if (this.players == null)
            {
               this.players = new PlayerSet();
            }
            
            boolean changed = this.players.add (item);

            if (changed)
            {
               item.withGame(this);
               firePropertyChange(PROPERTY_PLAYERS, null, item);
            }
         }
      }
      return this;
   } 

   public MancalaGame withoutPlayers(Player... value)
   {
      for (Player item : value)
      {
         if ((this.players != null) && (item != null))
         {
            if (this.players.remove(item))
            {
               item.setGame(null);
               firePropertyChange(PROPERTY_PLAYERS, item, null);
            }
         }
      }
      return this;
   }

   public Player createPlayers()
   {
      Player value = new Player();
      withPlayers(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * MancalaGame ----------------------------------- House
    *              game                   p1Houses
    * </pre>
    */
   
   public static final String PROPERTY_P1HOUSES = "p1Houses";

   private HouseSet p1Houses = null;
   
   public HouseSet getP1Houses()
   {
      if (this.p1Houses == null)
      {
         return HouseSet.EMPTY_SET;
      }
   
      return this.p1Houses;
   }

   public MancalaGame withP1Houses(House... value)
   {
      if(value==null){
         return this;
      }
      for (House item : value)
      {
         if (item != null)
         {
            if (this.p1Houses == null)
            {
               this.p1Houses = new HouseSet();
            }
            
            boolean changed = this.p1Houses.add (item);

            if (changed)
            {
               item.withGame(this);
               firePropertyChange(PROPERTY_P1HOUSES, null, item);
            }
         }
      }
      return this;
   } 

   public MancalaGame withoutP1Houses(House... value)
   {
      for (House item : value)
      {
         if ((this.p1Houses != null) && (item != null))
         {
            if (this.p1Houses.remove(item))
            {
               item.setGame(null);
               firePropertyChange(PROPERTY_P1HOUSES, item, null);
            }
         }
      }
      return this;
   }

   public House createP1Houses()
   {
      House value = new House();
      withP1Houses(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * MancalaGame ----------------------------------- House
    *              game                   p2Houses
    * </pre>
    */
   
   public static final String PROPERTY_P2HOUSES = "p2Houses";

   private HouseSet p2Houses = null;
   
   public HouseSet getP2Houses()
   {
      if (this.p2Houses == null)
      {
         return HouseSet.EMPTY_SET;
      }
   
      return this.p2Houses;
   }

   public MancalaGame withP2Houses(House... value)
   {
      if(value==null){
         return this;
      }
      for (House item : value)
      {
         if (item != null)
         {
            if (this.p2Houses == null)
            {
               this.p2Houses = new HouseSet();
            }
            
            boolean changed = this.p2Houses.add (item);

            if (changed)
            {
               item.withGame(this);
               firePropertyChange(PROPERTY_P2HOUSES, null, item);
            }
         }
      }
      return this;
   } 

   public MancalaGame withoutP2Houses(House... value)
   {
      for (House item : value)
      {
         if ((this.p2Houses != null) && (item != null))
         {
            if (this.p2Houses.remove(item))
            {
               item.setGame(null);
               firePropertyChange(PROPERTY_P2HOUSES, item, null);
            }
         }
      }
      return this;
   }

   public House createP2Houses()
   {
      House value = new House();
      withP2Houses(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * MancalaGame ----------------------------------- House
    *              game                   p1Store
    * </pre>
    */
   
   public static final String PROPERTY_P1STORE = "p1Store";

   private House p1Store = null;

   public House getP1Store()
   {
      return this.p1Store;
   }

   public boolean setP1Store(House value)
   {
      boolean changed = false;
      
      if (this.p1Store != value)
      {
         House oldValue = this.p1Store;
         
         if (this.p1Store != null)
         {
            this.p1Store = null;
            oldValue.setGame(null);
         }
         
         this.p1Store = value;
         
         if (value != null)
         {
            value.withGame(this);
         }
         
         firePropertyChange(PROPERTY_P1STORE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public MancalaGame withP1Store(House value)
   {
      setP1Store(value);
      return this;
   } 

   public House createP1Store()
   {
      House value = new House();
      withP1Store(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * MancalaGame ----------------------------------- House
    *              game                   p2Store
    * </pre>
    */
   
   public static final String PROPERTY_P2STORE = "p2Store";

   private House p2Store = null;

   public House getP2Store()
   {
      return this.p2Store;
   }

   public boolean setP2Store(House value)
   {
      boolean changed = false;
      
      if (this.p2Store != value)
      {
         House oldValue = this.p2Store;
         
         if (this.p2Store != null)
         {
            this.p2Store = null;
            oldValue.setGame(null);
         }
         
         this.p2Store = value;
         
         if (value != null)
         {
            value.withGame(this);
         }
         
         firePropertyChange(PROPERTY_P2STORE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public MancalaGame withP2Store(House value)
   {
      setP2Store(value);
      return this;
   } 

   public House createP2Store()
   {
      House value = new House();
      withP2Store(value);
      return value;
   } 
}
