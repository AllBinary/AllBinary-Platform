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
package org.allbinary.game.layer.weapon;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.multiplayer.layer.MultiPlayerGameLayer;
import org.allbinary.physics.movement.Movement;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.animation.Animation;
import org.allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.game.collision.CollidableBaseBehavior;
import org.allbinary.game.combat.damage.ExplosionResources;
import org.allbinary.game.combat.destroy.DestroyedLayerProcessor;
import org.allbinary.game.combat.weapon.WeaponProperties;
import org.allbinary.game.configuration.GameConfigurationUtil;
import org.allbinary.game.identification.BasicGroupFactory;
import org.allbinary.game.score.NoScoreable;
import org.allbinary.game.score.ScoreableInterface;
import org.allbinary.game.tick.TickableInterface;
import org.allbinary.graphics.Rectangle;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.view.ViewPosition;
import org.allbinary.game.multiplayer.layer.RemoteInfo;

public class WeaponLayer 
extends MultiPlayerGameLayer
implements TickableInterface
{
    // private static final int MIN = 200;

    private Animation animationInterface;
    protected Animation initAnimationInterface;
    private Animation destroyedAnimationInterface;

    private AllBinaryLayer sourceLayerInterface;

    // Initialized upon init
    // private int angle;
    protected int totalDamage;
    private int initDamage;
    private boolean destroyed = true;
    protected ScoreableInterface scoreableInterface = NoScoreable.getInstance();
    private Movement movement;

    private WeaponProperties weaponProperties;

    private final int multiPlayerType;

    /*
    public WeaponLayer(LayerInterface sourceLayerInterface,
            Movement movement,
            AnimationInterface animationInterface,
            Rectangle rectangle,
            short angle, int damage, ScoreableInterface scoreable)
            throws Exception
    {
       this(sourceLayerInterface,
               movement,
               animationInterface,
               rectangle,
               new ViewPosition(),
               angle, damage, scoreable);
    }
    */

    /*
    public WeaponLayer(LayerInterface sourceLayerInterface,
            Movement movement,
            AnimationInterface animationInterface,
            Rectangle rectangle,
            ViewPosition viewPosition,
            short angle, int damage, ScoreableInterface scoreable)
            throws Exception
    {
       super(Group.NONE, rectangle, viewPosition, true);

       this.initAnimationInterface = animationInterface;
       this.setAnimationInterface(animationInterface);
       this.collided = false;

       this.destroyedAnimationInterface = FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().getProcedural(ExplosionResources.THIRD_EXPLOSION_RESOURCE).getInstance(null);

       this.movement = movement;

       this.collisionHelper = CollidableHelperFactory.getInstance();

       this.init(sourceLayerInterface, angle, damage, 0, scoreable);
    }

    public WeaponLayer(
            Movement movement,
            AnimationInterface animationInterface,
            Rectangle rectangle,
            short angle, int damage, ScoreableInterface scoreable)
            throws Exception
    {
       this(movement,
               animationInterface,
               rectangle,
               new ViewPosition());
    }
    */

    protected WeaponLayer(Movement movement, Animation animationInterface,
            Rectangle rectangle, ViewPosition viewPosition) throws Exception
    {
        this(movement, animationInterface, 
                FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance()
                .getProcedural(ExplosionResources.getInstance().THIRD_EXPLOSION_RESOURCE)
                .getInstance(null), rectangle, viewPosition);
    }

    protected WeaponLayer(Movement movement, Animation animationInterface,
            Animation destroyedAnimationInterface, Rectangle rectangle,
            ViewPosition viewPosition) throws Exception
    {
        this(new RemoteInfo(StringUtil.getInstance().EMPTY_STRING, -1, -1, -1), -1, movement, animationInterface, destroyedAnimationInterface, rectangle, viewPosition);
    }

    protected WeaponLayer(RemoteInfo remoteInfo, int multiPlayerType, Movement movement, Animation animationInterface,
            Animation destroyedAnimationInterface, Rectangle rectangle,
            ViewPosition viewPosition)
            throws Exception
    {
        super(remoteInfo, BasicGroupFactory.getInstance().NONE_ARRAY, rectangle, viewPosition);
        // super(Group.NONE, rectangle, viewPosition, true);

        this.initAnimationInterface = animationInterface;
        this.setAnimationInterface(animationInterface);

        this.destroyedAnimationInterface = destroyedAnimationInterface;

        this.movement = movement;

        this.multiPlayerType = multiPlayerType;
    }

    private CollidableWeaponBehavior collidableWeaponBehavior;    

    public void setCollidableInferface(CollidableBaseBehavior collidableInferface)
    {
        super.setCollidableInferface(collidableInferface);
        this.collidableWeaponBehavior = (CollidableWeaponBehavior) this.getCollidableInferface();
    }
    
    public void init(int x, int y, int z) throws Exception
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void init(AllBinaryLayer sourceLayerInterface, short angle, short otherAngle,
            WeaponProperties weaponProperties, ScoreableInterface scoreable) throws Exception
    {
        /*
        if (weaponProperties.getDamage() < MIN)
        {
           throw new Exception("Initializing below minimum damage of: " + MIN);
        }

        if(weaponProperties.getDamage() > MAX)
        {
            throw new Exception("Initializing above max damage with: " + weaponProperties.getDamage());
        }
        */

        this.setWeaponProperties(weaponProperties);

        this.setReadyForExplosion(false);

        this.setAnimationInterface(this.getInitAnimationInterface());

        this.setOwnerLayerInterface(sourceLayerInterface);

        this.collidableWeaponBehavior.init(sourceLayerInterface);

        this.setGroupInterface();

        // this.angle = angle;

        if (scoreable != null)
        {
            this.scoreableInterface = scoreable;
        }

        // basicVelocityProperties.getVelocityXBasicDecimal().set(0);
        // basicVelocityProperties.getVelocityYBasicDecimal().set(0);
        this.totalDamage = 0;
        this.initDamage = weaponProperties.getDamage();
        this.setDestroyed(false);

        this.movement.init(weaponProperties.getSpeed(), angle, otherAngle);
    }

    // This is very important - does the owner of the WeaponLayer have the same team
    // Subgroups are not taken if enemy is the base group.
    protected void setGroupInterface()
    {
        final BasicGroupFactory basicGroupFactory = BasicGroupFactory.getInstance();
        if(sourceLayerInterface.getGroupInterface()[0] == basicGroupFactory.ENEMY) {
            this.setGroupInterface(basicGroupFactory.ENEMY_ARRAY);
        } else {
            this.setGroupInterface(sourceLayerInterface.getGroupInterface());
        }
    }

    public void processTick(AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {
        //final GameStrings gameStrings = GameStrings.getInstance();
        
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, gameStrings.PROCESS_TICK));

        if (this.isExhausted() && !this.isDestroyed())
        {
            //PreLogUtil.put("Explosion - Processing", this, gameStrings.PROCESS_TICK);
            
            if (this.isReadyForExplosion())
            {
                //PreLogUtil.put("Explosion - Ready Processing", this, gameStrings.PROCESS_TICK);
                
                IndexedAnimation indexedAnimationInterface = 
                    (IndexedAnimation) this.getAnimationInterface();

                int currentFrame = indexedAnimationInterface.getFrame();

                if (currentFrame < indexedAnimationInterface.getAnimationSize() - 1)
                {
                    //PreLogUtil.put("Explosion - " + currentFrame + " < " + (indexedAnimationInterface.getAnimationSize() - 1), this, gameStrings.PROCESS_TICK);
                    
                    //this.slow();
                    
                    // show next frame in explosion
                    indexedAnimationInterface.nextFrame();
                } else
                {
                    //LogUtil.put(LogFactory.getInstance("Explosion - End", this, tickableStrings.PROCESS_TICK));
                    //PreLogUtil.put("Explosion - End", this, gameStrings.PROCESS_TICK);

                    this.setDestroyed(true);
                }
            } else
            {                
                if (((CollidableWeaponBehavior) this.getCollidableInferface()).isCollided())
                {
                    // LogUtil.put(LogFactory.getInstance("Explosion - Begin", this, tickableStrings.PROCESS_TICK));
                    //PreLogUtil.put("Explosion - Begin", this, gameStrings.PROCESS_TICK);
                    
                    this.setAnimationInterface(this.destroyedAnimationInterface);
                    this.getMovement().stop();
                    this.setReadyForExplosion(true);
                } else
                {
                    // LogUtil.put(LogFactory.getInstance("Explosion - Begin and End", this, tickableStrings.PROCESS_TICK));
                    //PreLogUtil.put("Explosion - Begin and End", this, gameStrings.PROCESS_TICK);
                    
                    this.setDestroyed(true);
                }
            }
        }

        this.movement.process(this);

        // decrease the beams power over a distance
        this.totalDamage += this.weaponProperties.getDissipation();
    }

    /*
    private void slow()
    {
        Movement movement = this.getMovement();

        BasicVelocityProperties velocityProperties = 
            ((VelocityInterfaceCompositeInterface) movement).getVelocityProperties();

        BasicDecimal xBasicDecimal = velocityProperties.getVelocityXBasicDecimal();
        BasicDecimal yBasicDecimal = velocityProperties.getVelocityYBasicDecimal(); 

        //xBasicDecimal.multiply(4);
        xBasicDecimal.divide(2);

        //yBasicDecimal.multiply(4);
        yBasicDecimal.divide(2);
    }
    */
    
    public boolean isDestroyed()
    {
        return this.destroyed;
    }

    public void damage(int damage, int damageType)
    {
        this.totalDamage += damage * damage;
        // LogUtil.put(LogFactory.getInstance("Damage: " + damage +
        // " Points Left: " + (this.getInitDamage() - this.totalDamage), this,
        // "damage"));
    }

    protected void givePoints(int total)
    {
        scoreableInterface.addPoints(GameConfigurationUtil.getInstance().getCompetitionValue() * total);
    }

    // Is damage value gone
    protected boolean isExhausted()
    {
        // Less than 0 most likely means that it collided a bunch before last tick
        // as such it went beyond the positive max
        if (this.totalDamage > this.getInitDamage() || this.totalDamage < 0)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public int getDamage(int damageType)
    {
        if (!this.isExhausted())
        {
            int total = this.getInitDamage() - this.totalDamage;

            // if(total > MAX)
            // LogUtil.put(LogFactory.getInstance("Damage: " + total + " init: "
            // + this.getInitDamage() + " totalDamage: " + totalDamage, this,
            // "damage"));

            this.givePoints(total);
            return total;
        }

        return 0;
    }

    public void setDestroyed(boolean destroyed)
    {
        this.destroyed = destroyed;

        if (this.isDestroyed())
        {
            this.scoreableInterface = NoScoreable.getInstance();
            DestroyedLayerProcessor.getInstance().add(this);
            /*
            if (this.collided)
            {
               AllBinaryVibration.getInstance().vibrate(
                  GameConfigurationCentral.getInstance().VIBRATION.getValue().intValue() * 100, 0, 0);
            }
            */
        }
    }

    /*
    private void setInitDamage(int initDamage)
    {
        this.initDamage = initDamage;
    }
    */

    protected int getInitDamage()
    {
        return initDamage;
    }

    protected void setAnimationInterface(Animation animationInterface)
    {
        this.animationInterface = animationInterface;
    }

    protected Animation getAnimationInterface()
    {
        return animationInterface;
    }

    public Animation getInitAnimationInterface()
    {
        return initAnimationInterface;
    }

    public void setInitAnimationInterface(Animation initAnimationInterface)
    {
        this.initAnimationInterface = initAnimationInterface;
    }

    public Animation getDestroyedAnimationInterface()
    {
        return destroyedAnimationInterface;
    }

    /**
     * @return the sourceLayerInterface
     */
    public AllBinaryLayer getOwnerLayerInterface()
    {
        return sourceLayerInterface;
    }

    /**
     * @param sourceLayerInterface
     *            the sourceLayerInterface to set
     */
    public void setOwnerLayerInterface(AllBinaryLayer sourceLayerInterface)
    {
        this.sourceLayerInterface = sourceLayerInterface;
    }

    public void paint(Graphics graphics)
    {
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "paint"));

        ViewPosition viewPosition = this.getViewPosition();
        // int viewX = viewPosition.getX();
        // int viewY = viewPosition.getY();

        // LogUtil.put(LogFactory.getInstance("viewX: " + viewX + " viewY: " + viewY, this, "paint"));

        //TWB - the offset does not make sense? is collision offset? 
        this.animationInterface.paint(graphics, 
                viewPosition.getX() - this.getHalfWidth(),
                viewPosition.getY() - this.getHalfHeight());

        // super.paint(graphics);
    }

    public void paintThreed(Graphics graphics)
    {
        ViewPosition viewPosition = this.getViewPosition();

        this.animationInterface.paintThreed(graphics, 
                viewPosition.getX() - this.getHalfWidth(),
                viewPosition.getY() - this.getHalfHeight(),
                viewPosition.getZ() - this.getHalfDepth());
    }

    /**
     * @return the movement
     */
    public Movement getMovement()
    {
        return movement;
    }

    /**
     * @param movement
     *            the movement to set
     */
    public void setMovement(Movement movement)
    {
        this.movement = movement;
    }

    public int getType()
    {
        return getStaticType();
    }

    public static int getStaticType()
    {
        return 0;
    }

    public int getMultiPlayerType()
    {
        return multiPlayerType;
    }

    public void setWeaponProperties(WeaponProperties weaponProperties)
    {
        this.weaponProperties = weaponProperties;
    }

    public WeaponProperties getWeaponProperties()
    {
        return weaponProperties;
    }

    public boolean implmentsTickableInterface()
    {
        return true;
    }
    
    public void set(GL gl) throws Exception
    {
        //OpenGLSurfaceChangedInterface
        //OpenGLSurfaceChangedInterface openGLSurfaceChangedInterface = ;        

        this.initAnimationInterface.set(gl);

        //openGLSurfaceChangedInterface = 
          //      (OpenGLSurfaceChangedInterface) this.destroyedAnimationInterface;        

        //openGLSurfaceChangedInterface.set(gl);
    }
}