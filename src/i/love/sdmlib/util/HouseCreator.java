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
import i.love.sdmlib.House;
import de.uniks.networkparser.IdMap;
import i.love.sdmlib.MancalaGame;

public class HouseCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      House.PROPERTY_PEBBLES,
      House.PROPERTY_ISSTORE,
      House.PROPERTY_GAME,
      House.PROPERTY_HOUSE,
      House.PROPERTY_HOUSEACROSS,
      House.PROPERTY_HOUSEPREV,
      House.PROPERTY_HOUSENEXT,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new House();
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

      if (House.PROPERTY_PEBBLES.equalsIgnoreCase(attribute))
      {
         return ((House) target).getPebbles();
      }

      if (House.PROPERTY_ISSTORE.equalsIgnoreCase(attribute))
      {
         return ((House) target).isIsStore();
      }

      if (House.PROPERTY_GAME.equalsIgnoreCase(attribute))
      {
         return ((House) target).getGame();
      }

      if (House.PROPERTY_HOUSE.equalsIgnoreCase(attribute))
      {
         return ((House) target).getHouse();
      }

      if (House.PROPERTY_HOUSEACROSS.equalsIgnoreCase(attribute))
      {
         return ((House) target).getHouseAcross();
      }

      if (House.PROPERTY_HOUSEPREV.equalsIgnoreCase(attribute))
      {
         return ((House) target).getHousePrev();
      }

      if (House.PROPERTY_HOUSENEXT.equalsIgnoreCase(attribute))
      {
         return ((House) target).getHouseNext();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (House.PROPERTY_ISSTORE.equalsIgnoreCase(attrName))
      {
         ((House) target).setIsStore((Boolean) value);
         return true;
      }

      if (House.PROPERTY_PEBBLES.equalsIgnoreCase(attrName))
      {
         ((House) target).setPebbles(Integer.parseInt(value.toString()));
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (House.PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         ((House) target).setGame((MancalaGame) value);
         return true;
      }

      if (House.PROPERTY_HOUSE.equalsIgnoreCase(attrName))
      {
         ((House) target).setHouse((House) value);
         return true;
      }

      if (House.PROPERTY_HOUSEACROSS.equalsIgnoreCase(attrName))
      {
         ((House) target).setHouseAcross((House) value);
         return true;
      }

      if (House.PROPERTY_HOUSEPREV.equalsIgnoreCase(attrName))
      {
         ((House) target).setHousePrev((House) value);
         return true;
      }

      if (House.PROPERTY_HOUSENEXT.equalsIgnoreCase(attrName))
      {
         ((House) target).setHouseNext((House) value);
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
      ((House) entity).removeYou();
   }
}
