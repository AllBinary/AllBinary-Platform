/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.game.part.weapon;

import javax.microedition.lcdui.Graphics;

import abcs.logic.basic.NotImplemented;
import allbinary.animation.Animation;
import allbinary.game.combat.weapon.WeaponProperties;
import allbinary.game.part.PartInterface;
import allbinary.game.score.ScoreableInterface;
import allbinary.graphics.RelativeRelationship;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.view.ViewPosition;

public class BasicWeaponPart 
    implements PartInterface, SalvoInterface 
{
   private Animation animationInterface;
   
   private AllBinaryLayer ownerLayerInterface;

   private WeaponProperties weaponProperties;

   private ScoreableInterface scoreableInterface;

   protected RelativeRelationship relativeRelationship;

   public BasicWeaponPart(Animation animationInterface) {
      this.setAnimationInterface(animationInterface);
   }

   public BasicWeaponPart(
           Animation animationInterface, 
           AllBinaryLayer sourceLayerInterface, 
           WeaponProperties weaponProperties, 
           ScoreableInterface scoreableInterface, 
           RelativeRelationship relativeRelationship)
   {
      this.init(sourceLayerInterface, weaponProperties, scoreableInterface, relativeRelationship);
      this.setAnimationInterface(animationInterface);
   }
   
   public void init(AllBinaryLayer sourceLayerInterface, 
           WeaponProperties weaponProperties, 
           ScoreableInterface scoreableInterface, 
           RelativeRelationship relativeRelationship) 
   {

      this.setOwnerLayerInterface(sourceLayerInterface);
      
        this.setWeaponProperties(weaponProperties);
      
      this.scoreableInterface = scoreableInterface;
            
      this.relativeRelationship = relativeRelationship;
   }
   
   public void process(AllBinaryLayerManager allbinaryLayerManager, short angle)
           throws Exception {
      this.process(allbinaryLayerManager, angle, this.getWeaponProperties(), this.scoreableInterface);
   }

   public void process(AllBinaryLayerManager allbinaryLayerManager, short angle, WeaponProperties weaponProperties, ScoreableInterface scoreableInterface)
           throws Exception {
      throw new Exception(NotImplemented.NAME);
   }

   public AllBinaryLayer getOwnerLayerInterface() {
      return ownerLayerInterface;
   }

   public void setOwnerLayerInterface(AllBinaryLayer ownerLayerInterface) {
      this.ownerLayerInterface = ownerLayerInterface;
   }
   
   public Animation getAnimationInterface() {
      return animationInterface;
   }

   public void setAnimationInterface(Animation animationInterface) {
      this.animationInterface = animationInterface;
   }
   
   public void paint(Graphics graphics) {
      
       ViewPosition viewPosition =  this.getOwnerLayerInterface().getViewPosition();
       int viewX = viewPosition.getX();
       int viewY = viewPosition.getY();
      
       this.animationInterface.paint(graphics, viewX, viewY);
         //this.relativeRelationship.getX(), this.relativeRelationship.getY());
   }

   public void paintThreed(Graphics graphics)
   {
   }

    /**
     * @return the weaponProperties
     */
    public WeaponProperties getWeaponProperties()
    {
        return weaponProperties;
    }

    /**
     * @param weaponProperties the weaponProperties to set
     */
    public void setWeaponProperties(WeaponProperties weaponProperties)
    {
        this.weaponProperties = weaponProperties;
    }
}

