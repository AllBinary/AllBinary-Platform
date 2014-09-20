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

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.j2me.workarea.tools.GraphicItemInterface;
import org.allbinary.graphics.j2me.workarea.tools.LinesGraphicItem;
import org.allbinary.graphics.j2me.workarea.tools.Points;
import java.util.Vector;

import org.allbinary.graphics.pipeline.BasicGraphicsPipeline;
import java.util.HashMap;
import java.util.Iterator;
import org.allbinary.util.BasicArrayList;

public class VectorCenterGenerator {

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

        Iterator iter = hashMap.keySet().iterator();
        while (iter.hasNext()) 
        {
            GraphicItemInterface item = (GraphicItemInterface) hashMap.get(iter.next());
            if(item.getName() == LinesGraphicItem.getStaticName())
            {
                this.getInstance(item.getPointsInterface().getPoints());
            }
        }

        LogUtil.put(new Log("minX: " + minX + " minY: " + minY + " maxX: " + maxX + " maxY: " + maxY, this, "getInstance"));

        setWidth(maxX - minX);
        setHeight(maxY - minY);

        int max = getWidth();
        if (getHeight() > max) {
            max = getHeight();
        }

        //max = max + 1;

        int middle = max / 2;

        LogUtil.put(new Log("width: " + getWidth() + " height: " + getHeight() + " max: " + max + " middle: " + middle, this, "getInstance"));

        int currentMiddleX = minX + getWidth() / 2;
        int currentMiddleY = minY + getHeight() / 2;

        dx = middle - currentMiddleX;
        dy = middle - currentMiddleY;

    }
    
    public void transform(HashMap hashMap) throws Exception {

        this.calculate(hashMap);
        LogUtil.put(new Log(" dx: " + dx + " dy: " + dy, this, "getInstance"));

        Iterator iter = hashMap.keySet().iterator();
        while (iter.hasNext()) {
            GraphicItemInterface item = (GraphicItemInterface) hashMap.get(iter.next());

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
            LogUtil.put(new Log(CommonStrings.getInstance().EXCEPTION, "VectorCenterGenerator", "getInstance", e));
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
