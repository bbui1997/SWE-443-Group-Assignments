package i.love.sdmlib.util;

import org.sdmlib.models.pattern.PatternObject;
import i.love.sdmlib.MancalaGame;
import i.love.sdmlib.util.PlayerPO;
import i.love.sdmlib.Player;
import i.love.sdmlib.util.MancalaGamePO;
import i.love.sdmlib.util.PlayerSet;
import i.love.sdmlib.util.HousePO;
import i.love.sdmlib.House;
import i.love.sdmlib.util.HouseSet;

public class MancalaGamePO extends PatternObject<MancalaGamePO, MancalaGame>
{

    public MancalaGameSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MancalaGameSet matches = new MancalaGameSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MancalaGame) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MancalaGamePO(){
      newInstance(null);
   }

   public MancalaGamePO(MancalaGame... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public MancalaGamePO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void makeBoard(int x)
   {
      if (this.getPattern().getHasMatch())
      {
          ((MancalaGame) getCurrentMatch()).makeBoard(x);
      }
   }

   public PlayerPO createPlayersPO()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MancalaGame.PROPERTY_PLAYERS, result);
      
      return result;
   }

   public PlayerPO createPlayersPO(String modifier)
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(modifier);
      super.hasLink(MancalaGame.PROPERTY_PLAYERS, result);
      
      return result;
   }

   public MancalaGamePO createPlayersLink(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, MancalaGame.PROPERTY_PLAYERS);
   }

   public MancalaGamePO createPlayersLink(PlayerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, MancalaGame.PROPERTY_PLAYERS, modifier);
   }

   public PlayerSet getPlayers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MancalaGame) this.getCurrentMatch()).getPlayers();
      }
      return null;
   }

   public HousePO createP1HousesPO()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MancalaGame.PROPERTY_P1HOUSES, result);
      
      return result;
   }

   public HousePO createP1HousesPO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(MancalaGame.PROPERTY_P1HOUSES, result);
      
      return result;
   }

   public MancalaGamePO createP1HousesLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, MancalaGame.PROPERTY_P1HOUSES);
   }

   public MancalaGamePO createP1HousesLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, MancalaGame.PROPERTY_P1HOUSES, modifier);
   }

   public HouseSet getP1Houses()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MancalaGame) this.getCurrentMatch()).getP1Houses();
      }
      return null;
   }

   public HousePO createP2HousesPO()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MancalaGame.PROPERTY_P2HOUSES, result);
      
      return result;
   }

   public HousePO createP2HousesPO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(MancalaGame.PROPERTY_P2HOUSES, result);
      
      return result;
   }

   public MancalaGamePO createP2HousesLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, MancalaGame.PROPERTY_P2HOUSES);
   }

   public MancalaGamePO createP2HousesLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, MancalaGame.PROPERTY_P2HOUSES, modifier);
   }

   public HouseSet getP2Houses()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MancalaGame) this.getCurrentMatch()).getP2Houses();
      }
      return null;
   }

   public HousePO createP1StorePO()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MancalaGame.PROPERTY_P1STORE, result);
      
      return result;
   }

   public HousePO createP1StorePO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(MancalaGame.PROPERTY_P1STORE, result);
      
      return result;
   }

   public MancalaGamePO createP1StoreLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, MancalaGame.PROPERTY_P1STORE);
   }

   public MancalaGamePO createP1StoreLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, MancalaGame.PROPERTY_P1STORE, modifier);
   }

   public House getP1Store()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MancalaGame) this.getCurrentMatch()).getP1Store();
      }
      return null;
   }

   public HousePO createP2StorePO()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MancalaGame.PROPERTY_P2STORE, result);
      
      return result;
   }

   public HousePO createP2StorePO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(MancalaGame.PROPERTY_P2STORE, result);
      
      return result;
   }

   public MancalaGamePO createP2StoreLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, MancalaGame.PROPERTY_P2STORE);
   }

   public MancalaGamePO createP2StoreLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, MancalaGame.PROPERTY_P2STORE, modifier);
   }

   public House getP2Store()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MancalaGame) this.getCurrentMatch()).getP2Store();
      }
      return null;
   }

}
