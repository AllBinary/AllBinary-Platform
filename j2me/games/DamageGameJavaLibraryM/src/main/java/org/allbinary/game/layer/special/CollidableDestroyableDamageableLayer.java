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
package org.allbinary.game.layer.special;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.combat.damage.DamageableInterface;
import org.allbinary.game.combat.destroy.DestroyableInterface;
import org.allbinary.game.combat.destroy.event.DestroyedEvent;
import org.allbinary.game.identification.BasicGroupFactory;
import org.allbinary.game.identification.Group;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.CollidableCompositeLayer;
import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactoryInterface;
import org.allbinary.game.layer.pickup.PickupBehavior;
import org.allbinary.game.layer.pickup.PickupCompositeInterface;
import org.allbinary.game.part.PartInterface;
import org.allbinary.game.part.PartInterfaceUtil;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.RectangleFactory;
import org.allbinary.image.opengles.OpenGLSurfaceChangedInterface;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.math.PositionStrings;
import org.allbinary.string.CommonSeps;
import org.allbinary.view.ViewPosition;

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
    public static final CollidableDestroyableDamageableLayer NULL_COLLIDABLE_DESTROYABLE_DAMAGE_LAYER = new CollidableDestroyableDamageableLayer(
    BasicGroupFactory.getInstance().NONE_ARRAY, RectangleFactory.SINGLETON, ViewPosition.NULL_VIEW_POSITION);
        
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private Group[] groupInterface;
    private boolean readyForExplosion;

    private int initWidth;
    private int initHeight;
    private int initX;
    private int initY;
    private int initZ;

    protected PartInterface[] partInterfaceArrayP = PartInterfaceUtil.getZeroArray();
    private PickupBehavior pickupBehavior = PickupBehavior.NULL_PICKUP_BEHAVIOR;
 
    public AllBinaryGameLayerManager allBinaryGameLayerManagerP = AllBinaryGameLayerManager.NULL_ALLBINARY_LAYER_MANAGER;
    
    public CollidableDestroyableDamageableLayer(
            final Group[] groupInterface, final Rectangle layerInfo, final ViewPosition viewPosition)
    {
        super(layerInfo, viewPosition);

        this.initWidth = layerInfo.getWidth();
        this.initHeight = layerInfo.getHeight();
        
        this.groupInterface = groupInterface;
    }

    public CollidableDestroyableDamageableLayer(
            final Group[] groupInterface, final String name, final Rectangle layerInfo, final ViewPosition viewPosition)
    {
        super(name, layerInfo, viewPosition);

        this.initWidth = layerInfo.getWidth();
        this.initHeight = layerInfo.getHeight();
        
        this.groupInterface = groupInterface;
    }
    
    public void initPosition() throws Exception
    {
        this.setPosition(this.initX, this.initY, this.initZ);
    }

    public void initPosition(final int x, final int y, final int z) throws Exception
    {        
        this.initX = x;
        this.initY = y;
        this.initZ = z;
    }
    
    //Should be overridden
    @Override
    public void paint(final Graphics graphics)
    {
        //logUtil.put(commonStrings.NOT_IMPLEMENTED, this, canvasStrings.PAINT);
        super.paint(graphics);
    }

    @Override
    public void damage(final int damage, final int damageType) throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }

    @Override
    public int getDamage(final int damageType) throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }

    @Override
    public boolean isDestroyed() throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }

    /*
    private void setDestroyed(boolean destroyed) 
    throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }
    */
    
    @Override
    public Group[] getGroupInterface()
    {
        return groupInterface;
    }

    public void setGroupInterface(final Group[] teamInterface)
    {
        this.groupInterface = teamInterface;
    }

    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {
        this.allBinaryGameLayerManagerP = allBinaryGameLayerManager;
        //logUtil.put(new StringMaker().append(commonStrings.START).append(this.getName()).append(CommonSeps.getInstance().SPACE).append(allBinaryGameLayerManager).toString(), this, commonStrings.PROCESS);
        if (this.allBinaryGameLayerManagerP == null) {
            throw new RuntimeException();
        }
    }
    
    public final boolean isReadyForExplosion()
    {
        return readyForExplosion;
    }

    protected final void setReadyForExplosion(final boolean isReadyForExplosion)
    {
        this.readyForExplosion = isReadyForExplosion;
    }

    public int getInitWidth()
    {
        return this.initWidth;
    }

    public int getInitHeight()
    {
        return this.initHeight;
    }
    
    public int getInitX()
    {
        return initX;
    }

    public int getInitY()
    {
        return initY;
    }
    
    @Override
    public void up()
    throws Exception
    {
        
    }
    
    @Override
    public void down()
    throws Exception
    {
    }

    @Override
    public void right()
    throws Exception
    {
    }
    
    @Override
    public void left()
    throws Exception
    {
    }

    @Override
    public void strafeLeft() throws Exception
    {
    }

    @Override
    public void strafeRight() throws Exception
    {
    }
    
    @Override
    public void fire(final AllBinaryLayerManager layerManager, final GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }

    @Override
    public void special1(final AllBinaryLayerManager layerManager, final GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }    

    @Override
    public void special2(final AllBinaryLayerManager layerManager, final GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }

    @Override
    public void special3(final AllBinaryLayerManager layerManager, final GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }

    @Override
    public void special4(final AllBinaryLayerManager layerManager, final GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }

    @Override
    public void special5(final AllBinaryLayerManager layerManager, final GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }

    public void up(final int yAnalogValue)
    throws Exception
    {
        
    }
    
    public void down(final int yAnalogValue)
    throws Exception
    {
    }

    public void right(final int xAnalogValue)
    throws Exception
    {
    }

    public void left(final int xAnalogValue)
    throws Exception
    {
    }

    public void rightTrigger(final AllBinaryLayerManager layerManager, final GameKeyEvent gameKeyEvent, final int xAnalogValue)
    throws Exception
    {
    }

    public void leftTrigger(final AllBinaryLayerManager layerManager, final GameKeyEvent gameKeyEvent, final int xAnalogValue)
    throws Exception
    {
    }
    
    /*
    //For optimized handler but currently not enables until I decide on depencies
    public void onMovement(TrackingEvent trackingEvent)
    {
    }
    */
    
    public void onDestroyed(final DestroyedEvent destroyedEvent)
    {
    }

    @Override
    public PickupBehavior getPickupBehavior()
    {
        return pickupBehavior;
    }

    @Override
    public void setPickupBehavior(final PickupBehavior pickupBehavior)
    {
        this.pickupBehavior = pickupBehavior;
    }

    public void addPart(
            final PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface)
    throws Exception
    {
        
    }

    public void setPartInterfaceArray(final PartInterface[] partInterfaceArray)
    {
        this.partInterfaceArrayP = partInterfaceArray;
    }

    public PartInterface[] getPartInterfaceArray()
    {
        return partInterfaceArrayP;
    }
    
    @Override
    public void set(final GL gl) throws Exception
    {
        //OpenGLSurfaceChangedInterface
    	throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }
    
    //private static final String PARTS_LABEL = "Parts: ";
    
    private static final String READYFOREXPLOSION = "ReadyForExplosion: ";
    
    @Override
    public void toString(final StringMaker stringBuffer)
    {
        final CommonSeps commonSeps = CommonSeps.getInstance();
        
        final PositionStrings positionStrings = PositionStrings.getInstance();
        
        super.toString(stringBuffer);
        stringBuffer.append(commonSeps.NEW_LINE);
        final Group[] groupInterfaceArray = this.getGroupInterface();
        final int size = groupInterfaceArray.length;
        for(int index = 0; index < size; index++) {
            stringBuffer.append(groupInterfaceArray[index].toString());
            stringBuffer.append(commonSeps.COMMA);
        }
        
        final PickupBehavior pickupBehavior = this.getPickupBehavior();
        if (pickupBehavior != null)
        {
            stringBuffer.append(commonSeps.NEW_LINE);
            stringBuffer.append(pickupBehavior.toString());
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
        stringBuffer.append(commonStrings.INIT);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.X_LABEL);
        stringBuffer.append(initX);
        stringBuffer.append(positionStrings.Y_LABEL);
        stringBuffer.append(initX);
        stringBuffer.append(positionStrings.Z_LABEL);
        stringBuffer.append(initZ);
        
    }
    
    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();

        this.toString(stringBuffer);

        return stringBuffer.toString();
    }
    
}