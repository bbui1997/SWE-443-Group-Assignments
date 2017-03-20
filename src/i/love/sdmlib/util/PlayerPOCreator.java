package i.love.sdmlib.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import i.love.sdmlib.Player;

public class PlayerPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PlayerPO(new Player[]{});
      } else {
          return new PlayerPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return i.love.sdmlib.util.CreatorCreator.createIdMap(sessionID);
   }
}
