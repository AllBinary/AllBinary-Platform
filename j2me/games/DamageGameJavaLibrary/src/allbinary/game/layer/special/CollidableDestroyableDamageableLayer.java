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

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.CollidableCompositeLayer;

import abcs.logic.basic.NotImplemented;
import allbinary.game.combat.damage.DamageableInterface;
import allbinary.game.combat.destroy.DestroyableInterface;
import allbinary.game.combat.destroy.event.DestroyedEvent;
import allbinary.game.identification.Group;
import allbinary.game.input.event.GameKeyEvent;
import allbinary.graphics.Rectangle;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.view.ViewPosition;

public class CollidableDestroyableDamageableLayer 
extends CollidableCompositeLayer 
implements DestroyableInterface, DamageableInterface
{
    private Group groupInterface;
    private boolean readyForExplosion;

    private int initX;
    private int initY;
    
    public CollidableDestroyableDamageableLayer(
            Group groupInterface, 
            Rectangle layerInfo, ViewPosition viewPosition)
    {
        super(layerInfo, viewPosition);

        this.groupInterface = groupInterface;
    }

    public void initPosition() throws Exception
    {
        this.setPosition(this.initX, this.initY);
    }

    public void initPosition(int x, int y) throws Exception
    {        
        this.initX = x;
        this.initY = y;
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
    
    protected void up()
    throws Exception
    {
        
    }
    
    protected void down()
    throws Exception
    {
    }

    protected void right()
    throws Exception
    {
    }

    protected void left()
    throws Exception
    {
    }

    protected void strafeLeft() throws Exception
    {
    }

    protected void strafeRight() throws Exception
    {
    }
    
    protected void fire(AllBinaryLayerManager allbinaryLayerManager, GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }    

    protected void special1(AllBinaryLayerManager allbinaryLayerManager, GameKeyEvent gameKeyEvent)
    throws Exception
    {
    }    

    protected void special2(AllBinaryLayerManager allbinaryLayerManager, GameKeyEvent gameKeyEvent)
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
}