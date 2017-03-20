package i.love.sdmlib.util;

import org.sdmlib.models.pattern.PatternObject;
import i.love.sdmlib.Player;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import i.love.sdmlib.util.MancalaGamePO;
import i.love.sdmlib.MancalaGame;
import i.love.sdmlib.util.PlayerPO;

public class PlayerPO extends PatternObject<PlayerPO, Player>
{

    public PlayerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PlayerSet matches = new PlayerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Player) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PlayerPO(){
      newInstance(null);
   }

   public PlayerPO(Player... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PlayerPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PlayerPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PlayerPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public MancalaGamePO createGamePO()
   {
      MancalaGamePO result = new MancalaGamePO(new MancalaGame[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public MancalaGamePO createGamePO(String modifier)
   {
      MancalaGamePO result = new MancalaGamePO(new MancalaGame[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public PlayerPO createGameLink(MancalaGamePO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME);
   }

   public PlayerPO createGameLink(MancalaGamePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME, modifier);
   }

   public MancalaGame getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getGame();
      }
      return null;
   }

}
