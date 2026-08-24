package org.allbinary.animation;

import javax.microedition.lcdui.Graphics;
import org.allbinary.graphics.color.BasicColor;

/**
 *
 * @author User
 */
public class FillAnimation extends IndexedAnimation {
    
    private final int[] pixelBuffer;
    private final int width;
    private final int height;
    
    public FillAnimation(final int[] pixelBuffer, final int width, final int height, final AnimationBehavior animationBehavior) {
        super(animationBehavior);
        
        this.width = 100;
        this.height = 100;
        this.pixelBuffer = pixelBuffer;
    }

    @Override
    public void setBasicColorP(final BasicColor basicColor) {
        
        boolean changed = false;
        if(this.getBasicColorP() == null || this.getBasicColorP().intValue() != basicColor.intValue()) {
            changed = true;
        }
        
        super.setBasicColorP(basicColor);
        
        if(changed) {
//            this.setColorProcessor = SetColorProcessor.getInstance();
            this.updateModifiers();
        }
    }

    @Override
    public void changeBasicColor(final BasicColor basicColor) {
        
        boolean changed = false;
        //if(this.getBasicColorP() == null || this.getBasicColorP().intValue() != basicColor.intValue()) {
        if(this.getChangeBasicColor() == null || this.getChangeBasicColor().intValue() != basicColor.intValue()) {
            changed = true;
        }
        
        super.changeBasicColor(basicColor);
        
        if(changed) {
//            this.changeColorProcessor = ChangeColorProcessor.getInstance();
            this.updateModifiers();
        }
    }
    
    @Override
    public void setAlpha(final int alpha) {
        
        boolean changed = false;
        if(this.alphaP != alpha) {
            changed = true;
        }
        
        super.setAlpha(alpha);

        if(changed) {
//            this.alphaProcessor = AlphaProcessor.getInstance();
            this.updateModifiers();
        }
    }

    @Override
    public void setScale(final float scaleX, final float scaleY) {
        //this.logUtil.putF(new StringMaker().append("scaleX: ").append(scaleX).append("scaleY: ").append(scaleY).toString(), this, "setScale");
    }

    @Override
    public void setMaxScale(final float maxScaleX, final float maxScaleY) {
    }
    
    private void updateModifiers() {
    }
    
    protected void paint(Graphics g) {

        g.drawRGB(pixelBuffer, 0, width, 0, 0, width, height, true);
    }
}
