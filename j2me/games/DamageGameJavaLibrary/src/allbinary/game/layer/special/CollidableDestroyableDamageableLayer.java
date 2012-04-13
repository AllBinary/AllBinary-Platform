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
package allbinary.game.layer.special;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.CollidableCompositeLayer;
import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactoryInterface;
import org.allbinary.image.opengles.OpenGLSurfaceChangedInterface;

import abcs.logic.basic.NotImplemented;
import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringMaker;
import allbinary.game.combat.damage.DamageableInterface;
import allbinary.game.combat.destroy.DestroyableInterface;
import allbinary.game.combat.destroy.event.DestroyedEvent;
import allbinary.game.identification.Group;
import allbinary.game.input.event.GameKeyEvent;
import allbinary.game.layer.pickup.PickupBehavior;
import allbinary.game.layer.pickup.PickupCompositeInterface;
import allbinary.game.part.PartInterface;
import allbinary.graphics.Rectangle;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.math.PositionStrings;
import allbinary.view.ViewPosition;

/*
 * The Game Layers/Objects requirements change a bunch from game to game.
 * As such Game Layers/Objects are very complex in nature.
 * As a result it is best to create a game specific composite game layer for a specific game.
 * Although, I have 3 classes that break this rule because I have not thought of composites that fit yet.
 * The 3 classes are MultiPlayerGameLayer -> CollidableDestroyableDamageableLayer -> CollidableCompositeLayer.
 * In the future these 3 classes should probably go away and be pushed into game Layer/Object composites/properties.
 * I will probably always have one magical game class for new features that don't fit into a composite yet regardless.
 * For now just think of the 3 classes (MultiPlayerGameLayer -> CollidableDestroyableDamageableLayer -> CollidableCompositeLayer)
 * as SpecialGameLayer or MagicalGameLayer.
 */
public class CollidableDestroyableDamageableLayer 
extends CollidableCompositeLayer 
implements DestroyableInterface, DamageableInterface, 
PickupCompositeInterface, SpecialGameInputInterface,
OpenGLSurfaceChangedInterface
{
    private Group groupInterface;
    private boolean readyForExplosion;

    private int initX;
    private int initY;
    private int initZ;

    protected PartInterface[] partInterfaceArray;
    private PickupBehavior pickupBehavior;
    
    public CollidableDestroyableDamageableLayer(
            Group groupInterface, 
            Rectangle layerInfo, ViewPosition viewPosition)
    {
        super(layerInfo, viewPosition);

        this.groupInterface = groupInterface;
    }

    public void initPosition() throws Exception
    {
        this.setPosition(this.initX, this.initY, this.initZ);
    }

    public void initPosition(int x, int y, int z) throws Exception
    {        
        this.initX = x;
        this.initY = y;
        this.initZ = z;
    }
    
    //Should be overridden
    public void paint(Graphics graphics)
    {
        //LogUtil.put(LogFactory.getInstance(NotImplemented.NAME, this, "paint"));
        super.paint(graphics);
    }

    public void damage(int damage, int damageType) throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    public int getDamage(int damageType) throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    public boolean isDestroyed() throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    /*
    private void setDestroyed(boolean destroyed) 
    throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }
    */
    
    public Group getGroupInterface()
    {
        return groupInterface;
    }

    public void setGroupInterface(Group teamInterface)
    {
        this.groupInterface = teamInterface;
    }
    
    public final boolean isReadyForExplosion()
    {
        return readyForExplosion;
    }

    protected final void setReadyForExplosion(boolean isReadyForExplosion)
    {
        this.readyForExplosion = isReadyForExplosion;
    }
    
    public int getInitX()
    {
        return initX;
    }

    public int getInitY()
    {
        return initY;
    }
    
    public void up()
    throws Exception
    {
        
    }
    
    public void down()
    throws Exception
    {
    }

    public void right()
    throws Exception
    {
    }

    public void left()
    throws Exception
    {
    }

    public void strafeLeft() throws Exception
    {
    }

    public void strafeRight() throws Exception
    {
    }
    
    public void fire(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }

    public void special1(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }    

    public void special2(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }

    public void special3(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }

    public void special4(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }

    public void special5(AllBinaryLayerManager layerManager, GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }

    /*
    //For optimized handler but currently not enables until I decide on depencies
    public void onMovement(TrackingEvent trackingEvent)
    {
    }
    */
    
    public void onDestroyed(DestroyedEvent destroyedEvent)
    {
    }

    public PickupBehavior getPickupBehavior()
    {
        return pickupBehavior;
    }

    public void setPickupBehavior(PickupBehavior pickupBehavior)
    {
        this.pickupBehavior = pickupBehavior;
    }

    public void addPart(
            PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface)
    throws Exception
    {
        
    }

    public void setPartInterfaceArray(PartInterface[] partInterfaceArray)
    {
        this.partInterfaceArray = partInterfaceArray;
    }

    public PartInterface[] getPartInterfaceArray()
    {
        return partInterfaceArray;
    }
    
    public void set(GL gl) throws Exception
    {
        //OpenGLSurfaceChangedInterface
    	throw new Exception(NotImplemented.NAME);
    }
    
    //private static final String PARTS_LABEL = "Parts: ";
    
    private static final String READYFOREXPLOSION = "ReadyForExplosion: ";
    
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
        
        CommonSeps commonSeps = CommonSeps.getInstance();
        
        stringBuffer.append(super.toString());
        stringBuffer.append(commonSeps.NEW_LINE);
        stringBuffer.append(this.getGroupInterface().toString());
        
        if (this.getPickupBehavior() != null)
        {
            stringBuffer.append(commonSeps.NEW_LINE);
            stringBuffer.append(this.getPickupBehavior().toString());
        }

        /*
        if (this.getPartInterfaceArray() != null)
        {
            stringBuffer.append(commonSeps.NEW_LINE);
            int total = this.getPartInterfaceArray().length;
            stringBuffer.append(total);
            stringBuffer.append(commonSeps.SPACE);
            stringBuffer.append(PARTS_LABEL);

            for (int index = 0; index < total; index++)
            {
                stringBuffer.append(this.getPartInterfaceArray()[index].toString());
            }
        }
        */
        
        stringBuffer.append(commonSeps.NEW_LINE);
        stringBuffer.append(READYFOREXPLOSION);
        stringBuffer.append(readyForExplosion);

        stringBuffer.append(commonSeps.NEW_LINE);
        stringBuffer.append(CommonStrings.getInstance().INIT);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(PositionStrings.getInstance().X_LABEL);
        stringBuffer.append(initX);
        stringBuffer.append(PositionStrings.getInstance().Y_LABEL);
        stringBuffer.append(initX);
        
        return stringBuffer.toString();
    }
    
}