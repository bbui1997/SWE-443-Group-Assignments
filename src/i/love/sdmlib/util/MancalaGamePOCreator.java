package i.love.sdmlib.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import i.love.sdmlib.MancalaGame;

public class MancalaGamePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MancalaGamePO(new MancalaGame[]{});
      } else {
          return new MancalaGamePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return i.love.sdmlib.util.CreatorCreator.createIdMap(sessionID);
   }
}
