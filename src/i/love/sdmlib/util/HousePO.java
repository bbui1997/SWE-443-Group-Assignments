package i.love.sdmlib.util;

import org.sdmlib.models.pattern.PatternObject;
import i.love.sdmlib.House;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import i.love.sdmlib.util.MancalaGamePO;
import i.love.sdmlib.MancalaGame;
import i.love.sdmlib.util.HousePO;

public class HousePO extends PatternObject<HousePO, House>
{

    public HouseSet allMatches()
   {
      this.setDoAllMatches(true);
      
      HouseSet matches = new HouseSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((House) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public HousePO(){
      newInstance(null);
   }

   public HousePO(House... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public HousePO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void takeOppositePebbles()
   {
      if (this.getPattern().getHasMatch())
      {
          ((House) getCurrentMatch()).takeOppositePebbles();
      }
   }

   
   //==========================================================================
   
   public void redistributeCounterClockwise()
   {
      if (this.getPattern().getHasMatch())
      {
          ((House) getCurrentMatch()).redistributeCounterClockwise();
      }
   }

   
   //==========================================================================
   
   public void redistributeCounterClockwiseRecurse(int x, Object p0)
   {
      if (this.getPattern().getHasMatch())
      {
          ((House) getCurrentMatch()).redistributeCounterClockwiseRecurse(x, p0);
      }
   }

   public HousePO createPebblesCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(House.PROPERTY_PEBBLES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public HousePO createPebblesCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(House.PROPERTY_PEBBLES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public HousePO createPebblesAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(House.PROPERTY_PEBBLES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getPebbles()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((House) getCurrentMatch()).getPebbles();
      }
      return 0;
   }
   
   public HousePO withPebbles(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((House) getCurrentMatch()).setPebbles(value);
      }
      return this;
   }
   
   public HousePO createIsStoreCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(House.PROPERTY_ISSTORE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public HousePO createIsStoreAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(House.PROPERTY_ISSTORE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getIsStore()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((House) getCurrentMatch()).isIsStore();
      }
      return false;
   }
   
   public HousePO withIsStore(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((House) getCurrentMatch()).setIsStore(value);
      }
      return this;
   }
   
   public MancalaGamePO createGamePO()
   {
      MancalaGamePO result = new MancalaGamePO(new MancalaGame[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_GAME, result);
      
      return result;
   }

   public MancalaGamePO createGamePO(String modifier)
   {
      MancalaGamePO result = new MancalaGamePO(new MancalaGame[]{});
      
      result.setModifier(modifier);
      super.hasLink(House.PROPERTY_GAME, result);
      
      return result;
   }

   public HousePO createGameLink(MancalaGamePO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_GAME);
   }

   public HousePO createGameLink(MancalaGamePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_GAME, modifier);
   }

   public MancalaGame getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((House) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public HousePO createHousePO()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_HOUSE, result);
      
      return result;
   }

   public HousePO createHousePO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(House.PROPERTY_HOUSE, result);
      
      return result;
   }

   public HousePO createHouseLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_HOUSE);
   }

   public HousePO createHouseLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_HOUSE, modifier);
   }

   public House getHouse()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((House) this.getCurrentMatch()).getHouse();
      }
      return null;
   }

   public HousePO createHouseAcrossPO()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_HOUSEACROSS, result);
      
      return result;
   }

   public HousePO createHouseAcrossPO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(House.PROPERTY_HOUSEACROSS, result);
      
      return result;
   }

   public HousePO createHouseAcrossLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_HOUSEACROSS);
   }

   public HousePO createHouseAcrossLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_HOUSEACROSS, modifier);
   }

   public House getHouseAcross()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((House) this.getCurrentMatch()).getHouseAcross();
      }
      return null;
   }

   public HousePO createHousePrevPO()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_HOUSEPREV, result);
      
      return result;
   }

   public HousePO createHousePrevPO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(House.PROPERTY_HOUSEPREV, result);
      
      return result;
   }

   public HousePO createHousePrevLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_HOUSEPREV);
   }

   public HousePO createHousePrevLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_HOUSEPREV, modifier);
   }

   public House getHousePrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((House) this.getCurrentMatch()).getHousePrev();
      }
      return null;
   }

   public HousePO createHouseNextPO()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_HOUSENEXT, result);
      
      return result;
   }

   public HousePO createHouseNextPO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(House.PROPERTY_HOUSENEXT, result);
      
      return result;
   }

   public HousePO createHouseNextLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_HOUSENEXT);
   }

   public HousePO createHouseNextLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_HOUSENEXT, modifier);
   }

   public House getHouseNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((House) this.getCurrentMatch()).getHouseNext();
      }
      return null;
   }

}
