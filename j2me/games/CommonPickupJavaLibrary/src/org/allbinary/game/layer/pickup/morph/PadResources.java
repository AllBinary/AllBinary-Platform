package org.allbinary.game.layer.pickup.morph;

import org.allbinary.animation.AnimationToTextureFactory;
import org.allbinary.game.layer.resources.BasicGameResources;

public class PadResources extends BasicGameResources
{
    private static final PadResources SINGLETON = new PadResources();

    public final String FROZEN = "/pad_frozen_100_by_100.png";
    
    public final String BLUE_TEXTURE = "/paddle_n.png";
    public final String RED_TEXTURE = "/paddle_nred.png";

    public final String BLUE_LARGE_ANIMATION = "Large Blue Paddle Animation";
    public final String RED_LARGE_ANIMATION = "Large Red Paddle Animation";

    public final String BLUE_SMALL_ANIMATION = "Small Blue Paddle Animation";
    public final String RED_SMALL_ANIMATION = "Small Red Paddle Animation";
    
    protected PadResources()
    {
        //final String SIZE_SIX = "_100_by_100.png";
        final String SIZE_SIX = "_100_by_100.png";
        //final String[] SIZE = { SMALL, MEDIUM, SIZE_FOUR, SIZE_FIVE, SIZE_SIX };
        final String[] SIZE = { SIZE_SIX, SIZE_SIX, SIZE_SIX, SIZE_SIX, SIZE_SIX };

        super.init("/pad", SIZE);
        
        AnimationToTextureFactory animationToTextureFactory = 
        		AnimationToTextureFactory.getInstance();

        animationToTextureFactory.add(this.BLUE_LARGE_ANIMATION, this.BLUE_TEXTURE);
        animationToTextureFactory.add(this.BLUE_SMALL_ANIMATION, this.BLUE_TEXTURE);
        animationToTextureFactory.add(this.RED_LARGE_ANIMATION, this.RED_TEXTURE);
        animationToTextureFactory.add(this.RED_SMALL_ANIMATION, this.RED_TEXTURE);
    }
        
    public static PadResources getInstance()
    {
        return SINGLETON;
    }
}
