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
package allbinary.animation.image;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.opengles.OpenGLCapabilities;
import org.allbinary.util.CircularIndexUtil;

import allbinary.animation.IndexedAnimation;
import allbinary.graphics.Anchor;
import allbinary.logic.math.PrimitiveIntUtil;

public class AllBinaryTweenedImageAnimation extends IndexedAnimation
{
    private final Image image;

    private final int dx;
    private final int dy;
    
    private int totalFrames;

    protected CircularIndexUtil circularIndexUtil;
    
    public AllBinaryTweenedImageAnimation(
            Image image, int dx, int dy, int totalFrames) 
        throws Exception
    {
        super();

        this.image = image;
        
        this.dx = dx;
        this.dy = dy;
        
        this.totalFrames = totalFrames;
        
        this.circularIndexUtil = CircularIndexUtil.getInstance(this.totalFrames);
        
        // LogUtil.put(LogFactory.getInstance("Constructing", this,
        // "AllBinaryImageRotationAnimation"));
    }

    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }
    
    public void nextFrame()
    {
        this.circularIndexUtil.next();
    }

    public void previousFrame()
    {
        this.circularIndexUtil.previous();
    }

    public void setFrame(int index)
    {
        this.circularIndexUtil.setIndex(index);
    }

    public int getFrame()
    {
        return this.circularIndexUtil.getIndex();
    }

    public int getSize()
    {
        return this.totalFrames;
    }

    public void setSequence(int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics, int x, int y)
    {
        int frame = this.getFrame();
        int currentX = dx * frame; 
        int currentY = dy * frame;

        graphics.drawImage(this.image, x + currentX, y + currentY, anchor);
    }
        
}
