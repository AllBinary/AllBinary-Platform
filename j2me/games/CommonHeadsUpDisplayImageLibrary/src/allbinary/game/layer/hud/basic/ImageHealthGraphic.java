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
package allbinary.game.layer.hud.basic;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.animation.AnimationInterface;
import org.allbinary.game.health.HealthInterface;
import org.allbinary.graphics.Anchor;
import org.allbinary.image.ImageCacheFactory;

public class ImageHealthGraphic extends HealthGraphic
{
	private Image[] image;
	
	public ImageHealthGraphic(AnimationInterface animationInterface,
			HealthInterface healthInterface, int location,
			int direction) throws Exception {

	   super(animationInterface, healthInterface, location, direction);
	   
	   image = new Image[this.healthInterface.getMaxHealth()];

	   ImageCacheFactory imageCacheFactory = ImageCacheFactory.getInstance();
	   
		for (int numOfHealth = 0; numOfHealth <= max; numOfHealth++) {
			image[numOfHealth] = imageCacheFactory.get(this, numOfHealth + 1 * 10, 10);
		}

		for (int numOfHealth = 0; numOfHealth <= max; numOfHealth++) {
			for(int index = 0; index < numOfHealth; index++)
			{
			   this.animationInterface.paint(image[numOfHealth].getGraphics(), numOfHealth * 10, 0);
			}
		}
	}
	
	public void paint(Graphics graphics) {
		graphics.drawImage(this.image[max], x, y, Anchor.TOP_LEFT);
	}
	
}
