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
package org.allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Graphics;

import org.allbinary.AndroidUtil;
import org.allbinary.J2MEUtil;
import org.allbinary.animation.Animation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.animation.vector.RectangleFilledAnimation;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.HTMLFeatureFactory;
import org.allbinary.game.layer.SWTUtil;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.graphics.draw.DrawStringUtil;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureUtil;
import org.allbinary.graphics.paint.Paintable;

/**
 *
 * @author user
 */
public class BasicPopupMenuPaintable extends Paintable
{
    private static final String NAME = "MENU";

    protected final BasicColorSetUtil basicSetColorUtil = 
        BasicColorSetUtil.getInstance();

    private final String label;
    
    private final int BORDER;
    
    private final BasicColor foregroundBasicColor;
    
    private Rectangle rectangle;
    private int offset;

    private Animation animationInterface;

    public BasicPopupMenuPaintable(final Rectangle rectangle, 
            final BasicColor backgroundBasicColor, 
            final BasicColor foregroundBasicColor)
            throws Exception
    {
        this.foregroundBasicColor = foregroundBasicColor;

        /*
        if(AppletUtil.isAppletLoader(this))
        {
            label = NAME + " Esc";
        }
        else
        {
            label = NAME + " *";
        }
 */
        label = NAME;

        this.rectangle = rectangle;
        
        final Features features = Features.getInstance();
        final boolean isHTML = features.isDefault(HTMLFeatureFactory.getInstance().HTML);
        final boolean isOpenGL = features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL);

        if(isHTML || (AndroidUtil.isAndroid() && isOpenGL)) {
            this.BORDER = MyFont.getInstance().charWidth() / 2;
        } else if(AndroidUtil.isAndroid() || J2MEUtil.isJ2SE() || SWTUtil.isSWT) {
            this.BORDER = MyFont.getInstance().charWidth();
        } else {
            this.BORDER = MyFont.getInstance().charWidth() * 2;
        }

        if(J2MEUtil.isJ2ME())
        {
            //Image image = ImageCacheFactory.getInstance().get(
              //      this, this.rectangle.getWidth(), this.rectangle.getHeight());
            //image.getGraphics().setColor(BasicColor.TRANSPARENT_GREY.intValue());
            //image.getGraphics().drawRect(0, 0, this.rectangle.getWidth(), this.rectangle.getHeight());
            //this.animationInterface = new AllBinaryImageAnimation(image);
            this.animationInterface = NullAnimationFactory.getFactoryInstance().getInstance(0);
        }
        else
        {
            this.animationInterface = new RectangleFilledAnimation(
                this.rectangle.getWidth(), this.rectangle.getHeight(), 
                BasicColorFactory.getInstance().TRANSPARENT_GREY);
        }
        
        this.init(rectangle);
    }

    public void init(final Rectangle rectangle) throws Exception
    {
        this.rectangle = rectangle;

        final MyFont myFont = MyFont.getInstance();

        int heightOffset = rectangle.getHeight() - (myFont.DEFAULT_CHAR_HEIGHT * NAME.length());

        if (OpenGLFeatureUtil.getInstance().isAnyThreed()) {
            heightOffset -= myFont.DEFAULT_CHAR_HEIGHT + 2;
            if (AndroidUtil.isAndroid()) {
                heightOffset = myFont.DEFAULT_CHAR_HEIGHT;
            } else {
                heightOffset -= myFont.DEFAULT_CHAR_HEIGHT + 2;
            }
        }
        
        this.offset = (heightOffset >> 1);

       final int width = this.rectangle.getWidth();
       final int height = this.rectangle.getHeight();

        if(J2MEUtil.isJ2ME())
        {
            //Image image = ImageCacheFactory.getInstance().get(this, width, height);
            //image.getGraphics().setColor(BasicColor.TRANSPARENT_GREY.intValue());
            //image.getGraphics().drawRect(0, 0, width, height);
            //this.animationInterface = new AllBinaryImageAnimation(image);
        }
        else
        {
           final RectangleFilledAnimation rectangleFilledAnimation =
                   (RectangleFilledAnimation) this.animationInterface;
           rectangleFilledAnimation.setWidth(width);
           rectangleFilledAnimation.setHeight(height);
        }
    }
    
   private final DrawStringUtil drawStringUtil = DrawStringUtil.getInstance();
    
   public void paint(final Graphics graphics)
   {
       final GPoint point = this.rectangle.getPoint();
       final int x = point.getX();
       final int y = point.getY();

       final int width = this.rectangle.getWidth();
       int height = this.rectangle.getHeight();

       this.animationInterface.paint(graphics, x, y);

       this.basicSetColorUtil.setBasicColor(graphics, this.foregroundBasicColor);
       
       drawStringUtil.paintVerticle(graphics, label, x + BORDER, y + offset, 0);
       graphics.drawRect(x, y, width, height);
   }
   
}
