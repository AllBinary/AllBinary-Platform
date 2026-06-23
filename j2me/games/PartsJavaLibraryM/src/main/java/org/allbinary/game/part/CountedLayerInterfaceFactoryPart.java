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
package org.allbinary.game.part;

import java.util.Hashtable;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.game.layer.pickup.CountedPickedUpLayerInterfaceFactory;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.math.PrimitiveLongUtil;

public class CountedLayerInterfaceFactoryPart
    implements PartInterface, UpdateMyFontInterface {

    public static final CountedLayerInterfaceFactoryPart NULL_COUNTED_LAYER_INTERFACE_FACTORY = new CountedLayerInterfaceFactoryPart(0, CountedPickedUpLayerInterfaceFactory.NULL_COUNTED_PICKUP_LAYER_FACTORY);

    private final MyFontProcessor updateMyFontProcessor = new UpdateMyFontProcessor(this);
    private MyFontProcessor myFontProcessor = this.updateMyFontProcessor;

    private Animation animationInterface = NullAnimationFactory.getFactoryInstance().getInstance(0);
    private int total;
    //private String totalString;
    private char[] totalString = NullUtil.getInstance().NULL_CHAR_ARRAY;
    private int xOffset;
    private CountedPickedUpLayerInterfaceFactory countedPickedUpLayerInterfaceFactory = CountedPickedUpLayerInterfaceFactory.NULL_COUNTED_PICKUP_LAYER_FACTORY;

    private final PrimitiveLongUtil primitiveLongUtil;

    public CountedLayerInterfaceFactoryPart(final int total,
        final CountedPickedUpLayerInterfaceFactory countedPickedUpLayerInterfaceFactory) {
        this.primitiveLongUtil = PrimitiveLongUtil.createPowerOfTen(1000);

        this.init(total, countedPickedUpLayerInterfaceFactory);
    }
    
    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.setXOffset(font.charsWidth(this.totalString, 0, this.primitiveLongUtil.getCurrentTotalDigits()) + (font.getSize() >> 1));
        this.myFontProcessor = MyFontProcessor.getInstance();
    }

    private void init(final int total, final CountedPickedUpLayerInterfaceFactory countedPickedUpLayerInterfaceFactory) {
        this.setCountedPickedUpLayerInterfaceFactory(countedPickedUpLayerInterfaceFactory);
        this.total = total;
    }

    public AllBinaryLayer getInstance(Hashtable hashtable, int x, int y, int z)
        throws Exception {
        if (this.total > 0) {
            this.setTotal(this.total - 1);
            return this.getCountedPickedUpLayerInterfaceFactory().getNextInstance(hashtable, x, y, z);
        } else {
            throw new Exception(
                "No more left. Could use a listener to automatically remove");
        }
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;

        //this.totalString = this.primitiveLongUtil.getString(total);
        this.totalString = this.primitiveLongUtil.getCharArray(total);
        //this.setTotalStringWidth(font.stringWidth(totalString));
        this.myFontProcessor = this.updateMyFontProcessor;
    }

    @Override
    public void paint(Graphics graphics) {
        this.myFontProcessor.process(graphics);
    }

    @Override
    public void paintThreed(Graphics graphics) {
    }

    @Override
    public Animation getAnimationInterface() {
        return this.animationInterface;
    }

    public void setAnimationInterface(Animation animationInterface) {
        this.animationInterface = animationInterface;
    }

    /**
     * @return the totalStringWidth
     */
    public int getXOffset() {
        return this.xOffset;
    }

    /**
     * @param totalStringWidth the totalStringWidth to set
     */
    private void setXOffset(int totalStringWidth) {
        this.xOffset = totalStringWidth;
    }

    /**
     * @return the totalString
     */
    /*
   public String getTotalString()
   {
      return totalString;
   }
     */
    public char[] getTotalString() {
        return this.totalString;
    }

    /**
     * @return the countedPickedUpLayerInterfaceFactory
     */
    public CountedPickedUpLayerInterfaceFactory getCountedPickedUpLayerInterfaceFactory() {
        return this.countedPickedUpLayerInterfaceFactory;
    }

    /**
     * @param countedPickedUpLayerInterfaceFactory the
     * countedPickedUpLayerInterfaceFactory to set
     */
    public void setCountedPickedUpLayerInterfaceFactory(CountedPickedUpLayerInterfaceFactory countedPickedUpLayerInterfaceFactory) {
        this.countedPickedUpLayerInterfaceFactory = countedPickedUpLayerInterfaceFactory;
    }
}
