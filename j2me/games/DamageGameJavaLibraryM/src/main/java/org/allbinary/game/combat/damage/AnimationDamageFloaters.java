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

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.CircularIndexUtil;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.view.ViewPosition;

public class AnimationDamageFloaters extends DamageFloaters
{
    private AllBinaryLayer layerInterface;
    private IndexedAnimation[] animationInterfaceArray;

    //private int[] lastDamage;
    private final CircularIndexUtil circularIndexUtil;

    private int dx;
    private int dy;

    public AnimationDamageFloaters(AllBinaryLayer layerInterface, IndexedAnimation[] animationInterfaceArray, int dx, int dy)
    {
        this.animationInterfaceArray = animationInterfaceArray;

        for(int index = this.animationInterfaceArray.length - 1; index >= 0; index--)
        {
            this.animationInterfaceArray[index].setFrame(
               this.animationInterfaceArray[index].getSize() - 1);
        }

        //this.lastDamage = new int[animationInterfaceArray.length];
        this.layerInterface = layerInterface;

        this.circularIndexUtil = CircularIndexUtil.getInstance(this.animationInterfaceArray.length);

        this.dx = dx;
        this.dy = dy;
    }

    /*
    public AnimationDamageFloaters(AllBinaryLayer layerInterface)
    {
        this.layerInterface = layerInterface;

        this.circularIndexUtil = CircularIndexUtil.getInstance(0);
    }
    */

    public void add(int damage)
    {
        int i = this.circularIndexUtil.getIndex();
        //this.lastDamage[i] = damage;
        animationInterfaceArray[i].setFrame(0);

        this.circularIndexUtil.next();
    }

    public void paint(Graphics graphics)
    {
        try
        {
            ViewPosition viewPosition = this.layerInterface.getViewPosition();
            int x = viewPosition.getX();
            int y = viewPosition.getY();

            for (int index = 0; index < this.animationInterfaceArray.length; index++)
            {
                IndexedAnimation animationInterface = animationInterfaceArray[index];
                if (animationInterface.getFrame() < animationInterface.getAnimationSize() - 1)
                {
                    int delta = animationInterface.getFrame() * 20;
                    animationInterface.paint(graphics, x + dx, y - delta + dy);
                    /*
                    for(int index2 = 0; index2 < this.lastDamage[index2]; index2++)
                    {
                        animationInterface.paint(graphics, x + MyRandom.getAbsoluteNextInt(3 * animationInterface.getFrame()), y - delta);
                    }
                    */
                    animationInterface.nextFrame();
                }
                //this.lastDamage[index] = this.lastDamage[index] - 1;
            }
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "paint", e));
        }
    }
}
