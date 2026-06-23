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

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.AndroidUtil;
import org.allbinary.J2MEUtil;
import org.allbinary.animation.Animation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.animation.vector.RectangleFilledAnimation;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.layer.SWTUtil;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.graphics.draw.DrawVerticalStringUtil;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureUtil;
import org.allbinary.graphics.paint.Paintable;

/**
 *
 * @author user
 */
public class BasicPopupMenuPaintable extends Paintable implements UpdateMyFontInterface
{
    private static final String NAME = "MENU";

    protected final BasicColorSetUtil basicSetColorUtil = BasicColorSetUtil.getInstance();
    private final DrawVerticalStringUtil drawStringUtil = DrawVerticalStringUtil.getInstance();

    private final String label;
    private final BasicColor foregroundBasicColor;

    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);
    
    private Rectangle rectangle;
    private int BORDER;
    private int heightOffset;
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
            this.label = BasicPopupMenuPaintable.NAME + " Esc";
        }
        else
        {
            this.label = BasicPopupMenuPaintable.NAME + " *";
        }
 */
        this.label = BasicPopupMenuPaintable.NAME;

        this.rectangle = rectangle;

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

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        
        final Features features = Features.getInstance();
        final boolean isOpenGL = features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL);

        this.drawStringUtil.updateMeasurement(graphics, this.label);

        int BORDER = 0;
        if(J2MEUtil.isHTML() || (AndroidUtil.isAndroid() && isOpenGL)) {
            BORDER = UpdateMyFontProcessor.defaultCharWidth(font) / 2;
        } else if(AndroidUtil.isAndroid() || J2MEUtil.isJ2SE() || SWTUtil.isSWT) {
            BORDER = UpdateMyFontProcessor.defaultCharWidth(font);
        } else {
            BORDER = UpdateMyFontProcessor.defaultCharWidth(font) * 2;
        }
        this.BORDER = BORDER;

        this.heightOffset = this.rectangle.getHeight() - (font.getHeight() * BasicPopupMenuPaintable.NAME.length());

        if (OpenGLFeatureUtil.getInstance().isAnyThreed()) {
            this.heightOffset -= font.getHeight() + 2;
            if (AndroidUtil.isAndroid()) {
                this.heightOffset = font.getHeight();
            } else {
                this.heightOffset -= font.getHeight() + 2;
            }
        }

        this.offset = (this.heightOffset >> 1);
        
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
        
    public void init(final Rectangle rectangle) throws Exception
    {
        this.rectangle = rectangle;

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
    
   @Override
   public void paint(final Graphics graphics)
   {
       this.myFontProcessor.process(graphics);

       final GPoint point = this.rectangle.getPoint();
       final int x = point.getX();
       final int y = point.getY();

       final int width = this.rectangle.getWidth();
       final int height = this.rectangle.getHeight();

       this.animationInterface.paintXY(graphics, x, y);

       this.basicSetColorUtil.setBasicColorP(graphics, this.foregroundBasicColor);
       
       this.drawStringUtil.paintVerticle(graphics, this.label, x + this.BORDER, y + this.offset, 0);
       graphics.drawRect(x, y, width, height);
   }
   
}
