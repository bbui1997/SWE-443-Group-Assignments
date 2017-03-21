package i.love.sdmlib.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new PlayerCreator());
      jsonIdMap.with(new PlayerPOCreator());
      jsonIdMap.with(new MancalaGameCreator());
      jsonIdMap.with(new MancalaGamePOCreator());
      jsonIdMap.with(new HouseCreator());
      jsonIdMap.with(new HousePOCreator());
      return jsonIdMap;
   }
}
