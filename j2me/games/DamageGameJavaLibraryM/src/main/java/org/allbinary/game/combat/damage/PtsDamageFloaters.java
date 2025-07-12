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

import org.allbinary.game.rand.MyRandomFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorArrayIndexer;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.java.character.CharArrayFactory;
import org.allbinary.logic.math.PrimitiveLongUtil;
import org.allbinary.util.CircularIndexUtil;
import org.allbinary.view.ViewPosition;

public class PtsDamageFloaters extends DamageFloaters
{    
    protected final BasicColorSetUtil basicSetColorUtil = 
        BasicColorSetUtil.getInstance();
    
    private AllBinaryLayer layerInterface;

    private int[] lastDamage = new int[5];
    //private String[] lastDamageString = {
      //      StringUtil.getInstance(), StringUtil.getInstance(), StringUtil.getInstance(), 
        //    StringUtil.getInstance(), StringUtil.getInstance() };
    private char[][] lastDamageString = new char[5][];
    private int[] lastDamageStringSizeArray = new int[5];
    
    private CircularIndexUtil circularIndexUtil = 
        CircularIndexUtil.getInstance(5);

    private final PrimitiveLongUtil primitiveLongUtil;
    
    private final static BasicColorArrayIndexer basicColorArrayIndexer = 
        new BasicColorArrayIndexer( new BasicColor[]
            { BasicColorFactory.getInstance().WHITE, 
                BasicColorFactory.getInstance().RED, 
                BasicColorFactory.getInstance().YELLOW, 
                BasicColorFactory.getInstance().BLUE });

    /*
    public PtsDamageFloaters(AllBinaryLayer layerInterface,
            BasicColor[] basicColorArray) throws Exception
    {
        this.primitiveLongUtil = new PrimitiveLongUtil(100000);
        
        this.layerInterface = layerInterface;
    }
    */

    public PtsDamageFloaters(AllBinaryLayer layerInterface)
    {
        this.primitiveLongUtil = new PrimitiveLongUtil(100000);
        
        this.layerInterface = layerInterface;
        
        char[] CHAR_ARRAY = CharArrayFactory.getInstance().getZeroCharArray();
        
        for(int index = this.lastDamageString.length - 1; index >= 0; index--)
        {
            this.lastDamageString[index] = CHAR_ARRAY; 
        }

    }

    public void add(int damage)
    {
        int index = this.circularIndexUtil.getIndex();
        
        this.lastDamage[index] = damage;
        
        this.lastDamageString[index] = 
            this.primitiveLongUtil.getCharArray(this.lastDamage[index]);
        
        this.lastDamageStringSizeArray[index] = 
            this.primitiveLongUtil.getCurrentTotalDigits();
            
        this.circularIndexUtil.next();
    }

    private final MyRandomFactory myRandomFactory = MyRandomFactory.getInstance();
    
    public void paint(Graphics graphics)
    {
        ViewPosition viewPosition = this.layerInterface.getViewPosition();
        int x = viewPosition.getX();
        int y = viewPosition.getY();

        for (int index = this.lastDamage.length - 1; index >= 0; index--)
        {
            if (this.lastDamage[index] != 0)
            {
                int delta = 10 * (index + 2);
                
                this.basicSetColorUtil.setBasicColor(
                        graphics, basicColorArrayIndexer.get());
                
                //graphics.drawString(this.lastDamageString[index],
                graphics.drawChars(this.lastDamageString[index], 
                        0, this.lastDamageStringSizeArray[index], 
                        x - myRandomFactory.getNextInt(delta), y - delta, 0);
                basicColorArrayIndexer.next();
            }
            this.lastDamage[index] = 0;
        }
    }

}
