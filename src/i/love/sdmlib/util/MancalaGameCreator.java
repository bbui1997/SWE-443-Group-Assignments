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

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import i.love.sdmlib.MancalaGame;
import de.uniks.networkparser.IdMap;
import i.love.sdmlib.Player;
import i.love.sdmlib.House;

public class MancalaGameCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      MancalaGame.PROPERTY_PLAYERS,
      MancalaGame.PROPERTY_P1HOUSES,
      MancalaGame.PROPERTY_P2HOUSES,
      MancalaGame.PROPERTY_P1STORE,
      MancalaGame.PROPERTY_P2STORE,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MancalaGame();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (MancalaGame.PROPERTY_PLAYERS.equalsIgnoreCase(attribute))
      {
         return ((MancalaGame) target).getPlayers();
      }

      if (MancalaGame.PROPERTY_P1HOUSES.equalsIgnoreCase(attribute))
      {
         return ((MancalaGame) target).getP1Houses();
      }

      if (MancalaGame.PROPERTY_P2HOUSES.equalsIgnoreCase(attribute))
      {
         return ((MancalaGame) target).getP2Houses();
      }

      if (MancalaGame.PROPERTY_P1STORE.equalsIgnoreCase(attribute))
      {
         return ((MancalaGame) target).getP1Store();
      }

      if (MancalaGame.PROPERTY_P2STORE.equalsIgnoreCase(attribute))
      {
         return ((MancalaGame) target).getP2Store();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (MancalaGame.PROPERTY_PLAYERS.equalsIgnoreCase(attrName))
      {
         ((MancalaGame) target).withPlayers((Player) value);
         return true;
      }
      
      if ((MancalaGame.PROPERTY_PLAYERS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((MancalaGame) target).withoutPlayers((Player) value);
         return true;
      }

      if (MancalaGame.PROPERTY_P1HOUSES.equalsIgnoreCase(attrName))
      {
         ((MancalaGame) target).withP1Houses((House) value);
         return true;
      }
      
      if ((MancalaGame.PROPERTY_P1HOUSES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((MancalaGame) target).withoutP1Houses((House) value);
         return true;
      }

      if (MancalaGame.PROPERTY_P2HOUSES.equalsIgnoreCase(attrName))
      {
         ((MancalaGame) target).withP2Houses((House) value);
         return true;
      }
      
      if ((MancalaGame.PROPERTY_P2HOUSES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((MancalaGame) target).withoutP2Houses((House) value);
         return true;
      }

      if (MancalaGame.PROPERTY_P1STORE.equalsIgnoreCase(attrName))
      {
         ((MancalaGame) target).setP1Store((House) value);
         return true;
      }

      if (MancalaGame.PROPERTY_P2STORE.equalsIgnoreCase(attrName))
      {
         ((MancalaGame) target).setP2Store((House) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return i.love.sdmlib.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((MancalaGame) entity).removeYou();
   }
}
