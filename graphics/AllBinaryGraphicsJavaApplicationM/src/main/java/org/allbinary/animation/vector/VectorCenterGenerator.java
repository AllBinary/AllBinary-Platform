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

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.j2me.workarea.tools.GraphicItemInterface;
import org.allbinary.graphics.j2me.workarea.tools.LinesGraphicItem;
import org.allbinary.graphics.j2me.workarea.tools.Points;
import org.allbinary.graphics.pipeline.BasicGraphicsPipeline;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
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

        logUtil.put("minX: " + minX + " minY: " + minY + " maxX: " + maxX + " maxY: " + maxY, this, commonStrings.GET_INSTANCE);

        setWidth(maxX - minX);
        setHeight(maxY - minY);

        int max = getWidth();
        if (getHeight() > max) {
            max = getHeight();
        }

        //max = max + 1;

        int middle = max / 2;

        final CommonLabels commonLabels = CommonLabels.getInstance();
        final String s = new StringMaker().append(commonLabels.WIDTH_LABEL).append(getWidth()).append(commonLabels.HEIGHT_LABEL).append(getHeight()).append(" max: ").append(max).append(" middle: ").append(middle).toString();
        logUtil.put(s, this, commonStrings.GET_INSTANCE);

        int currentMiddleX = minX + getWidth() / 2;
        int currentMiddleY = minY + getHeight() / 2;

        dx = middle - currentMiddleX;
        dy = middle - currentMiddleY;

    }
    
    public void transform(HashMap hashMap) throws Exception {

        this.calculate(hashMap);
        logUtil.put(new StringMaker().append(" dx: ").append(dx).append(" dy: ").append(dy).toString(), this, commonStrings.GET_INSTANCE);

        final Object[] graphicItemArray = hashMap.keySet().toArray();
        final int size = graphicItemArray.length;
        for(int index = 0; index < size; index++)
        {
            GraphicItemInterface item = (GraphicItemInterface) hashMap.get(graphicItemArray[index]);

            BasicArrayList list = translate(item.getPointsInterface().getPoints(), dx, dy);

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

                if (point.getX() < minX) {
                    minX = point.getX();
                }
                if (point.getX() > maxX) {
                    maxX = point.getX();
                }
                if (point.getY() < minY) {
                    minY = point.getY();
                }
                if (point.getY() > maxY) {
                    maxY = point.getY();
                }
            }

        } catch (Exception e) {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
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
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
