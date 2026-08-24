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
package org.allbinary.game.combat.damage;

import jsinterop.annotations.JsType;

import org.allbinary.game.layer.CollidableCompositeLayer;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class DamageUtil
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final DamageUtil instance = new DamageUtil();
    
    /**
     * @return the instance
     */
    @JsMethod
    public static DamageUtil getInstance() {
        return DamageUtil.instance;
    }

    @JsProperty
    public final String COLLIDE = "collide";
    //public final String IS_COLLISION = "isCollision";
    
   @JsConstructor
   private DamageUtil()
   {
   }
   
   @JsMethod
   public void process(
      DamageableInterface damageableInterface, 
      DamageableInterface damageableInterface2)
   throws Exception
   {
      damageableInterface.damage(damageableInterface2.getDamage(
              0 //RAM
              ),
              0 //RAM
              );
      /*
      if(damageableInterface instance of PointInterface)
      {
         PointInterface pointInterface = (PointInterface) damageableInterface;
         pointInterface.add(damageableInterface.getDamage(RAM));
      }*/
   }

   @JsMethod
   public void debugDamage(CollidableCompositeLayer collidableInterfaceCompositeInterface1, CollidableCompositeLayer collidableInterfaceCompositeInterface) 
       throws Exception
   {
           final CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer = (CollidableDestroyableDamageableLayer) collidableInterfaceCompositeInterface;
           int damage = collidableDestroyableDamageableLayer.getDamage(0);
           if(damage > 20000)
           {
               StringMaker stringBuffer = new StringMaker();
               
               stringBuffer.append("Collision ");
               stringBuffer.append(collidableInterfaceCompositeInterface1.toString());
               stringBuffer.append(" with: ");
               stringBuffer.append(collidableInterfaceCompositeInterface.toString());
               stringBuffer.append(" damage: ");
               stringBuffer.appendint(damage);
               
               this.logUtil.putF(stringBuffer.toString(), collidableInterfaceCompositeInterface1, this.COLLIDE);
           }
   }
}