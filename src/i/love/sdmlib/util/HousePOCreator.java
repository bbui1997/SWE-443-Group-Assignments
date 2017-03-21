package i.love.sdmlib.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import i.love.sdmlib.House;

public class HousePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new HousePO(new House[]{});
      } else {
          return new HousePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return i.love.sdmlib.util.CreatorCreator.createIdMap(sessionID);
   }
}
