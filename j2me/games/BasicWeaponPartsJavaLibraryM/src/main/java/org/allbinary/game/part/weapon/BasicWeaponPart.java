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
package org.allbinary.game.part.weapon;

import javax.microedition.lcdui.Graphics;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.animation.Animation;
import org.allbinary.game.combat.weapon.WeaponProperties;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.part.PartInterface;
import org.allbinary.game.score.ScoreableInterface;
import org.allbinary.graphics.RelativeRelationship;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.view.ViewPosition;

public class BasicWeaponPart 
    implements PartInterface, SalvoInterface 
{
   private Animation animationInterface;
   
   private AllBinaryLayer ownerLayerInterface;

   private WeaponProperties weaponProperties;

   private ScoreableInterface scoreableInterface;
   
   protected RelativeRelationship relativeRelationship;

   public BasicWeaponPart(final Animation animationInterface) {
      this.setAnimationInterface(animationInterface);
   }

   public BasicWeaponPart(
           final Animation animationInterface, 
           final AllBinaryLayer sourceLayerInterface, 
           final WeaponProperties weaponProperties, 
           final ScoreableInterface scoreableInterface,
           final RelativeRelationship relativeRelationship)
   {
      this.init(sourceLayerInterface, weaponProperties, scoreableInterface, relativeRelationship);
      this.setAnimationInterface(animationInterface);
   }
   
   public void init(final AllBinaryLayer sourceLayerInterface, 
           final WeaponProperties weaponProperties, 
           final ScoreableInterface scoreableInterface,
           final RelativeRelationship relativeRelationship) 
   {

      this.setOwnerLayerInterface(sourceLayerInterface);
      
      this.setWeaponProperties(weaponProperties);
            
      this.relativeRelationship = relativeRelationship;
   }
   
   public void process(final AllBinaryLayerManager allbinaryLayerManager, final short angle, final short otherAngle)
           throws Exception {
       this.process(allbinaryLayerManager, angle, otherAngle, this.getWeaponProperties(), this.scoreableInterface);
   }

   public void process(final AllBinaryLayerManager allbinaryLayerManager, final short angle, final short otherAngle, final WeaponProperties weaponProperties, final ScoreableInterface scoreableInterface)
           throws Exception {
      throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
   }

   public AllBinaryLayer getOwnerLayerInterface() {
      return ownerLayerInterface;
   }

   public void setOwnerLayerInterface(final AllBinaryLayer ownerLayerInterface) {
      this.ownerLayerInterface = ownerLayerInterface;
   }
   
   public Animation getAnimationInterface() {
      return animationInterface;
   }

   public void setAnimationInterface(final Animation animationInterface) {
      this.animationInterface = animationInterface;
   }
   
   public void paint(final Graphics graphics) {
      
       final ViewPosition viewPosition =  this.getOwnerLayerInterface().getViewPosition();
       final int viewX = viewPosition.getX();
       final int viewY = viewPosition.getY();
      
       this.animationInterface.paint(graphics, viewX, viewY);
         //this.relativeRelationship.getX(), this.relativeRelationship.getY());
   }

   public void paintThreed(final Graphics graphics)
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
    public void setWeaponProperties(final WeaponProperties weaponProperties)
    {
        this.weaponProperties = weaponProperties;
    }
}

