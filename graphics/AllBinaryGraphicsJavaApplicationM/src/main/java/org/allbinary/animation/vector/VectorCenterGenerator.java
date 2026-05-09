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
package org.allbinary.animation.vector;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.j2me.workarea.tools.GraphicItemInterface;
import org.allbinary.graphics.j2me.workarea.tools.LinesGraphicItem;
import org.allbinary.graphics.j2me.workarea.tools.Points;
import org.allbinary.graphics.pipeline.BasicGraphicsPipeline;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class VectorCenterGenerator {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    int maxY = Integer.MIN_VALUE;
    private int dx = 0;
    private int dy = 0;
    
    private int width;
    private int height;

    public VectorCenterGenerator() {
    }

    public void calculate(HashMap hashMap) throws Exception {

        final Object[] graphicItemArray = hashMap.keySet().toArray();
        final int size = graphicItemArray.length;
        for(int index = 0; index < size; index++)
        {
            GraphicItemInterface item = (GraphicItemInterface) hashMap.get(graphicItemArray[index]);
            if(item.getName() == LinesGraphicItem.getStaticName())
            {
                this.getInstance(item.getPointsInterface().getPoints());
            }
        }

        this.logUtil.putF("minX: " + this.minX + " minY: " + this.minY + " maxX: " + this.maxX + " maxY: " + this.maxY, this, this.commonStrings.GET_INSTANCE);

        this.setWidth(this.maxX - this.minX);
        this.setHeight(this.maxY - this.minY);

        int max = this.getWidth();
        if (getHeight() > max) {
            max = this.getHeight();
        }

        //max = max + 1;

        int middle = max / 2;

        final CommonLabels commonLabels = CommonLabels.getInstance();
        final String s = new StringMaker().append(commonLabels.WIDTH_LABEL).appendint(getWidth()).append(commonLabels.HEIGHT_LABEL).appendint(getHeight()).append(" max: ").appendint(max).append(" middle: ").appendint(middle).toString();
        this.logUtil.putF(s, this, this.commonStrings.GET_INSTANCE);

        int currentMiddleX = this.minX + this.getWidth() / 2;
        int currentMiddleY = this.minY + this.getHeight() / 2;

        this.dx = middle - currentMiddleX;
        this.dy = middle - currentMiddleY;

    }
    
    public void transform(HashMap hashMap) throws Exception {

        this.calculate(hashMap);
        this.logUtil.putF(new StringMaker().append(" dx: ").appendint(this.dx).append(" dy: ").appendint(this.dy).toString(), this, this.commonStrings.GET_INSTANCE);

        final Object[] graphicItemArray = hashMap.keySet().toArray();
        final int size = graphicItemArray.length;
        for(int index = 0; index < size; index++)
        {
            GraphicItemInterface item = (GraphicItemInterface) hashMap.get(graphicItemArray[index]);

            BasicArrayList list = VectorCenterGenerator.translate(item.getPointsInterface().getPoints(), dx, dy);

            Points newPoints = new Points();
            newPoints.addPoints(list);

            item.setPointsInterface(newPoints);
        }
    }

    private void getInstance(BasicArrayList pointVector) throws Exception {
        try {

            Vector newVector = new Vector();
            int size = pointVector.size();

            for(int index = 0; index < size; index++)
            {
                GPoint point = (GPoint) pointVector.get(index);

                if (point.getX() < this.minX) {
                    this.minX = point.getX();
                }
                if (point.getX() > this.maxX) {
                    this.maxX = point.getX();
                }
                if (point.getY() < this.minY) {
                    this.minY = point.getY();
                }
                if (point.getY() > this.maxY) {
                    this.maxY = point.getY();
                }
            }

        } catch (Exception e) {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.GET_INSTANCE, e);
            throw e;
        }
    }

    public static BasicArrayList translate(BasicArrayList list, int x, int y) throws Exception
    {
        BasicGraphicsPipeline graphicsPipe = new BasicGraphicsPipeline(list);
        graphicsPipe.createMatrix();
        graphicsPipe.translate(x, y);

        return graphicsPipe.getMatrix();
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
